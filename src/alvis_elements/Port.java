package alvis_elements;

import static java.lang.Math.abs;

import java.util.Random;

public class Port {
	private int id;
	private String name;
	private float x;
	private float y;
	private boolean isFree;
	
	public Port(){
		this.id = changeID();
//		this.name = 
		this.x = 0;
		this.y = 0;
		isFree = true;
	}
	
	public int changeID(){ 
		  Random rand = new Random();
		  int x = 0;
		  x = rand.nextInt() + 1;
		  if( x < 0 ){
			  x = abs(x);	
		  }
		  // TODO: sprawdzanie czy ID jest unikalne
		  
		  return x;
	}
	
	
<<<<<<< HEAD
	public int getid(){
=======
	public int getid (){
>>>>>>> 1f69f824b47d44c36251d9491b5a141aa5eb347b
		return id;
	}
	public void setid(int id){
		this.id = id;
	}
	
	public String getName (){
		return name;
	}
	public void setName(String name, Agent ag){
		this.name = name;
		ag.incPortNum();
	}
	
	public String getX (){
		return String.valueOf(x);
	}
	public void setX(float x){
		this.x = x;
	}
	
	public String getY (){
		return String.valueOf(y);
	}
	public void setY(float y){
		this.y = y;
	}
	
	public boolean isFree(){
		return isFree;
	}
	
	public void setIsFree(boolean b){
		isFree = b;
	}
	
	
	// <port id="1981927620" name="port_0" x="0.0" y="0.2"/>
	

}
