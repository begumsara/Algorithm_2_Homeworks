//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Graph class
// Author: Begüm Şara Ünal
// Question: 2
// Assignment: 1
// Description: The Bag class was created to store a collection of items and allow efficient aggregation and iteration across items.
//------------------------------------------------------------------------------------------------------------------------------------
public class Graph 
{
    public final int V;
	public Bag<Integer>[] adjList;

     //--------------------------------------------------------
  	// Summary:Constructor of class.
   //--------------------------------------------------------
	public Graph(int V) {
		this.V = V;
		adjList = (Bag<Integer>[]) new Bag[V]; // Create array of lists

		for (int v = 0; v < V; v++) // Initialize all lists to empty
		{ 
			adjList[v] = new Bag<Integer>();
		}
	}

		  //--------------------------------------------------------
         // Summary: This is for return the size of array
	    // Precondition:-
	   // Postcondition: returns the size of array	
      //--------------------------------------------------------

	public int V()
	{
		return V;
	}

		//--------------------------------------------------------
	   // Summary: This is for create path between v and w.	
	  // Precondition: v is integer, w is integer
	 // Postcondition:Adds v to w's list and oposite.
    //--------------------------------------------------------
	public void addEdge(int v, int w)
	{
		adjList[v].add(w); // Add w to v’s list.
		adjList[w].add(v); // Add v to w’s list.
	}

	//--------------------------------------------------------
	 // Summary: This iterate values in array and returns
	 // Precondition: iter is an integer.
	 // Postcondition: Returns values in adjList[iter]
	 //--------------------------------------------------------
	public Iterable<Integer> adjList(int iter)
	{
		return adjList[iter];
	}

	

}
