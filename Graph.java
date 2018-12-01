//Will Kozar and Kathan thakkar collabaretively finished this.
//classID: 141 and 74 respectively
import java.util.List;

public class Graph {
	

	 private final List<Vertex> vertexList; //list of vertexes to graph
	 private final List<Edge> edgeList; //list of edges to graph

	 public Graph(List<Vertex> vertexList, List<Edge> edgeList) { //graph takes in list of vertexes and edges
	   this.vertexList = vertexList;
	   this.edgeList = edgeList;
	 }

	 public List<Vertex> getVertex() { //getter for vertices 
	   return vertexList;
	 }

	 public List<Edge> getEdge() { //setter for edges
	   return edgeList;
	 }
}