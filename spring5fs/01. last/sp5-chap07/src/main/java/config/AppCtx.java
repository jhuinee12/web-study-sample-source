package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

// @Aspect 애노테이션을 붙인 클래스를 공통 기능으로 적용하려면
// @EnableAspectJAutoProxy 애노테이션을 설정 클래스에 붙여야 함
// => 스프링은 @Aspect 애노테이션이 붙은 빈 객체를 찾아 빈 객체의 @Pointcut 설정과 @Around 설정 사용
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppCtx {
	
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
	
}
