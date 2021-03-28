
package phase2;

import java.util.LinkedList;

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
                
                int randomNum=(int)(Math.random()*100)+1; //a random weight from between 1 and 100
                
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
        with unordered priority queueon each graph and record the time it takes */
        
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
            
        
//        LinkedList< LinkedList<Integer[]> > g=makeGraph(5, 5);
//        for (int i=0; i<5; i++){
//            System.out.println(i+":");
//            for (int j=0; j<g.get(i).size(); j++){
//                System.out.print(g.get(i).get(j)[0]+"-->"+g.get(i).get(j)[1]+"   ");
//            }System.out.println("\n");}
        
        
        
        
    }
    
}
