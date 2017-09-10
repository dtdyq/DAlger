package dtdyq.alger.graph.util;

import dtdyq.alger.graph.Graph;

/**
 * Created by Admin on 2017/9/3.
 */
public class ComponentCount {
    private boolean[] marked;
    private int[] id;
    private int count;
    public ComponentCount(Graph G){
        int V = G.V();
        int E = G.E();
        marked = new boolean[V];
        id = new int[V];
        for(int i = 0;i < V;i++){
            if(!marked[i]){
                dfs(G,i);
                count++;
            }
        }
    }
    private void dfs(Graph G,int v){
        marked[v] = true;
        id[v] = count;
        for(int w:G.adj(v)){
            if(!marked[w]) dfs(G,w);
        }
    }
    public boolean connected(int v,int w){
        return id[v] == id[w];
    }
    public int count(){
        return count;
    }
    public int id(int v){
        return id[v];
    }
    public String toString(){
        StringBuilder res = new StringBuilder(count + " components");
        for(int i = 0;i < count;i++){
            for(int k :id){
                if(id[k] == i) res.append(k+" ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}







