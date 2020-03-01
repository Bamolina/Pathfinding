import java.util.*;

public class BFS {

	/*TEAM MEMBERS: CRISTIAN MOLINA AND BRYAN MOLINA */
	
    
    
    /*---------------------------------------------------BFS Method------------------------------------------------------------*/
    
    
	public static void BFS(int[][] map, Node start, Node goal) {
		LinkedList<Node> fringe = new LinkedList<Node>();
		int[][] visitedMap = new int[map.length][map[0].length]; //Keeps track of the visited nodes
		Node current = start;
		
		long startTime = System.nanoTime();
		long timeLimit = 180000;		//Time limit in milliseconds

		fringe.add(current);
		int nodesExpanded = 0;
		
		if(!isGoalReachable(map, goal.x, goal.y)) {
			System.out.println("Goal is unreachable");
			return;
		}
		
		while(fringe.peek() != null) {
			long currentTime = System.nanoTime();
			
			if((currentTime - startTime)/1000000 >= timeLimit) { //Time conversion from nanoseconds to milliseconds
				System.out.println("Time limit exceeded");
				return;
			}
			
			current = fringe.poll();
			visitedMap[current.y][current.x] = 1;
			
			if(current.weight == 0) continue; //checks is the terrain is impassable
			
			if(current.isEqual(goal)) 
				break;
			
			else {
				nodesExpanded++;
				Node[] neighborhood = generateSuccessor(map, current.x, current.y, current);
				for(Node neighbor: neighborhood) {
					if(neighbor == null) continue;
					if(visitedMap[neighbor.y][neighbor.x] != 1) {
						fringe.add(neighbor);
						visitedMap[neighbor.y][neighbor.x] = 1;
					}
				}
			}
			
		}
		long endTime = System.nanoTime();
		long timeElapsed = (endTime - startTime)/1000000;
		
		System.out.println("Cost of the path: " + getCost(current));
		System.out.println("Nodes expanded: " + nodesExpanded);
		System.out.println("Nodes in memory: " + fringe.size());
		System.out.println("Runtime: " + timeElapsed + " milliseconds");
		getPath(current);
		System.out.println();
		if(!current.isEqual(goal))
			System.out.println("Goal not reached");
	}
	
	
	/*----------------------------------------------------Helper Methods-------------------------------------------------------*/
	
	private static void getPath(Node current) {
		if(current.parent == null) {
			System.out.print("(" + current.y + "," + current.x +")");
			return;
		}
		getPath(current.parent);
		System.out.print(", " + "(" + current.y + "," + current.x +")");
		return;
	}
	
	private static int getCost(Node current) {
		if(current.parent == null)
			return 0;
		return current.weight + getCost(current.parent);
	}
	
	private static boolean isGoalReachable(int[][] map, int x, int y) { 
	    	
	        Node[] adjList = new Node[4];
	        adjList[0] = lookUp(map, x, y, null); 
	        adjList[1] = lookDown(map, x, y, null);
	        adjList[2] = lookLeft(map, x, y, null);
	        adjList[3] = lookRight(map, x, y, null);
	        for(Node neighbor: adjList) {
	        	if(neighbor != null)
	        		if(neighbor.weight != 0)
	        			return true;
	        }
	        return false;
	    }
	
	private static Node[] generateSuccessor(int[][] map, int x, int y, Node parent) { 
		 
        Node[] adjList = new Node[4];
        adjList[0] = lookUp(map, x, y, parent); 
        adjList[1] = lookDown(map, x, y, parent);
        adjList[2] = lookLeft(map, x, y, parent);
        adjList[3] = lookRight(map, x, y, parent);

        return adjList;
    }
	
	private static Node lookDown(int[][] map, int x, int y, Node parent) {
	    if(y+1 < map.length) {
		    	if (map[y + 1][x] != 0)
		            return new Node(map[y + 1][x], x, y+1, parent);
		    	return null;
	    }
	     else return null;
	 }
	
	 private static Node lookRight(int[][] map, int x, int y, Node parent) {
	 	if(x+1 < map[0].length) {
		        if (map[y][x +1] != 0)
		            return new Node(map[y][x + 1], x+1, y, parent);
		        return null;
	 	}
	     else return null;
	 }
	
	 private static Node lookLeft(int[][] map, int x, int y, Node parent) {
	     if(x-1 >= 0) {
		    	if (map[y][x - 1] != 0)
		            return new Node(map[y][x - 1], x-1, y, parent);
		    	return null;
		    }
	     else return null;
	 }
	
	 private static Node lookUp(int[][] map, int x, int y, Node parent) { 
	 	if(y-1 >= 0) {
		        if (map[y - 1][x] != 0)
		            return new Node(map[y - 1][x], x, y-1, parent);
	 		return null;
	 	}
	     else return null;
	 }
	 
	 private static void printMatrix(final int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
	  }
	
}