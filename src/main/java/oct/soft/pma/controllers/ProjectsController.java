package oct.soft.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.validation.Valid;
import oct.soft.pma.dto.TimeChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oct.soft.pma.entities.Project;
import oct.soft.pma.services.EmployeeService;
import oct.soft.pma.services.ProjectService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String createProject(@Valid Project project, Model model, BindingResult bindingResult) {
        System.out.println(project.getStartDate());
        if (bindingResult.hasErrors()) {
            return "projects/new-project";
        }
        proServ.save(project);

        // use a redirect to prevent duplicate submissions
        return "redirect:/projects";
    }

    @GetMapping("/update")
    public String displayProjectUpdateForm(@RequestParam("id") long id, Model model) {
        Project project = proServ.findById(id);
        model.addAttribute("project", project);
        return "projects/new-project";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("id") long id) {
        proServ.deleteById(id);
        return "redirect:/projects";
    }

    @GetMapping("/timelines")
    public String displayTimeLines(Model model) throws JsonProcessingException {
        List<TimeChartData> timelineData = proServ.getTimeData();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(timelineData);
        model.addAttribute("projectTimeList", json);
        return "projects/project-timelines";
    }
}
