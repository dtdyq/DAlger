package dtdyq.alger.graph;

import dtdyq.alger.array.List;
import dtdyq.alger.util.Connect;
import dtdyq.alger.util.MinPQ;

/**
 * Created by Admin on 2017/9/7.
 */
public class KruskalMST {
    private List<Edge> mst;
    public KruskalMST(EGraph g){
        mst = new List<>();
        MinPQ<Edge> pq = new MinPQ<>();
        Connect cc = new Connect(g.V());
        for(Edge e:g.edges())
            pq.add(e);
        while(!pq.isEmpty() && mst.size() < g.V()-1){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if(cc.connected(v,w)) continue;
            cc.connect(v,w);
            mst.add(e);
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
