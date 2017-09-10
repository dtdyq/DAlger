package dtdyq.alger.util;

public class AlgerTool {
	public static boolean notNull(Object...obj){
		for(Object o : obj){
			if(o == null){
				return false;
			}
		}
		return true;
	}
	public static boolean isPrime(int num){
		if(num<2) return false;
		for(int i = 2;i*i <= num;i++){
			if(num%i == 0) return false;
		}
		return true;
	}
}
