package gov.uspto.pe2e.cpc.wms.migration.analysis.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

public interface MigrationServices {

	public JSONObject createReqBody(String endPointName, JSONObject bodyData, String pathParam,
			boolean exceptionProcess,boolean singleProcess);

	JSONObject getDataFromApi(String endPointName, Object reqBody, String pathParam, String methodeType,
			boolean exceptionProcess,boolean singleProcess) throws Exception;
	
	public JSONObject  cancelAndDeleteOriginalProcessInst(String processId) throws IOException;
	public boolean checkOriginalProcessIfExist(String id) throws ClientProtocolException, IOException;
	
	public String reverse(String string);
	
	

}
