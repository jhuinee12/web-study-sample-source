package spring.cust_join;

import java.time.LocalDateTime;

import spring.cust_data.Member;
import spring.cust_data.MemberDao;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());

		// 같은 이름의 멤버가 존재하면 DuplicateMemberException 발생
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}

		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());

		memberDao.insert(newMember);

		return newMember.getId();
	}
}
