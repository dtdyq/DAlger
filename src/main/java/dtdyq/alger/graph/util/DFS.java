package dtdyq.alger.graph.util;

import dtdyq.alger.graph.Graph;

/**
 * Created by Admin on 2017/9/3.
 */
public class DFS {

    private boolean[] marked;
    private int count;
    public DFS(Graph G, int v){
        marked = new boolean[G.V()];
        this.count = 0;
        dfs(G,v);
    }
    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for(int w:G.adj(v)){
            if(!marked[w])    dfs(G,w);
        }
    }
    public boolean marked(int v){
        return marked[v];
    }
    public int count(){
        return count;
    }
}
