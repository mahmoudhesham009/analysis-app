package gov.uspto.pe2e.cpc.wms.migration.analysis.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

	private String taskDef;
	private String taskName;
	private String deltaType;

	List<Field> formFieldList = new ArrayList<Field>();

}
