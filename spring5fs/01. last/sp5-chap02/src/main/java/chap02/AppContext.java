package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration : 해당 클래스를 스프링 설정 클래스로 지정
@Configuration
public class AppContext {

	// @Bean : 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록
	@Bean 
	// 메서드 이름은 빈 객체를 구분할 때 사용 : greeter
	public Greeter greeter() {
		Greeter g = new Greeter(); // Greeter 객체 초기화
		g.setFormat("%s, 안녕하세요!");
		return g;
	}
	
	// greeter 한개와 greeter1에 해당하는 객체 한 개, 이렇게 두 개의 빈 객체가 생성됨 (싱글톤 : main2 참조)
	@Bean
	public Greeter greeter1() {
		Greeter g = new Greeter();
		g.setFormat("안녕하세요, %s님!");
		return g;
	}

}
