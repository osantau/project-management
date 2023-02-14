package oct.soft.pma.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import oct.soft.pma.entities.Employee;
import oct.soft.pma.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeAPIController {

	@Autowired
	EmployeeService empServ;

	@GetMapping
	public List<Employee> getEmployees() {		
		return empServ.getAll();
	}
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id)
	{
		return empServ.findById(id);
	}
	
	@RequestMapping(produces = "application/json", method = {RequestMethod.POST})
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody @Valid Employee employee)
	{	
		System.out.println("Posting Employee");
		return empServ.save(employee);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@PathVariable("id") Long id, @RequestBody @Valid Employee employee)
	{
		Employee empPersisted = empServ.findById(id);
		if(empPersisted != null)
		{
			if (employee.getFirstName()!=null) 
			{
				empPersisted.setFirstName(employee.getFirstName());
			}
			if (employee.getLastName()!=null) 
			{
				empPersisted.setLastName(employee.getLastName());
			}
			if (employee.getEmail()!=null) 
			{
				empPersisted.setEmail(employee.getEmail());
			}
			empServ.save(empPersisted);
		}
		return empPersisted;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id)
	{
		try {
		empServ.deleteById(id);
		}catch(EmptyResultDataAccessException e)
		{
			
		}
	}
	
}
