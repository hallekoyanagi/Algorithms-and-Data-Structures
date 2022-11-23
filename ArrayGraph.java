import helper.*;

public class ArrayGraph {
    private int[] storage; // stores the graph as a flat array
    private int n;         // number of vertices
    private int m;         // number of edges
    
    
    public ArrayGraph(Graph G) {
        n = G.V(); // get the number of vertices in G
        m = G.E(); // get the number of edges in G
        
        // the following code illustrates how to iterate through the neighbours v adjacent to a vertex u in G
        storage = new int[2*m];
        int i = 0;
        for(int u = 0; u < n; u++){
            for (int v: G.adj(u)) {
                if(v > u){
                    storage[i]=v;
                    storage[i+1] = u;
                    i += 2;
                }
            }
        }
        
    }
    
    // returns the number of vertices in G
    public int V() {
        return n;
    }
    
    // returns the number of edges in G
    public int E() {
        return m;
    }
        
    // returns the degree of a vertex v
    public int deg(int v) {
        int degree = 0;
        for(int i: storage){
            if(i == v)
                degree++;
        }
        return degree;
    }
    
    // returns an array of size deg(v) containing the neighbours of v
    public int[] neighbours(int v) {
        int size = 0;
        int[] neighbors = new int[deg(v)];
        for(int i = 0; i < 2*m; i++){
            if(storage[i] == v){
                if(i%2 == 0)
                    neighbors[size] = storage[i+1];
                else
                    neighbors[size] = storage[i-1];
                size++;                    
            }
        }
        return neighbors;
    }
    
    // returns if vertices u and v are adjacent or not
    public boolean isAdj(int u, int v) {
        for(int i : neighbours(u))
            if(i == v)
                return true;
        return false;        
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        ArrayGraph AG = new ArrayGraph(G);
        System.out.println("neighbors of 0:");
        for(int i : AG.neighbours(0)){
            System.out.println(i + ",");
        }
    }
}