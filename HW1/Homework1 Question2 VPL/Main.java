//-----------------------------------------------------------------------------------------------------------------------------------
// Title: Main class
// Author: Begüm Şara Ünal
// Proposed grade: 83.33 / 100
// Question: 2
// Assignment: 1
// Description: Driver class for question 2
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.Scanner;

public class Main
 {
    public static void main(String[] args) 
    {
        Scanner star = new Scanner(System.in);
        String text;
        String paths;

        int islands;
        int bidirectionalPaths;
        int start = 0;
        int end = 0;

		text = star.nextLine();
		text=text.replaceAll("\\s", "");
        
		islands = Character.getNumericValue(text.charAt(0));
		bidirectionalPaths = Character.getNumericValue(text.charAt(1));
        int vertex = bidirectionalPaths+1;
		
		Graph shipGraph = new Graph(vertex);
		
		while (bidirectionalPaths > 0) 
        {
			paths = star.nextLine();
			paths = paths.replaceAll("\\s", "");

			if (start == 0) 
            {
				start = Character.getNumericValue(paths.charAt(0));
			}

			shipGraph.addEdge(Character.getNumericValue(paths.charAt(0)), 
            Character.getNumericValue(paths.charAt(1)));
			bidirectionalPaths--;

			if (bidirectionalPaths == 0) 
            {
				end = Character.getNumericValue(paths.charAt(1));
			}
		}
        
         text = star.nextLine();
         text = text.replaceAll("\\s", "");

		DFS shipDFS = new DFS(shipGraph, start);

		shipDFS.checkPath(shipGraph, Character.getNumericValue(text.charAt(0)),
        Character.getNumericValue(text.charAt(1)));
        shipDFS.printDFS();

          star.close();
	}
    

}

/*
+------------------------------+
|  6 tests run/ 5 tests passed |
+------------------------------+

Incorrect program output
--- Input ---
4 5
1 2
1 4
2 3
2 4
3 4
1 2

--- Program output ---
1 2

--- Expected output (numbers)---
1 2 4

 */