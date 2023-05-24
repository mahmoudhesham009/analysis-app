package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MigrationJobRepoSpringImpl extends JpaRepository<MigrationJob, Integer> {

	@Query(value="select * from (select * from MIGRATION_JOB where to_date(?1,'YYYY-MM-DD HH24:MI:SS') between to_date(FROM_DATE,'YYYY-MM-DD HH24:MI:SS') and to_date(TO_DATE,'YYYY-MM-DD HH24:MI:SS') order by to_date(FROM_DATE,'YYYY-MM-DD HH24:MI:SS') ASC ) where rownum=1",
			nativeQuery=true)
	Optional<MigrationJob> getIntervalForToDate(String toDate);
	
	@Query(value="select * from (select * from MIGRATION_JOB where to_date(?1,'YYYY-MM-DD HH24:MI:SS') between to_date(FROM_DATE,'YYYY-MM-DD HH24:MI:SS') and to_date(TO_DATE,'YYYY-MM-DD HH24:MI:SS') order by to_date(TO_DATE,'YYYY-MM-DD HH24:MI:SS') DESC ) where rownum=1",
			nativeQuery=true)
	Optional<MigrationJob> getIntervalForFromDate(String fromDate);
	
	@Query(value="select PROCESSMIGRATIONINFO.JOBID , MIGRATION_JOB.FROM_DATE, MIGRATION_JOB.TO_DATE, PROCESSMIGRATIONINFO.ORIGINALPROCESSID , PROCESSMIGRATIONINFO.CLONEPROCESSID, PROCESSMIGRATIONINFO.STATUS from MIGRATION_JOB full outer join PROCESSMIGRATIONINFO on (PROCESSMIGRATIONINFO.JOBID = MIGRATION_JOB.ID)",nativeQuery=true)
	List<Object> joinTables();
	
	@Query(value="select * from MIGRATION_JOB where to_date(FROM_DATE,'YYYY-MM-DD HH24:MI:SS') between to_date(?1,'YYYY-MM-DD HH24:MI:SS') and to_date(?2,'YYYY-MM-DD HH24:MI:SS')",nativeQuery=true)
	List<MigrationJob> getJobInsideInterval(String fromDate,String toDate);
}
//TO_DATE(?1,'yyyy-mm-dd')