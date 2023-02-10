package oct.soft.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(oct.soft.pma.controllers..*) || within(oct.soft.pma.repositories..*)")
	public void definePackagePointcuts() {
		//empy method just to name the location specified in the pointcut		
	}
	
	@Before("definePackagePointcuts()")
	public void log() {
		log.debug(" ------------------------------------------------------ ");
	}
}
