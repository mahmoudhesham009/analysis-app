package gov.uspto.pe2e.cpc.wms.migration.analysis.constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import gov.uspto.pe2e.cpc.wms.migration.analysis.adapter.MigrationInputData;


@Component
public class Validation {

	public int inputDataValidation(MigrationInputData inputData) {
		int statusCode = -10;
		try {
		if (!(inputData.getFromDate().isEmpty() && inputData.getToDate().isEmpty())) {
				Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputData.getFromDate());
				Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputData.getToDate());
//			Date fromDate = inputData.getFromDate();
//			Date toDate = inputData.getToDate();


			if (toDate.after(fromDate) || toDate.equals(fromDate)) {
				statusCode = 0;
			}
		}
		}catch(ParseException e) {
			statusCode = -10;
		}
		
		return statusCode;

	}
}
