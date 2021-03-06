package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DBConfig;
import spring.cust_data.Member;
import spring.cust_data.WrongIdPasswordException;
import spring.cust_join.DuplicateMemberException;
import spring.cust_join.MemberRegisterService;
import spring.cust_join.RegisterRequest;
import spring.cust_pw_change.ChangePasswordService;
import spring.cust_pw_change.MemberNotFoundExcepion;

public class Main {
	private static AnnotationConfigApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
		// 데이터베이스 연결 객체
		ctx = new AnnotationConfigApplicationContext(DBConfig.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.println("명령어를 입력하세요.");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
			} else if (command.equals("list ")) {
				processListCommand();
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
			} else {
				printHelp();
			}
		}
		ctx.close();
	}

	private static void processNewCommand(String[] split) {
		if (split.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(split[1]);
		req.setName(split[2]);
		req.setPassword(split[3]);
		req.setConfirmPassword(split[4]);
		
		if (!req.isPasswrodEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다..\n");
		}
	}

	private static void processChangeCommand(String[] split) {

		if (split.length != 4) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(split[1], split[2], split[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundExcepion e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
		
	}

	private static void processListCommand() {
		
	}

	private static void processInfoCommand(String[] split) {
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}

}
