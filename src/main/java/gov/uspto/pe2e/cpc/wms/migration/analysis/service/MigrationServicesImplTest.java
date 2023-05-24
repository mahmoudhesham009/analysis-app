package gov.uspto.pe2e.cpc.wms.migration.analysis.service;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import gov.uspto.pe2e.cpc.wms.migration.analysis.adapter.MigrationInputData;
import gov.uspto.pe2e.cpc.wms.migration.analysis.constant.Constant;

@Service
@Profile("FixBug")
public class MigrationServicesImplTest implements MigrationServices {

	@Autowired
	private Environment env;

	@Autowired
	Constant constant;
	//===========================================
	public String reverse(String str) {
		String[] resultArr= str.split("-");
		String result="";
		for (int i= resultArr.length-1;i>=0;i--) {
			if(i==0) {
				result+=resultArr[i];
				return result;
			}
			result+=resultArr[i]+"-";
		}
		return result;
	}
	public JSONObject  cancelAndDeleteOriginalProcessInst(String processId) throws IOException {
//		String alfrescoURL = env.getProperty("spring.alfresco.rootURI") + env.getProperty("spring.alfresco.apsURL");
		// initialization
		int statusCode=0;
		String message="";
		JSONObject resultJSON= new JSONObject();

		// call api
		String uri ="enterprise/process-instances/"+processId;
//		String pathParam=processId;
		CloseableHttpClient client = HttpClients.createDefault();
		
		try {
		CloseableHttpResponse response = callApiCore(uri, new JSONObject(), "DELETE");
	 	statusCode = response.getStatusLine().getStatusCode();		
	 		// if statusCode == 200 call func again 
		 	if(statusCode==200) {
		 		resultJSON=cancelAndDeleteOriginalProcessInst( processId);
		 		message="process instance has been deleted successfully";
		 		/* if process instance deleted successfully(entered 200 block) and then not found(returned from 404 block)
		 		in the last call*/
		 		if(resultJSON.optInt("statusCode")==404) {
		 			// recreate JSON result to be successful
		 			resultJSON.remove("statusCode");
		 			resultJSON.remove("message");
		 			resultJSON.put("statusCode",200);
		 			resultJSON.put("message","process instance has been deleted successfully");
		 			
		 		}
		 		return resultJSON;
			} 	
		 	// else return status code : 403 (no permission) / 404 (not found)
			else if (statusCode==403) {
				message= "User does not have permission to cancel or remove the process instance";
			}
			else if(statusCode==404) {
				message="The process instance does not exist";
			}
			else {
				message="An Error Occurred. Please try again";
			}
		 	// Catching Server Errors >> Code:-500 
		} catch(IOException ex) {
				statusCode=-500;
				message="An Error Occurred. Please try again";
//				LOGGER.error("Error When Call API {0} " + alfrescoURL + uri + "\n" + "With Status Code is: {1} "
//						+ statusCode + "And message is {2}" + ex.getMessage());
			
			}
		finally {
		 	client.close();
		 	resultJSON.put("statusCode", statusCode);
		 	resultJSON.put("message", message);
			return resultJSON;
		 	
		}
		
	} //======================================

	public static JSONObject objectToJSONObject(Object object) {
		Object json = null;
		JSONObject jsonObject = null;
		try {
			json = new JSONTokener(object.toString()).nextValue();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (json instanceof JSONObject) {
			jsonObject = (JSONObject) json;
		}
		return jsonObject;
	}

	@Override
	public JSONObject getDataFromApi(String endPointName, Object reqBody, String pathParam, String methodeType,
			boolean exceptionProcess,boolean singleProcess) throws Exception {

		JSONObject reqBodyConverter = new JSONObject();
		JSONObject resultJSONObject = new JSONObject();
		JSONObject jsonReqBody = new JSONObject();

		if (methodeType.equals("POST")) {

			if (reqBody instanceof MigrationInputData) {
				reqBodyConverter = MigrationInputDataToJSON((MigrationInputData) reqBody);
			}
			 else {
					reqBodyConverter = new JSONObject();
					reqBodyConverter.put("processBusinessKey", reqBody);		
					reqBodyConverter.put("processUUID", ((MigrationInputData) reqBody).getProcessUUID());


				}

			jsonReqBody = createReqBody(endPointName, reqBodyConverter, pathParam, exceptionProcess,singleProcess);

		}
		resultJSONObject = new JSONObject();
		resultJSONObject = callApi(endPointName, jsonReqBody, pathParam, methodeType);
		if(resultJSONObject.optInt("size")!=resultJSONObject.optInt("total")) {
			jsonReqBody.put("size", resultJSONObject.optInt("total"));
			resultJSONObject = callApi(endPointName, jsonReqBody, pathParam, methodeType);

		}

		return resultJSONObject;
	}

	private JSONObject MigrationInputDataToJSON(MigrationInputData reqBody) {
		JSONObject inputData = new JSONObject();

		inputData.put("fromDate", reqBody.getFromDate());
		inputData.put("toDate", reqBody.getToDate());
		inputData.put("processUUID", reqBody.getProcessUUID());
		inputData.put("singleProcess", reqBody.isSingleProcess());
		// inputData.put("ins", reqBody.getIns());

		return inputData;
	}

	@Override
	public JSONObject createReqBody(String endPointName, JSONObject bodyData, String pathParam,
			boolean exceptionProcess, boolean singleProcess) {
		JSONObject reqBody = null;
		if (endPointName.equals("enterprise/historic-process-instances/query") && exceptionProcess == false) {
			reqBody = new JSONObject();
			
			// reqBody.put("processInstanceId", bodyData.getString("ins"));
			reqBody.put("includeProcessVariables", true);
//			reqBody.put("size", 1000);
			if(singleProcess) {
				// put process business key here
				
			reqBody.put("processBusinessKey", bodyData.optString("processUUID"));
//			reqBody.put("processInstanceId", "1376927");
//			reqBody.put("processInstanceId", "1053538");
//			reqBody.put("processInstanceId", "1136630");
//			reqBody.put("processInstanceId", "1342852");
			}
			else {

				reqBody.put("startedAfter", bodyData.getString("fromDate"));
				reqBody.put("startedBefore", bodyData.getString("toDate"));
			}

		} else if (exceptionProcess) {
			reqBody = new JSONObject();
			reqBody.put("processInstanceId", bodyData.optString("processInstanseId"));
			reqBody.put("includeProcessVariables", true);
		}
		return reqBody;
	}

	private JSONObject callApi(String endPointName, JSONObject reqBody, String pathParam, String methodType)
			throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();

		String uri = pathParam.isEmpty() ? endPointName
				: endPointName.equals("enterprise/task-forms/") ? endPointName + pathParam
						: endPointName.equals("enterprise/tasks/") ? endPointName + pathParam + "/action/claim"
								: pathParam.isEmpty() ? endPointName : endPointName + pathParam;
		String alfrescoURL = env.getProperty("spring.alfresco.rootURI") + env.getProperty("spring.alfresco.apsURL");
		int statusCode = 0;

		StringEntity entity;
		String result = null;
		JSONObject resultJSONObject = null;
		CloseableHttpResponse response = null;
		try {
			response=callApiCore(uri ,reqBody ,methodType);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			statusCode = response.getStatusLine().getStatusCode();
			
//			if (methodType.equalsIgnoreCase("POST")) {
//				HttpPost httpPost = new HttpPost(alfrescoURL + uri);
//
//				httpPost.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
//				entity = new StringEntity(reqBody.toString(), "UTF-8");
//				httpPost.setEntity(entity);
//				httpPost.setHeader("Accept", "application/json");
//				httpPost.setHeader("Content-type", "application/json");
//
//				response = client.execute(httpPost);
//
//				result = EntityUtils.toString(response.getEntity(), "UTF-8");
//
//				statusCode = response.getStatusLine().getStatusCode();
//			} else if (methodType.equalsIgnoreCase("GET")) {
//				HttpGet httpGet = new HttpGet(alfrescoURL + uri);
//
//				httpGet.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
//				httpGet.setHeader("Accept", "application/json");
//
//				response = client.execute(httpGet);
//
//				result = EntityUtils.toString(response.getEntity(), "UTF-8");
//
//				statusCode = response.getStatusLine().getStatusCode();
//			} else if (methodType.equalsIgnoreCase("DELETE")) {
//				HttpDelete httpDelete = new HttpDelete(alfrescoURL + uri);
//				httpDelete.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
////				httpDelete.setHeader("Accept", "application/json");
//				response = client.execute(httpDelete);
//
//			}else {
//				client = HttpClients.createDefault();
//				HttpPut httpPut = new HttpPut(alfrescoURL + uri);
//
//				httpPut.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
//
//				httpPut.setHeader("Accept", "application/json");
//				httpPut.setHeader("Content-type", "application/json");
//				response = client.execute(httpPut);
//				result = EntityUtils.toString(response.getEntity(), "UTF-8");
//				statusCode = response.getStatusLine().getStatusCode();
//
//			}
			if (statusCode == 200) {
				if (!result.isEmpty()) {
					if (response.getEntity() instanceof JSONArray) {
						resultJSONObject = new JSONObject();
						resultJSONObject.put("variables", result);
					} else {
						resultJSONObject = new JSONObject(result);
					}

				} else {
					resultJSONObject = new JSONObject();
				}
			} else {
				throw new Exception();

			}
			client.close();
		} catch (IOException ex) {
		}
		return resultJSONObject;
	}
	public boolean checkOriginalProcessIfExist(String id) throws ClientProtocolException, IOException {
		CloseableHttpResponse response = callApiCore("enterprise/process-instances/"+id,new JSONObject(), "GET");
		int statusCode=response.getStatusLine().getStatusCode();
		if(statusCode==200) {
			return true;
		}
		return false;
	}
	private CloseableHttpResponse callApiCore(String uri ,JSONObject reqBody ,String methodType) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		String alfrescoURL = env.getProperty("spring.alfresco.rootURI") + env.getProperty("spring.alfresco.apsURL");
		int statusCode = 0;
		StringEntity entity;
		String result = null;
		JSONObject resultJSONObject = null;
		CloseableHttpResponse response = null;
			if (methodType.equalsIgnoreCase("POST")) {
				HttpPost httpPost = new HttpPost(alfrescoURL + uri);

				httpPost.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
				entity = new StringEntity(reqBody.toString(), "UTF-8");
				httpPost.setEntity(entity);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Content-type", "application/json");

				response = client.execute(httpPost);

//				result = EntityUtils.toString(response.getEntity(), "UTF-8");

//				statusCode = response.getStatusLine().getStatusCode();
			} else if (methodType.equalsIgnoreCase("GET")) {
				HttpGet httpGet = new HttpGet(alfrescoURL + uri);

				httpGet.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
				httpGet.setHeader("Accept", "application/json");

				response = client.execute(httpGet);

//				result = EntityUtils.toString(response.getEntity(), "UTF-8");

//				statusCode = response.getStatusLine().getStatusCode();
			} else if (methodType.equalsIgnoreCase("DELETE")) {
				HttpDelete httpDelete = new HttpDelete(alfrescoURL + uri);
				httpDelete.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());
//				httpDelete.setHeader("Accept", "application/json");
				response = client.execute(httpDelete);

			}else {
				client = HttpClients.createDefault();
				HttpPut httpPut = new HttpPut(alfrescoURL + uri);

				httpPut.addHeader("Authorization", "Basic " + getEncryptedBasicAuthorizationCreds());

				httpPut.setHeader("Accept", "application/json");
				httpPut.setHeader("Content-type", "application/json");
				response = client.execute(httpPut);
//				result = EntityUtils.toString(response.getEntity(), "UTF-8");
//				statusCode = response.getStatusLine().getStatusCode();

			}
		
			return response;
	}

	private String getEncryptedBasicAuthorizationCreds() {
		String creds = "";
		creds = env.getProperty("spring.alfresco.userName") + ":" + env.getProperty("spring.alfresco.password");
		Base64 base64 = new Base64();
		creds = new String(base64.encode(creds.getBytes()));
		return creds;
	}

}
