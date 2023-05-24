package gov.uspto.pe2e.cpc.wms.migration.analysis.adapter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.uspto.pe2e.cpc.wms.migration.analysis.constant.Constant;
import gov.uspto.pe2e.cpc.wms.migration.analysis.constant.DeleteOverlappingBody;
import gov.uspto.pe2e.cpc.wms.migration.analysis.constant.Validation;
import gov.uspto.pe2e.cpc.wms.migration.analysis.entity.Field;
import gov.uspto.pe2e.cpc.wms.migration.analysis.entity.Task;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.AnalysisProcessInfo;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.AnalysisProcessInfoRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.CurrentModel;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.CurrentModelRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.ExceptionProcess;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.ExceptionProcessRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.MigrationJob;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.MigrationJobRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.OldProcess;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.OldProcessRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.OriginalProcessInfoRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.ProcessDeployment;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.ProcessDeploymentRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.ProcessMigrationInfo;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.ProcessMigrationInfoRepoSpringImpl;
import gov.uspto.pe2e.cpc.wms.migration.analysis.service.DataService;
import gov.uspto.pe2e.cpc.wms.migration.analysis.service.MigrationServices;
import gov.uspto.pe2e.cpc.wms.migration.analysis.service.ReadZipFile;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/analysisProcess")
public class AnalysisController {
	@Autowired
	private Environment env;

	@Autowired
	MigrationServices migrationServices;

	@Autowired
	Validation validation;

//	@Autowired
//	ReadZipFile readZipFile;

	@Autowired
	DataService dataService;

	@Autowired
	ProcessDeploymentRepoSpringImpl deploymentRepoSpringImpl;

	@Autowired
	MigrationJobRepoSpringImpl migrationJobRepoSpringImpl;

	@Autowired
	CurrentModelRepoSpringImpl currentModelRepoSpringImpl;

	@Autowired
	ExceptionProcessRepoSpringImpl exceptionProcessRepoSpringImpl;

	@Autowired
	AnalysisProcessInfoRepoSpringImpl analysisProcessInfoRepoSpringImpl;

	@Autowired
	OldProcessRepoSpringImpl oldProcessRepoSpringImpl;

	@Autowired
	ProcessMigrationInfoRepoSpringImpl processMigrationInfoRepoSpringImpl;

	@Autowired
	OriginalProcessInfoRepoSpringImpl originalProcessInfoRepoSpringImpl;

	@PostConstruct
	public void init() {
		getDeploymentForCurrentModel();
	}

	@PostMapping("checkOverlapping")
	public String checkOverlapping(@RequestBody MigrationInputData migrationInputData) throws ParseException {
		/*
		 * 2/10 - 11/10 -- input
		 * 
		 * DB 2/10 - 30/10 1/10 - 11/10
		 * 
		 * overlapping 2/10 - 11/10 ________________________________
		 * 
		 * 1/10 - 11/10 -- input [from_date is missing]
		 * 
		 * DB 5/10 - 11/10 2/10 - 30/10
		 * 
		 * overlapping 2/10-11/10 ________________________________
		 * 
		 * 2/10 - 30/10 -- input [to_date is missing]
		 * 
		 * DB 2/10 - 11/10 1/10 - 20/10
		 * 
		 * overlapping 2/10-20/10
		 * 
		 * 
		 * ____________________________________
		 * 
		 * 1/10 - 30/10 -- input [all is missing]
		 * 
		 * DB 15/10 - 25/10 5/10 - 20/10
		 * 
		 * 
		 * overlapping all
		 * 
		 */
		JSONObject responseBody = new JSONObject();
		Optional<MigrationJob> migrationJobFromDate = migrationJobRepoSpringImpl
				.getIntervalForFromDate(migrationInputData.getFromDate());
		Optional<MigrationJob> migrationJobToDate = migrationJobRepoSpringImpl
				.getIntervalForToDate(migrationInputData.getToDate());
		MigrationInputData migrationInputDate = new MigrationInputData();
		List<MigrationJob> migrationJobs = new ArrayList<>();
		if (migrationJobFromDate.isPresent() && migrationJobToDate.isPresent()) {
			// overlapping for the same interval input
			migrationInputDate.setFromDate(migrationInputData.getFromDate());
			migrationInputDate.setToDate(migrationInputData.getToDate());
		} else if (migrationJobFromDate.isPresent()) {
			// only fromDate is present
			// take to date of result query (nearest to given toDate)
			migrationInputDate.setFromDate(migrationInputData.getFromDate());
			migrationInputDate.setToDate(migrationJobFromDate.get().getToDate().toString());
		} else if (migrationJobToDate.isPresent()) {
			// only toDate is present
			// take from date of result query (nearest to given fromDate)
			migrationInputDate.setFromDate(migrationJobToDate.get().getFromDate().toString());
			migrationInputDate.setToDate(migrationInputData.getToDate());
		} else {
			migrationJobs = migrationJobRepoSpringImpl.getJobInsideInterval(migrationInputData.getFromDate(),
					migrationInputData.getToDate());
			if (migrationJobs.isEmpty()) {
				// no overlapping
				responseBody.put("statusCode", 404);
				responseBody.put("message", "No overlapping");
//			responseBody.put("overlappingInterval",new JSONObject( migrationInputDate));
				return responseBody.toString();
			} else {
				JSONObject resultObj = null;
				Set<String> allProcesses = new HashSet();
				for (MigrationJob migrationjob : migrationJobs) {
					MigrationInputData MigrationData = new MigrationInputData();
					MigrationData.setFromDate(migrationjob.getFromDate());
					MigrationData.setToDate(migrationjob.getToDate());
					String result = "";
					System.out.println(MigrationData.toString());
					result = checkOverlapping(MigrationData);
					resultObj = new JSONObject(result);
					if (resultObj.optInt("statusCode") == 200) {
						for (Object id : resultObj.optJSONArray("processInstanceIDs")) {
							allProcesses.add((String) id);
						}

					}
				}
				resultObj.put("processInstanceIDs", allProcesses);
				return resultObj.toString();
			}
		}

		// change pattern from '29-12-2022' to '2022-12-13 00:00:00'
		// 2022-05-08

		String fromDate = migrationServices.reverse(migrationInputDate.getFromDate()) + " "
				+ Constant.TIME_START_INTERVAL;
		String toDate = migrationServices.reverse(migrationInputDate.getToDate()) + " " + Constant.TIME_END_INTERVAL;
		// check original process inside overlapping interval
//		List<String>oldProcessesIDs=oldProcessRepoSpringImpl.findOldProcessesWithinInterval(fromDate, toDate);
		List<String> oldProcessesIDs = originalProcessInfoRepoSpringImpl.findOldProcessesWithinInterval(fromDate,
				toDate);
		if (oldProcessesIDs.isEmpty()) {
			responseBody.put("statusCode", 404);
			responseBody.put("message", "no migrated processes inside  this overlapping interval");
			responseBody.put("overlappingInterval", new JSONObject(migrationInputDate));
			return responseBody.toString();
		}
		responseBody.put("statusCode", 200);
		responseBody.put("message",
				"The timeframe for this Job overlaps with a previous Job. If you decide to continue, any processes that were cloned in the previous Job will be deleted, otherwise, please cancel and choose a different timeframe.");
		responseBody.put("processInstanceIDs", oldProcessesIDs);
		responseBody.put("overlappingInterval", new JSONObject(migrationInputDate));

		return responseBody.toString();

	}

	@PostMapping("confirmDeleteAllNewProcesses")
	public String confirmDeleteAllNewProcesses(@RequestBody List<String> processIDs) throws IOException {
		// Initializing response body
		JSONObject responseBody = new JSONObject();
		JSONObject result = new JSONObject();
		// get all cloned processes related to original ones in overlapping intervals
		// and delete each
		for (String originalProcessId : processIDs) {
			if (!migrationServices.checkOriginalProcessIfExist(originalProcessId)) {
				System.out.println("Not Exist: " + originalProcessId);
				// delete only cloned processes with status code!=200 (Failed to migrate)
				List<String> IDs = processMigrationInfoRepoSpringImpl
						.getClonedProcessIdByOriginalProcessIdAndStatus(originalProcessId);

				if (IDs.isEmpty()) {
					System.out.println("Empty list");
					continue;
				}
				for (String id : IDs) {
					result = migrationServices.cancelAndDeleteOriginalProcessInst(id);
					if (result.optInt("statusCode") == -500 || result.optInt("statusCode") == 403) {
						responseBody.put("statusCode", -500);
						responseBody.put("message", "An Error Occurred");
						return responseBody.toString();
					}
				}
				continue;
			}

			System.out.println("Exist: " + originalProcessId);
			List<String> cloneProcessIDs = processMigrationInfoRepoSpringImpl
					.getClonedProcessIdByOriginalProcessId(originalProcessId);
			if (cloneProcessIDs.isEmpty()) {
				continue;
			}

			for (String cloneProcesssId : cloneProcessIDs) {
				result = migrationServices.cancelAndDeleteOriginalProcessInst(cloneProcesssId);
				if (result.optInt("statusCode") == -500 || result.optInt("statusCode") == 403) {
					responseBody.put("statusCode", -500);
					responseBody.put("message", "An Error Occurred");
					return responseBody.toString();
				}
				System.out.println(cloneProcesssId);
			}
		}
		responseBody.put("statusCode", 200);
		responseBody.put("message", "All cloned proccesses of overlapping Interval have been deleted successfully");
		return responseBody.toString();

	}

	@PostMapping("/exception")
	public String getAnalysisExceptionByProcessInstanceIdOrByProjectum(@RequestBody ExceptionProcessBody processBody)
			throws ParseException, JsonProcessingException, NumberFormatException {
		String data = "";
		if (processBody.getProcessInstanceId() == null || processBody.getProcessInstanceId().isEmpty()) {
			data = analysisExceptionProcessByProjectNum(processBody);
			if (data == null) {
				return HttpStatus.BAD_REQUEST.toString();
			} else {
				return data;
			}

		} else if (processBody.getProjectNum() == null || processBody.getProjectNum().isEmpty()) {
			data = analysisExceptionProcessByProcessInstanceId(processBody);
			if (data == null) {
				return HttpStatus.BAD_REQUEST.toString();

			} else {
				return data;
			}
		}
		return HttpStatus.BAD_REQUEST.toString();
	}

	public String analysisExceptionProcessByProcessInstanceId(@RequestBody ExceptionProcessBody processBody)
			throws ParseException, JsonProcessingException, NumberFormatException {
		LocalDate fromDate = null;
		LocalDate toDate = null;
		LocalDate started = null;
		String processDeploymentId = null;
		JSONArray analysisObject = null;
		JSONArray array = null;
		JSONObject resultJSONObject;
		String processInstanceId = "";
		String projectNum = "";
		JSONObject varJsonObj;
		JSONObject jsonObject;
		String jsonStr = "";
		ObjectMapper obj = new ObjectMapper();
		JSONObject resultJO = new JSONObject();
		try {
			resultJSONObject = migrationServices.getDataFromApi("enterprise/historic-process-instances/query",
					processBody.getProcessInstanceId(), "", "POST", true, false);

			if (!resultJSONObject.isEmpty()) {
				started = convertToDate(resultJSONObject.optJSONArray("data").getJSONObject(0).optString("started")
						.substring(0, resultJSONObject.optJSONArray("data").getJSONObject(0).optString("started")
								.indexOf("T")));
				processDeploymentId = resultJSONObject.optJSONArray("data").getJSONObject(0)
						.getString("processDefinitionDeploymentId");
				processInstanceId = resultJSONObject.optJSONArray("data").getJSONObject(0).getString("id");

				JSONArray variables = resultJSONObject.optJSONArray("data").getJSONObject(0).getJSONArray("variables");
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < variables.length(); i++) {
					list.add(variables.getJSONObject(i).optString("name"));
				}
				JSONObject varJSONObject;
				if (list.contains("PROPOSAL_ALIAS")) {
					for (Object var : variables) {
						varJSONObject = Constant.objectToJSONObject(var);
						if (varJSONObject.optString("name").equals("PROPOSAL_ALIAS")) {
							projectNum = varJSONObject.optString("value");
						}
					}
				} else {
					JSONObject resultJS = migrationServices.getDataFromApi("enterprise/process-instances/", null,
							processInstanceId, "GET", false, false);
					variables = resultJS.getJSONArray("variables");
					for (Object var : variables) {
						varJSONObject = Constant.objectToJSONObject(var);
						if (varJSONObject.optString("name").equals("PROPOSAL_ALIAS")) {
							projectNum = varJSONObject.optString("value");
						}
					}
				}

				Optional<ExceptionProcess> optionalExceptionProcess = exceptionProcessRepoSpringImpl
						.findByProcessInstanceIdAndJobId(processBody.getProcessInstanceId(), processBody.getJobId());

				if (optionalExceptionProcess.isPresent()) {
					array = new JSONArray(optionalExceptionProcess.get().getAnalysisObject());
					// ==========================================
//					jsonStr= obj.writeValueAsString(analysisProcessInfoRepoSpringImpl.findByProcessInstanceIdAndJobId(processInstanceId,Integer.valueOf(processBody.getJobId())).get());
					JSONObject AnalysisProcessAsJson = new JSONObject(analysisProcessInfoRepoSpringImpl
							.findByProcessInstanceIdAndJobId(processInstanceId, Integer.valueOf(processBody.getJobId()))
							.get());
					// ==========================================
					resultJO.put("deployments", array);
					resultJO.put("processInfo", AnalysisProcessAsJson);
					return resultJO.toString();
				}

				Optional<ProcessDeployment> deployments = deploymentRepoSpringImpl
						.findByDeploymentId(processDeploymentId);

				if (deployments.isPresent()) {
					Optional<MigrationJob> jobList = migrationJobRepoSpringImpl
							.findById(Integer.valueOf(processBody.getJobId()));

					if (jobList.isPresent()) {
						fromDate = convertToDate(jobList.get().getFromDate());
						toDate = convertToDate(jobList.get().getToDate());
//						fromDate =jobList.get().getFromDate();
//						toDate = jobList.get().getToDate();
					}
					if (fromDate != null && toDate != null) {

						if ((started.equals(fromDate) || started.isAfter(fromDate))
								&& (started.equals(toDate) || started.isBefore(toDate))) {
							analysisObject = new JSONArray(jobList.get().getAnalysisObjectBeforeSave().toString());
							for (Object object : analysisObject) {
								jsonObject = Constant.objectToJSONObject(object);

								if (processDeploymentId.equals(jsonObject.optString("DeploymentId"))) {
									array = new JSONArray();
									array.put(jsonObject);
									saveOnExceptionTable(processBody, array, processInstanceId, projectNum);
								}
							}

						} else {
							return HttpStatus.BAD_REQUEST.toString() + " " + "The Process is Not in the date range";
						}
					}
				} else {
					return HttpStatus.BAD_REQUEST.toString();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ==========================================
//		jsonStr= obj.writeValueAsString(analysisProcessInfoRepoSpringImpl.findByProcessInstanceIdAndJobId(processInstanceId,Integer.valueOf(processBody.getJobId())).get());
		JSONObject AnalysisProcessAsJson = new JSONObject(analysisProcessInfoRepoSpringImpl
				.findByProcessInstanceIdAndJobId(processInstanceId, Integer.valueOf(processBody.getJobId())).get());

		// ==========================================
		resultJO.put("deployments", array);
		resultJO.put("processInfo", AnalysisProcessAsJson);
		return resultJO.toString();
	}

	public String analysisExceptionProcessByProjectNum(@RequestBody ExceptionProcessBody processBody)
			throws ParseException, JsonProcessingException, NumberFormatException {
		MigrationInputData data = new MigrationInputData();
		String processDeploymentId = null;
		String processInstanceId = "";
		String projectNum = "";
		JSONArray array = null;
		JSONObject resultJSONObject;
		JSONArray variables;
		JSONObject varJsonObj;
		String name;
		JSONObject resultJO = new JSONObject();
		String jsonStr = "";
		ObjectMapper obj = new ObjectMapper();
		JSONObject analysisJsonObject;
		try {
			Optional<MigrationJob> jobList = migrationJobRepoSpringImpl
					.findById(Integer.valueOf(processBody.getJobId()));

			if (jobList.isPresent()) {
				data.setFromDate(jobList.get().getFromDate().toString());
				data.setToDate(jobList.get().getToDate().toString());

				resultJSONObject = migrationServices.getDataFromApi("enterprise/historic-process-instances/query", data,
						"", "POST", false, false);

				if (!resultJSONObject.isEmpty()) {

					JSONArray dataArray = resultJSONObject.optJSONArray("data");
					for (Object object : dataArray) {
						JSONObject jsonObject = Constant.objectToJSONObject(object);
						processDeploymentId = jsonObject.optString("processDefinitionDeploymentId");
						processInstanceId = jsonObject.optString("id");
						variables = jsonObject.getJSONArray("variables");
						ArrayList<String> list = new ArrayList<String>();
						for (int i = 0; i < variables.length(); i++) {
							list.add(variables.getJSONObject(i).optString("name"));
						}
						JSONObject varJSONObject;
						if (!list.contains("PROPOSAL_ALIAS")) { // project_number
							JSONObject resultJS = migrationServices.getDataFromApi("enterprise/process-instances/",
									null, processInstanceId, "GET", false, false);
							JSONArray variables2 = resultJS.getJSONArray("variables");
							for (Object var : variables2) {
								varJSONObject = Constant.objectToJSONObject(var);
								if (varJSONObject.optString("name").equals("PROPOSAL_ALIAS")) {
									variables.put(varJSONObject);
								}
							}
						}

						for (Object var : variables) {
							varJsonObj = Constant.objectToJSONObject(var);
							name = varJsonObj.optString("name");
							if (name.equals("PROPOSAL_ALIAS")) {
								projectNum = varJsonObj.optString("value");
								if (processBody.getProjectNum().equals(varJsonObj.optString("value"))) {
									Optional<ExceptionProcess> optionalExceptionProcess = exceptionProcessRepoSpringImpl
											.findByProjectNumAndJobId(processBody.getProjectNum(),
													processBody.getJobId());

									if (optionalExceptionProcess.isPresent()) {
										array = new JSONArray(optionalExceptionProcess.get().getAnalysisObject());
										// =======================================
										// jsonStr=
										// obj.writeValueAsString(analysisProcessInfoRepoSpringImpl.findByProjectNumberAndJobId(projectNum,Integer.valueOf(processBody.getJobId())).get());
										JSONObject AnalysisProcessAsJson = new JSONObject(
												analysisProcessInfoRepoSpringImpl.findByProjectNumberAndJobId(
														projectNum, Integer.valueOf(processBody.getJobId())).get());
										// ========================================

										resultJO.put("deployments", array);
										resultJO.put("processInfo", AnalysisProcessAsJson);
										return resultJO.toString();
									}

									Optional<ProcessDeployment> deployments = deploymentRepoSpringImpl
											.findByDeploymentId(processDeploymentId);

									if (deployments.isPresent()) {
										JSONArray analysisObject = new JSONArray(
												jobList.get().getAnalysisObjectBeforeSave().toString());
										for (Object analysis : analysisObject) {
											analysisJsonObject = Constant.objectToJSONObject(analysis);

											if (processDeploymentId
													.equals(analysisJsonObject.optString("DeploymentId"))) {
												array = new JSONArray();
												array.put(analysisJsonObject);
												saveOnExceptionTable(processBody, array, processInstanceId, projectNum);
											}

										}
									}

								}
							}
						}
					}
					// =======================================
//					jsonStr= obj.writeValueAsString(analysisProcessInfoRepoSpringImpl.findByProjectNumberAndJobId(projectNum,Integer.valueOf(processBody.getJobId())).get());
					JSONObject AnalysisProcessAsJson = new JSONObject(analysisProcessInfoRepoSpringImpl
							.findByProjectNumberAndJobId(projectNum, Integer.valueOf(processBody.getJobId())).get());
					// ========================================
					resultJO.put("deployments", array);
					resultJO.put("processInfo", AnalysisProcessAsJson);
					return resultJO.toString();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void saveOnExceptionTable(ExceptionProcessBody processBody, JSONArray array, String processInstanceId,
			String projectNum) {

		ExceptionProcess exceptionProcess = new ExceptionProcess();
		exceptionProcess.setAnalysisObject(array.toString());
		exceptionProcess.setJobId(processBody.getJobId());
		if (processBody.getProcessInstanceId().isEmpty()) {
			exceptionProcess.setProcessInstanceId(processInstanceId);

		} else {
			exceptionProcess.setProcessInstanceId(processBody.getProcessInstanceId());
		}

		if (processBody.getProjectNum().isEmpty()) {
			exceptionProcess.setProjectNum(projectNum);

		} else {
			exceptionProcess.setProjectNum(processBody.getProjectNum());
		}

		exceptionProcessRepoSpringImpl.save(exceptionProcess);

	}

	private Date convertToDateTime(String string) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(string);

	}

	private LocalDate convertToDate(String string) throws ParseException {
//		return new SimpleDateFormat("yyyy-mm-dd").parse(string);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT);
		LocalDate date = LocalDate.parse(string, formatter);
		return date;

	}

	private void saveProcessinfoForjob(int jobId, String processInstanceId, String name, JSONArray variables,
			String startedTime) throws Exception {
		JSONObject varJSONObject;
		String projectNumber = "";
		AnalysisProcessInfo analysisProcessInfo = new AnalysisProcessInfo();
//		ArrayList<String> list = new ArrayList<String>();
//		for( int i =0 ; i< variables.length();i++) {
//			list.add(variables.getJSONObject(i).optString("name"));
//		}
//		if(list.contains("PROPOSAL_ALIAS")) {
		for (Object var : variables) {
			varJSONObject = Constant.objectToJSONObject(var);
			if (varJSONObject.optString("name").equals("PROPOSAL_ALIAS")) {
				projectNumber = varJSONObject.optString("value");
			}
		}
//		}
//		else {
//			JSONObject resultJSONObject =migrationServices.getDataFromApi("enterprise/process-instances/",null,processInstanceId,"GET",false);
//			variables = resultJSONObject.getJSONArray("variables");
//			for (Object var : variables) {
//				varJSONObject = Constant.objectToJSONObject(var);
//				if (varJSONObject.optString("name").equals("PROPOSAL_ALIAS")) {
//					projectNumber = varJSONObject.optString("value");
//				}
//		}
//		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_TIME_FORMAT);
		String startedTimeReplaceT = startedTime.replace("T", " ");
		String startedTimesub = startedTimeReplaceT.substring(0, startedTime.indexOf("."));
//		System.out.print("++++++++++++++++++++++++++++" +  startedTimesub );
		LocalDateTime dateTime = LocalDateTime.parse(startedTimesub, formatter);

		analysisProcessInfo.setJobId(jobId);
		analysisProcessInfo.setProcessInstanceId(processInstanceId);
		analysisProcessInfo.setName(name);
		analysisProcessInfo.setProjectNumber(projectNumber);

		analysisProcessInfo.setStartedTime(dateTime);

		analysisProcessInfoRepoSpringImpl.saveAndFlush(analysisProcessInfo);
	}

	@PostMapping("/singleInstance")
	public String getJobForSingleInstance(@RequestBody MigrationInputData migrationInputData) {
		JSONObject resultJSONObject;
		MigrationInputData inputData = new MigrationInputData();
		JSONObject JO = new JSONObject();
		try {
			resultJSONObject = migrationServices.getDataFromApi("enterprise/historic-process-instances/query",
					migrationInputData, "", "POST", false, true);

			if (!resultJSONObject.isEmpty()) {
				if (resultJSONObject.optInt("size") > 1) {
					resultJSONObject = resultJSONObject.getJSONArray("data")
							.getJSONObject(resultJSONObject.getJSONArray("data").length() - 1);

				} else if (resultJSONObject.optInt("size") == 0) {
					migrationInputData.setProcessUUID(migrationInputData.getProcessUUID().toUpperCase());

					resultJSONObject = migrationServices.getDataFromApi("enterprise/historic-process-instances/query",
							migrationInputData, "", "POST", false, true);

					if (!resultJSONObject.isEmpty()) {
						if (resultJSONObject.optInt("size") > 1) {
							resultJSONObject = resultJSONObject.getJSONArray("data")
									.getJSONObject(resultJSONObject.getJSONArray("data").length() - 1);
						} else if (resultJSONObject.optInt("size") == 0) {
							JO.put("message", "Process instance not found");
							JO.put("statusCode", 404);
							return JO.toString();
//							return HttpStatus.NOT_FOUND.toString();
						}
					}

				}

				inputData.setFromDate(resultJSONObject.optString("started"));
				inputData.setToDate("");
				inputData.setProcessUUID(migrationInputData.getProcessUUID());
				inputData.setSingleProcess(migrationInputData.isSingleProcess());
				return analysis(inputData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	@PostMapping("/analysis")
	public String analysis(@RequestBody MigrationInputData inputData) {
		Map<String, Task> currentTaskMap = new HashMap<String, Task>();
		Map<String, Task> hisTaskMap = new HashMap<>();
		JSONArray array = new JSONArray();
		int jobId = 0;
		JSONObject jsonObject;
		Optional<CurrentModel> currentDeployment;
		Optional<ProcessDeployment> deployments;
		JSONObject deploymentData;
		JSONObject deploymentDataIteratorOnArray;
		String ProcessInstanceName = "";

		boolean isExist = false;
		ArrayList<String> list = new ArrayList<>();
		try {
			JSONObject resultJSONObject;
//			if (!singleProcess) {
//				resultJSONObject = migrationServices.getDataFromApi("enterprise/historic-process-instances/query",
//						inputData, "", "POST", false, false);
//			} else {
//				ProcessInstanceName = resultJSONObject.getString("name");
//			}

			resultJSONObject = migrationServices.getDataFromApi("enterprise/historic-process-instances/query",
					inputData, "", "POST", false, inputData.isSingleProcess());

			if (resultJSONObject.optInt("total") == 0) {
				return "Date range is empty";
			}

			if (!resultJSONObject.isEmpty()) {
				jobId = startAnalysis(inputData);
				JSONArray dataArray = resultJSONObject.getJSONArray("data");
				for (Object object : dataArray) {
					list = new ArrayList<>();
					jsonObject = Constant.objectToJSONObject(object);
					currentTaskMap = new HashMap<String, Task>();
					currentDeployment = currentModelRepoSpringImpl
							.findByProcessName(jsonObject.optString("processDefinitionName"));

					if (!currentDeployment.isPresent()) {
						init();

						currentDeployment = currentModelRepoSpringImpl
								.findByProcessName(jsonObject.optString("processDefinitionName"));
					}

					deployments = deploymentIdIsExist(currentDeployment.get().getCurrentDeploymentId());

					if (deployments.isPresent()) {
						currentTaskMap = getDeploymentData(deployments);
					}
					if (currentTaskMap.isEmpty()) {
						currentTaskMap = getZIPFileForModel(currentDeployment.get().getCurrentDeploymentId(), true);
						saveDeploymentObject(currentDeployment.get().getCurrentDeploymentId(), currentTaskMap);
					}
					// *****
					JSONArray variables = jsonObject.optJSONArray("variables");
					for (int i = 0; i < variables.length(); i++) {
						list.add(variables.getJSONObject(i).optString("name"));
					}
					if (!list.contains("PROPOSAL_ALIAS")) {
						JSONObject result = migrationServices.getDataFromApi("enterprise/process-instances/", null,
								jsonObject.optString("id"), "GET", false, false);
						JSONArray variables2 = result.getJSONArray("variables");
						JSONObject tmpJO = new JSONObject();
						for (Object var : variables2) {
							JSONObject varJSONObject = Constant.objectToJSONObject(var);
							if (varJSONObject.optString("name").equals("PROPOSAL_ALIAS")) {
								variables.put(varJSONObject);
							}
						}

					}
					// ******
					saveProcessinfoForjob(jobId, jsonObject.optString("id"), jsonObject.optString("name"), variables,
							jsonObject.optString("started"));

					if (!jsonObject.optString("processDefinitionDeploymentId")
							.equals(currentDeployment.get().getCurrentDeploymentId())) {
						deployments = deploymentIdIsExist(jsonObject.optString("processDefinitionDeploymentId"));

						if (deployments.isPresent()) {
							for (Object object2 : array) {
								deploymentData = Constant.objectToJSONObject(object2);
								if (deploymentData.optString("DeploymentId")
										.equals(jsonObject.optString("processDefinitionDeploymentId"))) {
									isExist = true;

								}
							}
							if (!isExist) {
								hisTaskMap = getDeploymentData(deployments);
							}
						}
						if (!isExist) {
							if (hisTaskMap.isEmpty()) {
								hisTaskMap = getZIPFileForModel(jsonObject.optString("processDefinitionDeploymentId"),
										false);
								saveDeploymentObject(jsonObject.optString("processDefinitionDeploymentId"), hisTaskMap);
							}
						}
						isExist = false;
					}

					if (currentTaskMap.size() > 0 && hisTaskMap.size() > 0) {
						JSONObject diffAsJSON = new JSONObject(dataService.compare(currentTaskMap, hisTaskMap));
						JSONArray diffJsonArray = new JSONArray();
						diffJsonArray.put(diffAsJSON);
						JSONObject jsonObject2 = new JSONObject();
						jsonObject2.put("processInstanceName", ProcessInstanceName);
						jsonObject2.put("DeploymentId", jsonObject.optString("processDefinitionDeploymentId"));
						jsonObject2.put("processVersion", jsonObject.optString("processDefinitionVersion"));
						jsonObject2.put("processName", jsonObject.optString("processDefinitionKey"));
						jsonObject2.put("data", diffJsonArray);
						array.put(jsonObject2);
						hisTaskMap = new HashMap<>();
					}

				}

				Iterator<Object> iterator = array.iterator();
				while (iterator.hasNext()) {
					deploymentDataIteratorOnArray = Constant.objectToJSONObject(iterator.next());
					if (deploymentDataIteratorOnArray.optJSONArray("data").optJSONObject(0).isEmpty()) {
						iterator.remove();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jobId != 0)
			endAnalysis(jobId, array.toString());
		JSONObject res = new JSONObject();
		res.put("jobId", jobId);
		res.put("data", array);
		return res.toString();
	}

	private Optional<ProcessDeployment> deploymentIdIsExist(String deploymentId) {
//		System.out.println("-----------------------------------"+deploymentId);
		Optional<ProcessDeployment> deployments = deploymentRepoSpringImpl.findByDeploymentId(deploymentId);
		if (deployments.isPresent()) {
			return deployments;
		}
		return Optional.empty();
	}

	private Map<String, Task> getDeploymentData(Optional<ProcessDeployment> deployments) {
		Map<String, Task> map = new HashMap<>();
		Task task = null;
		Field field = null;
		JSONObject dataJsonObject;

		JSONObject jsonObject = new JSONObject(deployments.get().getObject());
		JSONArray dataArray = jsonObject.getJSONArray("data");
		List<Field> fields = null;
		for (Object object : dataArray) {
			fields = new ArrayList<>();
			dataJsonObject = Constant.objectToJSONObject(object);
			task = new Task();
			task.setTaskDef(dataJsonObject.optString("taskDef"));
			task.setTaskName(dataJsonObject.optString("taskName"));
			task.setDeltaType(dataJsonObject.optString("deltaType"));
			for (Object object2 : dataJsonObject.optJSONArray("formFieldList")) {
				JSONObject fieldObject = Constant.objectToJSONObject(object2);
				field = new Field();
				field.setId(fieldObject.optString("id"));
				field.setName(fieldObject.optString("name"));
				field.setRequired(fieldObject.optBoolean("isRequired"));
				field.setType(fieldObject.optString("type"));
				field.setValue(fieldObject.optString("value"));
				field.setOptions(fieldObject.optJSONArray("options"));
				fields.add(field);
			}
			task.setFormFieldList(fields);
			map.put(dataJsonObject.optString("taskDef"), task);
		}

		return map;

	}

	private void saveDeploymentObject(String deploymentId, Map<String, Task> map) {
		ProcessDeployment processDeployment = new ProcessDeployment();
		JSONObject bigJsonObject = new JSONObject();
		JSONArray data = new JSONArray();
		JSONObject taskObject = null;
		JSONArray fieldArray;

		JSONObject fieldObject = null;

		for (Task task : map.values()) {
			fieldArray = new JSONArray();
			taskObject = new JSONObject();
			taskObject.put("taskDef", task.getTaskDef());
			taskObject.put("taskName", task.getTaskName());
			taskObject.put("deltaType", task.getDeltaType());

			for (Field field : task.getFormFieldList()) {

				fieldObject = new JSONObject();
				fieldObject.put("id", field.getId());
				fieldObject.put("name", field.getName());
				fieldObject.put("value", field.getValue());
				fieldObject.put("isRequired", field.isRequired());
				fieldObject.put("type", field.getType());
				fieldObject.put("options", field.getOptions());
				fieldArray.put(fieldObject);
			}
			taskObject.put("formFieldList", fieldArray);
			data.put(taskObject);
		}
		bigJsonObject.put("data", data);
		processDeployment.setDeploymentId(deploymentId);

		processDeployment.setObject(bigJsonObject.toString());

		deploymentRepoSpringImpl.save(processDeployment);
	}

	private int startAnalysis(MigrationInputData inputData) throws ParseException {
		MigrationJob migrationJob = new MigrationJob();
		migrationJob.setStatus("startAnalysis");
		migrationJob.setCreatedDate(new Date());

		if (inputData.isSingleProcess()) {
			migrationJob.setProcessName(inputData.getProcessUUID());
		} else {
			migrationJob.setFromDate(inputData.getFromDate());
			migrationJob.setToDate(inputData.getToDate());
		}
		migrationJob.setSingleProcess(inputData.isSingleProcess());

		return migrationJobRepoSpringImpl.save(migrationJob).getId();

	}

	private void endAnalysis(int jobId, String json) {
		Optional<MigrationJob> jobList = migrationJobRepoSpringImpl.findById(jobId);

		if (jobList.isPresent()) {
			MigrationJob job = jobList.get();
			job.setAnalysisObject(json);
			job.setAnalysisObjectBeforeSave(json);
			job.setModifyDate(new Date());
			job.setProcessName(jobList.get().getProcessName());
			job.setSingleProcess(jobList.get().isSingleProcess());
			job.setStatus("analysisFinished");
			migrationJobRepoSpringImpl.saveAndFlush(job);
		}

	}

	public Map<String, Task> getZIPFileForModel(String DeploymentId, boolean isCurrent) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		Map<String, Task> finalMap = new HashMap<>();
		Map<String, Task> currentTaskMap = new HashMap<String, Task>();
		Map<String, Task> hisTaskMap = new HashMap<>();
		ReadZipFile readZipFile = new ReadZipFile();
		String uri = "enterprise/export-app-deployment/" + DeploymentId;
		String alfrescoURL = env.getProperty("spring.alfresco.rootURI") + env.getProperty("spring.alfresco.apsURL");
		int statusCode = 0;

		HttpEntity entity;

		CloseableHttpResponse response = null;
		try {

			HttpGet httpGet = new HttpGet(alfrescoURL + uri);
			httpGet.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
			httpGet.setHeader("Accept", "application/zip");
			response = client.execute(httpGet);
			statusCode = response.getStatusLine().getStatusCode();
			entity = response.getEntity();
			finalMap = readZipFile.convertResponseToFile(entity, isCurrent);
//			System.out.println("------------------------FinalMap: "+finalMap);
			if (isCurrent) {
				currentTaskMap = finalMap;
			} else {
				hisTaskMap = finalMap;
			}

			client.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return finalMap;
	}

	public void getDeploymentForCurrentModel() {
		String currentDeploymentId = null;
		JSONObject jsonObject;

		try {
			JSONObject resultJSONObject = migrationServices.getDataFromApi("enterprise/runtime-app-definitions", "", "",
					"GET", false, false);
			for (Object object : resultJSONObject.getJSONArray("data")) {
				jsonObject = Constant.objectToJSONObject(object);
				currentDeploymentId = jsonObject.optString("deploymentId");
				if (currentDeploymentId != null && !(currentDeploymentId.isEmpty())) {
					saveCurrentDeploymentId(jsonObject.optString("deploymentId"), jsonObject.optString("name"));
				}
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void saveCurrentDeploymentId(String deploymentId, String processName) {
		Optional<CurrentModel> optional = currentModelRepoSpringImpl.findByProcessName(processName);
		if (optional.isPresent()) {
			if (!optional.get().getCurrentDeploymentId().equals(deploymentId)) {
				optional.get().setCurrentDeploymentId(deploymentId);
				currentModelRepoSpringImpl.save(optional.get());
			}
		} else {
			CurrentModel currentModel = new CurrentModel();
			currentModel.setCurrentDeploymentId(deploymentId);
			currentModel.setProcessName(processName);
			currentModelRepoSpringImpl.save(currentModel);
		}

	}

	private String getEncryptedBasicAuthorizationCreds() {
		String creds = "";
		creds = env.getProperty("spring.alfresco.userName") + ":" + env.getProperty("spring.alfresco.password");
		Base64 base64 = new Base64();
		creds = new String(base64.encode(creds.getBytes()));
		return creds;
	}
}
