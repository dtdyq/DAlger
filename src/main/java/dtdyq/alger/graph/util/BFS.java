package dtdyq.alger.graph.util;

import dtdyq.alger.graph.Graph;
import dtdyq.alger.util.Queue;

/**
 * Created by Admin on 2017/9/3.
 */
public class BFS{
    private boolean[] marked;
    private int count;
    public BFS(Graph G, int s){
        marked = new boolean[G.V()];
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(s);
        while(!queue.isEmpty()){
            int v = queue.deQueue();
            if(!marked[v]) {
                marked[v] = true;
                count++;
            }
            for(int w:G.adj(v))    queue.enQueue(w);
        }
    }
    public boolean marked(int v){
        return marked[v];
    }
    public int count(){
        return count;
    }
}
