package dtdyq.alger.set;

import dtdyq.alger.core.Container;
import dtdyq.alger.core.Set;
import dtdyq.alger.exception.InvalidParamException;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Admin on 2017/9/2.
 *
 */
public class TreeSet<T extends Comparable<T>> implements Set<T> {

    private final class Node{
        T val;
        Node left;
        Node right;
        int N;
        public Node(T val,Node left,Node right,int N){
            this.val = val;
            this.left = left;
            this.right = right;
            this.N = N;
        }
    }
    private Node root;

    @Override
    public boolean any(Predicate p) {
        return any(root,p);
    }
    private boolean any(Node node, Predicate p) {
        if(node == null)    return false;
        if(p.test(node.val))    return true;
        return any(node.left,p) || any(node.right,p);
    }

    @Override
    public boolean all(Predicate p) {
        return !any(p);
    }

    @Override
    public void removeIf(Predicate p) {
        removeIf(root,p);
    }

    private void removeIf(Node node, Predicate p) {
        if(node == null)    return;
        if(p.test(node.val))    remove(node.val);
        removeIf(node.left,p);
        removeIf(node.right,p);
    }
    @Override
    public <E> Set<E> map(Function<? super T, ? extends E> mapper) {
        Set<E> res = new HashSet<>();
        map(root,res,mapper);
        return res;
    }

    private <E> void map(Node node, Set<E> set,Function<? super T, ? extends E> mapper) {
        if(node == null)    return;
        set.add(mapper.apply(node.val));
        map(node.left,set,mapper);
        map(node.right,set,mapper);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if(node == null)
            return 0;
        return node.N;
    }

    @Override
    public boolean contain(T key) {
        if(key == null)    throw new InvalidParamException("param is null");
        return contain(root,key);
    }

    private boolean contain(Node node, T key) {
        if(node == null)    return false;
        if(key.equals(node.val))    return true;
        return contain(node.left,key) || contain(node.right,key);
    }

    @Override
    public Iterator iterator() {
        //TODO
        return null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void add(T value) {
        if(contain(value))    return;
        root = add(root,value);
    }

    @Override
    public void remove(T value) {
        if(!contain(value))    return;
        root = remove(root,value);
    }

    private Node remove(Node node, T value) {
        if(node == null)    return null;
        int cmp = value.compareTo(node.val);
        if(cmp < 0)    node.left = remove(node.left,value);
        else if(cmp > 0)    node.right = remove(node.right,value);
        else{
            if(node.left == null)    return node.right;
            if(node.right == null)    return node.left;
            Node tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.N = size(node.right) + size(node.left) + 1;
        return node;
    }
    public T min(){
        if(isEmpty())    return null;
        Node tmp = min(root);
        return tmp.val;
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
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }
    public T max(){
        if(isEmpty())    return null;
        Node tmp = max(root);
        return tmp.val;
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
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node add(Node node, T value) {
        if(node == null)    return new Node(value,null,null,1);
        int cmp = value.compareTo(node.val);
        if(cmp < 0)    node.left = add(node.left,value);
        else if(cmp > 0)    node.right = add(node.right,value);
        else node.val = value;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void add(Container<T> con) {
        for(T t:con){
            if(!contain(t)){
                add(t);
            }
        }
    }

    @Override
    public void add(Collection<T> col) {
        for(T t:col){
            if(!contain(t)){
                add(t);
            }
        }
    }

}
