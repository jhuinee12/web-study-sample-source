package spring.cust_pw_change;

import org.springframework.transaction.annotation.Transactional;

import spring.cust_data.Member;
import spring.cust_data.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		// Member 데이터를 찾기 위해 email 사용
		Member member = memberDao.selectByEmail(email);
		
		// member 데이터가 존재하지 않으면 익셉션 발생
		if (member == null) 
			throw new MemberNotFoundExcepion();
		
		// 암호 변경
		member.changePassword(oldPwd, newPwd);
		// 변경된 데이터 보관
		memberDao.update(member);
	}
	
	// 외존하는 MemberDao를 전달받음 :: setter를 통해 의존 객체를 주입
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
