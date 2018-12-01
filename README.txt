Name: Kathan Thakkar and Will Kozar
kthakkar@u.rochester.edu and wkozar@u.rochester.edu
Classid: 74 and 141
Project 4 (STREET MAPPING)

Folder Content 
DijkstraAlgorithm.java
	This class calculates the shortest path between two input vertices
Edge.java
	This class is used to represent the define the edge type
Graph.java
	This class is used to represent the graph
GraphCreator.java
	This class creates the graph using the input text file
Painter.java
	Graphics class. Used as driver class as well. Creates image of graph
Vertex.java
	This class is used to define the Vertex type.
README.txt
	This file.

This code takes in the text file specified in the command line. The bulk of the code executed comes from the Painter class. The Painter class determines which file to use, then creates a map with edges connecting the vertices, using lists of edges and vertices. Each edge is drawn by finding the coordinates of the two vertices for the specific edge, and drawing a line between them. Using Dijksra's algorithm, the shortest is determined. That path is colored green and the edges traversed in the path are printed.

We found difficulty with the implementation of dijkstra's algorithm, and used an online source (http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html) for help. 


The graphing algorithm is O(|v|) where |v| is the number of vertices. The graph was mapped by iterating through the edges. The big datasets like nys.txt cause problems as the runtime is very slow and inefficient. The Dijkstra's algorithm i used is O(|V|^2). The program iterates through every vertice, and getPath iterates through the nodes to add the path vertices to the LinkedList.




