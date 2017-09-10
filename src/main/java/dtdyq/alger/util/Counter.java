package dtdyq.alger.util;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
	private static final String DEFAULT_NAME="counter";
	private static int cnt = 1;
	private AtomicLong count = new AtomicLong(0);
	private String name;
	/**
	 * create a counter with default name
	 */
	public Counter(){
		this.name=DEFAULT_NAME+String.valueOf(cnt++);
	}
	/**
	 * create a counter with specified name
	 * @param name
	 */
	public Counter(String name){
		this.name=name;
	}
	/**
	 * create a counter with initial value
	 * @param initValue
	 */
	public Counter(int initValue){
		this.count.set(initValue);
	}
	/**
	 * create a counter with  the specific name and initial value
	 * @param name
	 * @param initValue
	 */
	public Counter(String name,int initValue){
		this.name = name;
		this.count.set(initValue);
	}
	/**
	 * call this method to make counter plus one
	 */
	public void increase(){
		this.count.incrementAndGet();
	}
	public synchronized void increase(long value){
		this.count.set(count.get()+value);
	}
	
	/**
	 * merge the specified Counter to this counter,and this counter's value equals to
	 * before value plus specified Counter's value
	 * @param Counter
	 */
	public synchronized void join(Counter counter){
		if(counter==null){
			return;
		}
		this.count.set(this.count.get()+counter.count.get());;
	}
	/**
	 * call this method to make counter minus one
	 */
	public void decrease(){
		this.count.decrementAndGet();
	}
	public synchronized void decrease(long value){
		count.set(count.get()-value);
	}
	@Override
	public String toString(){
		return this.name+": "+this.count;
	}
	/**
	 * @return long
	 */
	public long value(){
		return this.count.get();
	}
}
