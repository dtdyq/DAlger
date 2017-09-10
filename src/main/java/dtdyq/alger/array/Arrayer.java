package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

public class Arrayer {
	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array,null,null);
	}
	public static <T extends Comparable<T>> void sort(Array<T> array,Comparator<? super T> cmp){
		sort(array,cmp,null);
	}

	public static <T extends Comparable<T>> void sort(Array<T> array,SortType type){
		sort(array,null,type);
	}
	public static <T extends Comparable<T>> void sort(Array<T> array,Comparator<? super T> cmp,SortType type){
		switch(type){
		case SELECT:SelectSort.sort(array, cmp);
		case INSERT:InsertSort.sort(array,cmp);
		case SHELL:ShellSort.sort(array,cmp);
		case QUICK:QuickSort.sort(array,cmp);
		case HEAP:HeapSort.sort(array,cmp);
		case BUBBLE:BubbleSort.sort(array,cmp);
		case MERGE:
		default:MergeSort.sort(array,cmp);
		}
	}
	
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		sort(array,cmp,null);
	}

	public static <T extends Comparable<T>> void sort(T[] array,SortType type){
		sort(array,null,type);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp,SortType type){
		switch(type){
		case SELECT:SelectSort.sort(array, cmp);
		case INSERT:InsertSort.sort(array,cmp);
		case SHELL:ShellSort.sort(array,cmp);
		case QUICK:QuickSort.sort(array,cmp);
		case HEAP:HeapSort.sort(array,cmp);
		case BUBBLE:BubbleSort.sort(array,cmp);
		case MERGE:
		default:MergeSort.sort(array,cmp);
		}
	}
	public static <T extends Comparable<T>> void swap(Array<T> array,int i,int j){
		T tmp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, tmp);
	}
	public static <T extends Comparable<T>> void swap(T[] array,int i,int j){
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	public static <T extends Comparable<T>> int compare(T ele1,T ele2,Comparator<? super T> cmp){
		if(cmp == null){
			return ele1.compareTo(ele2);
		}else{
			return cmp.compare(ele1,ele2);
		}
	}
}








