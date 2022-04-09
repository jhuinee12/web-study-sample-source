// DI(의존 주입) : 의존하는 객체를 직접 생성하는 대신 의존 객체를 전달받는 방식 사용

package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
	// 의존 객체 직접 생성
		// private MemberDao memberDao = new MemberDao();
	// 의존하는 MemberDao 객체도 함께 생성
		// MemberRegisterService svc = new MemberRegisterService();
	
	// DI 방식 적용
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Long regist(RegisterRequest req) {
		// MemberDao 객체의 selectByEmail() 메서드를 이용해서
		// 동일한 이메일을 가진 회원 데이터가 존재하는지 확인
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			// 같은 이메일이 존재한다면 익셉션 발생
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		// 같은 이메일을 가진 회원이 존재하지 않으면 DB에 삽입
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
