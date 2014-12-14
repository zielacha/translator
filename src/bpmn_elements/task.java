package bpmn_elements;

/* task w BPMN
task - completionQuantity, id, isForCompensation, name, startQuantity
- incoming - sid 
- outgoing - sid */

public class task extends Item{
	private int completionQuantity;
	private int startQuantity;

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
