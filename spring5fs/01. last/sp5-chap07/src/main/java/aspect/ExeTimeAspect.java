package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.core.Signature;

@Aspect
public class ExeTimeAspect {
	// Pointcut : 공통 기능을 적용할 대상을 설정함
	// chap07 패키지와 그 하위 패키지에 위치한 타입의 public 매서드를 Pointcut으로 설정한다
	@Pointcut("execution(public * chap07..*(..))")
	private void publicTarget() {
		
	}
	
	// Around : Around Advice를 설정함.
	// Around 애노테이션의 값 = "publicTarget()"
	// publicTarget() 메서드에 정의한 Pointcut에 공통 기능을 적용한다는 것을 의미함
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			long finish = System.nanoTime();
			org.aspectj.lang.Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()),
					(finish-start));
		}
	}

}
