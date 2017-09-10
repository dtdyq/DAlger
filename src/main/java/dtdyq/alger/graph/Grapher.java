package dtdyq.alger.graph;

import dtdyq.alger.graph.util.*;
import dtdyq.alger.util.Queue;

/**
 * Created by Admin on 2017/9/3.
 */
public class Grapher {
    public static int degree(Graph g,int v){
        int degree = 0;
        for(int w:g.adj(v)){
            degree++;
        }
        return degree;
    }
    public static int maxDegree(Graph g){
        int md = 0;
        int v = g.V();
        for(int i = 0;i<v;i++){
            int tmp = -1;
            if((tmp = degree(g,i))>md)
                md = tmp;
        }
        return md;
    }
    public static double avgDegree(Graph g){
        return 2.0*g.V()/g.E();
    }
    public static int selfLoopCount(Graph g){
        int cnt = 0;
        int V = g.V();
        for(int i = 0;i < V;i++){
            for(int j:g.adj(i)){
                if(i == j)    cnt++;
            }
        }
        return cnt/2;
    }
    public static boolean dfsConnect(Graph G, int v, int s){
        return new DFS(G,v).marked(s);
    }
    public static int dfsCount(Graph G,int v){
        return new DFS(G,v).count();
    }

    public static boolean bfsConnect(Graph G,int s,int v){
        return new BFS(G,s).marked(v);
    }
    public static int bfsCount(Graph G,int v){
        return new BFS(G,v).count();
    }

    public static boolean dfpHasPathTo(Graph G,int s, int v){
        return new DFP(G,s).isConnect(v);
    }
    public static Iterable<Integer> dfpPathTo(Graph G,int s,int v){
        return new DFP(G,s).pathTo(v);
    }
    public static boolean bfpHasPathTo(Graph G,int s,int v){
        return new BFP(G,s).isConnect(v);
    }
    public static Iterable<Integer> bfpPathTo(Graph G,int s,int v){
        return new BFP(G,s).pathTo(v);
    }
    public static boolean hasCycle(Graph G){
        return new Cycle(G).hasCycle();
    }
    public static boolean isTwoColor(Graph G){
        return new TwoColor(G).isTwoColor();
    }
}










