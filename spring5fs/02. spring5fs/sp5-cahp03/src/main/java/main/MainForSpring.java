package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.cust_data.WrongIdPasswordException;
import spring.cust_join.DuplicateMemberException;
import spring.cust_join.MemberRegisterService;
import spring.cust_join.RegisterRequest;
import spring.cust_pw_change.ChangePasswordService;
import spring.cust_pw_change.MemberNotFoundExcepion;

public class MainForSpring {
	
	private static ApplicationContext ctx = null;

	// arg[0] = new | arg[1] = email | arg[2] = name | arg[3] = pw | arg[3] = pwcheck
	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswrodEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch(DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePwdSvc = ctx.getBean("memberRegSvc", ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundExcepion e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}


	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();	// BufferedReader#readLine() 메서드를 이용해 한 줄 입력받음
			
			// 입력한 문자열이 exit이면 프로그램 종료
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			// 입력한 문자열이 new 로 시작하면 processNewCommand() 메서드 실행
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} 
			// 입력한 문자열이 change 로 시작하면 processChangeCommand() 메서드 실행
			else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			
			// 명령어를 잘못 입력하면 printHelp() 메서드 실행
			// continue를 시용하면 남은 코드 건너뜀
			printHelp();
		}
	}
}
