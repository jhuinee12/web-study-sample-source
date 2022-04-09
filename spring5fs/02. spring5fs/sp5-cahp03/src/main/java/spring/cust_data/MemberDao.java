package spring.cust_data;

import java.util.HashMap;
import java.util.Map;

/**
 * Map에 회원 데이터 저장
 */
public class MemberDao {
	private static long nextId = 0;
	
	// Map<email, member>
	private Map<String, Member> map = new HashMap<>();
	
	/* key값이 email(매개변수로 값 받아옴)인 데이터 가져옴 */
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	// id를 숫자 증감으로 넣기
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
}
