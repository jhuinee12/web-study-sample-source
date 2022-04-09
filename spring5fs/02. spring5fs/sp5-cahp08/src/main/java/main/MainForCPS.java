package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DBConfig;
import spring.cust_data.WrongIdPasswordException;
import spring.cust_pw_change.ChangePasswordService;
import spring.cust_pw_change.MemberNotFoundExcepion;

public class MainForCPS {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);
		
		ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		try {
			cps.changePassword("madvirus@madvirus.net", "0x1.3b55b621a39bp-2", "1111");
		} catch (MemberNotFoundExcepion e) {
			System.out.println("회원 데이터가 존재하지 않습니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("암호가 올바르지 않습니다.");
		}
		
		ctx.close();
	}

}
