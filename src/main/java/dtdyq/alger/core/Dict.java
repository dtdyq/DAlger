package dtdyq.alger.core;

import java.util.Iterator;

import dtdyq.alger.util.Pair;

public interface Dict<K,V>{
	int size();
	V get(K key);
	void put(K key,V val);
	void put(Dict<K, V> dict);
	boolean isEmpty();
	boolean hasKey(K key);
	void delete(K key);
	Iterator<Pair<K, V>> iterator();
	Array<K> keys();
	Array<V> values();
	Set<Pair<K, V>> items();
}
