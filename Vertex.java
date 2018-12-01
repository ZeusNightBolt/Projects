//Will Kozar invdividually finsihed this.
//classID: 141

public class Vertex {
	
	private String name; //name of the intersection
	public int IDnumber; //id of the intersection
	private double latitude; //latitude of intersection
	private double longitude; //longitude of intersection
	
	
	public Vertex(String name, double latitude, double longitude, int IDnumber) {	//vertex will take in name, idnumber, latitude, and longitude	
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.IDnumber = IDnumber;
	}	
	//Getter methods
	public String getName() {		
		return name;
	}
	public double getLatitude() {		
		return latitude;
	}
	public double getLongitude() {		
		return longitude;
	}
	public int getIDnumber() {		
		return IDnumber;
	}	
//Setter methods	
	public void setName(String name) {		
		this.name = name;
	}
	public void setLatitude(double latitude) {		
		this.latitude = latitude;
	}	
	public void setLongitude(double longitude) {		
		this.longitude = longitude;
	}
	public void setIDnumber(int IDnumber) {		
		this.IDnumber = IDnumber;
	}	
}
