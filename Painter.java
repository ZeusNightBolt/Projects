//Will Kozar and Kathan thakkar collabaretively finished this.
//classID: 141 and 74 respectively

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painter extends JPanel {

	private static int whichFile = 0; //indicates which file is being input
	private static int show = 0; //indicator variable for whether to show map (0 = no, 1 = yes)
	private static int directions = 0; //indicator variable for whether to print directions (0 = no, 1 = yes)
	private static String startIntersection = ""; //starting intersection
	private static String endIntersection = ""; //ending intersection
	private static String fileName = ""; //filename

	GraphCreator graphCreator = new GraphCreator();
	Graph graph = graphCreator.createFromFile(fileName);
	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	
	public void paintComponent(Graphics g) {		
		if (show == 1) { //code to print the map in the canvas			
			Graphics2D g2d = (Graphics2D) g;			
			List<Edge> listOfEdges = graph.getEdge(); //obtains edges from graph class
			for (int i = 0; i < listOfEdges.size(); i++){ //obtains the latitude and longitude for the vertices for each edge				
				double x1 = listOfEdges.get(i).getV().getLongitude();
				double y1 = listOfEdges.get(i).getV().getLatitude();
				double x2 = listOfEdges.get(i).getW().getLongitude();
				double y2 = listOfEdges.get(i).getW().getLatitude();				
				g2d.setPaint(Color.BLACK); //paints edges and vertices in black				
				Shape shape = null;				
				if (whichFile == 1) { //if ur.txt is input
					//formats  to fit in window
					shape = new Line2D.Double((getWidth()*((100000*(x1+90)-1236185)))/1800, (getHeight()*(-(100000*(y1+40)-8312447)+800))/1000, (getWidth()*(100000*(x2+90)-1236185))/1800, (getHeight()*(-(100000*(y2+40)-8312447)+800))/1000);
				}				
				//monroe and NYS are significantly bigger maps than UR, so different dimensions must be used
				if (whichFile == 2) { //if monroe.txt is selected					
					shape = new Line2D.Double((getWidth()*(2000*(x1+90)-23820))/1800, (getHeight()*(-(((2000*(y1+40)))-165800)+1000))/1000, (getWidth()*(2000*(x2+90)-23820))/1800, (getHeight()*(-(((2000*(y1+40)))-165800)+1000))/1000);
				}
				if (whichFile == 3) {
					//if nys.txt is selected
					shape = new Line2D.Double((getWidth()*((200*(x1+90)-1950)))/1800, (getHeight()*(-(200*(y1+40)-15900)+1150))/1000, (getWidth()*((200*(x2+90)-1950)))/1800, (getHeight()*(-(200*(y2+40)-15900)+1150))/1000);
				}

				g2d.draw(shape);			
			}

			if (directions == 1) {
				//if directions are to be printed
				int startIntersectionIndex = 0;
				int endIntersectionIndex = 0;
				
				for (int i = 0; i < graph.getVertex().size(); i++) { //loops through each vertex
			 
					if (graph.getVertex().get(i).getName().equals(startIntersection)) {				
						startIntersectionIndex = i;
					}
				}				
				for (int j = 0; j < graph.getVertex().size(); j++) { //loops through each vertex
					
					if (graph.getVertex().get(j).getName().equals(endIntersection)) {
						
						endIntersectionIndex = j;
					}
				}
				
				dijkstra.dijkstra(graph.getVertex().get(startIntersectionIndex)); //calculates shortest path from initial intersection
				LinkedList<Vertex> pathInListForm = dijkstra.getPath(graph.getVertex().get(endIntersectionIndex)); //stores the vertices visited to reach end vertex
				
				if (pathInListForm == null) { //if no path exists
					
					System.out.println("There exists no path between " + graph.getVertex().get(startIntersectionIndex).getName() + " and " + graph.getVertex().get(endIntersectionIndex).getName() + ".");
					
				} 
				else { //creates list of edges traveled
					
					List<Edge> edges = graph.getEdge();
					int j = 0;
					double totalDistance = 0;
					
					System.out.println("Roads Traveled: ");
					
					for (int i = 1; i < pathInListForm.size(); i++) {
						//iterates through path, painting edges green
						for (int p = 0; p < edges.size(); p++) {
							
							if (edges.get(p).getV().equals(pathInListForm.get(j)) && edges.get(p).getW().equals(pathInListForm.get(i))) {
								
								System.out.println(edges.get(p).getID());
								totalDistance += edges.get(p).getWeight();
								double x1 = edges.get(p).getV().getLongitude();
								double y1 = edges.get(p).getV().getLatitude();
								double x2 = edges.get(p).getW().getLongitude();
								double y2 = edges.get(p).getW().getLatitude();
								
								g2d.setPaint(Color.GREEN);
								
								Shape shape = null;
								
								if (whichFile == 1) {
									
									shape = new Line2D.Double((getWidth()*((100000*(x1+90)-1236185)))/1800, (getHeight()*(-(100000*(y1+40)-8312447)+800))/1000, (getWidth()*(100000*(x2+90)-1236185))/1800, (getHeight()*(-(100000*(y2+40)-8312447)+800))/1000);
								}
								
								if (whichFile == 2) {
									
									shape = new Line2D.Double((getWidth()*(2000*(x1+90)-23820))/1800, (getHeight()*(-(((2000*(y1+40)))-165800)+1000))/1000, (getWidth()*(2000*(x2+90)-23820))/1800, (getHeight()*(-(((2000*(y1+40)))-165800)+1000))/1000);
								}
								
								if (whichFile == 3) {
									
									shape = new Line2D.Double((getWidth()*((200*(x1+90)-1950)))/1800, (getHeight()*(-(200*(y1+40)-15900)+1150))/1000, (getWidth()*((200*(x2+90)-1950)))/1800, (getHeight()*(-(200*(y2+40)-15900)+1150))/1000);
								}
								
								g2d.draw(shape);
							}
						}
						j++;
					}
					System.out.println("Total Distance Traveled: " + totalDistance);
				}
			}
		} 
		else {
			//same printing of directions, if show is not selected
			if (directions == 1) {
				
				int startIntersectionIndex = 0;
				int endIntersectionIndex = 0;
				
				for (int i = 0; i < graph.getVertex().size(); i++) {
				
					if (graph.getVertex().get(i).getName().equals(startIntersection)) {
						
						startIntersectionIndex = i;
					}
				}
				
				for (int j = 0; j < graph.getVertex().size(); j++) {
					
					if (graph.getVertex().get(j).getName().equals(endIntersection)) {
						
						endIntersectionIndex = j;
					}
				}				
				dijkstra.dijkstra(graph.getVertex().get(startIntersectionIndex));
				LinkedList<Vertex> pathList = dijkstra.getPath(graph.getVertex().get(endIntersectionIndex));
				
				if (pathList == null) {
					
					System.out.println("There exists no path between " + graph.getVertex().get(startIntersectionIndex).getName() + " and " + graph.getVertex().get(endIntersectionIndex).getName() + ".");
					
				} else {
					
					List<Edge> edges = graph.getEdge();
					int j = 0;
					double totalDistance = 0;
					
					System.out.println("Roads Traveled: ");
					
					for (int i = 1; i < pathList.size(); i++) {
						
						for (int p = 0; p < edges.size(); p++) {
							
							if (edges.get(p).getV().equals(pathList.get(j)) && edges.get(p).getW().equals(pathList.get(i))) {
								
								System.out.println(edges.get(p).getID());
								totalDistance += edges.get(p).getWeight();
							}
						}
						j++;
					}
					System.out.println("Total Distance Traveled: " + totalDistance);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		if (args[0].equals("ur.txt")) {
			fileName = args[0];
			whichFile = 1;
		}
		if (args[0].equals("monroe.txt")) {
			fileName = args[0];
			whichFile = 2;
		}
		if (args[0].equals("nys.txt")) {
			
			fileName = args[0];
			whichFile = 3;
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("directions")) {
				directions = 1;
				startIntersection = args[i+1];
				endIntersection = args[i+2];
			}
			if (args[i].equals("show")) {
				show = 1;
			}	
		}
		JFrame frame = new JFrame("Animation");
		Painter canvas = new Painter();
		//Adding panel to frame
		frame.add(canvas);
		frame.setSize(1800, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
