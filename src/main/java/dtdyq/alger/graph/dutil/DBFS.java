package dtdyq.alger.graph.dutil;

import dtdyq.alger.graph.DGraph;
import dtdyq.alger.util.Queue;

/**
 * Created by Admin on 2017/9/6.
 */
public class DBFS{
    private boolean[] marked;
    private int count = 1;

    public DBFS(DGraph dg,int v){
        marked = new boolean[dg.V()];
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(v);
        while(!queue.isEmpty()){
            int tmp = queue.deQueue();
            marked[tmp] = true;
            for(int w:dg.adj(tmp)){
                if(!marked[w]){
                    queue.enQueue(w);
                    count++;
                }
            }
        }
    }
    public DBFS(DGraph dg,Iterable<Integer> it){
        marked = new boolean[dg.V()];
        Queue<Integer> queue = new Queue<>();
        it.forEach(ele->queue.enQueue(ele));
        while(!queue.isEmpty()){
            int tmp = queue.deQueue();
            marked[tmp] = true;
            for(int w:dg.adj(tmp)){
                if(!marked[w]){
                    queue.enQueue(w);
                }
            }
        }
    }
    public int count(){
        return count;
    }
    public boolean marked(int v){
        return marked[v];
    }
}






