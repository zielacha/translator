package bpmn_elements;



public class gateway extends Item{
	private String gatewayDirection; // moze przyjac tylko dwa argumenty 
//	private String type; 
    public Item prew; //prev ;pnoo
	public Item next;
	
// na podstawie pola gatewayDirection -> do którego agenta dodaæ porty.
// na podstawie pola type -> ile portów dodaæ 

// mo¿e przechowywaæ gdzieœ tutaj ID task'a przed bramk¹ i po bramce ? 

// gatewayDirection
	public String getGatewayDirection(){
		return gatewayDirection;
	}
	public void setGatewayDirection (String gatewayDirection){
		this.gatewayDirection = gatewayDirection;
	}
// type 	
//	public String gettype(){
//		return type;
//	}
//	
//	public void settype(String type){
//		this.type = type;
//	}
	
//	@Override
//	public String toString() {
//		return "[GATEWAY gateway Direction=" + gatewayDirection + ", BPMN_ID =" + getbpmn_id() + 
//				 ", incoming=" + getIncoming() + ", outgoing=" + getOutgoing() + "]";
//	}
	
	
	
}

//<parallelGateway gatewayDirection="Converging" id="sid-33AEE21E-3B19-4AD9-B953-4F2FA94E8D10" name="">
//<inclusiveGateway gatewayDirection="Diverging" id="sid-A9DEC949-B514-4E3F-8A84-C41174D81EC9" name="">
//<exclusiveGateway gatewayDirection="Diverging" id="sid-1F254C33-0142-4456-87A5-4CDEAA71D85C" name="">