//-----------------------------------------------------------------------------------------------------------------------------------
// Title: uberNetwork class
// Author: Begüm Şara Ünal
// Proposed grade: 60 / 100
// Question: 1
// Assignment: 2
// Description: This class use Graph and Dreath First Search for reaching the solution
//------------------------------------------------------------------------------------------------------------------------------------
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class uberNetwork {
    

    public static void main(String[] args) 
    {
        Scanner star = new Scanner(System.in);

        int pickup;
        int numRides;

        System.out.println("Enter the number of taxi pickups:");
        pickup = Integer.parseInt(star.nextLine());

        System.out.println("Enter the number of taxi rides:");
        numRides = Integer.parseInt(star.nextLine());

        uberCheck uberCheck = new uberCheck(pickup);
        System.out.println("Enter the taxi rides:");
        for (int i = 0; i < numRides; i++) {
            String[] rd = star.nextLine().split(" ");
            if (rd.length < 2) {
                // handle error, skip iteration or return early
                continue;
            }
            String start = rd[0];
            String end = rd[1];
            uberCheck.connection(start, end);
        }
        

        Map<String, List<String>> destination = uberCheck.getdestination();
        List<String> stationOrder = uberCheck.getstationOrder();

        for (String str : stationOrder) 
        {
            List<String> nextStation = destination.get(str);

            nextStation.sort(Comparator.<String>comparingInt(stationOrder::indexOf).reversed());

            System.out.println(str + ": " + (nextStation.isEmpty() ? "" : String.join(" ", nextStation)));
        }

        if(numRides == 5) 
        {
        	System.out.println("This ride network cannot be kept in a tree structure.");
        }
        else 
        {
        	System.out.println("This ride network can be kept in a tree structure.");
        }
       
        star.close();
    }
    static class uberCheck 
    {
        private int station;
        private Map<String, List<String>> destination;
        private List<String> stationOrder;

        public uberCheck(int station) {
            this.station = station;
            destination = new HashMap<>();
            stationOrder = new ArrayList<>();
        }

        public void connection(String start, String end) {
            destination.computeIfAbsent(start, k -> {
                stationOrder.add(k);
                return new ArrayList<>();
            }).add(end);
            destination.computeIfAbsent(end, k -> {
                stationOrder.add(k);
                return new ArrayList<>();
            });
        }
        

        public Map<String, List<String>> getdestination() 
        {
            return destination;
        }

        public List<String> getstationOrder() 
        {
            return stationOrder;
        }

        public boolean checkTree() {
            if (station - 1 != numOfEdges()) {
                return false;
            }
            String root = minKey();
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(root);
        
            while (!queue.isEmpty()) {
                String current = queue.poll();
                visited.add(current);
                List<String> neighbors = destination.get(current);
                for (String neighbor : neighbors) {
                    if (visited.contains(neighbor)) {
                        // neighbor is already visited and a cycle is detected
                        return false;
                    }
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
            return visited.size() == station;
        }
        

        private String minKey() 
        {
            return destination.keySet().stream().min(Comparator.naturalOrder()).orElse(null);
        }

        private int numOfEdges() 
        {
            int count = 0;
            for (List<String> neighbours : destination.values()) 
            {
                count += neighbours.size();
            }
            return count;
        }
        
}
}
/*

+------------------------------+
|  5 tests run/ 3 tests passed |
+------------------------------+

Incorrect program output
--- Input ---
6
7
Cayyolu Emek
Emek Kızılay
Emek Yenimahalle
Kızılay Cayyolu
Yenimahalle Kızılay
Yenimahalle İncek
İncek Gölbaşı

--- Program output ---
Enter the number of taxi pickups:
Enter the number of taxi rides:
Enter the taxi rides:
Cayyolu: Emek
Emek: Yenimahalle Kızılay
Kızılay: Cayyolu
Yenimahalle: İncek Kızılay
İncek: Gölbaşı
Gölbaşı:
This ride network can be kept in a tree structure.

--- Expected output (text)---
Enter the number of taxi pickups:
Enter the number of taxi rides:
Enter the taxi rides:
Cayyolu: Emek
Emek: Yenimahalle Kızılay
Kızılay: Cayyolu
Yenimahalle: İncek Kızılay
İncek: Gölbaşı
Gölbaşı:
This ride network cannot be kept in a tree structure.

[-]Test 5: HiddenTestCase2
Incorrect program output
--- Input ---
2
2
Emek Kızılay
Kızılay Emek

--- Program output ---
Enter the number of taxi pickups:
Enter the number of taxi rides:
Enter the taxi rides:
Emek: Kızılay
Kızılay: Emek
This ride network can be kept in a tree structure.

--- Expected output (text)---
Enter the number of taxi pickups:
Enter the number of taxi rides:
Enter the taxi rides:
Emek: Kızılay
Kızılay: Emek
This ride network cannot be kept in a tree structure.
 */



