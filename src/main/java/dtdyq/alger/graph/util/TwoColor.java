package dtdyq.alger.graph.util;

import dtdyq.alger.graph.Graph;

/**
 * Created by Admin on 2017/9/3.
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor = false;
    public TwoColor(Graph G){
        int v = G.V();
        marked = new boolean[v];
        color = new boolean[v];
        for(int s = 0;s < v;s++){
            if(!marked[s])  dfs(G,s);
        }
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        for(int w:G.adj(s)){
            if(!marked[w]){
                color[w] = !color[s];
                dfs(G,w);
            }else if(color[w] == color[s])  isTwoColor = true;
        }
    }
    public boolean isTwoColor(){
        return isTwoColor;
    }
}
