package assembler;

import spring.cust_data.MemberDao;
import spring.cust_join.MemberRegisterService;
import spring.cust_pw_change.ChangePasswordService;

public class Assembler {
	
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	/**
	 * MemberRegisterService 객체와 ChangePasswordService 객체에 대한 의존 주입
	 * MemberRegisterService :: 생성자를 통해 MemberDao 객체를 주입받음
	 * ChangePasswordService :: 세터를 통해 주입받음
	 */
	public Assembler() {
		memberDao = new MemberDao();
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}

}
