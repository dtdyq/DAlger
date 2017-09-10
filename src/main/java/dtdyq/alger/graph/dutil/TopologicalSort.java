package dtdyq.alger.graph.dutil;

import dtdyq.alger.graph.DGraph;

/**
 * Created by Admin on 2017/9/7.
 */
public class TopologicalSort {
    private Iterable<Integer> order;
    public TopologicalSort(DGraph dg){
        DCycle cycle = new DCycle(dg);
        if(!cycle.hasCycle()){
            DFO dfo = new DFO(dg);
            order = dfo.reversePost();
        }
    }
    public boolean isDAG(){
        return order!=null;
    }
    public Iterable<Integer> topoSort(){
        return order;
    }
}
