package staxparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import alvis_elements.*;
import bpmn_elements.*;

public class createXML {

	public List<Agent> agents;
	public List<Element> docAgents;
	public List<Connection> connList;
	

	public boolean creatingxml(List<Item> items, List<String> gatewayID) {
		agents = new ArrayList<Agent>();
		docAgents = new ArrayList<Element>();
		connList = new ArrayList<Connection>();

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			// <!DOCTYPE alvisproject PUBLIC "alvisPublicId-v0.1">
			Document doc = docBuilder.newDocument();
			DOMImplementation domImpl = doc.getImplementation();
			DocumentType doctype = domImpl.createDocumentType("alvisproject", "\"alvisPublicId-v0.1\"", 
																						"\"alvisSystemId-v0.1\"");
			doc.appendChild(doctype);
			
			Element alvisproject = doc.createElement("alvisproject");
			doc.appendChild(alvisproject);

			// hierarchy elements
			Element hierarchy = doc.createElement("hierarchy");
			alvisproject.appendChild(hierarchy);
			// node element
			Element node = doc.createElement("node");
			hierarchy.appendChild(node);
			// node attributes
			// defaultowo System, sk�d bra� nazw� ?
			node.setAttribute("agent", "");
			node.setAttribute("name", "System");

			// page elements
			Element page = doc.createElement("page");
			alvisproject.appendChild(page);
			page.setAttribute("name", "System");
			System.out.println(items.size());
			
			// forek na genty dla ka�dego Itema
			for (Item it : items) {
				if (it.gettype() == "agent") {
					Agent ag = new Agent(it);
					Element agent = doc.createElement("agent");
					page.appendChild(agent);
					// forek na porty
					for (int i = 0; i < it.getIncoming().size(); i++ ) {
						Port p = ag.getPort(ag.getPortNum());
						p.setName("port_" + String.valueOf(ag.getPortNum()), ag);
						Element port = doc.createElement("port");
						agent.appendChild(port);
						port.setAttribute("id", String.valueOf(p.getid()) );
						port.setAttribute("name", p.getName());
						port.setAttribute("x", p.getX());
						port.setAttribute("y", p.getY());
					}
					for (int i = 0; i < it.getOutgoing().size(); i++ ){
						Port p = ag.getPort(ag.getPortNum());
//						p.setid(ag.getPortId(ag.getPortNum()));
						p.setName("port_" + String.valueOf(ag.getPortNum()), ag);
						Element port = doc.createElement("port");
						agent.appendChild(port);
						port.setAttribute("id", String.valueOf(p.getid()) );
						port.setAttribute("name", p.getName());
						port.setAttribute("x", p.getX());
						port.setAttribute("y", p.getY());
					}
					
					agent.setAttribute("active", ag.getactive());
					agent.setAttribute("height", ag.getheight());
					agent.setAttribute("index", ag.getindex());
					agent.setAttribute("name", ag.getname());
					agent.setAttribute("running", ag.getrunning());
					agent.setAttribute("width", ag.getwidth());
					agent.setAttribute("x", ag.getX());
					agent.setAttribute("y", ag.getY());
					agents.add(ag);
					docAgents.add(agent);
				}
			}
// gateway 
			for(Item it : items){
			 if(it.gettype() == "gateway"){
					// dodawanie port�w do odpowiednich agent�w 
					if (((gateway) it).getGatewayDirection().equals("Diverging") ){
						String in = it.getIncoming().get(0); 
						sequenceFlow sQ = (sequenceFlow) findItemById(in, items);
						Agent agS = findAgentById(sQ.getsourceref(), agents);		
						//create ports
						int suma = it.sum_of_going(sQ.getsourceref());
						for(int i = 0; i < suma -2; i++){
							Port p = agS.getPort(agS.getPortNum()-2);
							p.setName("port_" + String.valueOf(agS.getPortNum()), agS);
							Element port = doc.createElement("port");
							Element agent = findAgentElem(agS.getname(), docAgents);
							agent.appendChild(port); 
							port.setAttribute("id", String.valueOf(p.getid()) );
							port.setAttribute("name", p.getName());
							port.setAttribute("x", p.getX());
							port.setAttribute("y", p.getY());
						}
//						agS.setOutgoing(it.getOutgoing());
						for (int i = 0; i < suma - 1 ; i++){
							Connection conne = null;
							String out = it.getOutgoing().get(i);
							sequenceFlow sQ2 = (sequenceFlow) findItemById(out, items);
							Agent agT = findAgentById(sQ2.gettargetref(), agents); 
							boolean stop = false;
							for(Port p : agS.getPorts()){
								if(p.isFree()){
									for(Port p2: agT.getPorts()){
										if(p2.isFree()){
											p.setIsFree(false);
											p2.setIsFree(false);
											conne = new Connection(p.getid(), p2.getid());
											connList.add(conne);
											stop = true;
											break;
										}
									}
								}
								if(stop)
									break;
							}
						}
//						//create connection 
									
					}
					else if (((gateway) it).getGatewayDirection().equals("Converging") ){
						String out = it.getOutgoing().get(0); 
						sequenceFlow sQ = (sequenceFlow) findItemById(out, items);
						Agent agT = findAgentById(sQ.gettargetref(), agents);		
						//create ports
						int suma = it.sum_of_going(sQ.getsourceref());
						for(int i = 0; i < suma -2; i++){
							Port p = agT.getPort(agT.getPortNum()-2);
							p.setName("port_" + String.valueOf(agT.getPortNum()), agT);
							Element port = doc.createElement("port");
							Element agent = findAgentElem(agT.getname(), docAgents);
							agent.appendChild(port); 
							port.setAttribute("id", String.valueOf(p.getid()) );
							port.setAttribute("name", p.getName());
							port.setAttribute("x", p.getX());
							port.setAttribute("y", p.getY());
						}
						for (int i = 0; i < suma ; i++){
							String incoming = ((gateway) it).getIncoming().get(0);
							sequenceFlow seqFlow = (sequenceFlow) findItemById(incoming, items);
							String source = seqFlow.getsourceref();
							Agent ag = findAgentById(source, agents); 
							Element agent = findAgentElem(ag.getname(), docAgents);
						}
						//create connection 						
						
					}
				}
// connections
				else if(it.gettype() == "connection") {
					sequenceFlow sQ = (sequenceFlow) it;
					boolean createConnection = true;
					if(findItemById(sQ.getsourceref(), items).gettype().equals("gateway") || 
							findItemById(sQ.gettargetref(), items).gettype().equals("gateway") ){
						createConnection = false;
					}
					if(createConnection){
						Connection conne = null;
						Agent agS = findAgentById(sQ.getsourceref(), agents);
						Agent agT = findAgentById(sQ.gettargetref(), agents);
						for(Port p : agS.getPorts()){
							if(p.isFree()){
								for(Port p2: agT.getPorts()){
									if(p2.isFree()){
										conne = new Connection(p.getid(), p2.getid());
										p.setIsFree(false);
										p2.setIsFree(false);
										connList.add(conne);
										break;
									}
								}
							}
						}
//						// connection element
//						Element connect = doc.createElement("connection");
//						page.appendChild(connect);
//						connect.setAttribute("direction", conne.getdirection());
//						connect.setAttribute("source", conne.getsource());
//						connect.setAttribute("style", conne.getstyle());
//						connect.setAttribute("target", conne.gettarget());	
					}
				}
			}
			
			for(Connection c : connList){
				Element connect = doc.createElement("connection");
				page.appendChild(connect);
				connect.setAttribute("direction", c.getdirection());
				connect.setAttribute("source", c.getsource());
				connect.setAttribute("style", c.getstyle());
				connect.setAttribute("target", c.gettarget());	
			}
	
			// code elements
			Element code = doc.createElement("code");
			alvisproject.appendChild(code);

			// agent elements

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\Studia\\Ania\\translator\\alvisfile.alvis"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		return true;
	}
	
	private Element findAgentElem(String name, List<Element> agents){
		for(Element a: agents){
			if(a.getAttribute("name").equals(name) ){
				return a;
			}
		}
		return null;
	}
	
	private Agent findAgentById(String bpmn, List<Agent> agents){
		for(Agent a: agents){
			if(a.getbpmn_id().equals(bpmn)){
				return a;
			}
		}
		return null;
	}	
	
    private Item findItemById(String bpmnID, List<Item> items){
    	for(Item i : items){
    		if(i.getbpmn_id().equals(bpmnID)){
    			return i;
    		}
    	}
    	return null;
    }
}