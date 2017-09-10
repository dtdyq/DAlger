package dtdyq.alger.graph;

import dtdyq.alger.set.HashSet;

/**
 * Created by Admin on 2017/9/7.
 */
public class EGraph {
    private final int V;
    private int E;
    private HashSet<Edge>[] adj;
    public EGraph(int V){
        this.V = V;
        this.E = 0;
        adj = new HashSet[V];
        for(int i = 0;i < V;i++){
            adj[i] = new HashSet<>();
        }
    }

    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public Iterable<Edge> edges(){
        HashSet<Edge> res = new HashSet<>();
        for(int v = 0;v < V;v++){
            for(Edge e:adj[v]){
                if(e.other(v) > v) res.add(e);
            }
        }
        return res;
    }
}
