package dtdyq.alger.graph.dutil;

import dtdyq.alger.graph.DGraph;
import dtdyq.alger.util.Queue;
import dtdyq.alger.util.Stack;

/**
 * Created by Admin on 2017/9/7.
 * 有向图的前序、后序和逆后序
 */
public class DFO {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    public DFO(DGraph dg){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[dg.V()];
        for(int v = 0;v < dg.V();v++){
            if(!marked[v])  dfs(dg,v);
        }
    }
    private void dfs(DGraph dg,int v){
        pre.enQueue(v);
        marked[v] = true;
        for(int w:dg.adj(v)){
            if(!marked[w])  dfs(dg,w);
        }
        post.enQueue(v);
        reversePost.push(v);
    }
    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }
    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
