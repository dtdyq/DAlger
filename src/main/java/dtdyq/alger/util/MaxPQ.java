package dtdyq.alger.util;

import dtdyq.alger.array.Arrayer;
import dtdyq.alger.array.List;
import dtdyq.alger.array.Vector;
import dtdyq.alger.core.Array;
import dtdyq.alger.exception.InvalidParamException;

/**
 * Created by Admin on 2017/9/2.
 */
public class MaxPQ<E extends Comparable<E>>{
    private E[] ele;
    private int size = 0;
    private int N = 16;
    public MaxPQ(){
        ele = (E[])new Object[N];
    }
    public MaxPQ(int capacity) {
        if(capacity <= 0){
            throw new InvalidParamException("invalid initial capacity");
        }
        N = capacity;
        ele = (E[])new Object[N];
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void add(E e){
        resize();
        ele[++size] = e;
        swim(size);
    }

    public E max(){
        return size == 0?null:ele[1];
    }
    public Array<E> max(int num){
        Array<E> res = new Vector<>();
        for(int i = 0;i < num;i++){
            res.add(delMax());
        }
        res.forEach(item->add(item));
        return res;
    }
    public E delMax(){
        if(isEmpty()){
            return null;
        }
        E res = ele[1];
        ele[1] = ele[size];
        ele[size--] = null;
        sink(1);
        return res;
    }
    public Array<E> delMax(int num){
        Array<E> res = new List<>();
        for(int i = 0;i < num;i++){
            res.add(delMax());
        }
        return res;
    }

    private void swim(int size){
        int tmp = size;
        while(tmp >1 && Arrayer.compare(ele[tmp],ele[tmp/2],null) > 0){
            Arrayer.swap(ele,tmp,tmp/2);
            tmp = tmp/2;
        }
    }
    private void resize() {
        if(size < N/2 && N > 16){
            N = N/2;
            E[] tmp = ele;
            ele = (E[])new Object[N];
            for(int i=0;i<=size;i++){
                ele[i] = tmp[i];
            }
        }
        if(size > N/2){
            N = N*2;
            E[] tmp = ele;
            ele = (E[])new Object[N];
            for(int i=0;i<=size;i++){
                ele[i] = tmp[i];
            }
        }
    }
    private void sink(int k) {
        while(k*2 <= size){
            int j = k*2;
            if(j<size && Arrayer.compare(ele[j],ele[j+1],null)<0)    j++;
            if(Arrayer.compare(ele[k],ele[j],null)>0)    break;
            Arrayer.swap(ele,k,j);
            k = j;
        }
    }
}







