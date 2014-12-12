package bpmn_elements;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import bpmn_elements.*;
import static java.lang.Math.*;

public class Item {
	  private String bpmn_id; 
	 
	  private String name;
	  private float height; 
	  private float width;
	  private float x;
	  private float y;
	  private String type;
	  private List<String> incoming = new ArrayList<String>();
	  private List<String> outgoing = new ArrayList<String>();
	  
	  private String gatewayDirection;
	  //private String current;
	  //private String interactive;
	  
// ID BPMN	  
	  public String getbpmn_id() {
	    return bpmn_id;
	  }
	  public void setbpmn_id(String bpmn_id) {
	    this.bpmn_id = bpmn_id;
	  }
// name	  
	  public String getname() {
	    return name;
	  }
	  public void setname(String name) {
	    this.name = name;
	  }
// height	  
	  public float getheight(){
		  return height;
	  }	  
	  public void setheight(float height){
		  this.height = height;
	  }
// width	  
	  public float getwidth(){
		  return width;
	  }
	  public void setwidth(float width){
		  this.width = width;
	  }
// Y	  
	  public float getY(){
		  return y;
	  }
	  public void setY(float y){
		  this.y = y;
	  }
// X	  
	  public float getX(){
		  return x;
	  }
	  public void setX(float x){
		  this.x = x;
	  }
// type	  
	  public String gettype(){
		  return type;
	  }
	  public void settype(String type){
		  this.type = type;
	  }
	  
	  public void addOut(String s){
		  outgoing.add(s);
	  }
	  public void addIn(String s){
		  incoming.add(s);
	  }
	  
	  public int getInSize(){
		  return incoming.size();
	  }
	  
	  public int getOutSize(){
		  return outgoing.size();
	  }
	  
	  public List<String> getIncoming(){
		  return incoming;
	  }
	  public List<String> getOutgoing(){
		  return outgoing;
	  }
	  
	  @Override
	  public String toString() {
	    return type + "[id=" + bpmn_id + ", name=" + name + ", height=" + height + ", width=" + width + 
	    		", x=" + x + ", y=" + y + ", incoming=" + incoming + ", outgoing=" + outgoing + "]";
	  }
	  
	// Suma outgoing+incoming = liczba portów agenta w Alvis
	  public int sum_of_going(String id){
			int suma;
			suma = incoming.size() + outgoing.size();
			return suma;
		}
	  
	  
	} 


/*

startEvent - id, name, ==> Item
		   - outgoing

endEvent - id, name, ==> Item
		 - ingoing 

task - completionQuantity, id, isForCompensation, name, startQuantity
	 - incoming - sid 
	 - outgoing - sid

sequenceFlow - id, name, sourceRef, targetRef,

exclusiveGateway - gatewayDirection, id, name
				 - incoming - kilka, osobne tagi
				 - outgoing

inclusiveGateway


³¹czniki...


*/