package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "migration_job")
public class MigrationJob implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private int id;
	
	@Column(name="process_name")
	private String processName;
	
	@Column(name="from_date")
	private String fromDate;
	
	@Column(name="to_date")
	private String toDate;

	@Lob
	@Column(name="analysis_object")
	private String analysisObject;
	
	@Lob
	private String analysisObjectBeforeSave;

	@Transient
	private JSONArray test;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modify_date")
	private Date modifyDate;

	@Column(name="status")
	private String status;

	@Column(name="single_process")
	private boolean singleProcess;
}
