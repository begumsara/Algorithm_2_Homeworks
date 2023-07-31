//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Q2 class
// Author: Begüm Şara Ünal - Giray Berk Kuşhan
// Proposed grade: 83.33 / 100
// Question: 2
// Assignment: 1
// Description: This class use Graph and Breath First Search for reaching the solution
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.*;
     //--------------------------------------------------------
	 // Summary: The Edge class represents an edge in the graph.
	// 
   // Postcondition: The Edge class hides the target and the weight. 
  //--------------------------------------------------------
class Edge {
    int aim, IdForBus; 
    int depart,arrive;

     //--------------------------------------------------------
  	// Summary:Constructor of class.
   //--------------------------------------------------------
    public Edge(int target, int IdForBus, int depart, int arrive) {
        this.aim = target;
        this.IdForBus = IdForBus;
        this.depart = depart;
        this.arrive = arrive;
    }
}

       //---------------------------------------------------------------------
	  // Summary: The Q2 class represents an edge in the graph.
	 // 
    // Postcondition: Calculates the minimum time required to reach each station 
   //in a transportation system with more than one bus and station.
  //---------------------------------------------------------------------
public class Q2 {
     //--------------------------------------------------------
  	// Summary: Breath-First Search algorithm to find the minimum time to reach each station.
   //--------------------------------------------------------
    static void breathForSearch(int start, List<List<Edge>> graph, int[] dist, List<List<Integer>> busTimes) {
        boolean[] checkVisit = new boolean[graph.size()];
        
       
        checkVisit[start] = true;
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int currentStation = queue.poll();
            checkVisit[currentStation] = false; // we can revisit this station later with smaller dist

            // Iterate through the edges connected to the current station
            for (Edge edge : graph.get(currentStation)) {
                int IDForBus = edge.IdForBus,nextSt = edge.aim;
                int departTime = edge.depart, arriveTime = edge.arrive;

                //// Calculate the waiting time for the bus if needed
                int waitTime = (departTime - dist[currentStation] % busTimes.get(IDForBus).size() + busTimes.get(IDForBus).size()) % busTimes.get(IDForBus).size();
                int newDist = dist[currentStation] + waitTime + 1;

                // Update the minimum distance to the next station if a shorter path is found
                if (newDist < dist[nextSt]) {
                    dist[nextSt] = newDist;
                    if (!checkVisit[nextSt]) {
                        queue.add(nextSt);
                        checkVisit[nextSt] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // number of stations
        int M = scanner.nextInt(); // number of buses

        // Create the adjacency list
        List<List<Edge>> graph = new LinkedList<>();
        int count = 0;
        while (count <= N) {
            graph.add(new LinkedList<>());
            count++;
        }

        List<List<Integer>> busTimes = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            int t = scanner.nextInt(); // number of stations the bus visits
            List<Integer> route = new LinkedList<>();
            for (int j = 0; j < t; j++) {
                route.add(scanner.nextInt());
            }
            busTimes.add(route);

            int control = 0;
    //--------------------------------------------------------
  	// Summary: Add edges to the graph based on the bus route
   //--------------------------------------------------------
            while (control < t) {
                
                graph.get(route.get(control)).add(new Edge(route.get((control + 1) % t), i, control, (control + 1) % t));
                control++;
            }

        }

        // Run BFS and calculate the minimum time to reach each station
        int[] dist = new int[N + 1];   // created array and put use this in bfs method.
        breathForSearch(1, graph, dist, busTimes);// Run BFS and calculate the minimum time

        
     //--------------------------------------------------------
  	// Summary: Print the results
   //--------------------------------------------------------
        int i = 2;
        do {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.print("-1 ");
            } else {
                System.out.print(dist[i] + " ");
            }
            i++;
        } while(i <= N);
    }

}
/*
+------------------------------+
|  6 tests run/ 5 tests passed |
+------------------------------+

Incorrect program output
--- Input ---
7 3
4 1 2 3 5
3 4 5 6
2 6 7 1

--- Program output ---
1 2 6 3 5 7

--- Expected output (numbers)---
1 2 6 3 3 4

 */
