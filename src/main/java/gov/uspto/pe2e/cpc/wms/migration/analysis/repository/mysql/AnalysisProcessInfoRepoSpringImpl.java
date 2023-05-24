package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalysisProcessInfoRepoSpringImpl extends JpaRepository<AnalysisProcessInfo, Integer>{
	
	Optional<AnalysisProcessInfo> findByJobId(int jobId);
	
//	Optional<AnalysisProcessInfo> findByProcessInstanceId(String proInsId);
	Optional<AnalysisProcessInfo> findByProcessInstanceIdAndJobId(String proInsId,int jobId);
//	Optional<AnalysisProcessInfo> findByProjectNumber(String projectNum);
	@Query(value = "select * from (select * from ANALYSIS_PROCESS_INFO where  PROJECT_NUMBER = ?1 AND JOB_ID = ?2 order by STARTED_TIME ASC) where rownum= 1", 
			nativeQuery = true)
	Optional<AnalysisProcessInfo> findByProjectNumberAndJobId(String projectNum,int jobId);
}
