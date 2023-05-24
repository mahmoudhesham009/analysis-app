package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingFieldsRepoSpringImpl extends JpaRepository<MissingFields, Integer>{
	
	List<MissingFields> findByTaskId(String taskId);
	

}
