
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectL {

	@Pointcut("execution(* main(..))")
	public void anyMain() {
	}

	@Before("anyMain()")
	public void beforeMain(JoinPoint joinPoint) {
		System.out.println("aspect before main");
	}

	@After("anyMain()")
	public void afterMain(JoinPoint joinPoint) {
		System.out.println("aspect after main");
	}
}
