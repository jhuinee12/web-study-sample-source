package spring.cust_data;

import java.time.LocalDateTime;

public class Member {
	private Long id;							// 회원 아이디
	private String email;						// 회원 이메일
	private String password;					// 회원 비밀번호
	private String name;						// 회원 이름
	private LocalDateTime registerDateTime;		// 회원 가입일자
	
	public Member(String email, String password, String name, LocalDateTime regDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = regDateTime;
	}

	public void setId(Long id) { this.id = id; }
	
	public Long getId() { return id; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getName() { return name; }
	public LocalDateTime getRegisterDateTime() { return registerDateTime; }

	/**
	 * changePassword : 암호 변경 기능 메서드
	 * @param oldPassword
	 * @param newPassword
	 */
	public void changePassword(String oldPassword, String newPassword) {
		// oldPassword와 현재 암호(password)가 다르면
		// WrongIdPasswordException 발생
		if (!password.equals(oldPassword))
			throw new WrongIdPasswordException();	// runtime exception
		
		// oldPassword와 현재 암호(password)가 같으면
		// newPassword로 변경
		this.password = newPassword;
	}
}
