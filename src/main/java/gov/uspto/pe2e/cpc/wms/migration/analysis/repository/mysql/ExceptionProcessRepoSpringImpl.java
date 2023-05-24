package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionProcessRepoSpringImpl extends JpaRepository<ExceptionProcess, Integer> {

	Optional<ExceptionProcess> findByProcessInstanceIdAndJobId(String processInstanceId, String jobId);

	Optional<ExceptionProcess> findByProjectNumAndJobId(String projectNum, String jobId);

}
