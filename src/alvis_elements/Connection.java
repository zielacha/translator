package alvis_elements;


public class Connection {
	
//	<connection direction="target" source="1122212160" style="straight" target="1925661722"/>
	private String direction;
	private int source;
	private String style;
	private int target;
	
	public Connection(int agS, int agT) {
		this.direction = "target";
		this.source = agS;
		this.style = "straight";
		this.target = agT;
	}

// direction
	public String getdirection (){
		return direction;
	}
	public void setdirection(String direction){
		this.direction = direction;
	}
// source
	public String getsource(){
		return String.valueOf(source);
	}
	public void setsource(int source){
		this.source = source;
	}
// style
	public String getstyle (){
		return style;
	}
	public void setstyle (String style) {
		this.style = style;
	}
// target
	public String gettarget () {
		return String.valueOf(target);
	}
	public void settarget (int target){
		this.target = target;
	}
	
}
