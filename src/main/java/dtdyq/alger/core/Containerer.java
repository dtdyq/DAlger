package dtdyq.alger.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import dtdyq.alger.array.List;

public class Containerer {
	public static <T> void union(Container<T> c1,Container<T> c2){
		unionTo(c1,c2,c1);
	}
	public static <T>  void unionTo(Container<T> c1, Container<T> c2, Container<T> dest) {
		c2.forEach(val->{
			if(!c1.contain(val)){
				dest.add(val);
			}
		});
		if(c1 == dest){
			return;
		}
		c1.forEach(val->{
			dest.add(val);
		});
	}
	public static <T> void  unique(Container<T> c){
		uniqueTo(c, c);
	}
	public static <T> void uniqueTo(Container<T> src,Container<T> dest){
		Set<T> tmp = new HashSet<>();
		Iterator<T> it = src.iterator();
		while(it.hasNext()){
			tmp.add(it.next());
		}
		if(src == dest){
			src.clear();
		}
		dest.add(tmp);
	}
	public static <T> void intersect(Container<T> c1,Container<T> c2){
		intersectTo(c1, c2,c1);
	}

	public static <T> void intersectTo(Container<T> c1, Container<T> c2, Container<T> dest) {
		List<T> list = new List<>(); 
		Iterator<T> iterator = c2.iterator();
		while(iterator.hasNext()){
			T tmp = iterator.next();
			if(c1.contain(tmp)){
				list.add(tmp);
			}
		}
		if(c1 == dest)
			c1.clear();
		dest.add(list);
	}
}



