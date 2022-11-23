import helper.*;

public class DetectCycle {
    private ArrayGraph AG; // the graph which we are interested if there exists a cycle or not
    
    // You may use any other private variables that you want
    
    public DetectCycle(ArrayGraph AG) {
        this.AG = AG;
    }

    
    // returns if there exists a cycle in the graph AG or not using either DFS or BFS
    public boolean existsCycle() {
        boolean[] exploredVert = new boolean[AG.V()];
        boolean[][] exploredEdge = new boolean[AG.V()][AG.V()];
        
        for(int i = 0;i < AG.V();i++){
            if(!exploredVert[i]){
                if(DFS(exploredVert, exploredEdge, i)){
                    return true;
                }
            }            
        }
        return false;
    }
    //
    public boolean DFS(boolean[] exploredVert, boolean[][] exploredEdge, int u){
        exploredVert[u] = true;

        for(int i : AG.neighbours(u)){
            if(!exploredEdge[u][i] && !exploredEdge[i][u]){
                exploredEdge[u][i] = true;
                if(!exploredVert[i]){
                    if(DFS(exploredVert, exploredEdge, i)) //if a back edge exists, a cycle exists
                        return true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        ArrayGraph AG = new ArrayGraph(G);
        DetectCycle dc = new DetectCycle(AG);

        System.out.println(dc.existsCycle());
    }
}