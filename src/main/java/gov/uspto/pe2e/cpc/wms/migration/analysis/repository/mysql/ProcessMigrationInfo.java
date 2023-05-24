package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ProcessMigrationInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String originalProcessId;
	
	private String cloneProcessId;

	private String name;
	private String defKey;

	private int status;
	private String errorMSG;

	private String createdDate;
	
	private int jobId;
	
	private String lastUpdateDate;
	
	private String projectNum;

}
