package dtdyq.alger.array;

import static dtdyq.alger.util.AlgerTool.notNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import dtdyq.alger.core.Array;
import dtdyq.alger.core.Container;
import dtdyq.alger.exception.InvalidParamException;


public class Vector<T> implements Array<T>{
	private T[] array;
	private int size;
	private int N = 16;
	@SuppressWarnings("unchecked")
	public Vector() {
		this.array = (T[]) new Object[N];
		this.size = 0;
	}
	public Vector(int capacity){
		N = capacity;
		if(capacity<=0){
			throw new InvalidParamException("invalid initial capacity");
		}
		this.array = (T[]) new Object[capacity];
		this.size = 0;
	}
	@SuppressWarnings("unchecked")
	public Vector(T[] vals){
		this.array = (T[]) new Object[N];
		this.size = 0;
		for(T t: vals){
			add(t);
		}
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contain(T key) {
		if(key == null){
			return false;
		}
		boolean flag = false;
		Iterator<T> iterator = iterator();
		while(iterator.hasNext()){
			if(key.equals(iterator.next())){
				flag = true;break;
			}
		}
		return flag;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int cnt = 0;
			@Override
			public boolean hasNext() {
				return cnt < size;
			}

			@Override
			public T next() {
				return array[cnt++];
			}
			public void remove(){
				array[--cnt] = null;
				for(int i=cnt;i<size-1;i++){
					array[i] = array[i+1];
				}
				array[--size] = null;
			}
		};
	}

	@Override
	public Iterator<T> reverseIterator(){
		return new Iterator<T>() {
			private int cnt = size;
			@Override
			public boolean hasNext() {
				return cnt-->0;
			}

			@Override
			public T next() {
				return array[cnt];
			}
			public void remove(){
				array[cnt] = null;
				for(int i=cnt;i<size-1;i++){
					array[i] = array[i+1];
				}
				array[--size] = null;
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.N = 16;
		this.array = (T[]) new Object[N];
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(T value) {
		if(value == null){
			return;
		}
		resize();
		this.array[size++] = value;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		if(size < N/2 && N > 16){
			N = N/2;
			T[] tmp = array;
			array = (T[])new Object[N];
			for(int i=0;i<size;i++){
				array[i] = tmp[i];
			}
		}
		if(size > N/2){
			N = N*2;
			T[] tmp = array;
			array = (T[])new Object[N];
			for(int i=0;i<size;i++){
				array[i] = tmp[i];
			}
		}
	}
	@Override
	public void remove(T value) {
		if(value == null){
			return;
		}
		int i = 0;
		for(;i<size;i++){
			if(value.equals(array[i])) break;
		}
		if(i == size){
			return;
		}
		size--;
		for(;i<size;){
			array[i] = array[++i];
		}
		this.array[size] = null;
		resize();
	}

	@Override
	public T get(int index) {
		if(index < 0||index >= size){
			return null;
		}
		return this.array[index];
	}

	@Override
	public void set(int index, T value) {
		if(index < 0||index >= size||value == null){
			return;
		}
		this.array[index] = value;
	}

	@Override
	public int indexOf(T value) {
		if(value == null){
			return -1;
		}
		for(int i = 0;i < size;i ++){
			if(value.equals(array[i])){
				return i;
			}
		}
		return -1;
	}
	private void remove(int index) {
		if(index < 0||index >= size){
			return;
		}
		size--;
		for(;index < size;index++){
			this.array[index] = this.array[++index];
		}
		this.array[size] = null;
		resize();
	}
	@Override
	public boolean any(Predicate<T> p) {
		for(int i=0;i<size;i++){
			if(p.test(array[i])){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean all(Predicate<T> p) {
		return any(p);
	}
	@Override
	public Object[] toOriginal() {
		Object[] dest = new Object[size];
		for(int i=0;i<size;i++){
			dest[i] = array[i];
		}
		return dest;
	}
	@Override
	public void removeIf(Predicate<T> p) {
		for(int i=0;i<size;i++){
			if(p.test(get(i))){
				remove(i);
			}
		}
	}
	@Override
	public void add(Container<T> con) {
		if(con != this){
			con.forEach(item->{
				add(item);
			});
		}
	}
	@Override
	public void add(Collection<T> col) {
		if(!notNull(col))    return;
		Iterator<T> iterator = col.iterator();
		while(iterator.hasNext()){
			T tmp = iterator.next();
			if(notNull(tmp))
				add(tmp);
		}		
	}
	@Override
	public <E> Array<E> map(Function<? super T, ? extends E> mapper) {
		Array<E> res = new Vector<E>();
		for(int i = 0;i < size;i++){
			E e = mapper.apply(array[i]);
			res.add(e);
		}
		return res;
	} 
}






