package oct.soft.pma.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import oct.soft.pma.entities.Employee;
import oct.soft.pma.entities.Project;
import oct.soft.pma.services.EmployeeService;
import oct.soft.pma.services.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectAPIController {

	@Autowired
	ProjectService proServ;

	@GetMapping
	public List<Project> getProjects() {		
		return proServ.getAll();
	}
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id)
	{
		return proServ.findById(id);
	}
	
	@RequestMapping(produces = "application/json", method = {RequestMethod.POST})
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project project)
	{			
		return proServ.save(project);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@PathVariable("id") Long id, @RequestBody Project project)
	{
		Project projectPersisted = proServ.findById(id);
		if(projectPersisted != null)
		{
			if (project.getName()!=null) 
			{
				projectPersisted.setName(project.getName());
			}
			if (project.getStage()!=null) 
			{
				projectPersisted.setStage(project.getStage());
			}
			if (project.getDescription()!=null) 
			{
				projectPersisted.setDescription(project.getDescription());
			}
			proServ.save(projectPersisted);
		}
		return projectPersisted;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id)
	{
		try {
		proServ.deleteById(id);
		}catch(EmptyResultDataAccessException e)
		{
			
		}
	}
	
}
