package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

class HeapSort {
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		int N = array.length;
		for(int i = N/2;i>=1;i--){
			sink(array,i,N);
		}
		while(N > 1){
			Arrayer.swap(array,1,N--);
			sink(array,1,N);
		}
		//bad!!!
		int index = 0;
		while(Arrayer.compare(array[0],array[++index],null)>0);
		T tmp = array[0];
		for(int i = 0;i<index-1;i++){
			array[i] = array[i+1];
		}
		array[index-1] = tmp;
	}

	private static <T extends Comparable<T>> void sink(T[] array, int k, int N) {
		while(k*2 <= N) {
			int j = k * 2;
			if (j < N && Arrayer.compare(array[j], array[j + 1], null) < 0) j++;
			if (Arrayer.compare(array[k], array[j], null) > 0) break;
			Arrayer.swap(array, k, j);
			k = j;
		}
	}

	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(Array<T> array,Comparator<? super T> cmp){
		int N = array.size();
		for(int i = N/2;i>=1;i--){
			sink2(array,i,N);
		}
		while(N > 1){
			Arrayer.swap(array,1,N--);
			sink2(array,1,N);
		}
		int index = 0;
		while(Arrayer.compare(array.get(0),array.get(++index),null)>0);
		T tmp = array.get(0);
		for(int i = 0;i<index-1;i++){
			array.set(i,array.get(i+1));
		}
		array.set(index-1,tmp);
	}

	private static <T extends Comparable<T>> void sink2(Array<T> array, int k, int N) {
		while(k*2 <= N) {
			int j = k * 2;
			if (j < N && Arrayer.compare(array.get(j), array.get(j+1), null) < 0) j++;
			if (Arrayer.compare(array.get(k), array.get(k+1), null) > 0) break;
			Arrayer.swap(array, k, j);
			k = j;
		}
	}
}
