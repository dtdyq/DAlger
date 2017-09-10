package dtdyq.alger.util;


import java.util.Iterator;

/**
 * 
 * @author dtdyq
 *
 */
public class Stack<T> implements Iterable<T> {
	private int size;
	private Node head;

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node tmp = head;
			@Override
			public boolean hasNext() {
				return tmp != null;
			}

			@Override
			public T next() {
				T res = tmp.val;
				tmp = tmp.next;
				return res;
			}
		};
	}

	private class Node{
		T val;
		Node next;
		public Node(T val,Node next){
			this.val=val;
			this.next=next;
		}
	}
	
	public Stack(){
		this.size = 0;
	}
	/**
	 * push the specified element into satck
	 * @param val
	 * @return if be added successfully ,return true,else return false; 
	 */
	public Stack<T> push(T val){
		head=new Node(val,head);
		size++;
		return this;
	}
	/**
	 * pop the element of satck's top and delete it
	 * @return element
	 */
	public T pop(){
		if(isEmpty()){
			return null;
		}
		T item=head.val;
		head=head.next;
		size--;
		return item;
	}
	/**
	 * obtain the frist element of satck's top but not delete it
	 * @return element
	 */
	public T top(){
		if(isEmpty()){
			return null;
		}
		return head.val;
	}
	/**
	 * @return return true if satck is empy
	 */
	public boolean isEmpty(){
		return size==0;
	}
	/**
	 * @return return the num of element
	 */
	public int size(){
		return size;
	}
	/**
	 * clear the stack to empty
	 */
	public void clear(){
		this.size = 0;
		this.head = null;
	}
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("[");
		for(Node tmp = head;tmp!=null;tmp = tmp.next){
			res.append(tmp.val+",");
			System.out.println("==="+tmp.val);
		}
		if(res.length()!=1)
			res.delete(res.length()-1, res.length());
		return res.append("]").toString();
		
	}
}
