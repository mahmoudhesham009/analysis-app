package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "analysisProcessInfo")
public class AnalysisProcessInfo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private int jobId;
	private String processInstanceId;
	private String projectNumber;
	private String name;
	private LocalDateTime startedTime;
	
	@Override
	public String toString() {
		return "AnalysisProcessInfo [id="
				+id
				+", jobId="
				+ jobId
				+ ", processInstanceId="
				+processInstanceId
				+", projectNumber="
				+projectNumber
				+", name="+ name +"]";
	}

}
