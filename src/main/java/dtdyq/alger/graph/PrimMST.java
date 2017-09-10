package dtdyq.alger.graph;

import dtdyq.alger.array.List;
import dtdyq.alger.util.MinPQ;

/**
 * Created by Admin on 2017/9/7.
 */
public class PrimMST {
    private boolean[] marked;
    private List<Edge> mst;
    private MinPQ<Edge> pq;
    public PrimMST(EGraph g){
        marked = new boolean[g.V()];
        mst = new List<>();
        pq = new MinPQ<>();

        mark(g,0);
        while(!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if(marked[v]&&marked[w])  continue;
            mst.add(e);
            if(!marked[v]) mark(g,v);
            if(!marked[w]) mark(g,w);
        }
    }

    private void mark(EGraph g, int i) {
        marked[i] = true;
        for(Edge e:g.adj(i)){
            if(!marked[e.other(i)])  pq.add(e);
        }
    }
    public Iterable<Edge> edges(){
        return mst;
    }
    public double weights(){
        double sum = 0.0;
        for(Edge e:mst){
            sum += e.weight();
        }
        return sum;
    }
}
