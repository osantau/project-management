package oct.soft.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import oct.soft.pma.dto.ChartDataDto;
import oct.soft.pma.services.EmployeeService;
import oct.soft.pma.services.ProjectService;

@Controller
public class HomeController {

	@Autowired
	private ProjectService proServ;
	@Autowired
	private EmployeeService empServ;

	// read value from properties file
	@Value("${version}")
	private String ver;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		model.addAttribute("versionNumber", ver);
		model.addAttribute("projects", proServ.getAll());
		model.addAttribute("employeeListProjectsCnt", empServ.employeeProjects());
		List<ChartDataDto> projectData = proServ.getProjectStatus();
		// lets convert projectdata object into a json structure in javascript
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(projectData);
		model.addAttribute("projectStatusCnt", jsonString);
		System.out.println(jsonString);
		return "main/home";
	}
}
