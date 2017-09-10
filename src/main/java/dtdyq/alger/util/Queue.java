package dtdyq.alger.util;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
	private int size;
	private Node head,tail;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node tmp = head;
            @Override
            public boolean hasNext() {
                return tmp!=null;
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
	/**
	 * create a queue with default name
	 */
	public Queue(){
		size = 0;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	/**
	 * add a element to the rear of queue
	 * @param item
	 */
	public void enQueue(T item){
		Node tmp = new Node(item, null);
		if(isEmpty()){
			head = tail = tmp;
		}else{
			tail.next = tmp;
			tail = tail.next;
		}
		size++;
	}
	/**
	 * delete element form the head of queue and return
	 * @return item
	 */
	public T deQueue(){
		if(isEmpty()){
			return null;
		}
		Node res = head;
		head = head.next;
		size--;
		if(isEmpty()){
			tail = null;
		}
		return res.val;
	}
	/**
	 * get the size of queue
	 * @return
	 */
	public int size(){
		return this.size;
	}
	/**
	 * peek the head element of queue but not delete it
	 */
	public T head(){
		if(isEmpty()){
			return null;
		}
		return head.val;
	}
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("[");
		for(Node tmp = head;tmp!=null;tmp = tmp.next){
			res.append(tmp.val+",");
		}
		if(res.length()!=1)
			res.delete(res.length()-1, res.length());
		return res.append("]").toString();
	}
}
