
package phase2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Phase2 {

    /*this function takes the number of nodes n, number of edges m, 
    and returns a random graph in adjacency matrix forms*/
    public static LinkedList< LinkedList<Integer[]> > makeGraph(int n, int m){
        
        /* REPRESENTATION:
        we will represent the graph using a linked list of a linked list.
        each element in the list is an array of 2 elements. Element 0 is the 
        VERTEX number and element 1 is the WEIGHT */
        LinkedList< LinkedList<Integer[]> > graph=new LinkedList< LinkedList<Integer[]> >();
        
        //initilaize the vertices list
        for(int i=0; i<n; i++){
            graph.add(new LinkedList<Integer[]>());
        }
        
        int edges=0; //to keep track of how many edges we created
        
        for(int i=0; i<n; i++)
            for (int j=i+1; j<n && edges<n; j++, edges++){
                
                int randomNum=(int)(Math.random()*100)+1; //a random weight between 1 and 100
                
                //add edge from i to j, then from j to i
                graph.get(i).add(new Integer [2]);
                graph.get(i).getLast()[0]=j;
                graph.get(i).getLast()[1]=randomNum;
                
                graph.get(j).add(new Integer [2]);
                graph.get(j).getLast()[0]=i;
                graph.get(j).getLast()[1]=randomNum;
                
            }
        
        return graph;
    }
    
    public static void main(String[] args) {
        /*PART 1:
        generate 10 random adjacency list graphs, then apply prim's algorithm 
        with unordered priority queue on each graph and record the time it takes */
        
        // array that stores 10 pairs of m, n values for the 10 cases
        int [][] cases={{1000, 10000}, {1000, 15000}, {1000, 25000},
                        {5000, 15000}, {5000, 25000},
                        {10000, 15000}, {10000, 25000},
                        {20000, 200000}, {20000, 300000},
                        {50000, 1000000}};
        
        //create 10 graphs
        LinkedList< LinkedList<Integer[]> > g1=makeGraph(cases[0][0], cases[0][1]);
        LinkedList< LinkedList<Integer[]> > g2=makeGraph(cases[1][0], cases[1][1]);
        LinkedList< LinkedList<Integer[]> > g3=makeGraph(cases[2][0], cases[2][1]);
        LinkedList< LinkedList<Integer[]> > g4=makeGraph(cases[3][0], cases[3][1]);
        LinkedList< LinkedList<Integer[]> > g5=makeGraph(cases[4][0], cases[4][1]);
        LinkedList< LinkedList<Integer[]> > g6=makeGraph(cases[5][0], cases[5][1]);
        LinkedList< LinkedList<Integer[]> > g7=makeGraph(cases[6][0], cases[6][1]);
        LinkedList< LinkedList<Integer[]> > g8=makeGraph(cases[7][0], cases[7][1]);
        LinkedList< LinkedList<Integer[]> > g9=makeGraph(cases[8][0], cases[8][1]);
        LinkedList< LinkedList<Integer[]> > g10=makeGraph(cases[9][0], cases[9][1]);
            
        System.out.println("Times for Prim's algorithm using\nunordered priority queue in ms\n");
        long time;
        
        time=System.currentTimeMillis();
        prim1(g1);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g2);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g3);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g4);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g5);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g6);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g7);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g8);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim1(g9);
        System.out.println(System.currentTimeMillis()-time); 
        time=System.currentTimeMillis();
        prim1(g10);
        System.out.println(System.currentTimeMillis()-time); 

        
        /* PART2:
        apply Kruskal algorithm on the graphs and mesaure the time in ms */
        
        System.out.println("Times for kruskal algorithm in ms\n");
        
        time=System.currentTimeMillis();
        kruskal(g1);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g2);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g3);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g4);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g5);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g6);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g7);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g8);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        kruskal(g9);
        System.out.println(System.currentTimeMillis()-time); 
        time=System.currentTimeMillis();
        kruskal(g10);
        System.out.println(System.currentTimeMillis()-time);

        /* PART3:
        apply Prim's algorithm using min heap on the graphs and mesaure the time in ms */
        
        System.out.println("Times for Prim's algorithm using min heap in ms\n");
        
        time=System.currentTimeMillis();
        prim2(g1);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g2);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g3);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g4);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g5);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g6);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g7);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g8);
        System.out.println(System.currentTimeMillis()-time);
        time=System.currentTimeMillis();
        prim2(g9);
        System.out.println(System.currentTimeMillis()-time); 
        time=System.currentTimeMillis();
        prim2(g10);
        System.out.println(System.currentTimeMillis()-time);
        
        
    }
    
    public static PQvertex [] prim1(LinkedList< LinkedList<Integer[]> > graph){
  
        // priority queue to to implement fringe+unseen list, it store the vertex, its parent, and weight
        ArrayList<PQvertex> pq = new ArrayList<>();
  
        // to store MST data
        PQvertex vt[] = new PQvertex[graph.size()];
  
        // Initialize pq. Fringe+unseen list
        for (int i = 0; i < graph.size(); i++) {
            pq.add(new PQvertex(i, -1, Integer.MAX_VALUE)); //PQvertex(vertex, its parent, its weight)
            vt[i]=null; //nothing in the MST initially
        }
  
        //weight of root initially 0, to add it first to the MST
        pq.get(0).w=0;
  
        // Prim's algorithm
        for (int i = 0; i < graph.size() - 1; i++) {
            
            // Pick thd minimum weight vertex from the fringe list
            int index = findMin_Prim1(pq, vt);      //index is its order in the PQ
            int u=pq.get(index).v;            //u is the order in the graph (its name)
  
            // Add the picked vertex to the MST
            vt[u] = pq.get(index);
            
            //delete it from the PQ
            pq.remove(index);
  
            // update the PQ for all adjacent vertices
            updatePQ_Prim1(u, pq, vt, graph);
            
        }
        
        return vt;
    }//end of prim1
    
    static int findMin_Prim1(ArrayList<PQvertex> pq, PQvertex vt[]){
        
        int min = Integer.MAX_VALUE, min_index = -1;
  
        for (int i = 0; i < pq.size(); i++)
            if (vt[i] == null && pq.get(i).w < min) {
                min = pq.get(i).w;
                min_index = i;
            }
  
        return min_index;
    }
    
    static void updatePQ_Prim1(int u, ArrayList<PQvertex> pq, PQvertex vt[], LinkedList< LinkedList<Integer[]> > graph){
        // update the PQ for all adjacent vertices
        for (int j = 0; j < graph.get(u).size(); j++){

            // skip updating if the vertex is already in the MST
            if (vt[graph.get(u).get(j)[0]] != null)
                continue;


            //find the index of j'th vertex in the PQ
            int k=0;
            for (k=0; k< pq.size(); k++)
                if (pq.get(k).v == graph.get(u).get(j)[0])
                    break;


            // Update the weight in PQ only if edge (u,j) is smaller than (parent,j)
            if (graph.get(u).get(j)[1] < pq.get(k).w) {
                pq.get(k).p= u;                         //update parent
                pq.get(k).w= graph.get(u).get(j)[1];    //update weight
            }
        }        
    }
    
    
    static Edge [] kruskal(LinkedList< LinkedList<Integer[]> > graph){
        
        //sort all edges in a priority queue
        PriorityQueue<Edge> pq = sortEdges(graph);
        
        //to store the subset of the vertex
        int [] subset=new int[graph.size()];
        for (int i=0; i<graph.size(); i++)
            subset[i]=i;
        
        int ecounter=0;     //edge counter
        Edge [] et=new Edge[graph.size()-1];    //store edges of MST
        
        while (ecounter<graph.size()-1){
            Edge edge=pq.poll();
            
            if (subset[edge.src] != subset[edge.des]){ //check that the vertices are not in the same subset
                
                et[ecounter]=edge;      //add the edge to the MST
                ecounter++;
                union(edge, subset);    //unionize the subsets of the src and des of the edge
            }
        }

        return et;
    }//end of Kruskal
    
    
    //return a priority queue of all edges sorted in increasing order 
    static PriorityQueue<Edge> sortEdges(LinkedList< LinkedList<Integer[]> > graph){
        
        PriorityQueue<Edge> pq= new PriorityQueue<Edge>(new Edge());
        
        for (int i=0; i< graph.size(); i++)
            for (int j=0; j<graph.get(i).size(); j++)
                pq.add(new Edge(i, graph.get(i).get(j)[0], graph.get(i).get(j)[1]));
                
        return pq;
    }
    
    //unionize the subsets of the src and des of the edge
    static void union(Edge edge, int subset []){
        
        int desSubset= subset[edge.des];    //the destination vertex subset
        int srcSubset= subset[edge.src];    //the source vertex subset
        
        //combine the subset of the destination vertex to the subset of the source
        for (int i=0; i<subset.length; i++)
            if (subset[i] == desSubset)     //if the vertex is in the destination subset 
                subset[i] = srcSubset;      //move it to the source subset
    }
    
    
    public static Edge [] prim2(LinkedList< LinkedList<Integer[]> > graph){
  
        // Min heap to store fringe+unseen list,
        //it stores the vertex (des), its parent (src) , and weight (w)
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new Edge());
  
        // to store MST edges
        Edge vt[] = new Edge[graph.size()];
  
        // Initialize all min heap vertices as unseen
        for (int i = 0; i < graph.size(); i++) {
            minHeap.add(new Edge(-1, i, Integer.MAX_VALUE)); // (parent, vertex, weight) i.e (source, dest, w)
            vt[i]=null; //nothing in the MST initially
        }
        
        //weight of root initially 0, to add it first to the MST
        minHeap.peek().w=0;
  
        // Prim's algorithm
        for (int i = 0; i < graph.size() - 1; i++) {
            
            // Pick thd minimum weight vertex from the fringe list
            Edge edge= minHeap.poll();
  
            // Add the picked vertex to the MST
            vt[edge.des] = edge;
  
            // update the PQ for all adjacent vertices
            updateHeap(edge, minHeap, vt, graph);
            
        }
        
        return vt;
    }//end of prim2
    
    
    static void updateHeap(Edge edge, PriorityQueue<Edge> minHeap, Edge vt[], LinkedList< LinkedList<Integer[]> > graph){
        //edge is the newly added edge to the MST
        
        // update the min heap for all adjacent vertices
        for (int i = 0; i < graph.get(edge.des).size(); i++){

            // skip updating if the vertex is already in the MST
            if (vt[graph.get(edge.des).get(i)[0]] != null)
                continue;


            //find the i'th vertex in the min heap
            Edge adjEdge=new Edge();
            Iterator iter = minHeap.iterator();
            
            while (iter.hasNext()){
                adjEdge=(Edge)iter.next();
                
                if (adjEdge.des == graph.get(edge.des).get(i)[0])
                    break;      //adjacent i'th edge was found in the min heap
            }

            
            // Update the weight in the min heap if the new edge is smaller
            /* NOTE that if we need to update a value in a min heap we must
            remove it and insert it again to maintain the heap property */
            if (graph.get(edge.des).get(i)[1] < adjEdge.w) {
                minHeap.remove(adjEdge);
                adjEdge.src=edge.des;                           //update parent
                adjEdge.w = graph.get(edge.des).get(i)[1];      //update weight
                minHeap.add(adjEdge);                 
            }
        }
    }// end of updateHeap    
}


//objects to store in unordered priority queue
class PQvertex{
    int v,p,w; //vertex, parent, weight. 
    
    public PQvertex(int v, int p, int w) {
        this.v = v;
        this.p = p;
        this.w = w;
    }
}

class Edge implements Comparator<Edge>{
    int src, des, w;  //source (parent), destination (vertex), weight

    public Edge(int src, int des, int w) {
        this.src = src;
        this.des = des;
        this.w = w;
    }
    
    Edge(){}

    //to sort edges in a priority queue for kruskal algorithm
    @Override
    public int compare(Edge o1, Edge o2) { 
        if (o1.w > o2.w)
            return 1;
        else if (o1.w < o2.w)
            return -1;
        return 0;
    }
    
}
