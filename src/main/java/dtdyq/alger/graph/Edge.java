package dtdyq.alger.graph;

/**
 * Created by Admin on 2017/9/7.
 */
public class Edge implements Comparable<Edge>{
    private final int v;
    private final int w;
    private final double weight;
    public Edge(int v,int w,double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public double weight(){
        return weight;
    }
    public int either(){
        return v;
    }
    public int other(int v){
        if(v == this.v)  return w;
        else return v;
    }
    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight,o.weight);
    }
    @Override
    public String toString(){
        return String.format("%d-%d %.2f",v,w,weight);
    }
}
