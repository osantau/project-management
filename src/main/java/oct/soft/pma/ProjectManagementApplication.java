package oct.soft.pma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import oct.soft.pma.repositories.EmployeeRepository;
import oct.soft.pma.repositories.ProjectRepository;

@SpringBootApplication(scanBasePackages = {"oct.soft.pma","oct.soft.utils"})
public class ProjectManagementApplication {

	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	ProjectRepository proRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner() {
		return args -> {
			Employee emp1 = new Employee("Santau", "Octavian", "octavian.santau@plastor.ro");
			Employee emp2 = new Employee("Rosu", "Marius", "marius.rosu@plastor.ro");
			Employee emp3 = new Employee("Dorel", "Berdie", "dorel.berdie@plastor.ro");
			Employee emp4 = new Employee("Neaga", "Andreea", "andreea.neaga@plastor.ro");
			Employee emp5 = new Employee("Bococi", "Andreea", "andreea.bococi@plastor.ro");

			Project pro1 = new Project("Large Production Deploy", "NOTSTARTED",
					"This requires all hands on the final deployment of the software production");
			Project pro2 = new Project("New Employee Budget", "COMPLETED", "Decide on a new employee bonus...");
			Project pro3 = new Project("Office Reconstruction", "INPROGRESS",
					"With the recent data hack, the security needs to be improved for implementation");

			pro1.addEmployee(emp1);
			pro1.addEmployee(emp2);
			pro2.addEmployee(emp3);
			pro3.addEmployee(emp4);
			pro3.addEmployee(emp5);

			emp1.setProjects(Arrays.asList(pro1, pro2, pro3));
			emp2.setProjects(Arrays.asList(pro1));
			emp3.setProjects(Arrays.asList(pro2, pro3));
			emp4.setProjects(Arrays.asList(pro3));
			emp5.setProjects(Arrays.asList(pro3));
			
			empRepo.save(emp1);
			empRepo.save(emp2);
			empRepo.save(emp3);
			empRepo.save(emp4);
			empRepo.save(emp5);
			proRepo.save(pro1);
			proRepo.save(pro2);
			proRepo.save(pro3);
		};
	}*/

}
