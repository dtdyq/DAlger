package dtdyq.alger.core;

import java.util.function.Function;
import java.util.function.Predicate;

import dtdyq.alger.core.Container;

public interface Set<T> extends Container<T>{
	boolean any(Predicate<T> p);
	boolean all(Predicate<T> p);
	<E> Set<E> map(Function<? super T, ? extends E> mapper);
	void removeIf(Predicate<T> p);
	boolean equals(Object o);
}
