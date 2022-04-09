package chap02;

//AnnotationConfigApplicationContext
//: 자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// AnnotationConfigApplicationContext 객체를 생성할 때 앞서 작성한
		// AppContext 클래스를 생성자 파라미터로 전달
		// AppContext에 정의한 @Bean 설정 정보를 읽어와 Greeter 객체를 생성하고 초기화 한다
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);

		// getBean() 메서드는 AnnotationConfigApplicationContext가 자바 설정을 읽어와
		// 생성한 빈(Bean) 객체를 검색할 때 사용
		// 첫번쨰 파라미터 : 빈 객체 이름(메서드 이름), 두번째 파라미터 : 검색할 빈 객체 타입
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greet("스프링");
		System.out.println(msg);

		Greeter g1 = ctx.getBean("greeter1", Greeter.class);
		String msg1 = g1.greet("스프링");
		System.out.println(msg1);
		ctx.close();
	}
}
