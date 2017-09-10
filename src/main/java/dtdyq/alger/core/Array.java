package dtdyq.alger.core;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import dtdyq.alger.core.Container;

public interface Array<T> extends Container<T>{
	T get(int index);
	void set(int index,T value);
	int indexOf(T value);
	boolean any(Predicate<T> p);
	boolean all(Predicate<T> p);
	Object[] toOriginal();
	void removeIf(Predicate<T> p); 
	Iterator<T> reverseIterator();
	<E> Array<E> map(Function<? super T, ? extends E> mapper);
}
