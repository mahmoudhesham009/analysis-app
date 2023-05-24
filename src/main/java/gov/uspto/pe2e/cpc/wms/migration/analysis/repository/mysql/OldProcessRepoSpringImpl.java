package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("OldProcessRepoSpringImpl")
public interface OldProcessRepoSpringImpl extends JpaRepository<OldProcess, Integer> {
	
	@Query(value = "select * from OLDPROCESS where PROCESSINSTANCEID = ?1", nativeQuery = true)
	public Optional<OldProcess> findOldProcessByProcessInstanceId(String processInstanceId);
	
	@Query(value="select PROCESSINSTANCEID from OLDPROCESS where CREATEDDATE between ?1 and ?2" , nativeQuery=true)
	public List<String> findOldProcessesWithinInterval(String fromDate,String toDate);

}
