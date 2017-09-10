package dtdyq.alger.util;

/**
 *
 * Created by dtdyq on 2017/9/2.
 *
 */
public class Connect {
    private int id[];
    private int sz[];
    private int cnt;
    public Connect(int N){
        cnt = N;
        id = new int[cnt];
        for(int i = 0;i < cnt;i++){
            id[i] = i;
        }
        sz = new int[cnt];
        for(int i = 0;i < cnt;i++){
            sz[i] = 1;
        }
    }
    public int count(){
        return cnt;
    }
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }
    private int find(int k){
        while(id[k] != k) k = id[k];
        return k;
    }
    public void connect(int p,int q){
        int i = find(p);
        int j = find(q);
        if(i == j)    return;
        if(sz[i] > sz[j]){
            sz[i] += sz[j];
            id[j] = i;
        }else{
            sz[j] += sz[i];
            id[i] = j;
        }
        cnt--;
    }
}










