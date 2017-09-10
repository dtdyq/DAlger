package dtdyq.alger.util;	 

public class Pair<First,Second>{
	private First first;
	private Second second;
	public Pair(){
		
	}
	public Pair(First t1, Second t2){
		this.first = t1;
		this.second = t2;
	}
	public void setFirst(First t1){
		this.first = t1;
	}
	public void setSecond(Second t2){
		this.second = t2; 
	}
	public First first(){
		return first;
	}
	public Second second(){
		return second;
	}
	@Override
	public String toString() {
		return String.format("Pair[%s,%s]", first, second);
	}

}
