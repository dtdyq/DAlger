package dtdyq.alger.dict;

import java.util.Iterator;

import dtdyq.alger.array.List;
import dtdyq.alger.core.Array;
import dtdyq.alger.core.Dict;
import dtdyq.alger.core.Set;
import dtdyq.alger.exception.InvalidParamException;
import dtdyq.alger.set.HashSet;
import dtdyq.alger.util.AlgerTool;
import dtdyq.alger.util.Pair;

public class HashDict<K,V> implements Dict<K, V>{
	private static final int INITCAPACITY = 17;
	private static final double LOADFACTORY = 0.8;
	
	private int capacity;
	private int size = 0;
	private double loadFactor;
	private K[] keys;
	private V[] vals;
	@SuppressWarnings("unchecked")
	public HashDict() {
		capacity = INITCAPACITY;
		loadFactor = LOADFACTORY;
		keys = (K[])new Object[INITCAPACITY];
		vals = (V[])new Object[INITCAPACITY];
	}
	public HashDict(int capacity){
		if(capacity <= 0){
			throw new InvalidParamException("invalid initial capacity:"+capacity);
		}
		this.capacity = capacity;
		loadFactor = LOADFACTORY;
		keys = (K[])new Object[capacity];
		vals = (V[])new Object[capacity];
	}
	public HashDict(double loadFactor){
		if(loadFactor<=0 || loadFactor>=1){
			throw new InvalidParamException("invalid initial loadFactor:"+loadFactor);
		}
		keys = (K[])new Object[INITCAPACITY];
		vals = (V[])new Object[INITCAPACITY];
		this.loadFactor = loadFactor;
		this.capacity = INITCAPACITY;
	}
	public HashDict(int capacity,double loadFactory){
		if(capacity <= 0){
			throw new InvalidParamException("invalid initial capacity:"+capacity);
		}
		if(loadFactory<=0 || loadFactory>=1){
			throw new InvalidParamException("invalid initial loadFactor:"+loadFactor);
		}
		this.capacity = capacity;
		this.loadFactor = loadFactory;
		keys = (K[])new Object[capacity];
		vals = (V[])new Object[capacity];
	}
	public int size() {
		return size;
	}

	public V get(K key) {
		if(key == null)    return null;
		int index = -1;
		if(keys[index = hash(key)] != null){
			while(!key.equals(keys[index++]));
				return vals[index-1];
		}
		return null;
	}

	public void put(K key, V val) {
		resize();
		if(!AlgerTool.notNull(key)){
			throw new InvalidParamException("key is null");
		}
		int index = -1;
		if(keys[index = hash(key)] == null){
			keys[index] = key;
			vals[index] = val;
		}else{
			while(keys[index] != null){
				index = (index + 1)%capacity;
			}
			keys[index] = key;
			vals[index] = val;
		}
	} 

	private void resize() {
		if((double)size/(double)capacity > loadFactor){
			HashDict<K,V> tmp = new HashDict<>(nextPrime(capacity));
			for(int i = 0;i<capacity;i++){
				if(keys[i] != null){
					tmp.put(keys[i],vals[i]);
				}
			}
			this.capacity = tmp.capacity;
			this.keys = tmp.keys;
			this.vals = tmp.vals;
		}
	}

	public void put(Dict<K, V> dict) {
		Iterator<Pair<K,V>> it = dict.iterator();
		while(it.hasNext()){
			Pair<K,V> tmp = it.next();
			put(tmp.first(),tmp.second());
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean hasKey(K key) {
		if(!AlgerTool.notNull(key)){
			throw new InvalidParamException("key is null");
		}
		for(int i=0;i<capacity;i++){
			if(key.equals(keys[i])){
				return true;
			}
		}
		return false;
	}

	@Override
	public void delete(K key) {
		if(!hasKey(key)) return;
		resize();
		int index = hash(key);
		while(!key.equals(keys[index])){
			index = (index + 1)%capacity;
		}
		keys[index] = null;
		vals[index] = null;
		index = (index + 1)%capacity;
		while(keys[index] != null){
			K redoKey = keys[index];
			V redoVal = vals[index];
			keys[index] = null;
			vals[index] = null;
			size--;
			put(redoKey,redoVal);
			index = (index + 1)%capacity;
		}
		size--;
	}

	public Iterator<Pair<K, V>> iterator() {
		return new Iterator<Pair<K, V>>() {
			int cnt = 0;
			int index = 0;
			@Override
			public boolean hasNext() {
				return cnt <= size;
			}

			@Override
			public Pair<K, V> next() {
				cnt++;
				while(keys[index++] == null);
				return new Pair<>(keys[index-1],vals[index-1]);
			}
		};
	}

	public Array<K> keys() {
		Array<K>  res = new List<>();
		for(int i = 0;i < capacity;i++){
			if(AlgerTool.notNull(keys[i])){
				res.add(keys[i]);
			}
		}
		return res;
	}

	public Array<V> values() {
		Array<V>  res = new List<>();
		for(int i = 0;i < capacity;i++){
			if(AlgerTool.notNull(vals[i])){
				res.add(vals[i]);
			}
		}
		return res;
	}

	public Set<Pair<K, V>> items() {
		Set<Pair<K,V>> res = new HashSet<Pair<K,V>>();
		for(int i = 0;i < capacity;i++){
			if(AlgerTool.notNull(keys[i])){
				res.add(new Pair<>(keys[i],vals[i]));
			}
		}
		return res;
	}
	private int hash(K key){
		return key.hashCode()>>4%capacity;
	}
	private int nextPrime(int prime){
		for(int tmp = prime*2;AlgerTool.isPrime(tmp);tmp++){
			return tmp;
		}
		return prime*2;
	}
}
