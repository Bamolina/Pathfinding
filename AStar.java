// Java Program to illustrate reading from Text File 
// using Scanner Class 

import java.util.*;

/*TEAM MEMBERS: CRISTIAN MOLINA AND BRYAN MOLINA */

public class AStar {
    

    //---------------------Methods-----------------------//
    private static void printMatrix(final int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }


//    private static Node[] generateSuccessorAstar(int[][] map, int x, int y, Node goal) { 
//    	//every time you create a node, calculate its heuristic value with getF.
//        Node[] adjList = new Node[4];
//        
//        adjList[0] = lookUp(map, x, y); 
//        adjList[0].setF(getDistance(adjList[0], goal));
//        
//        adjList[1] = lookRight(map, x, y);
//        adjList[1].setF(getDistance(adjList[1], goal));
//        
//        adjList[2] = lookLeft(map, x, y);
//        adjList[2].setF(getDistance(adjList[2], goal));
//        
//        adjList[3] = lookDown(map, x, y);
//        adjList[3].setF(getDistance(adjList[3], goal));
//
//        return adjList;
//    }
    
    
    static void aStar2(Node start, Node goal, int[][] map) {
    	long startTime = System.currentTimeMillis();
    	long timeLimit = 180000; //time limit in milliseconds
        PriorityQueue < Node > openSet = new PriorityQueue < Node > (); //set of nodes to be evaluated
        int[][] visitedMap = new int[map.length][map[0].length]; //Keeps track of the visited nodes
        int counter = 0;
        openSet.add(start);
        while (!openSet.isEmpty()) { //loop
        	long currentTime = System.currentTimeMillis();
        	if(currentTime - startTime >= timeLimit) {
        		System.out.println("Time limit exceeded");
        		return;
        	}
        	
            Node currentNode = openSet.poll(); //current = node in OPEN with lowest f cost
            counter++;
            visitedMap[currentNode.y][currentNode.x] = 1;
            if (currentNode.isEqual(goal)) {
            	long timeElapsed = System.currentTimeMillis() - startTime;
            	System.out.println("Cost of the path: " + getCost(currentNode));
            	System.out.println("Nodes expanded: " + counter);
            	System.out.println("Nodes in memory: " + openSet.size());
            	System.out.println("Runtime: " + timeElapsed + " milliseconds");
            	getPath(currentNode);
            	break;
            }
          
             //for each neighbor of the current node
            
  /*--------------if the neighbor is not traversable or neighbor is in CLOSED, skip to the next neighbor--------------------*/
                      //if the neighbor is traversale and neighbor is in OPEN, 
            Node[] neighborhood = generateSuccessor(map, currentNode.x, currentNode.y, currentNode);
                for (Node neighbor: neighborhood) { //store the adjacency list somewhere pass it to the for loop for readability
                	if(neighbor==null)
                			continue;
                   if(visitedMap[neighbor.y][neighbor.x]==1)
                	   continue;
                	int tentativeGScore = currentNode.weight + getDistance(currentNode, neighbor);
                
                	if(!openSet.contains(neighbor))
                		 openSet.add(neighbor);
                	
                	if(visitedMap[neighbor.y][neighbor.x] != 1) {
						visitedMap[neighbor.y][neighbor.x] = 1;
					}
                	else if(tentativeGScore >= neighbor.weight)
                			continue;
                	neighbor.weight = tentativeGScore;
                	neighbor.setFCost(neighbor.weight + getDistance(neighbor, goal));
                    
                }//for each end
        } //while loop (Astar)
    }//Astar end

    /*returns the Manhattan distance of any 2 input nodes*/
    
    private static Node[] generateSuccessor(int[][] map, int x, int y, Node parent) { 
    	 
        Node[] adjList = new Node[4];
        adjList[0] = lookUp(map, x, y, parent); 
        adjList[1] = lookDown(map, x, y, parent);
        adjList[2] = lookLeft(map, x, y, parent);
        adjList[3] = lookRight(map, x, y, parent);

        return adjList;
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


    
    
   static  int getDistance(Node nodeA, Node nodeB) {
    	int dstX = Math.abs(nodeA.x - nodeB.x);
    	int dstY = Math.abs(nodeA.y - nodeB.y);
    	return dstX + dstY;
    	
    }

   static  ArrayList<Node> retracePath(Node startNode, Node endNode) {
    	 ArrayList<Node> path = new ArrayList<Node>();
    	 Node currentNode = endNode;
    	 while(currentNode != startNode) {
    		 path.add(currentNode);
    		 currentNode = currentNode.parent;
    		 Collections.reverse(path);
    	 }
		return path;
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
}

 //class