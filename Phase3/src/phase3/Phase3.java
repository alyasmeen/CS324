
package phase3;

import java.util.LinkedList;

class Phase3 {
    //set up for the maximum flow problem
    static int V=6; //number of vertices
    static int residual[][] = new int[V][V]; //store residual graph


    
    
    public static void main(String[] args){
        //Graph of the maximum flow problem
        int graph[][]={{ 0, 2, 7, 0, 0, 0 },
                       { 0, 0, 0, 3, 4, 0 },
                       { 0, 0, 0, 4, 2, 0 },
                       { 0, 0, 0, 0, 0, 1 },
                       { 0, 0, 0, 0, 0, 5 },
                       { 0, 0, 0, 0, 0, 0 }};

        System.out.println("The augmenting paths, their residual capacities\nbefore augmentation, and the minimum flow added.\n(from right to left):\n");
        System.out.println("\nMaximum flow: " + fordFulkerson(graph, 0, 5));
        System.out.println("\nMin cut:");
        minCut(graph, 0);
        System.out.println("\n------------------------------------------------\n\n");
        
        
        int bipartite[][]={{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                           { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                           { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
                           { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                           { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                           { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                           { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                           { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                           { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                           { 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                           { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                           { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};
        
        System.out.println("Maximum matching:\n");
        System.out.println("\nMaximum matching= "+maximumMatching(bipartite));
    }
    
    
    //Returns the maximum flow
    public static int fordFulkerson(int graph[][], int s, int t){
        int u, v; //counter variables
        
        //initialize the residual graph with the initial capacities
        for (u = 0; u < V;u++)
            for (v = 0; v < V;v++)
                residual[u][v]=graph[u][v];
 
        // Store parent vertex for edges . It is filled by BFS to store augmented path
        int parent[] = new int[V];
 
        int maxFlow=0; //to store max flow, no flow initially
 
        // while there is an augmented path
        while (bfs(s, t, parent)) {
            //After running bfs, "parent" is now filled with the augmented path edges
            
            //traverse the path to find the minimum residual capacity
            //also print the path while traversing
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]){
                u = parent[v];
                pathFlow=Math.min(pathFlow, residual[u][v]);
                
                //to print the path
                System.out.print("("+(v+1)+")");
                if (graph[u][v]!=0) //if the edge exists in the original graph, it is a forward edge
                    System.out.print("<--"+residual[u][v]+"--"); //print the capacity of the edge
                else
                    System.out.print("--"+residual[u][v]+"-->");
            }
            System.out.println("("+(s+1)+")");
            System.out.println("Flow= "+pathFlow+"\n");
 
            ///update residual graph
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                residual[u][v]-=pathFlow;
                residual[v][u]+=pathFlow;
            }
 
            // update max flow
            maxFlow += pathFlow;
        }
        
        
        return maxFlow;
    }
    
    /* Returns true if there exists an augmented path in the residual
    graph and stores its information in "parent" */
    static boolean bfs(int s, int t, int parent[]){
        
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;
        
        LinkedList<Integer> queue=new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
 
        //do bfs to find a path
        while (queue.size() != 0) {
            int u = queue.poll();
 
            for (int v = 0; v < V; v++) {
                if (visited[v] == false && residual[u][v] > 0) {
                    
                    //We reached the sink. It means a path is found to the sink
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        
        return false;
    } 
 

    
    //prints the min cut edges and its value using the residual graph
    static void minCut(int [][] graph, int s){
          
        boolean[] reached = new boolean[graph.length]; 
        
        //dfs to find the vertices that can be reached from the source in the residual graph
        dfs(s, reached);
          
        
        int minCut=0; //to store sum of capacities of all min cut edges
        
        //Min cut are the set of edges from a reachable vertex to non reachable vertex 
        for (int i = 0; i < graph.length; i++) {
            
            for (int j = 0; j < graph.length; j++) {
                
                if (graph[i][j] > 0 && reached[i] && !reached[j]) {
                    System.out.println("("+(i+1) + ")--"+graph[i][j]+"-->(" + (j+1)+")");
                    minCut+=graph[i][j];
                }
            }
        }
        
        System.out.println("Min cut= "+minCut);
    }
    
    
    //dfs to find the vertices that can be reached from the source in the residual graph
    static void dfs(int s, boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < residual.length; i++) {
                if (residual[s][i] > 0 && !visited[i]) {
                    dfs(i, visited);
                }
        }
    }
    
    //finds the maximum matching and print it, it returns the number of matchings
    static int maximumMatching(int [][] graph){
        int len=graph.length;
        int n=len/2; //to iterate over the set V
        
        int matching []=new int[len]; //matching[u]=v means vertex u is matched with v
        int label []=new int[len];
        for (int i=0; i< len; i++){
            matching[i]=-1;
            label[i]=-1;
        }
        
        LinkedList<Integer> queue= new LinkedList<>();
        for (int i=0; i< n; i++)
            queue.add(i);
        
        while(!queue.isEmpty()){
            
            int w=queue.poll();
            if (w<n){ // w is in V
                
                for (int u=n ; u<len ; u++){
                    
                    if (graph[w][u]==0) //u is not adjacent to w
                        continue;
                    
                    if (matching[u]==-1){ // u is free
                        matching[w]=u; matching[u]=w;  //add edge w-u to the matching set
                        
                        int v=w;
                        while (label[v] != -1){ //while v is labeled
                            u= label[v]; matching[v]=-1; matching[u]=-1;
                            v= label[u]; matching[v]=u; matching[u]=v;
                        }
                        
                        for (int i=0; i< label.length; i++) //remove all vertex labels
                            label[i]=-1;
                        
                        queue.clear();  //reinitialize queue with all free vertices in V
                        for (int i=0; i<n; i++)
                            if (matching[i] == -1)
                                queue.add(i);
                        break;
                    }
                    
                    else{  //u is matched
                        if (matching[w]!=u && label[u]==-1){
                            label[u]=w;
                            queue.add(u);
                        }
                    }
                }
            }
            else{  //w is in U
                label[matching[w]]=w;
                queue.add(matching[w]);
            }
        }
        
        String name []= {"Ahmed", "Mahmoud", "Eman", "Fatimah", "Kamel", "Nojood",
                        "King Abdulaziz University", "King Fahad", "East Jeddah",
                        "King Fahad Armed Forces", "King Faisal Specialist",
                        "Ministry of National Guard"};
        
        //print the matching
        for (int i=0; i<n; i++)
            if (matching[i]!=-1)
                System.out.println(name[i]+"-----"+name[matching[i]]);

        //count and return number of matchings
        int maximumMatching=0;
        for (int i=0; i<n; i++)
            if (matching[i]!=-1)
                maximumMatching++;
        
        return maximumMatching;
    }
}
