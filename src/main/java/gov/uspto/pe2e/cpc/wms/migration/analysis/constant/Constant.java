package gov.uspto.pe2e.cpc.wms.migration.analysis.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

@Component
public class Constant {
	
	public static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss"; //2022-12-06T21:36:33.267+0000
	public static final String DATE_FORMAT="yyyy-MM-dd"; //2022-12-06
	public static final String TIME_START_INTERVAL="00:00:00";
	public static final String TIME_END_INTERVAL="23:59:59";

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

	public String getCurrentDate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);

	}

}
