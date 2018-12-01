//Will Kozar and Kathan thakkar collabaretively finished this.
//classID: 141 and 74 respectively
public class Edge {
	
	private String name; //edge name
	private Vertex v; //start vertex
	private Vertex w; //end vertex
	private double weight; //distance of edge

	public Edge(String name, Vertex v, Vertex w, double weight) {  //Edge takes in the edge's name, the two vertices being connected, and the distance of the edge
		this.name = name;
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	//getter methods
	public String getID() { 
		return name;
	}
	public Vertex getV() {	
		return v;
	}
	public Vertex getW() {
		return w;
	}
	public double getWeight() {	
		return weight;
	}
	//Setter methods
	public void setID(String name) {	
		this.name = name;
	}
	public void setV(Vertex v) {
		this.v = v;
	}
	public void setW(Vertex w) {
		this.w = w;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
}

