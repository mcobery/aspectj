import java.util.HashMap;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for handling cacheable methods.
 */
@Aspect
public class CacheAspect {

	private Map<String, Object> myCache;

	public CacheAspect() {
		myCache = new HashMap<String, Object>();
	}

	/**
	 * Pointcut for all methods annotated with <code>@Cacheable</code>
	 */
	@Pointcut("execution(@com.emnify.Cacheable * *.*(..))")
	private void cache() {
	}

	@Around("cache()")
	public Object aroundCachedMethods(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		System.out.println("Execution of Cacheable method catched");
		// generate the key under which cached value is stored
		// will look like caching.aspectj.Calculator.sum(Integer=1;Integer=2;)
		StringBuilder keyBuff = new StringBuilder();

		// append name of the class and name
		keyBuff.append(thisJoinPoint.getTarget().getClass().getName()).append(".")
				.append(thisJoinPoint.getSignature().getName()).append("(");

		// loop through cacheable method arguments
		for (final Object arg : thisJoinPoint.getArgs()) {
			// append argument type and value
			keyBuff.append(arg.getClass().getSimpleName() + "=" + arg + ";");
		}
		keyBuff.append(")");
		String key = keyBuff.toString();

		System.out.println("Key = " + key);
		Object result = myCache.get(key);
		if (result == null) {
			System.out.println("Result not yet cached. Must be calculated...");
			result = thisJoinPoint.proceed();
			System.out.println("Storing calculated value '" + result + "' to cache");
			myCache.put(key, result);
		} else {
			System.out.println("Result '" + result + "' was found in cache");
		}
		return result;
	}
}
