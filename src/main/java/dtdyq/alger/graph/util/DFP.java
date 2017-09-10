package dtdyq.alger.graph.util;

import dtdyq.alger.graph.Graph;
import dtdyq.alger.util.Stack;

/**
 * Created by Admin on 2017/9/3.
 */
public class DFP {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public DFP(Graph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfp(G,s);
    }
    private void dfp(Graph G,int s){
        marked[s] = true;
        for(int w:G.adj(s)){
            edgeTo[w] = s;
            dfp(G,s);
        }
    }
    public boolean isConnect(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if(!isConnect(v))    return null;
        Stack<Integer> path = new Stack<>();
        for(int w = v;w !=s ;w = edgeTo[w]){
            path.push(w);
        }
        return path.push(s);
    }
}











