package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;	// 스프링 설정 클래스 의미

import spring.cust_data.MemberDao;
import spring.cust_join.MemberRegisterService;
import spring.cust_pw_change.ChangePasswordService;

@Configuration
public class AppCtx {
	// @Bean 애노테이션을 붙인 각각의 메서드마다 한 개의 빈 객체를 생성함
	// 이 때 메서드 이름을 빈 객체 이름으로 사용
	// ex) memberDao() 메서드를 이용해서 생성한 빅 객체는 "memberDao"라는 이름으로 스프링에 등록
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		// MemberRegisterService 생성자를 호출할 때 memberDao() 메서드를 호출
		return new MemberRegisterService(memberDao());
	}
	
	@Bean // ChangePasswordService 타입의 빈을 설정
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());	// 세터를 잉용해서 의존 객체 주입
		return pwdSvc;
	}

}
