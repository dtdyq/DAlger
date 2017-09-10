package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

class BubbleSort {
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		boolean flag=false;
		int N=array.length;
		do{
			flag=false;
			for(int i=0;i<N-1;i++){
				if(Arrayer.compare(array[i], array[i+1], cmp)>0){
					Arrayer.swap(array,i,i+1);
					flag=true;
				}
			}
			N--;
		}while(flag);
	}
	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array, null);
	}
	public static <T extends Comparable<T>> void sort(Array<T>array,Comparator<? super T> cmp){
		int N = array.size();
		boolean flag;
		do{
			flag = false;
			for(int i=0;i<N-1;i++){
				if(Arrayer.compare(array.get(i), array.get(i+1), cmp)>0){
					Arrayer.swap(array,i,i+1);
					flag = true;
				}
			} 
			N--;
		}while(flag);
	}
}
