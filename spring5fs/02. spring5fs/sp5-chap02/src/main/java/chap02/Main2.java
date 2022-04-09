package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * 싱글톤(Singleton) 객체
 * 싱글톤 : 단일 객체(single object)를 의미하는 단어
 * 싱글톤은 기본적으로 한 개의 @Bean 애노테이션에 대한 한 개의 빈 객체를 생성
 *
 */
public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		System.out.println("(g1==g2)= " + (g1==g2));
		
		Greeter g3 = ctx.getBean("greeter1", Greeter.class);
		System.out.println(g3.greet("greet1"));
		System.out.println("(g2==g3)= " + (g2==g3));
		ctx.close();
	}

}
