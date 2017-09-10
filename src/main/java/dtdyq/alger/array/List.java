package dtdyq.alger.array;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import dtdyq.alger.core.Array;
import dtdyq.alger.core.Container;
import dtdyq.alger.util.AlgerTool;
import static dtdyq.alger.util.AlgerTool.notNull;

public class List<T> implements Array<T> {
	private class Node {
		T value;
		Node pre;
		Node rear;

		Node(T value, Node pre, Node rear) {
			this.value = value;
			this.pre = pre;
			this.rear = rear;
		}

		@Override
		public boolean equals(Object o) {
			@SuppressWarnings("unchecked")
			Node tmp = (Node) o;
			return this.value.equals(tmp.value);
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public List() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public List(T[] vals){
		this.head = null;
		this.tail = null;
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
	public boolean contain(T value) {
		if (isEmpty() || !notNull(value)) {
			return false;
		}
		boolean flag = false;
		Node tmp = head;
		for (; notNull(tmp); tmp = tmp.rear) {
			if (value.equals(tmp.value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node tmp = head;

			@Override
			public boolean hasNext() {
				return notNull(tmp);
			}

			@Override
			public T next() {
				T res = tmp.value;
				tmp = tmp.rear;
				return res;
			}
			public void remove(){
				if(!notNull(tmp)){
					tail = tail.pre;
					if(notNull(tail)){
					    tail.rear = null;
					}else{
						head = null;
					}
				}else{
					Node moved = tmp.pre;
					if(moved == head){
						head = head.rear;
						if(notNull(head))
							head.pre = null;
					}
					if(notNull(moved.pre,moved.rear)){
						moved.pre.rear = moved.rear;
						moved.rear.pre = moved.pre;
					} 
				}
				size--;
			}
		};
	}
	@Override
	public Iterator<T> reverseIterator() {
		return new Iterator<T>() {
			private Node tmp = tail;
			@Override
			public boolean hasNext() {
				
				return notNull(tmp);
			}
			@Override
			public T next() {
				T res = tmp.value;
				tmp = tmp.pre;
				return res;
			}
			public void remove(){
				if(tmp == null){
					head = head.rear;
					if(notNull(head)){
						head.pre = null;
					}else{
						tail = null;
					}
				}else{
					Node moved = tmp.rear;
					if(moved == tail){
						tail = tail.pre;
						if(notNull(tail))
							tail.rear = null;
					}
					if(notNull(moved.pre,moved.rear)){
						moved.pre.rear = moved.rear;
						moved.rear.pre = moved.pre;
					} 
				}
			}
		};

	}

	@Override
	public void clear() {
		this.size = 0;
		this.head = null;
		this.tail = head;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(T value) {
		if (value == null)
			return;
		if (isEmpty()) {
			head = new Node(value, null, null);
			tail = head;
			size++;
			return;
		}
		Node tmp = new Node(value, null, null);
		tail.rear = tmp;
		tmp.pre = tail;
		tail = tmp;
		size++;
	}

	@Override
	public void remove(T value) {
		if (isEmpty() || !contain(value) || value == null)
			return;
		Node tmp = head;
		size--;
		while (notNull(tmp)) {
			if (tmp.value.equals(value)) {
				break;
			}
			tmp = tmp.rear;
		}
		if (tmp.equals(head)) {
			head = head.rear;
			head.pre = null;
		} else if (tmp.equals(tail)) {
			tail = tail.pre;
			tail.rear = null;
		} else {
			tmp.pre.rear = tmp.rear;
			tmp.rear.pre = tmp.pre;
			tmp = null;
		}
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		Node tmp = head;
		int cnt = 0;
		while (notNull(tmp)) {
			if (cnt == index) {
				return tmp.value;
			}
			tmp = tmp.rear;
			cnt++;
		}
		return null;
	}

	@Override
	public void set(int index, T value) {
		if (index < 0 || index >= size) {
			return;
		}
		Node tmp = head;
		int cnt = 0;
		while (notNull(tmp)) {
			if (cnt == index) {
				tmp.value = value;
				break;
			}
			tmp = tmp.rear;
			cnt++;
		}
	}

	@Override
	public int indexOf(T value) {
		int cnt = 0;
		Node tmp = head;
		while (notNull(tmp) && !tmp.value.equals(value)) {
			cnt++;
		}
		if (tmp == null) {
			return -1;
		}
		return cnt;
	}

	private void remove(int index) {
		T value = get(index);
		System.out.println(value);
		remove(value);
	}

	@Override
	public boolean any(Predicate<T> p) {
		Node tmp = head;
		while(notNull(tmp)){
			if(p.test(tmp.value)){
				return true;
			}
			tmp = tmp.rear;
		}
		return false;
	}

	@Override
	public boolean all(Predicate<T> p) {
		return !any(p);
	}
	@Override
	public Object[] toOriginal() {
		Object[] res = new Object[size];
		for(int i=0;i<size;i++){
			res[i] = get(i);
		}
		return res;
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
		if(!AlgerTool.notNull(col))    return;
		Iterator<T> iterator = col.iterator();
		while(iterator.hasNext()){
			T tmp = iterator.next();
			if(notNull(tmp))
				add(tmp);
		}
	}
	@Override
	public <E> Array<E> map(Function<? super T, ? extends E> mapper) {
		Node tmp = head;
		Array<E> res = new List<E>();
		while(notNull(tmp)){
			E e = mapper.apply(tmp.value);
			res.add(e);
			tmp = tmp.rear;
		}
		return res;
	}

}
