package oct.soft.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import oct.soft.pma.entities.Employee;
import oct.soft.pma.repositories.EmployeeRepository;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("Entered valdiation method...");
		Employee emp = empRepo.findByEmail(value);
		return emp==null ? true: false;
	
	}

}
