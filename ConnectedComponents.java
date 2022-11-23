import helper.*;

public class ConnectedComponents {
    private ArrayGraph AG; // the graph which we are interested if there exists a cycle or not
    
    
    public ConnectedComponents(ArrayGraph AG) {
        this.AG = AG;
    }
    
    // returns the number of connected components in the graph AG using DFS or BFS
    public int numConnectedComponents() {  
        boolean[] exploredVert = new boolean[AG.V()];
        boolean[][] exploredEdge = new boolean[AG.V()][AG.V()];
        int count = 0;
        for(int i = 0;i < AG.V();i++){
            if(!exploredVert[i]){
                System.out.println("starting iteration " + i);
                DFS(exploredVert, exploredEdge, i);
                count++;
            }            
        }
        return count;
    }

    public void DFS(boolean[] exploredVert, boolean[][] exploredEdge, int u){
        System.out.println("marked as explored: " + u);
        exploredVert[u] = true;
        for(int i : AG.neighbours(u)){
            if(!exploredEdge[u][i] && !exploredEdge[i][u]){
                exploredEdge[u][i] = true;
                System.out.println("marked as explored: [" + u + "][" + i + "]");
                if(!exploredVert[i]){
                    System.out.println("discovery edge at [" + u + "][" + i + "]");
                    DFS(exploredVert, exploredEdge, i);                
                } else {
                    System.out.println("back edge at [" + u + "][" + i + "]");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        ArrayGraph AG = new ArrayGraph(G);
        ConnectedComponents cc = new ConnectedComponents(AG);
        System.out.println(cc.numConnectedComponents());
    }
}