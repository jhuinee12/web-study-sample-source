package spring;

// 이미 동일한 이메일을 갖고 있는 회원이 존재할 때  MemberRegisterService를 발생시키는 익셉션 타입
public class DuplicateMemberException extends RuntimeException {

	public DuplicateMemberException(String message) {
		super(message);
	}

}
