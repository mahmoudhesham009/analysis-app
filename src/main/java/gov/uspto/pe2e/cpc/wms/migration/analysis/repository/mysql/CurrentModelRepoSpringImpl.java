package gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentModelRepoSpringImpl extends JpaRepository<CurrentModel, Integer> {

	
	Optional<CurrentModel> findByProcessName(String processName);

}
