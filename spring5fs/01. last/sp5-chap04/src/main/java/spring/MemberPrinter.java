package spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	// 생성자 초기화와 필수 여부 지정 방식 동작 이해
	public void print() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}

	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf(
					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n", 
					member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf(
					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n", 
					member.getId(), member.getEmail(),
					member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	
	// 필수 여부 지정
	@Autowired (required = false) // ① 매칭되는 빈이 없어도 익셉션이 발생하지 않으며 자동 주입을 수행하지 않음
	//② public void setDateFormatter(Oprional<DateTimeFormatter> formatterOpt) 로도 가능! (required=false 대신)
	//③ public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter)
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
