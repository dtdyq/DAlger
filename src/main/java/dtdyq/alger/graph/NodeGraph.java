package dtdyq.alger.graph;

import dtdyq.alger.dict.HashDict;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Admin on 2017/9/3.
 */
public class NodeGraph<Node> {
    private HashDict<Node,Integer> n2v;
    private Node[] v2n;
    private Graph G;
    public NodeGraph(int v,Node[] nodes){
        n2v = new HashDict<>();
        v2n = nodes;
        G = new Graph(v);
        for(int i = 0;i < v;i++)
            n2v.put(nodes[i],i);
    }
    public boolean contains(Node n){
        return n2v.hasKey(n);
    }
    public int index(Node n){
        return n2v.get(n);
    }
    public Node entity(int index){
        return v2n[index];
    }
    public Graph G(){
        return G;
    }
    public void addEdge(int v,int w){
        G.addEdge(v,w);
    }
    public void addEdge(Node n,Node m) {
        G.addEdge(n2v.get(n), n2v.get(m));
    }
}
