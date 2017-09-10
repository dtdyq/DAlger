package dtdyq.alger.graph;

import dtdyq.alger.graph.dutil.*;
import dtdyq.alger.graph.util.*;

/**
 * Created by Admin on 2017/9/6.
 */
public class DGrapher {
    public static boolean dfsConnect(DGraph G, int v, int s){
        return new DDFS(G,v).marked(s);
    }
    public static int dfsCount(DGraph G,int v){
        return new DDFS(G,v).count();
    }

    public static boolean bfsConnect(DGraph G,int s,int v){
        return new DBFS(G,s).marked(v);
    }
    public static int bfsCount(DGraph G,int v){
        return new DBFS(G,v).count();
    }

    public static boolean dfpHasPathTo(DGraph G,int s, int v){
        return new DDFP(G,s).isConnect(v);
    }
    public static Iterable<Integer> dfpPathTo(DGraph G,int s,int v){
        return new DDFP(G,s).pathTo(v);
    }
    public static boolean bfpHasPathTo(DGraph G,int s,int v){
        return new DBFP(G,s).isConnect(v);
    }
    public static Iterable<Integer> bfpPathTo(DGraph G,int s,int v){
        return new DBFP(G,s).pathTo(v);
    }
    public static boolean hasCycle(DGraph G){
        return new DCycle(G).hasCycle();
    }
    public static Iterable<Integer> topoSort(DGraph dg){
        return new TopologicalSort(dg).topoSort();
    }
}
