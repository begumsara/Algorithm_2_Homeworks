//-----------------------------------------------------------------------------------------------------------------------------------
// Title: DFS class
// Author: Begüm Şara Ünal
// Question: 2
// Assignment: 1
// Description: This class for making depth first search in graph.
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DFS {
    
    ArrayList<Integer> dfsList;
	String str;
	HashMap<Integer, String> hashMap;
	public boolean visited[];
	public boolean check;

	 //--------------------------------------------------------
  	// Summary:Constructor of class.
   //--------------------------------------------------------
	public DFS(Graph graph, int sindex) 
	{
		dfsList = new ArrayList<Integer>();
		hashMap = new HashMap<Integer, String>();

		dfsList.add(sindex);

		visited = new boolean[graph.V()];
		visited[sindex] = true;
		check = false;
		str = "";
	}
 	//--------------------------------------------------------
	 // Summary: This is for mark boolean visited
	 // Precondition: graph is from Graph class, index is an integer.
	 // Postcondition: Returns true or false 
	 //--------------------------------------------------------
	public boolean isPath(Graph graph, int index)
	{
		for (int i : graph.adjList(index)) 
		{
			if (visited[i] == false)
				return true;
		}
		return false;
	}
	//--------------------------------------------------------
	 // Summary: This is for check path
	 // Precondition: graph is from Graph class, sindex and findex are integer.
	 // Postcondition: -
	 //--------------------------------------------------------
	public void checkPath(Graph graph, int sindex, int findex)
	{
		for (int w : graph.adjList(sindex)) {
			if (visited[w]) {
				continue;
			}
			dfsList.add(w);
			visited[w] = true;
			if (dfsList.contains(findex) && !isPath(graph, w)) {
				visited[w] = false;
				for (int i = 0; i < dfsList.size(); i++) {
					str += Integer.toString(dfsList.get(i));
				}
				hashMap.put(str.length(), str);
				str = "";
			}

			checkPath(graph, w, findex);
			dfsList.remove(dfsList.size() - 1);

		}
	}

 	//--------------------------------------------------------
	// Summary: This is for print dfs
    // Precondition: -
	// Postcondition: Print values in dfsList.
	//--------------------------------------------------------
	public void printDFS() {
		int index = 0;
		while (hashMap.get(index) == null) {
			index++;
		}
		dfsList.clear();
		str = hashMap.get(index);
		for (int i = 0; i < str.length(); i++) {
			dfsList.add(Character.getNumericValue(str.charAt(i)));
		}
		Collections.sort(dfsList);
		for(int i=0;i<dfsList.size();i++) {
			System.out.print(dfsList.get(i)+" ");
		}
	}

}


