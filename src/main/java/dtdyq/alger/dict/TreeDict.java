package dtdyq.alger.dict;

import dtdyq.alger.array.Arrayer;
import dtdyq.alger.array.Vector;
import dtdyq.alger.core.Array;
import dtdyq.alger.core.Dict;
import dtdyq.alger.core.Set;
import dtdyq.alger.set.HashSet;
import dtdyq.alger.util.AlgerTool;
import dtdyq.alger.util.Pair;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by Admin on 2017/9/2.
 */
public class TreeDict<K extends Comparable<K>,V> implements Dict<K,V>{
    private final class Node{
        K key;
        V val;
        Node left;
        Node right;
        int N;
        public Node(K key,V val,Node left,Node right,int N){
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
            this.N = N;
        }
    }

    private Node root;
    public TreeDict(){
        root = null;
    }
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if(node == null)    return 0;
        return node.N;
    }

    @Override
    public V get(K key) {
        if(key == null){
            return null;
        }
        return get(root,key);
    }

    private V get(Node node, K key) {
        if(node == null)    return null;
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return get(node.left,key);
        }else if(cmp > 0){
            return get(node.right,key);
        }else{
            return node.val;
        }
    }

    @Override
    public void put(K key, V val) {
        root = put(root,key,val);
    }

    private Node put(Node node, K key, V val) {
        if(node == null)    return new Node(key,val,null,null,1);
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.left = put(node.left,key,val);
        }else if(cmp > 0){
            node.right = put(node.right,key,val);
        }else{
            node.val = val;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void put(Dict<K, V> dict) {
        if(!AlgerTool.notNull(dict))   return;
        Iterator<Pair<K,V>> it = dict.iterator();
        while(it.hasNext()){
            Pair<K,V> tmp = it.next();
            put(tmp.first(),tmp.second());
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean hasKey(K key) {
        return hasKey(root,key);
    }

    private boolean hasKey(Node node, K key) {
        if(node == null)    return false;
        if(key.compareTo(node.key) == 0){
            return true;
        }else{
             return hasKey(node.left,key) || hasKey(node.right,key);
        }
    }
    public Pair<K,V> min(){
        if(isEmpty())    return null;
        Node tmp = min(root);
        return new Pair<>(tmp.key,tmp.val);
    }

    private Node min(Node node) {
        if(node.left == null)    return node;
        return min(node.left);
    }
    public void deleteMin(){
        if(isEmpty())    return;
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if(node.left == null)    return node.right;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void delete(K key) {
        root = delete(root,key);
    }

    private Node delete(Node node, K key) {
        if(node == null)    return null;
        int cmp = key.compareTo(node.key);
        if(cmp<0){
            node.left = delete(node.left,key);
        }else if(cmp>0){
            node.right = delete(node.right,key);
        }else{
            if(node.left == null)    return node.right;
            if(node.right == null)    return node.left;
            Node t = node;
            node = min(node.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Pair<K,V> max(){
        if(isEmpty())    return null;
        Node tmp = max(root);
        return new Pair(tmp.key,tmp.val);
    }

    private Node max(Node node) {
        if(node.right == null)    return node;
        return max(node.right);
    }
    public void deleteMax(){
        if(isEmpty())    return;
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if(node.right == null)    return node.left;
        node.right = deleteMax(node.right);
        node.N = size(node.right) + size(node.left) + 1;
        return node;
    }

    public Pair<K,V> select(int index){
        if(index > size())    return null;
        Node tmp = select(root,index);
        return new Pair<>(tmp.key,tmp.val);
    }

    private Node select(Node node, int index) {
        if(node == null)    return null;
        int t = size(node.left);
        if(t < index){
            return select(node.right,index-t-1);
        }else if(t > index){
            return select(node.left,index);
        }else{
            return node;
        }
    }
    public int index(K key){
        if(!hasKey(key))    return -1;
        return index(root,key);
    }

    private int index(Node node, K key) {
        if(node == null)    return   0;
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return index(node.left,key);
        }else if(cmp > 0){
            return index(node.right,key) + size(node.left) + 1;
        }else{
            return size(node.left);
        }

    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        if(isEmpty())    return null;
        return iterator(min().first(),max().first());
    }
    public Iterator<Pair<K,V>> iterator(K min,K max){
        if(min == null)    min = min().first();
        if(max == null)    max = max().first();
        return iterables(min,max).iterator();
    }

    private Iterable<Pair<K,V>> iterables(K min, K max) {
        Array<Pair<K,V>> array = new Vector<>();
        iterables(root,min,max,array);
        return array;
    }

    private void iterables(Node node, K min, K max, Array<Pair<K, V>> array) {
        if(node == null)    return;
        int cmpl = min.compareTo(node.key);
        int cmph = max.compareTo(node.key);
        if(cmpl < 0)    iterables(node.left,min,max,array);
        if(cmph > 0)    iterables(node.right,min,max,array);
        if(cmpl <= 0 && cmph >= 0)    array.add(new Pair<>(node.key,node.val));
    }

    @Override
    public Array<K> keys() {
        Array<K> res = new Vector<>();
        keys(root,res);
        return res;
    }

    private void keys(Node node, Array<K> array) {
        if(node == null)    return;
        array.add(node.key);
        keys(node.left,array);
        keys(node.right,array);
    }

    @Override
    public Array<V> values() {
        Array<V> res = new Vector<>();
        values(root,res);
        return res;
    }

    private void values(Node node, Array<V> array) {
        if(node == null)    return;
        array.add(node.val);
        values(node.left,array);
        values(node.right,array);
    }

    @Override
    public Set<Pair<K, V>> items() {
        Set<Pair<K,V>> res = new HashSet<>();
        items(root,res);
        return res;
    }

    private void items(Node node, Set<Pair<K, V>> set) {
        if(node == null)    return;
        set.add(new Pair<>(node.key,node.val));
        items(node.left,set);
        items(node.right,set);
    }
}
