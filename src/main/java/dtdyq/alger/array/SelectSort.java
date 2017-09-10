package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

class SelectSort {
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		int N = array.length;
		while(N>1){
			int index = 0;
			for(int i=1;i<N;i++){
				if(Arrayer.compare(array[i], array[index], cmp)>0){
					index = i;
				}
			}
			Arrayer.swap(array, index, --N);
		}
	}
	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array,null);
	}
	
	public static <T extends Comparable<T>> void sort(Array<T> array, Comparator<? super T> cmp) {
		int N = array.size();
		while(N>1){
			int index = 0;	
			for(int i=1;i<N;i++){
				if(Arrayer.compare(array.get(i), array.get(index), cmp)>0){
					index = i;
				}
			}
			Arrayer.swap(array, index, --N);
		}
	}

}
