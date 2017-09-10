package dtdyq.alger.graph.dutil;

import dtdyq.alger.graph.DGraph;
import dtdyq.alger.util.Stack;

/**
 * Created by Admin on 2017/9/6.
 */
public class DCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    public DCycle(DGraph dg){
        onStack = new boolean[dg.V()];
        marked = new boolean[dg.V()];
        edgeTo = new int[dg.V()];
        for(int v = 0;v<dg.V();v++){
            if(!marked[v])    dfs(dg,v);
        }
    }

    private void dfs(DGraph dg, int v) {
        marked[v] = true;
        onStack[v] = true;
        for(int w:dg.adj(v)){
            if(hasCycle())  return;
            else if(!marked[w]){
                dfs(dg,w);edgeTo[w] = v;
            }
            else if(onStack[w]){
                cycle = new Stack<>();
                for(int x = v;x != w;x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w).push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }
}
