package dtdyq.alger.graph.dutil;

import dtdyq.alger.graph.DGraph;

/**
 * Created by Admin on 2017/9/6.
 */
public class DDFS {
    private boolean[] marked;
    private int count = 0;
    public DDFS(DGraph dg,int v){
        marked = new boolean[dg.V()];
        dfs(dg,v);
    }
    public DDFS(DGraph dg,Iterable<Integer> it){
        marked = new boolean[dg.V()];
        count++;
        for(int v:it){
            dfs(dg,v);
        }
    }
    private void dfs(DGraph dg, int v) {
        marked[v] = true;
        for(int w:dg.adj(v)){
            if(!marked[w]){
                dfs(dg,w);

            }
        }
    }
    public int count(){
        return count;
    }
    public boolean marked(int v){
        return marked[v];
    }

}
