package com.emnify.aspects;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

// import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Aspect for handling traceable methods.
 */
@Aspect
public class TraceAspect {

	/**
	 * Pointcut for all methods annotated with <code>@Traceable</code>
	 */
	@Pointcut("execution(@com.emnify.Traceable * *.*(..))")
	private void trace() {
	}

	@Around("trace()")
	public Object aroundTracedMethods(ProceedingJoinPoint thisJoinPoint) throws Throwable {

		System.out.printf("Enters on method: \"%s\". \n", thisJoinPoint.getSignature());

		Object[] arguments = thisJoinPoint.getArgs();
		for (int i = 0; i < arguments.length; i++) {
			Object argument = arguments[i];
			if (argument != null) {
				System.out.printf("With argument of type %s and value \"%s\". \n", argument.getClass().toString(),
						argument);
			}
		}

		Object result = thisJoinPoint.proceed();

		Method method = ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
		Type type = method.getGenericReturnType();
		System.out.println("return type = " + type);
		System.out.printf("Exits method: %s. \n", thisJoinPoint.getSignature());

		System.out.println("Return type: " + type.getTypeName() + "  --->   Value: " + result.toString());

		return result;
	}
}
