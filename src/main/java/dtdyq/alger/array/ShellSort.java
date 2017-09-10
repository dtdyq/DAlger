package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

class ShellSort {
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		int N = array.length;
		int h = 1;
		while(h<N/3)    h = h*3+1;
		while(h>0){
			for(int i = h;i<N;i++){
				for(int j = i; j>=h && Arrayer.compare(array[j], array[j-h], cmp)<0; j-=h){
					Arrayer.swap(array, j, j-h);
				}
			}
			h = h/3;
		}
	}
	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(Array<T> array,Comparator<? super T> cmp){
		int N = array.size();
		int h = 1;
		while(h<N/3)    h = h*3+1;
		while(h>0){
			for(int i = h;i<N;i++){
				for(int j = i; j>=h && Arrayer.compare(array.get(j), array.get(j-h), cmp)<0; j-=h){
					Arrayer.swap(array, j, j-h);
				}
			}
			h = h/3;
		}
	}
}
