package bpmn_elements;

import java.util.ArrayList;
import java.util.List;
/* task w BPMN
task - completionQuantity, id, isForCompensation, name, startQuantity
- incoming - sid 
- outgoing - sid */

public class task extends Item{
	private int completionQuantity;
	private int startQuantity;
	private boolean isForCompensation;

	// incoming i outgoing
//	private List<String> incoming = new ArrayList<String>();
//	private List<String> outgoing = new ArrayList<String>();
//	
//	// Suma outgoing+incoming = liczba portów agenta w Alvis
//	public int sum_of_going(String id){
//		int suma;
//		suma = incoming.size() + outgoing.size();
//		return suma;
//	}
//	
//	  public void addOut(String s){
//		  outgoing.add(s);
//	  }
//	  public void addIn(String s){
//		  incoming.add(s);
//	  }

//completionQuantity
	public int getcompletionQuantity() {
	    return completionQuantity;
	  }
	public void setcompletionQuantity(int completionQuantity) {
	    this.completionQuantity = completionQuantity;
	  }
// startQuantity	
	public int getstartQuantity() {
	    return startQuantity;
	  }
	public void setstartQuantity(int startQuantity) {
	    this.startQuantity = startQuantity;
	  }
}
