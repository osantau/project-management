package oct.soft.pma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import oct.soft.pma.dto.EmployeeProjectDto;
import oct.soft.pma.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {		
	
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery = true, value="select e.first_name as firstName, e.last_name as lastName ,count(pe.project_id) as projectCount"
			+ " from employee e"
			+ " left join project_employee pe on pe.employee_id = e.employee_id group by e.first_name, e.last_name order by projectCount desc")
	public List<EmployeeProjectDto> employeeProjects();
}
