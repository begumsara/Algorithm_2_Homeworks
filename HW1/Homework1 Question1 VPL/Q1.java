//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Q1 class
// Author: Begüm Şara Ünal
// Proposed grade: 83.33 / 100
// Question: 1
// Assignment: 1
// Description: This class use Graph and Breath First Search for reaching the solution
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Q1 {
    
    
public static void main(String[] args)
 {

        Scanner star = new Scanner(System.in);

        int city = star.nextInt(); //takes city
        int roads = star.nextInt(); //takes bidirectional roads between cities
        int changeState = star.nextInt(); //takes time required the airports to change their state
        int flightRoute = star.nextInt(); //takes travel through any flight route

        int c=city+1;
        
        List<Integer>[] adjList = new List[c]; //// Create array of lists

        
        for (int i = 1; i <= city; i++) //// Initialize all lists to empty
        {
            adjList[i] = new ArrayList<>();
        }
        //--------------------------------------------------------
	   // Summary: This is for add vertex into graph 
	  // 
	 // Postcondition:Adds v to w's list and oposite.
    //--------------------------------------------------------
        for (int i = 0; i < roads; i++) 
        {
            int u = star.nextInt();
            int v = star.nextInt();
            adjList[u].add(v); // Add u to v’s list.
            adjList[v].add(u);// Add v to u’s list.
        }
        
        int start = star.nextInt(); //takes start vertex
        int finish = star.nextInt();//takes finish vertex
        
        
        int[] edgeTo = new int[c]; // last vertex on known path to this vertex
        Arrays.fill(edgeTo, Integer.MAX_VALUE); // start max value 
        edgeTo[start] = 0;

        boolean[] marked = new boolean[c]; // marks visited vertexes 
        int[] parent = new int[c];
        Arrays.fill(parent, -1);
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
         //--------------------------------------------------------
	   // Summary: This is for making breath first search in graph.
	  //
	 // Postcondition: It adds all the neighbors of the nodes it visits to a queue and 
    //selects the nodes it will visit according to the order in the queue.
    //--------------------------------------------------------
        while (!q.isEmpty())
         {
         int u = q.poll();
            if (marked[u]) continue;
            marked[u] = true; // Marks the current vertex

            for (int v : adjList[u]) 
            {
                int text = flightRoute;

                if (parent[u] != -1 && parent[u] != v && ((edgeTo[u] / changeState) % 2 == 1)) //calculates travveling time
                {
                    text += changeState - (edgeTo[u] % changeState);
                }
                if (edgeTo[v] > edgeTo[u] + text) 
                {
                    edgeTo[v] = edgeTo[u] + text;
                    q.offer(v);
                    parent[v] = u;
                }
            }
        }
    //--------------------------------------------------------
	 // Summary: Iteratze for reach paths
	 // 
	 // Postcondition: Returns reversed version of path
	 //--------------------------------------------------------
        List<Integer> path = new ArrayList<>();
       
        for (int pat = finish; pat != -1; pat = parent[pat]) 
        {
            path.add(pat);
        }

        Collections.reverse(path);
        
        System.out.println(path.size());//print path size

        for (int i = 0; i < path.size(); i++) 
        {
            System.out.print(path.get(i) + " ");//prints path
        }
        
        System.out.println();
        System.out.println(edgeTo[finish]);//print total travelling time

        star.close();
}
}

/*
+------------------------------+
|  6 tests run/ 5 tests passed |
+------------------------------+

Incorrect program output
--- Input ---
5 5 2 3
1 2
2 3
2 5
3 5
4 5
1 4

--- Program output ---
4
1 2 5 4
11

--- Expected output (numbers)---
4
1 2 4 5
11
*/ 

