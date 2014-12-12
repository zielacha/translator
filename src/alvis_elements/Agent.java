package alvis_elements;

import static java.lang.Math.abs;
import bpmn_elements.Item;

import java.util.*;

import alvis_elements.Port;

public class Agent {
//	<agent active="1" height="100.0" index="1" name="Agent_0" running="0" width="140.0" x="20.0" y="20.0">
	private String active;
	private int alvis_id;
	private String bpmn_id;
	private float height;
	private String index;
	private String name;
	private String running;
	private float width;
	private float x;
	private float y;
	private List<Port> ports; 
	private static int agentNum = 0;
	private int portNum = 0;

	public int count_attr = 8;

	public Agent(Item it) {
		this.active = "1";
		this.height = it.getheight();
		this.index = "1";
		//TODO: zmiana spacji na _
		if(it.getname().equals("")){
			this.name = "Agent_" + agentNum;
			agentNum++;
		}else{
			this.name = it.getname();
		}
		this.running = "0";
		this.width = it.getwidth();
		this.x = it.getX();
		this.y = it.getY();
		ports = new ArrayList<Port>();
		for (int i = 0; i < it.getInSize() + it.getOutSize(); i++) {
			ports.add(new Port());
		}	
		this.alvis_id = changeID(it.getbpmn_id());
		this.bpmn_id = it.getbpmn_id();
	}
	// change bpmn ID to Alvis ID
		  public int changeID(String bpmn_id){ 
			  Random rand = new Random();
			  int x = 0;
			  x = rand.nextInt() + 1;
			  if( x < 0 ){
				  x = abs(x);	
			  }
			  setalvis_id(x);
			  // TODO: sprawdzanie czy ID jest unikalne
			  
			  return alvis_id;
		  }
// ID ALVIS  
	public int getalvis_id() {
		return alvis_id;
	}
	
	public void setalvis_id(int alvis_id) {
		this.alvis_id = alvis_id;
	}
	
	public String getbpmn_id() {
		return bpmn_id;
	}
	
// active
	public String getactive(){
		return String.valueOf(active);
	}
	
	public void setactive(int active) {
		this.active = "1";
	}

// height
	public String getheight() {
		return String.valueOf(height);
	}
	public void setheight(int height) {
		this.height = height;
	}

// index
	public String getindex() {
		return String.valueOf(index);
	}
	public void setindex(int index) {
		this.index = "1";
	}

// name
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}

// running
	public String getrunning() {
		return String.valueOf(running);
	}
	public void setrunning(String running) {
		this.running = running;
	}

// width
	public String getwidth() {
		return String.valueOf(width);
	}
	public void width(int width) {
		this.width = width;
	}

// X
	public String getX() {
		return String.valueOf(x);
	}
	public void setX(int x) {
		this.x = x;
	}

// Y
	public String getY() {
		return String.valueOf(y);
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getPortNum(){
		return portNum;
	}
	
	public int getPortId(int pnum){
		return ports.get(portNum).getid();
	}
	
	public void incPortNum(){
		portNum++;
	}
	
	public Port getPort(int num){
		return ports.get(num);
	}
	

	public List<Port> getPorts() {
		return ports;
	}
}
