//Will Kozar and Kathan thakkar collabaretively finished this.
//classID: 141 and 74 respectively

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
public class GraphCreator {
	
	 public static Graph createFromFile(String fileName) {
		 
		 int counter = 0;
		 
		 List<Vertex> vertices = new ArrayList<Vertex>();
		 List<Edge> edges = new ArrayList<Edge>();
			
			try {
				//Scanning in the file that the user specifies.
				Scanner scan = new Scanner(new File(fileName));

				//Reading the contents of the file.
				while (scan.hasNext()) {
					
					String line = scan.nextLine();
					String[] result = line.split("\\s");
					if (result[0].equals("i")) { //if intersection is scanned
						
						String intersectionID = result[1]; //id is the first entry after the i
						double latitude = Double.parseDouble(result[2]); //first number is the latitude
						double longitude = Double.parseDouble(result[3]); //second number is the longitude
						
						vertices.add(new Vertex(intersectionID, latitude, longitude, counter)); //adds vertex to vertice list
						
						counter++;
						
					} else { //if not an intersection, it is a road

						String roadID = result[1]; //second entry is roadID
						int indexOfVertex1 = 0; 
						int indexOfVertex2 = 0;
						
						for (int i = 0; i < vertices.size(); i++) {
							
							if (vertices.get(i).getName().equals(result[2])) { //checks to see which vertices match the latitude of the edge
								
								indexOfVertex1 = i;
							}
							
							if (vertices.get(i).getName().equals(result[3])) { //checks to see which vetices match the longitude of the edge
								
								indexOfVertex2 = i;
							}
						}
						
						double x1 = vertices.get(indexOfVertex1).getLongitude();
						double y1 = vertices.get(indexOfVertex1).getLatitude();
						double x2 = vertices.get(indexOfVertex2).getLongitude();
						double y2 = vertices.get(indexOfVertex2).getLatitude();
						
						double weight = getDistance(x1, y1, x2, y2); //calculates distance between the two vertices
						
						edges.add(new Edge(roadID, vertices.get(indexOfVertex1), vertices.get(indexOfVertex2), weight)); //adds edge to edge list
					}
				}
				//Closes file.
				scan.close();

			} catch (FileNotFoundException e) { //message to print in case file cannot be found
				
				System.out.println(e.getMessage());
			}
			
			Graph graph = new Graph(vertices, edges); //graphs vertice and edge lists
			
			return graph;
		}
	 
	 public static double getDistance(double lat1, double long1, double lat2, double long2) { //determines distance between two points
		 
		 double latitude1 = Math.toRadians(lat1); //radians used for trig calculations
		 double longitude1 = Math.toRadians(long1);
		 double latitude2 = Math.toRadians(lat2);
		 double longitude2 = Math.toRadians(long2);
			
		 double distLat = latitude2 - latitude1; //distance of the latitudes
		 double distLong = longitude2 - longitude1; //distance of the longitudes
		//haversines formula used to calculate distance bewteen points. implementation found https://www.movable-type.co.uk/scripts/latlong.html
		 double a = (Math.sin(distLat/2)*Math.sin(distLat/2))+(Math.cos(latitude1)*Math.cos(latitude2)*Math.sin(distLong/2)*Math.sin(distLong/2));
		 double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		 double d = 3959*c; //accepted value for Earth's radius is 3959 miles
		 
		 return d;
		 
	 }
}
