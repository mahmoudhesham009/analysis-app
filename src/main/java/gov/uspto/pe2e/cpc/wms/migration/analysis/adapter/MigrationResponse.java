package gov.uspto.pe2e.cpc.wms.migration.analysis.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MigrationResponse {

	private int statusCode;

	private String errorDesc;
	
	private String responseBody;

}
