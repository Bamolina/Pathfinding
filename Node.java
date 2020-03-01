/*TEAM MEMBERS: CRISTIAN MOLINA AND BRYAN MOLINA */

public class Node implements Comparable<Node> {
	int weight;
	int x;
	int y;
	int hCost;
	int f;
	Node parent;
	
	public Node(int v, int x, int y) {
		this.weight = v;
		this.x = x;
		this.y = y;
		this.hCost = 0;
		this.f= 0;
		this.parent = null;
	}
	
	public Node(int v, int x, int y, Node p) {
		this.weight = v;
		this.x = x;
		this.y = y;
		this.hCost = 0;
		this.f= 0;
		this.parent = p;
	}
	
	public Node(int v, int x, int y, int h, Node p) {
		this.weight = v;
		this.x = x;
		this.y = y;
		this.hCost = h;
		this.f= weight + h;
		this.parent = p;
	}
	
	public int compareTo(Node neighbour) {
		if(this.f > neighbour.f)
			return 1;
		else if(this.f < neighbour.f)
			return -1;
		else
		return 0;
	}
	
	public boolean isEqual(Node other) {
		if(this.x == other.x && this.y == other.y)
			return true;
		else 
			return false;
	}
	
	void setHeuristic(int heuristic) {
		this.hCost = heuristic;
		this.f = this.weight + this.hCost;
	}

	void setFCost(int weightedHeuristic) {
		this.f = weightedHeuristic;
	}
	
	
}
