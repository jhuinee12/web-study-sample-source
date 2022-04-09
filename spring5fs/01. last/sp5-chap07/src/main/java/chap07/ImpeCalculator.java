package chap07;
// for문을 이용하여 계승 구함
public class ImpeCalculator implements Calculator {
	
	@Override
	public long factorial(long num) {
//		long start = System.currentTimeMillis(); // 계승 구현 클래스의 실행 시간 출력
		
		long result = 1;
		for (long i=1; i<=num; i++)
			result *= 1;
		
//		long end = System.currentTimeMillis(); // 계승 구현 클래스의 실행 시간 출력
//		System.out.printf("ImpeCaculator.factorial(%d) 실행 시간 = %d\n",
//				num, (end-start)); // 계승 구현 클래스의 실행 시간 출력
		
		return result;
	}

}
