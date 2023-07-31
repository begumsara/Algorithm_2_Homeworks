//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Q1 class
// Author: Begüm Şara Ünal - Giray Berk Kuşhan
// Proposed grade: 100 / 100
// Question: 1
// Assignment: 1
// Description: This class use Graph Edge,and Dijkstra's Algorithm for reaching the solution
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.*;

public class Q1 {

	
      //--------------------------------------------------------
	 // Summary: The Edge class represents an edge in the graph.
	// 
   // Postcondition: The Edge class hides the target and the weight. 
  //--------------------------------------------------------
	static class Edge {
		int to, weight;

	 //--------------------------------------------------------
  	// Summary:Constructor of class.
   //--------------------------------------------------------
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

static final int INF = 10000000;

	static int n, m, f;
	static int[] capacity;
	static ArrayList<Edge>[] graph;

        //---------------------------------------------------------------------
	   // Summary:  Initialize the graph
      //-------------------------------------------------------------------------	
	@SuppressWarnings("unchecked")
	static void initGraph() {
		graph = new ArrayList[n];
		int i = 0;
		while (i < n) {
		    graph[i] = new ArrayList<>();
		    i++;
		}

	}
        //---------------------------------------------------------------------
	   // Summary: This is for create path between u and v.	w is weights.
	  // Precondition: v is integer, w is integer, w is integer.
	 // Postcondition:Adds v to w's list and oposite.
    //-------------------------------------------------------------------------
	static void addEdge(int u, int v, int w) {
		graph[u].add(new Edge(v, w));
		graph[v].add(new Edge(u, w));
	}
        //-------------------------------------------------------------------------------
	   // Summary: This method for using Dijkstra's algorithm.
	  // Precondition: source is integer.
	 // Postcondition: Finds the shortest paths between nodes in a weighted graph.
    //----------------------------------------------------------------------------------
	static int[] dijkstra(int source) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n];
		Arrays.fill(dist, INF);
		dist[source] = 0;
		pq.offer(new Node(source, 0));
		
		//---------------------------------------------------------------------
	   // Summary: Dijkstra's algorithm to find the shortest paths from the source node
      //-------------------------------------------------------------------------
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if (dist[curr.index] != curr.cost) { // If the current distance to the node is not the shortest, skip it
				continue;
			}
	    //---------------------------------------------------------------------
	   // Summary: Explore all neighboring edges of the current node
      //-------------------------------------------------------------------------
			for (Edge e : graph[curr.index]) {
				int newCost = curr.cost + e.weight;
				if (newCost < dist[e.to]) { // If a shorter path is found, update the distance and add the node to the priority queue
					dist[e.to] = newCost;
					pq.offer(new Node(e.to, newCost));
				}
			}
		}
		return dist; // Return the array of shortest distances from the source node
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();m = sc.nextInt();f = sc.nextInt(); // takes number in order of nodes, edges and fixed costs
		
		//---------------------------------------------------------------------
	   // Summary:  Initialize capacity array
      //-------------------------------------------------------------------------
		capacity = new int[n];
		for (int i = 0; i < n; i++) {
			capacity[i] = sc.nextInt(); // Reads the capacity of each node
		}
		initGraph(); // Initialize the graph
		//---------------------------------------------------------------------
	   // Summary:  Adds edges to the graph
      //-------------------------------------------------------------------------
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt() - 1;int v = sc.nextInt() - 1;int w = sc.nextInt();//// Reads in order of starting node of the edge,ending node of the edgei and weight of the edge
			addEdge(u, v, w); //adds edges
		}
		int k = sc.nextInt();// Number of iterations
		int[] totalCost = new int[k];
		int[] currCapacity = Arrays.copyOf(capacity, n);
		int currSlot = 0;
		for (int i = 0; i < k; i++) {
			int[] dist = dijkstra(0); // Apply Dijkstra's algorithm to find shortest paths
			int minCost = INF;
			int minSlot = -1;
			for (int j = 0; j < n; j++) {
				if (currCapacity[j] > 0) {
					int cost = dist[j] + f; // Calculate the cost for using node j
					if (cost < minCost) {
						minCost = cost;
						minSlot = j; // Update the minimum cost slot
					}
				}
			}
			if (minSlot == -1) {
				totalCost[i] = -1; // No available slots, set cost to -1
			} else {
				totalCost[i] = minCost; // Store the minimum cost for this iteration
				currCapacity[minSlot]--;
				currSlot++;
			}
		}
		//---------------------------------------------------------------------
	   // Summary:  Prints the total costs for each iteration
      //-------------------------------------------------------------------------
		for (int i = 0; i < k; i++) {
			System.out.print(totalCost[i] + " ");
		}
		System.out.println();
	}
}

/*
+------------------------------+
|  6 tests run/ 6 tests passed |
+------------------------------+
 */