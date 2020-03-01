import java.util.LinkedList;

/*TEAM MEMBERS: CRISTIAN MOLINA AND BRYAN MOLINA */

public class IDS{
	static int nodesExpanded;
	
	
	/*---------------------------------------------------IDS Method--------------------------------------------------*/
	 public static void IDS(int[][] map, Node start, Node goal) {
		 long startTime = System.currentTimeMillis();
		 long timeLimit = 180000;
		 nodesExpanded = 0;
		 for (int depth = 0; depth < Integer.MAX_VALUE; depth++) {
			if(System.currentTimeMillis() - startTime >= timeLimit) {
				System.out.println("Time limit exceeded");
				return;
			}
			
			int[][] visitedMap = new int[map.length][map[0].length];
			LinkedList<Node> fringe = new LinkedList<Node>();
			fringe.push(start);
			Node reached = DLS(map, start, goal,visitedMap, fringe, depth);
			
			if(reached != null) {
				long timeElapsed = System.currentTimeMillis() - startTime;
				System.out.println("Cost of the path: " + getCost(reached));
				System.out.println("Nodes expanded: " + nodesExpanded);
				System.out.println("Nodes in memory: " + fringe.size());
				System.out.println("Runtime: " + timeElapsed + " milliseconds" );
				getPath(reached);
				return;
			}
		}
	 }
	 /*---------------------------------------------------DLS Method--------------------------------------------------*/
	 private static Node DLS(int[][] map, Node current, Node goal, int[][] visitedMap, LinkedList<Node> fringe, int depth) {
	    if (current.isEqual(goal) && depth == 0) {
	        return current;
	    }
	    
	    visitedMap[current.y][current.x] = 1;
	    nodesExpanded++;
	    
	    if (depth > -1) {
	    	Node[] neighborhood = generateSuccessor(map,current.x, current.y, current);
	        for (Node neighbor: neighborhood) {
	        	if(neighbor == null) continue;
	        	if(visitedMap[neighbor.y][neighbor.x] != 1 ) {
	        		fringe.push(neighbor);
		            Node child = DLS(map, neighbor, goal, visitedMap, fringe, depth - 1);
		            if (child != null) {
		            	fringe.push(child);
		                return child;
		            }
	            }
	        }
	    }
	    return null;
	}
	
	
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
