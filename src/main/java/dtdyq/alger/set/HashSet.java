package dtdyq.alger.set;

import dtdyq.alger.array.Vector;
import dtdyq.alger.core.Array;
import dtdyq.alger.core.Container;
import dtdyq.alger.core.Set;
import dtdyq.alger.dict.HashDict;
import dtdyq.alger.util.AlgerTool;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Admin on 2017/9/2.
 *
 */
public class HashSet<T> implements Set<T> {
    private static final int INITCAPACITY = 23;
    private static final double LOADFACTOR = 0.9;
    private Vector<T>[] items;
    private int size;
    private int capacity;
    private double loadFactor;
    public HashSet(){
        this(INITCAPACITY);
    }
    public HashSet(int capacity){
        this(capacity,LOADFACTOR);
    }
    public HashSet(double loadFactory){
        this(INITCAPACITY,loadFactory);
    }
    public HashSet(int capacity,double loadFactor){
        this.size = 0;
        this.capacity = capacity;
        this.items = new Vector[capacity];
        for(int i = 0;i < capacity;i++){
            items[i] = new Vector<>();
        }
    }
    @Override
    public boolean any(Predicate p) {
        for(Vector v:items){
            if(!v.isEmpty() && v.any(p)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean all(Predicate p) {
        return !any(p);
    }

    @Override
    public void removeIf(Predicate p) {
        for(Vector v:items){
            if(!v.isEmpty()){
                int tmp = v.size();
                v.removeIf(p);
                this.size -= (tmp - v.size());
            }
        }
    }

    @Override
    public <E> Set<E> map(Function<? super T, ? extends E> mapper) {
         Set<E> res = new HashSet<>();
         for(Vector v:items){
             Array<E> tmp = v.map(mapper);
             tmp.forEach(ele->res.add(ele));
         }
        return res;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean contain(T key) {
        for(Vector<T> v:items){
            if(v.contain(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        //TODO
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.capacity = INITCAPACITY;
        this.items = new Vector[capacity];
        for(int i = 0;i < capacity;i++){
            items[i] = new Vector<>();
        }
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T value) {
         resize();
         if(contain(value))    return;
         items[hash(value)].add(value);
         size++;
    }
    private void resize() {
        if((double)size/(double)capacity > loadFactor){
            HashSet<T> tmp = new HashSet<>(nextPrime(capacity));
            for(Vector<T> v:items){
                v.forEach(ele->tmp.add(ele));
            }
            this.capacity = tmp.capacity;
            this.items = tmp.items;
        }
    }

    @Override
    public void add(Container<T> con) {
        Iterator<T> it = con.iterator();
        while(it.hasNext()){
            T tmp = it.next();
            if(!contain(tmp)){
                add(tmp);
            }
        }
    }

    @Override
    public void add(Collection<T> col) {
        Iterator<T> it = col.iterator();
        while(it.hasNext()){
            T tmp = it.next();
            if(!contain(tmp)){
                add(tmp);
            }
        }
    }

    @Override
    public void remove(T value) {
        for(Vector<T> v:items){
            if(v.contain(value)){
                v.remove(value);
                size--;
            }
        }
    }
    private int hash(T key){
        return key.hashCode()>>4%capacity;
    }
    private int nextPrime(int prime){
        for(int tmp = prime*2; AlgerTool.isPrime(tmp); tmp++){
            return tmp;
        }
        return prime*2;
    }
}
