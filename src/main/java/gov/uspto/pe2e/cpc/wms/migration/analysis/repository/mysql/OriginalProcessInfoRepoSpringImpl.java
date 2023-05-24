package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginalProcessInfoRepoSpringImpl extends JpaRepository< OriginalProcessInfo,Integer> {

	@Query(value="select PROCESSINSTANCEID  from ORIGINALPROCESSINFO where to_date(STARTEDTIME,'DD-MM-YYYY HH24:MI:SS') between to_date(?1,'DD-MM-YYYY HH24:MI:SS') and to_date(?2,'DD-MM-YYYY HH24:MI:SS')" , nativeQuery=true)
	public List<String> findOldProcessesWithinInterval(String fromDate,String toDate);
}
