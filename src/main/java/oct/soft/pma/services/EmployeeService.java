package oct.soft.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import oct.soft.pma.dto.EmployeeProjectDto;
import oct.soft.pma.entities.Employee;
import oct.soft.pma.repositories.EmployeeRepository;

@Service // this is required for Spring Container for scanning classes
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}

	public List<EmployeeProjectDto> employeeProjects() {
		return empRepo.employeeProjects();
	}
	
	public Employee findById(Long id) {
		return empRepo.findById(id).get();
	}

	public void deleteById(Long id) {
		empRepo.deleteById(id);
		
	}
        
        public Iterable<Employee> findPageable(Pageable pageable)
        {
            return empRepo.findAll(pageable);
        }
              
}
