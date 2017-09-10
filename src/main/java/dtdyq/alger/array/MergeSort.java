package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

class MergeSort {
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		@SuppressWarnings("unchecked")
		T[] tmp = (T[]) new Comparable[array.length];
		mergeSort(array,0,array.length-1,cmp,tmp);
	}
	private static <T extends Comparable<T>> void mergeSort(T[] array, int low, int hi, Comparator<? super T> cmp,T[] tmp) {
		if(low>=hi) return;
		int mid = (low+hi)/2;
		mergeSort(array, low, mid,cmp,tmp);
		mergeSort(array, mid+1, hi, cmp,tmp);
		merge(array,low,mid, hi,cmp,tmp);
	}
	private static <T extends Comparable<T>> void merge(T[] array, int low, int mid, int hi, Comparator<? super T> cmp,T[] tmp) {
		for(int i = low;i<=hi;++i){
			tmp[i] = array[i];
		}
		int i = low,j = mid+1;
		for(int k = low;k<=hi;k++){
			if(i>mid) array[k] = tmp[j++];
			else if(j>hi) array[k] = tmp[i++];
			else if(Arrayer.compare(array[j], array[i], cmp)<0) array[k] = tmp[j++];
			else array[k] = tmp[i++];
		}
	}
	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array,null);
	}
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> void sort(Array<T> array,Comparator<? super T> cmp){
		int N = array.size();
		T[] tmp = (T[]) new Comparable[N];
		for(int sz = 1;sz<N;sz = sz*2){
			for(int low=0;low<N-sz;low+=sz+sz){
				merge(array, low, low+sz-1,Math.min(low+sz+sz-1, N-1), cmp, tmp);
			}
		}
	}
	private static <T extends Comparable<T>> void merge(Array<T> array, int low, int mid, int hi, Comparator<? super T> cmp, T[] tmp) {
		for(int i = low;i<=hi;++i){
			tmp[i] = array.get(i);
		}
		int i = low,j = mid+1;
		for(int k = low;k<=hi;k++){
			if(i>mid) array.set(k,tmp[j++]);
			else if(j>hi) array.set(k,tmp[i++]);
			else if(Arrayer.compare(array.get(j), array.get(i), cmp)<0) array.set(k, tmp[j++]);
			else array.set(k,tmp[i++]);
		}
	}
	public static void main(String[] args){
		String[] string = {"iue","wcibr","qicbe"};
		sort(string);
		for(String str: string){
			System.out.println(str);
		}
		Array<String> array = new Vector<>(string);
		sort(array);
		array.forEach(System.out::println);
	}
}
