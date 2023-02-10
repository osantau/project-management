package oct.soft.pma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import oct.soft.pma.dto.ChartDataDto;
import oct.soft.pma.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value="select stage as label, count(*) as \"value\"  from project  group by stage")
	public List<ChartDataDto> getProjectStatus();
}
