package dtdyq.alger.dict;

import dtdyq.alger.core.Array;
import dtdyq.alger.core.Dict;
import dtdyq.alger.core.Set;
import dtdyq.alger.exception.InvalidParamException;
import dtdyq.alger.util.Pair;

import java.util.Iterator;

/**
 * Created by Admin on 2017/9/3.
 */
public class RBDict<K extends Comparable<K>,V> implements Dict<K,V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        K key;
        V val;
        Node left,right;
        int N;
        boolean color;
        Node(K key,V val,Node left,Node right,int N,boolean color){
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
            this.N = N;
            this.color = color;
        }
    }
    private boolean isRed(Node x){
        if(x == null)    return false;
        return x.color == RED;
    }
    private int size(Node node) {
        if(node == null)    return 0;
        return node.N;
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node root;
    @Override
    public int size() {
        return size(root);
    }

    @Override
    public V get(K key) {
        if(key == null) throw new InvalidParamException("key is null");
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
        root.color = BLACK;
    }

    private Node put(Node node, K key, V val) {
        if(node == null)    return new Node(key,val,null,null,1,RED);
        int cmp = key.compareTo(node.key);
        if(cmp < 0)    node.left = put(node.left,key,val);
        else if(cmp > 0)    node.right = put(node.right,key,val);
        else node.val = val;
        if(isRed(node.right) && !isRed(node.left))    node = rotateLeft(node);
        if(isRed(node.left) && isRed(node.left.left))    node = rotateRight(node);
        if(isRed(node.left) && isRed(node.right))    flipColor(node);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private void flipColor(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    @Override
    public void put(Dict<K, V> dict) {
        Iterator<Pair<K,V>> it = dict.iterator();
        while(it.hasNext()){
            Pair<K,V> p = it.next();
            put(p.first(),p.second());
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
        if(key.compareTo(node.key) == 0)    return true;
        return hasKey(node.left,key) || hasKey(node.right,key);
    }
    public void delMin(){
        if(!isRed(root.left) && !isRed(root.right))    root.color = RED;
        root = delMin(root);
        if(!isEmpty())    root.color = BLACK;
    }
    private Node delMin(Node node) {
        if(node.left == null)    return null;
        if(!isRed(node.left) && !isRed(node.left.left)) node = moveRedLeft(node);
        node.left = delMin(node.left);
        return balance(node);
    }

    private Node balance(Node node) {
        if(isRed(node.right))    node = rotateLeft(node);
        //TODO

        return null;
    }

    private Node moveRedLeft(Node node) {
        flipColor(node);
        if(isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }


    @Override
    public void delete(K key) {
        //
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return null;
    }

    @Override
    public Array<K> keys() {
        return null;
    }

    @Override
    public Array<V> values() {
        return null;
    }

    @Override
    public Set<Pair<K,V>> items() {
        return null;
    }


}
