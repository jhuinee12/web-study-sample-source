package spring.cust_join;

public class DuplicateMemberException extends RuntimeException {

	/**
	 * 동일한 이메일을 갖고 있는 회원이 이미 존재할 때 MemgerRegisterService가 발생시키는 익셉션 타입
	 * @param message
	 */
	public DuplicateMemberException(String message) {
		super(message);
	}
}
