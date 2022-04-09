// 싱글톤(Singleton) 객체
// 싱글톤 : 단일 객체 의미. 스프링은 기본적으로 한 개의 @Bean 어노테이션에 대해 한 개의 빈 객체를 생성한다.

package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);
		
		// 이름이 greeter인 빈 객체를 구해서 각각 g1과 g2 변수에 할당
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		// 이름이 greeter1인 빈 객체를 구해서 g3 변수에 할당
		Greeter g3 = ctx.getBean("greeter1", Greeter.class);
		
		// g1과 g2가 같은 객체인지 여부를 콘솔에 출력
		System.out.println("(g1 == g2) = " + (g1 == g2));
		System.out.println("(g1 == g3) = " + (g1 == g3));
		
		ctx.close();
	}
}
