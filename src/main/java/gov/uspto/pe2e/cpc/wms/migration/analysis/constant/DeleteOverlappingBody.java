package gov.uspto.pe2e.cpc.wms.migration.analysis.constant;

import java.util.List;

import gov.uspto.pe2e.cpc.wms.migration.analysis.adapter.MigrationInputData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteOverlappingBody {

	public List<String> processIDs;
	String fromDate;
	String toDate;
	
}
