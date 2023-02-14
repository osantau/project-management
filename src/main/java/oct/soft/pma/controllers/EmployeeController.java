package oct.soft.pma.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import oct.soft.pma.entities.Employee;
import oct.soft.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService empServ;
	
	//constructor injection
	public EmployeeController(EmployeeService empServ) {	
		this.empServ = empServ;
	}

	
	@GetMapping
	public String listEmployees(Model model) {
		
		model.addAttribute("employees", empServ.getAll());
		return "employees/list-employee";
	}
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
		{
			return "employees/new-employee";
		}
		empServ.save(employee);
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees";
	}
}
