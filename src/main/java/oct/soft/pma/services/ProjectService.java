package oct.soft.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oct.soft.pma.dto.ChartDataDto;
import oct.soft.pma.entities.Project;
import oct.soft.pma.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository proRepo;

	public List<Project> getAll() {
		return proRepo.findAll();
	}

	public List<ChartDataDto> getProjectStatus() {
		return proRepo.getProjectStatus();
	}

	public Project save(Project project) {
		return proRepo.save(project);
	}
}
