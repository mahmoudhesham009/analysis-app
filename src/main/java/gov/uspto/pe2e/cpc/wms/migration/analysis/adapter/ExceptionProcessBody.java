package gov.uspto.pe2e.cpc.wms.migration.analysis.adapter;

import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionProcessBody {

	private String processInstanceId;
	private String jobId;
	private String projectNum;

	@Lob
	private String analysisObject;

}
