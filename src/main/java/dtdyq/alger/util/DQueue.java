package dtdyq.alger.util;

public class DQueue<Item> {
	private class Node{
		Item item;
		Node last;
		Node next;
		public Node(Item item,Node last, Node next){
			this.item=item;
			this.last=last;
			this.next=next;
		}
		public String toString(){
			return item+"";
		}
	}
	private Node head,tail;
	private int N;				//length of DeQueue
	/**
	 * initialize a DeQueue with default name;
	 */
	public DQueue(){
	}
	public void clear(){
		this.head = null;
		this.tail = null;
		this.N = 0;
	}
	/**
	 * get the size of DeQueue
	 */
	public int size(){
		return N;
	}
	/**
	 * add the specified element to head of DeQueue
	 * @param item
	 */
	public void pushFront(Item item){
		N++;
		if(head==null){
			head=new Node(item,null,null);
			tail=head;
			return;
		}
		head=new Node(item,null,head);
		head.next.last=head;
	}
	/**
	 * add the specified element to tail of DeQeue
	 * @param item
	 */
	public void pushBack(Item item){
		N++;
		if(tail==null){
			tail=new Node(item,null,null);
			head=tail;
			return;
		}
		tail=new Node(item,tail,null);
		tail.last.next=tail;
	}
	/**
	 * delete a element from the head of DeQueue
	 */
	public Item popFront(){
		if(head==null){
			return null;
		}
		Item item=head.item;
		head=head.next;
		if(head==null){
			tail=null;
		}
		else{
			head.last=null;
		}
		N--;
		return item;
	}
	/**
	 * delete a element from the tail of DeQueue
	 */
	public Item popBack(){
		if(tail==null){
			return null;
		}
		Item item=tail.item;
		tail=tail.last;
		if(tail==null){
			head=null;
		}
		else{
			tail.next=null;
		}
		N--;
		return item;
	}
	/**
	 * get the head element but donnot delete it
	 */
	public Item front(){
		if(head==null){
			return null;
		}
		return head.item;
	}
	/**
	 * get the tail element but donnot delete it
	 */
	public Item back(){
		if(tail==null){
			return null;
		}
		return tail.item;
	}
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("[");
		for(Node tmp = head;tmp!=null;tmp = tmp.next){
			res.append(tmp.item+",");
		}
		if(res.length()!=1)
			res.delete(res.length()-1, res.length());
		return res.append("]").toString();
	}
}






