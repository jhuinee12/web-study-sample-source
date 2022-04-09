package chap07;

public class ExeTimeCalculator implements Calculator {
	
	private Calculator delegate; // Calculator 인터페이스 구현
	
	// Calculator 객체를 전달받아 delegate 필드에 할당
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial (long num) {
		long start = System.nanoTime(); // 시작 시간
		// factorial 메서드에서 delegate.factorial() 메서드 실행
		long result = delegate.factorial(num);
		long end = System.nanoTime(); // 끝 시간
		System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",
				delegate.getClass().getSimpleName(),
				num, (end-start));
		return result;
	}

}
