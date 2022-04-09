package chap02;

public class Greeter {
	private String format;

	// greet() : String의 문자열 포맷을 이용해서 새로운 문자열 생성 : 메시지 생성
	public String greet(String guest) {
		return String.format(format, guest);
	}

	// greet() 메서드에서 사용할 문자열 포맷을 설정 : 문자열 포맷 지정
	public void setFormat(String format) {
		this.format = format;
	}

}
