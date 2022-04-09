package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// 1. 설정 정보를 이용해서 빈 객체를 생성한다.
		// AnnotationConfigApplicationContext :: 자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리함
		// AnnotationConfigApplicationContext 객체를 생성할 때 앞서 작성한 AppContext 클래스를 생성자 파라미터로 전달
		// AnnotationConfigApplicationContext는 AppContext에 정의한 @Bean 설정 정보를 읽어와 Greeter 객체를 생성하고 초기화함
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		// 2. 빈 객체를 제공한다.
		// getBean :: AnnotationConfigApplicationContext가 자바 설정을 읽어와 생성한 빈(bean) 객체를 검색할 때 사용됨
		// 첫번째 파라미터("greeter") : 빈 객체 이름
		// 두번째 파라미터(Greeter.class) : 검색할 빈 객체 타입
		Greeter g = ctx.getBean("greeter", Greeter.class);
		
		// Greeter.java(위 코드에서 가져옴)의 greet 메서드 실행
		String msg = g.greet("스프링");
		
		System.out.println(msg);
		ctx.close();
	}

}
