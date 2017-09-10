package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

class InsertSort {
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		int N = array.length;
		for(int i=1;i<N;i++){
			for(int j=i;j>0;j--){
				if(Arrayer.compare(array[j], array[j-1], cmp)>0)   break;
				Arrayer.swap(array, j, j-1);
			}
		}
	}
	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(Array<T> array,Comparator<? super T> cmp){
		int N = array.size();
		for(int i=1;i<N;i++){
			for(int j=i;j>0;j--){
				if(Arrayer.compare(array.get(j), array.get(j-1), cmp)>0)   break;
				Arrayer.swap(array, j, j-1);
			}
		}
	}
	public static void main(String[] args){
		String[] string = {"iue","wcibr","qicbe"};
		sort(string);
		for(String str: string){
			System.out.println(str);
		}
		Integer[] is = {4,7,3,2,7,4,0,8,9};
		sort(is);
		for(Integer str: is){
			System.out.println(str);
		}
	}
}
