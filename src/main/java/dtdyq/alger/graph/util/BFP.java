package dtdyq.alger.graph.util;

import dtdyq.alger.graph.Graph;
import dtdyq.alger.util.Queue;
import dtdyq.alger.util.Stack;

/**
 * Created by Admin on 2017/9/3.
 */
public class BFP {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public BFP(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        marked[s] = true;
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(s);
        while(!queue.isEmpty()){
            int v = queue.deQueue();
            for(int w:G.adj(v)){
                if(!marked[v]){
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }
    public boolean isConnect(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        Stack<Integer> stack = new Stack<>();
        for(int w = v;w != s;w = edgeTo[w]){
            stack.push(w);
        }
        return stack.push(s);
    }
}












