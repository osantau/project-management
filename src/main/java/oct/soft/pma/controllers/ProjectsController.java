package oct.soft.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oct.soft.pma.entities.Project;
import oct.soft.pma.services.EmployeeService;
import oct.soft.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

	@Autowired
	private ProjectService proServ;
	@Autowired
	private EmployeeService empServ;
	
	@GetMapping
	public String listProjects(Model model) {
		
		model.addAttribute("projects", proServ.getAll());
		return "projects/list-projects";
	}
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("allEmployees", empServ.getAll());
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {				
		proServ.save(project);		
	
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
}
