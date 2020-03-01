import java.io.File;
import java.util.Scanner;


/*TEAM MEMBERS: CRISTIAN MOLINA AND BRYAN MOLINA */

public class Pathfinding {
	public static void main(String[] args) throws Exception {
		 /*Reading map file*/
//		String arg1 = args[0];
//		String arg2 = args[1];
		
		
        File file =
            new File("C:\\Users\\dontn\\Desktop\\map2.txt");

        Scanner sc = new Scanner(file);
        /*----------------------------------------------Creating Map -----------------------------------------------------*/
        int row = sc.nextInt();
        int column = sc.nextInt();
        int[][] map = new int[row][column];
        /*------------------------------------------Reading Start Node Info----------------------------------------------------*/
        int startRow = sc.nextInt();
        int startColumn = sc.nextInt();
        /*-----------------------------------------Reading End Node Info------------------------------------------------------*/
        int goalRow = sc.nextInt();
        int goalColumn = sc.nextInt();
        /*----------------------------------------storing the map--------------------------------------------------------------*/
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (sc.hasNextInt()) {
                    map[i][j] = sc.nextInt();
                }
            }
        }
        printMatrix(map);
        //----------------------------------Creating Starting & Goal Nodes ---------------------------------------------------*/
        Node start = new Node(map[startRow][startColumn], startColumn, startRow);
        Node goal = new Node(map[goalRow][goalColumn], goalColumn, goalRow);
        //start.setF(getDistance(start,goal));
        System.out.println("\nStart Node data");
        System.out.println("x = " + start.x);
        System.out.println("y = " + start.y);
        System.out.println("weight = " + start.weight);

        System.out.println("\nGoal Node data");
        System.out.println("x = " + goal.x);
        System.out.println("y = " + goal.y);
        System.out.println("weight = " + goal.weight);

        /*--------------------------------------------------BFS---------------------------------------------------------------*/
//        if(arg2.equalsIgnoreCase("BFS"))
//        BFS.BFS(map, start, goal);
        /*--------------------------------------------------IDS Search--------------------------------------------------------*/
//        if(arg2.equalsIgnoreCase("IDS"))
        IDS.IDS(map, start, goal);
        
        /*---------------------------------------------------A* Search--------------------------------------------------------*/
//        if(arg2.equalsIgnoreCase("AStar"))
//        AStar.aStar2(start, goal, map);
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
