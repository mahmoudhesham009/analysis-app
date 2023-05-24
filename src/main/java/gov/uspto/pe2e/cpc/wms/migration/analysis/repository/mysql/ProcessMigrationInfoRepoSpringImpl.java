package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ProcessMigrationInfoRepoSpringImpl extends JpaRepository<ProcessMigrationInfo, Integer> {
	//@Query(value = "select * from processmigrationinfo where status != 200 and jobId = ?1", nativeQuery = true)
	//public List<ProcessMigrationInfo> findFailureDataByJobId(int jobId);
	
	public List<ProcessMigrationInfo> findByJobId(int jobId);

//	@Query(value = "select count(1) as total,  count(IF(status = 200,1,NULL)) AS success , count(IF(status != 200,1,NULL)) as failure from processmigrationinfo where jobId = ?1", nativeQuery = true)
	@Query(value = "select count(1) as total,  count(case when status = 200 then 1 end) AS success, count(case when status != 200 then 1 end) as failure from processmigrationinfo where jobId = ?1", nativeQuery = true)
	public List<Object[]> getReportData(int jobId);

	@Query(value = "select * from processmigrationinfo where cloneProcessId= ?1", nativeQuery = true)
	public Optional<ProcessMigrationInfo>  findByCloneProcessId(String cloneProcessId);
	
	@Query(value = "select * from processmigrationinfo where ORIGINALPROCESSID= ?1", nativeQuery = true)
	public Optional<ProcessMigrationInfo>  findByOriginalProcessId(String originalProcessId);
	
	@Query(value = "select cloneProcessId from processmigrationinfo where ORIGINALPROCESSID= ?1", nativeQuery = true)
	public List<String>  getClonedProcessIdByOriginalProcessId(String originalProcessId);
	
	@Query(value = "select cloneProcessId from processmigrationinfo where ORIGINALPROCESSID= ?1 and status != 200", nativeQuery = true)
	public List<String>  getClonedProcessIdByOriginalProcessIdAndStatus(String originalProcessId);
	
	@Query(value = "select ORIGINALPROCESSID from PROCESSMIGRATIONINFO  where CLONEPROCESSID= ?1 and JOBID=?2", nativeQuery = true)
	public Optional<String> findOriginalProcessInstanceIdByCloneIdAndJobId(String cloneProcessId,int jobId);
	
	public void deleteByCloneProcessIdAndOriginalProcessId(String cloneProcessId,String originalProcessId);

	
}
