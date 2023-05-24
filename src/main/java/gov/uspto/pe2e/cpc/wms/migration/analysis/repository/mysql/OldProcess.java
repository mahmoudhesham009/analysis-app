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
public class OldProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String name;

	private String processInstanceId;
	private String processDefinitionName;
	private String processDefinitionKey;
	private String businessKey;

	private String createdDate;
	
	private String projectNum;
}
