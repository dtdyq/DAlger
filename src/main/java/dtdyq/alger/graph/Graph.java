package dtdyq.alger.graph;

import dtdyq.alger.core.Set;
import dtdyq.alger.set.HashSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;

/**
 * Created by Admin on 2017/9/3.
 */
public class Graph{
    private final int V;
    private int E;
    private Set<Integer>[] adj;
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = new HashSet[V];
        for(int i = 0;i < V;i++){
            adj[i] = new HashSet<>();
        }
    }
    public Graph(BufferedReader reader,String spliter) throws IOException {
        this(Integer.parseInt(reader.readLine()));
        this.E = Integer.parseInt(reader.readLine());
        for(int i = 0;i < E;i++){
            String[] tmp = reader.readLine().split(spliter);
            addEdge(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]));
        }
    }
    public int V(){return    V;}
    public int E(){return    E;}
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("vertices: "+V+"  edges: "+E+"\n");
        for(int i = 0;i < V;i++){
            res.append(i+" : ");
            for(int w:adj(i)){
                res.append(w+" ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}







