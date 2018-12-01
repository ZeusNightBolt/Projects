//Will Kozar and Kathan thakkar collabaretively finished this.
//classID: 141 and 74 respectively
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {
	
	//http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html used for help with implementation.

	 private final List<Vertex> nodes;
	 private final List<Edge> edges;
	 private Set<Vertex> visitedNodes;
	 private Set<Vertex> unvisitedNodes;
	 private Map<Vertex, Vertex> predecessors;
	 private Map<Vertex, Integer> distance;

	 public DijkstraAlgorithm(Graph graph) {
	   // create a copy of the array so that we can operate on this array
	   this.nodes = new ArrayList<Vertex>(graph.getVertex()); //sets the vertices of the graph as nodes
	   this.edges = new ArrayList<Edge>(graph.getEdge()); //sets the edges of the graph as edges
	 }
	 
	 public void dijkstra(Vertex firstVertex) {
		 
		 visitedNodes = new HashSet<Vertex>(); //creates hashset of visited vertices
		 unvisitedNodes = new HashSet<Vertex>(); //creates hashset of unvisited vertices
		 distance = new HashMap<Vertex, Integer>(); //stores distance for each vertex from initial vertex
		 predecessors = new HashMap<Vertex, Vertex>();  //stores vertex visited prior to current vertex
		 distance.put(firstVertex, 0); //initial vertex has distance of 1
		 unvisitedNodes.add(firstVertex);
		 
		 while (unvisitedNodes.size() > 0) { //while there are still unvisited nodes
			 Vertex node = getMinimum(unvisitedNodes); //calls getMinimum method for node
		     visitedNodes.add(node); //adds node to visitedNodes
		     unvisitedNodes.remove(node); //removes node from visitedNodes
		     findMinimalDistances(node); //finds the next distane from each neightboring node
		 }
	 }
	 
	 private void findMinimalDistances(Vertex node) {
		 List<Vertex> adjacentNodes = getNeighbors(node); //looks at all neighboring nodes
		 for (Vertex target : adjacentNodes) { 
			 if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) { //updates the distance if the total distance to the target node is shorter
		        distance.put(target, (int) (getShortestDistance(node) + getDistance(node, target))); //puts target node in distance hashmap with new total distance
		        predecessors.put(target, node); //updates predecessors list
		        unvisitedNodes.add(target); 
		     }
		 }
	 }
	 
	 private double getDistance(Vertex node, Vertex target) {
		 
		 for (Edge edge : edges) {
			 if (edge.getV().equals(node) && edge.getW().equals(target)) {
		        
				 return edge.getWeight(); //returns distance calculated in graph creator class
		      }
		    }
		    throw new RuntimeException("Should not happen");
	 }
	 
	 private List<Vertex> getNeighbors(Vertex node) {
		 
		 List<Vertex> neighbors = new ArrayList<Vertex>();
		 for (Edge edge : edges) {
			 if (edge.getV().equals(node) && !isVisited(edge.getW())) { //searches for unvisited vertices which are connected to node by an edge
		        neighbors.add(edge.getW());
		      }
		 }
		 
		 return neighbors;
	 }
	 
	 private Vertex getMinimum(Set<Vertex> vertices) {
		 
		 Vertex minimum = null;
		 for (Vertex vertex : vertices) {
			 if (minimum == null) {
		        minimum = vertex;
		      } 
			 else {
		        if (getShortestDistance(vertex) < getShortestDistance(minimum)) { //sets the vertex which has the minimum distance from current vertex
		          minimum = vertex;
		        }
		      }
		 }
		 return minimum;
	 }
	 
	 private boolean isVisited(Vertex vertex) {
		 
		    return visitedNodes.contains(vertex);
	 }
	 
	 private int getShortestDistance(Vertex destination) {
		 
		 Integer dist = distance.get(destination);
		 if (dist == null) { //sets initial minimum distance to max value
		      return Integer.MAX_VALUE;
		 } else { 
		      return dist;
		 }
	 }
	 
	 public LinkedList<Vertex> getPath(Vertex target) {
		 
		 LinkedList<Vertex> path = new LinkedList<Vertex>(); //link of vertices connected by dijkstra's
		 Vertex pathStep = target;
		 // check if the vertex is on the path `
		 if (predecessors.get(pathStep) == null) {
			 
			 return null;
		 }
		 path.add(pathStep);
		 while (predecessors.get(pathStep) != null) {
			 
			 pathStep = predecessors.get(pathStep);
		     path.add(pathStep);
		 }
		 // return it in the correct order
		 Collections.reverse(path);
		 return path;
	 }
	 
	 
}
