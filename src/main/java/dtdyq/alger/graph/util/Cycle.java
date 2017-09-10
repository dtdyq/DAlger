package dtdyq.alger.graph.util;

import dtdyq.alger.graph.Graph;

/**
 * Created by Admin on 2017/9/3.
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(Graph G){
        marked = new boolean[G.V()];
        int v = G.V();
        for(int i = 0;i < v;i++){
            if(!marked[i]){
                dfs(G,i,i);
            }
        }
    }

    private void dfs(Graph G, int s, int v) {
        marked[s] = true;
        for(int w:G.adj(s)){
            if(marked[w])  dfs(G,w,s);
            else if(w != v)  hasCycle = true;
        }
    }
    public boolean hasCycle(){
        return hasCycle;
    }
}
