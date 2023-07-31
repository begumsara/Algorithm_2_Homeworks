//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Farm class
// Author: Begüm Şara Ünal
// Proposed grade: 100 / 100
// Question: 2
// Assignment: 2
// destinationarription: This class use Minimum Spanning Tree for reaching the solution
//------------------------------------------------------------------------------------------------------------------------------------

import java.util.*;

public class Farm 
{
    static int[] parent;
    static int[][] structure;

    public static void main(String[] args) 
    {
        Scanner star = new Scanner(System.in);

        int numTestCases = star.nextInt(); //takes number of test cases

        while (numTestCases-- > 0) //when numTestCases reaches 0
        {
            int numRows = star.nextInt();  //takes rows
            int numCols = star.nextInt(); //takes colums

            int r1 = star.nextInt() - 1; //takes coordinate of starting cell
            int c1 = star.nextInt() - 1; //takes coordinate of starting cell
            int r2 = star.nextInt() - 1; //takes coordinate of finishing cell
            int c2 = star.nextInt() - 1; //takes coordinate of finishing cell

            structure = new int[numRows][numCols]; //create matrix to solve question

            for (int i = 0; i < numRows; i++) 
            {
                for (int j = 0; j < numCols; j++) 
                {
                    structure[i][j] = star.nextInt(); //makes matrix to solve question
                }
            }

            int vertices = numRows * numCols; //grid size (N X M)

            parent = new int[vertices];
            Arrays.fill(parent, -1);

            List<Edge> e = new ArrayList<>(); //using the arraylist add into graph

            
        //--------------------------------------------------------
	   // Summary: This is for add vertex into graph 
	  // 
	 // Postcondition:Adds horizontal edges
    //--------------------------------------------------------
            for (int i = 0; i < numRows; i++) 
            {
                for (int j = 0; j < numCols - 1; j++) 
                {//calculate the horizonal edges size
                    int horizontal = i * numCols + j; 
                    int horizontalEdge = horizontal + 1;
                    int weight = structure[i][j] ^ structure[i][j+1];

                    e.add(new Edge(horizontal, horizontalEdge, weight)); //adds horizontal edges and weights
                }
            }

       //--------------------------------------------------------
	   // Summary: This is for add vertex into graph 
	  // 
	 // Postcondition:Adds vertical edges
    //--------------------------------------------------------
            for (int i = 0; i < numRows - 1; i++) 
            {
                for (int j = 0; j < numCols; j++) 
                {//calculate the horizonal edges size
                    int vertical = i * numCols + j;
                    int verticalEdge = (i+1) * numCols + j;
                    int weight = structure[i][j] ^ structure[i+1][j];

                    e.add(new Edge(vertical, verticalEdge, weight));//adds vertical edges and weights
                }
            }

            int calculate = r1 * numCols + c1;
            int destination = r2 * numCols + c2;

            int cost = kruskalAlgorithm(e, vertices, calculate, destination); //makes kruskal algorithm to solve question

            System.out.println(cost);//prints the result

            star.close();
        }
    }
        //---------------------------------------------------------------
	   // Summary: This method uses the algorithm is executed until the number of edges (N-1) is obtained
      //
     //--------------------------------------------------------------------------
    static int detect(int u) 
    {
        if (parent[u] == -1) 
            return u;
        return parent[u] = detect(parent[u]);
    }

    static void union(int u, int v) 
    {
        int uParent = detect(u);
        int vParent = detect(v);
        parent[uParent] = vParent;
    }
        //---------------------------------------------------------------
	   // Summary: Makes kruskal algorithm
      //
	 // Postcondition: This method to reach all nodes in a graph without
    // without looping in the shortest way with using 
   // kruskal algorithm in graph.
  //--------------------------------------------------------------------------
    static int kruskalAlgorithm(List<Edge> e, int vertices, int calculate, int destination) 
    {
        int cost = 0;
        int count = 0;

        Collections.sort(e, (a, b) -> a.weight - b.weight); //The side lengths are ordered from smallest to largest
        //It is selected from the smallest edge 
        for (Edge edge : e) 
        {//it is checked whether it forms a loop in the graph
            if (detect(edge.u) != detect(edge.v))  //If the loop does not occur, the edge is included
            {
                union(edge.u, edge.v); //goes to uninion function
                cost += edge.weight; //adds weights
                count++;//intrease count
            }

            if (count == vertices - 1 && detect(calculate) == detect(destination)) //But if a loop occurs, that edge is not used.
            {
                break; 
            }
        }

        return cost; //returns cost
    }
}
/*
+------------------------------+
|  3 tests run/ 3 tests passed |
+------------------------------+
*/
