package oct.soft.pma.logging;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(oct.soft.pma.controllers..*)")
	public void definePackagePointcuts() {
		//empy method just to name the location specified in the pointcut		
	}
	
	@Around("definePackagePointcuts()")
	public Object logAround(ProceedingJoinPoint jp) {
		          log.debug("\n\n\n");

            log.debug("***** Before method execution ******* \n {}.{} () with argument(s) = {}",
                    jp.getSignature().getDeclaringTypeName(),
                    jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
            log.debug("___________________________________________________________________\n");
            Object o=null;
            try {
                o = jp.proceed();
            } catch (Throwable ex) {
               ex.printStackTrace();
            }
            log.debug("***** After method execution ******* \n {}.{} () with argument(s) = {}",
                    jp.getSignature().getDeclaringTypeName(),
                    jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
            log.debug("___________________________________________________________________\n");
            return o;
        }
        
}
