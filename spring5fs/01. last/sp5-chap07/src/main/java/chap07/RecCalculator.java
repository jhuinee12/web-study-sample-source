package chap07;
// 재귀호출 이용하여 계승을 구함
public class RecCalculator implements Calculator {
	
	@Override
	public long factorial(long num) {
		if (num == 0)
			return 1;
		else
			return num * factorial(num - 1);
	}

}
