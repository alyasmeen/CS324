
package phase1;

public class Phase1 {
    //set infinity equal to 999, weights are small
    static int inf=999;
    
    
    //function that prints a matrix
    public static void printMatrix(int [][] matrix) {
        
        for (int i=0; i<matrix.length; i++){
            
            for (int j=0; j<matrix.length; j++){
                
                // 221E is unicode infinity symbol
                String num= matrix[i][j]==inf? "\u221e" : matrix[i][j]+"";
                System.out.printf("%-4s", num);
            }
            
            System.out.println();
        }
        System.out.println("--------------------------------------------");
        
    }

    public static void floydWarshall(int [][] graph) {
        
        
        for (int k=0; k<graph.length; k++){
            System.out.println("Iteration "+ (k+1)+ ":\n");
            
            for (int i=0; i<graph.length; i++){
                
                for (int j=0; j<graph.length; j++){
                    
                    //find the shortest distance from vertex i to vertex j with a maximum of k+1 intermediate vertices
                    graph[i][j]= Math.min(graph[i][j] , (graph[i][k] + graph[k][j]));
                    
                }
            }
            
            printMatrix(graph); //print the distance matrix
        }        
        
    }
    
    public static void main(String[] args) {
        
        //weight matrix: directed distances between all pairs of vertices
        int [][] graph={{0,10,inf,inf,inf,5,inf,inf,inf,inf},
                        {inf,0,3,inf,3,inf,inf,inf,inf,inf},
                        {inf,inf,0,4,inf,inf,inf,5,inf,inf},
                        {inf,inf,inf,0,inf,inf,inf,inf,4,inf},
                        {inf,inf,4,inf,0,inf,2,inf,inf,inf},
                        {inf,3,inf,inf,inf,0,inf,inf,inf,2},
                        {inf,inf,inf,7,inf,inf,0,inf,inf,inf},
                        {inf,inf,inf,4,inf,inf,inf,0,3,inf},
                        {inf,inf,inf,inf,inf,inf,inf,inf,0,inf},
                        {inf,6,inf,inf,inf,inf,8,inf,inf,0}};
        
        System.out.println("The weight matrix:\n");
        printMatrix(graph);
        System.out.println("\n\n");
        
        
        //apply Floyd-Warshall algorithm
        floydWarshall(graph);
        
        
        System.out.println("\n\n\nThe final matrix:\n");
        printMatrix(graph);
        
        
        
    }
    
}
