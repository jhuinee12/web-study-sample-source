package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// 해당 클래스를 스프링 설정 클래스로 지정
public class AppContext {
	
	@Bean	// 해당 메서드가 생성한 객체를 스프링이 관리하는 빈(Bean) 객체로 등록 :: Bean 객체 - 스프링이 생성하는 객체
	public Greeter greeter() {
		Greeter g = new Greeter();	// 객체 생성
		g.setFormat("%s, 안녕하세요!");	// 객체 초기화
		return g;
	}
	
	@Bean	// 해당 메서드가 생성한 객체를 스프링이 관리하는 빈(Bean) 객체로 등록 :: Bean 객체 - 스프링이 생성하는 객체
	public Greeter greeter1() {
		Greeter g = new Greeter();	// 객체 생성
		g.setFormat("안녕하세요, %s님!");	// 객체 초기화
		return g;
	}

}