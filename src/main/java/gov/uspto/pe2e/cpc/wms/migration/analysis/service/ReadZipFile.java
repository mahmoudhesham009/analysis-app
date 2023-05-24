package gov.uspto.pe2e.cpc.wms.migration.analysis.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.uspto.pe2e.cpc.wms.migration.analysis.constant.Constant;
import gov.uspto.pe2e.cpc.wms.migration.analysis.entity.Field;
import gov.uspto.pe2e.cpc.wms.migration.analysis.entity.Task;

@Service
public class ReadZipFile {

//	@Autowired
//	ReadXmlFile readXmlFile;

	public Map<String, Task> convertResponseToFile(HttpEntity entity, boolean isCurrent) throws Exception {
		Map<String, Task> finalMap = new HashMap<>();
		File zipFile = new File("ProcessInfo" + ".zip");
		Path source = Paths.get(zipFile.getPath());

		File unzipFile = new File("ProcessInfoExtracted");
		unzipFile.mkdir();
		Path target = Paths.get(unzipFile.getPath());

		InputStream inputStream = entity.getContent();
		int c;
		byte buf[] = new byte[16384];
		try (FileOutputStream fos = new FileOutputStream(zipFile)) {
			while ((c = inputStream.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			unZipFile(source, target);
			finalMap = listFilesForFolder(unzipFile, isCurrent, new HashMap<>(), new HashMap<>(), new HashMap<>(),
					new HashMap<>(),new ReadXmlFile());
			deleteFile(unzipFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			zipFile.delete();
		}
		return finalMap;
	}

	private void deleteFile(File index) {

		for (File fileEntry : index.listFiles()) {
			if (fileEntry.isDirectory()) {
				deleteFile(fileEntry);
			} else {
				fileEntry.delete();
			}
		}
	}

	private void unZipFile(Path source, Path target) throws IOException, InterruptedException {

		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source.toFile()))) {

			ZipEntry zipEntry = zis.getNextEntry();

			while (zipEntry != null) {

				boolean isDirectory = false;
				if (zipEntry.getName().endsWith(File.separator)) {
					isDirectory = true;
				}

				Path newPath = zipSlipProtect(zipEntry, target);

				if (isDirectory) {
					Files.createDirectories(newPath);
				} else {
					if (newPath.getParent() != null) {
						if (Files.notExists(newPath.getParent())) {
							Files.createDirectories(newPath.getParent());
						}
					}
					Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
				}

				zipEntry = zis.getNextEntry();

			}
			zis.closeEntry();
			zis.close();
		}

	}

	public static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir) throws IOException {

		Path targetDirResolved = targetDir.resolve(zipEntry.getName());

		Path normalizePath = targetDirResolved.normalize();
		if (!normalizePath.startsWith(targetDir)) {
			throw new IOException("Bad zip entry: " + zipEntry.getName());
		}

		return normalizePath;
	}

	public Map<String, Task> listFilesForFolder(File folder, boolean isCurrent, Map<String, Task> finallMap,
			Map<String, Task> temp, Map<String, Task> currentTaskMap, Map<String, Task> hisTaskMap,ReadXmlFile readXmlFile) throws Exception {
//		Map<String, Task> currentTaskMap = new HashMap<String, Task>();
//		Map<String, Task> hisTaskMap = new HashMap<>();
//		 Map<String, Task> temp = new HashMap<>();
// ===============================
		Map<String, Task> result = new HashMap<>();
// ===============================

//		ReadXmlFile readXmlFile = new ReadXmlFile();

		for (File fileEntry : folder.listFiles()) { //start for 
			if (fileEntry.isDirectory()) {
//				Map<String, Task> tempFinallMap = new HashMap<>();
//				tempFinallMap = finallMap;
				
				
				/*listFilesForFolder(fileEntry, isCurrent, finallMap, temp, currentTaskMap, hisTaskMap,readXmlFile);*/
				
				
				result=listFilesForFolder(fileEntry, isCurrent, finallMap, temp, currentTaskMap, hisTaskMap,readXmlFile);
			} // end isDirectory condition
			else {
//				System.out.println("-----------------------------------FileEntry: "+fileEntry);
				if (fileEntry.getName().contains(".xml")) {
					if (isCurrent) {
						// finallMap = new HashMap<>();
						// System.out.println("-------------------------" + fileEntry);

						currentTaskMap = readXmlFile.run(fileEntry);
						finallMap.putAll(currentTaskMap);
						fileEntry.delete();
					} else {
						// finallMap = new HashMap<>();
						hisTaskMap = readXmlFile.run(fileEntry);
						finallMap.putAll(hisTaskMap);
						fileEntry.delete();
					}

				} // end if file contains XML condition
				else {
					if (fileEntry.getParentFile().getName().equals("form-models")) {
//						readFile(fileEntry,
//								finallMap.get(fileEntry.getName().substring(0, fileEntry.getName().indexOf("-"))),
//								isCurrent);
//					} else if (fileEntry.getParentFile().getName().equals("form-models") && !isCurrent) {
//						readFile(fileEntry,
//								finallMap.get(fileEntry.getName().substring(0, fileEntry.getName().indexOf("-"))),
//								isCurrent);
//					}
						temp=readFile(fileEntry,
								finallMap.get(fileEntry.getName().substring(0, fileEntry.getName().indexOf("-"))),
								isCurrent);
						// ==========================
						for(Map.Entry<String, Task> pair : temp.entrySet()) {
							result.put(pair.getKey(),pair.getValue());
						}
						// ==========================
		
					}

				}// end if NOT contains XML condition 
			} // end else for isDirectory Condition

		}
//		return temp;
		return result;
	}

	private Map<String, Task> readFile(File fileName, Task task, boolean isCurrent) {
		Map<String, Task> finallMap = new HashMap<>();
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(fileName)) {
			Object obj = jsonParser.parse(reader);

			JSONObject jsonObject = Constant.objectToJSONObject(obj);

			finallMap = fillMap(task, jsonObject, isCurrent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finallMap;

	}

	private Map<String, Task> fillMap(Task task, JSONObject jsonObject, boolean isCurrent) {

		Map<String, Task> finallMap = new HashMap<>();
		task.setFormFieldList(getFormField(jsonObject));
//		if (isCurrent) {
//			finallMap.put(task.getTaskDef(), task);
//		} else {
//			finallMap.put(task.getTaskDef(), task);
//		}
		finallMap.put(task.getTaskDef(), task);
		return finallMap;
	}

	private List<Field> getFormField(JSONObject jsonObject) {
		Field field = new Field();
		List<Field> formFieldList = new ArrayList<Field>();
		JSONArray array = jsonObject.optJSONArray("fields");
		for (Object object : array) {
			JSONObject object2 = (JSONObject) object;
			for (Object object3 : object2.getJSONObject("fields").getJSONArray("1")) {
				JSONObject object4 = (JSONObject) object3;
				field = new Field();
				field.setId(object4.optString("id"));
				field.setName(object4.optString("name"));
				field.setValue(object4.optString("value"));
				field.setRequired(object4.optBoolean("required"));
				field.setType(object4.optString("type"));
				if (object4.optString("type").equals("radio-buttons")) {
					field.setOptions(object4.optJSONArray("options"));
				}
				if (object4.optString("type").equals("readonly")) {
					object4.put("type", "date");
				}
				formFieldList.add(field);
			}
		}
		return formFieldList;
	}

}
