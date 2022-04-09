package spring.printer;

import org.springframework.beans.factory.annotation.Autowired;

import spring.cust_data.Member;
import spring.cust_data.MemberDao;

public class MemberInfoPrinter {

	@Autowired // 스프링 빈에 의존하는 다른 빈을 자동으로 주입하고 싶을 때 사용
	private MemberDao memDao;
	@Autowired
	private MemberPrinter printer;

	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}

	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
