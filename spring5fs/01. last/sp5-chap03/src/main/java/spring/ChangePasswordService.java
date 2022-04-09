package spring;

public class ChangePasswordService {

	private MemberDao memberDao;

	// 암호를 변경할 Member 데이터를 찾기 위해 email 사용
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		// 멤버가 존재하지 않을 시 익셉션 발생
		if (member == null)
			throw new MemberNotFoundException();

		// 암호 변경
		member.changePassword(oldPwd, newPwd);

		// 변경된 데이터 보관
		memberDao.update(member);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
