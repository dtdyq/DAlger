package dtdyq.alger.array;

import java.util.Comparator;

import dtdyq.alger.core.Array;

class QuickSort {
	public static <T extends Comparable<T>> void sort(T[] array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(T[] array,Comparator<? super T> cmp){
		int low = 0,hi = array.length-1;
		quickSort(array,low,hi,cmp);
		
	}
	private static <T extends Comparable<T>> void quickSort(T[] array, int low, int hi, Comparator<? super T> cmp) {
		if(low>=hi)	return;
		int i=pick(array,low,hi,cmp);
		quickSort(array,low,i-1,cmp);
		quickSort(array,i+1,hi,cmp);
	}
	private static <T extends Comparable<T>> int pick(T[] array, int low, int hi, Comparator<? super T> cmp) {
		T tmp= array[low];
		int i=low,j=hi+1;
		while(true){
			while(Arrayer.compare(array[++i], tmp, cmp)<0)
				if(i == hi) break;
			while(Arrayer.compare(array[--j], tmp, cmp)>0)
				if(j == low) break;
			if(i>=j)    break;
		    Arrayer.swap(array,i,j);
		}
		Arrayer.swap(array, low, j);
		return j;
	}
	public static <T extends Comparable<T>> void sort(Array<T> array){
		sort(array,null);
	}
	public static <T extends Comparable<T>> void sort(Array<T> array,Comparator<? super T> cmp){
		quickSort(array, 0, array.size()-1, cmp);
	}
	private static <T extends Comparable<T>> void quickSort(Array<T> array, int low, int hi, Comparator<? super T> cmp) {
		if(hi<=low) return;
		int lt = low,i = low +1,gt = hi;
		T tmp = array.get(low);
		while(i<=gt){
			int cp = Arrayer.compare(tmp, array.get(i), cmp);
			if(cp < 0)   Arrayer.swap(array, i, gt--);
			if(cp > 0)   Arrayer.swap(array, i++, lt++);
			else i++;
		}
		quickSort(array, low, lt-1, cmp);
		quickSort(array, lt+1, hi, cmp);		
	}
	public static void main(String[] args){
		String[] strings = {"xwr","worcbn"};
		sort(strings);
		for(String str : strings){
			System.out.println(str);
		}
		Integer[] ints = {7};
		sort(ints);
		for(int i : ints){
			System.out.println(i);
		}
		System.out.println("===========================");
		Array<String> array = new List<>();
		array.add("oieyt");
		array.add("icrr");
		array.add("mrer");
		sort(array);
		array.forEach(System.out::println);
	}
}
