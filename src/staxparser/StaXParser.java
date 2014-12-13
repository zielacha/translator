package staxparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import bpmn_elements.*;


public class StaXParser {
  static final String ID = "id";
  static final String NAME = "name";
  static final String STARTEVENT = "startEvent";
  static final String TASK = "task";
  static final String ENDEVENT = "endEvent";
  static final String SEQENCEFLOW = "sequenceFlow";
  static final String BPMNELEMENT = "bpmnElement";
  static final String BPMNDIAGRAMSHAPE = "BPMNShape";
  static final String SHAPE = "Bounds";
  static final String INCOMING = "incoming";
  static final String OUTGOING = "outgoing";
  static final String SOURCEREF = "sourceRef";
  static final String TARGETREF = "targetRef";
  static final String EXCLUSIVE_G = "exclusiveGateway";
  static final String INCLUSIVE_G = "inclusiveGateway";
  static final String PARALLEL_G = "parallelGateway";
  static final String GATEWAYDIRECTION = "gatewayDirection";
  public int length;
  public List<Item> items;
  private List<String> gatewayID;
  
  @SuppressWarnings({ "unchecked", "null" })
  public List<Item> readConfig(String configFile) {
    items = new ArrayList<Item>();
    setGatewayID(new ArrayList<String>());
    try {
      // First, create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = new FileInputStream(configFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // read the XML document
      Item item = null;
      task zadanie = null;
      gateway gate = null;
      sequenceFlow seqflow = null;

      while (eventReader.hasNext()) {
          XMLEvent event = eventReader.nextEvent();
          if (event.isStartElement()) {
            StartElement startElement = event.asStartElement();
            // If we have an item element, we create a new item
// STARTEVENT            
            if (startElement.getName().getLocalPart() == (STARTEVENT)){
                item = new Item();
                item.settype("agent");
                // We read the attributes from this tag and add the id and name
                // attribute to our object
                Iterator<Attribute> attributes = startElement.getAttributes();              
                while (attributes.hasNext()) {                	
                  Attribute attribute = attributes.next();
                  if (attribute.getName().toString().equals(ID)) {
                    item.setbpmn_id(attribute.getValue());    
                  }
                  if (attribute.getName().toString().equals(NAME)) {
                     item.setname(attribute.getValue());
                  }
                }
                boolean cont = true;
            	do {
            			event = eventReader.nextEvent();
            			if(event.isStartElement()){
            				if(event.asStartElement().getName().getLocalPart() == (OUTGOING)){
            					event = eventReader.nextEvent();
            					item.addOut(event.asCharacters().getData());
            				}
            			}
            			if(event.isEndElement()){
            				if(event.asEndElement().getName().getLocalPart() == (STARTEVENT))
            					cont = false;
            			}
            			
           		}while ( cont );
// ENDEVENT
            }else if (startElement.getName().getLocalPart() == (ENDEVENT)){
            	item = new Item();
                Iterator<Attribute> attributes = startElement.getAttributes();
                item.settype("agent");
                while (attributes.hasNext()) {
                  Attribute attribute = attributes.next();
                  if (attribute.getName().toString().equals(ID)) {
                    item.setbpmn_id(attribute.getValue());                                  
                  }
                  if (attribute.getName().toString().equals(NAME)) {
                      item.setname(attribute.getValue());
                  }
                }
                boolean cont = true;
            	do {
            			event = eventReader.nextEvent();
            			if(event.isStartElement()){
            				if(event.asStartElement().getName().getLocalPart() == (INCOMING)){
            					event = eventReader.nextEvent();
            					item.addIn(event.asCharacters().getData());
            				}
            			}
            			if(event.isEndElement()){
            				if(event.asEndElement().getName().getLocalPart() == (ENDEVENT))
            					cont = false;
            			}
            			
           		}while ( cont );
// TASK                
            }else if (startElement.getName().getLocalPart() == (TASK)){
            	zadanie = new task();
            	Iterator<Attribute> attributes = startElement.getAttributes();
                zadanie.settype("agent");
                 while (attributes.hasNext()) {
                	 Attribute attribute = attributes.next();
                	 if (attribute.getName().toString().equals(ID)) {
                		 zadanie.setbpmn_id(attribute.getValue());                                  
                	 }
                	 if (attribute.getName().toString().equals(NAME)) {
                		 zadanie.setname(attribute.getValue());
                     }
                 }
                 boolean cont = true;
             	 do {
             			event = eventReader.nextEvent();
             			if(event.isStartElement()){
             				if(event.asStartElement().getName().getLocalPart() == (INCOMING)){
             					event = eventReader.nextEvent();
             					zadanie.addIn(event.asCharacters().getData());
             				}
             			}
             			if(event.isStartElement()){
             				if(event.asStartElement().getName().getLocalPart() == (OUTGOING)){
             					event = eventReader.nextEvent();
             					zadanie.addOut(event.asCharacters().getData());
             				}
             			}
             			if(event.isEndElement()){
             				if(event.asEndElement().getName().getLocalPart() == (TASK))
             					cont = false;
             			}
             			
            		}while ( cont );
// GATEWAY   
            }else if (startElement.getName().getLocalPart() == (EXCLUSIVE_G) ||
            		  startElement.getName().getLocalPart() == (INCLUSIVE_G) ||
            		  startElement.getName().getLocalPart() == (PARALLEL_G) ){
            	gate = new gateway();
              	Iterator<Attribute> attributes = startElement.getAttributes();
            	gate.settype("gateway");
            	gate.setGateType(startElement.getName().getLocalPart());
                 while (attributes.hasNext()) {
                	 Attribute attribute = attributes.next();
                	 if (attribute.getName().toString().equals(ID)) {
                		 gate.setbpmn_id(attribute.getValue());                                  
                	 }
                	 if (attribute.getName().toString().equals(NAME)) {
                		 gate.setname(attribute.getValue());
                     }
                	 if (attribute.getName().toString().equals(GATEWAYDIRECTION)) {
                		 gate.setGatewayDirection(attribute.getValue());
                     }
                	 getGatewayID().add(gate.getbpmn_id());
                 }
                 boolean cont = true;
             	 do {
             			event = eventReader.nextEvent();
             			if(event.isStartElement()){
             				if(event.asStartElement().getName().getLocalPart() == (INCOMING)){
             					event = eventReader.nextEvent();
             					gate.addIn(event.asCharacters().getData());
             				}
             			}
             			if(event.isStartElement()){
             				if(event.asStartElement().getName().getLocalPart() == (OUTGOING)){
             					event = eventReader.nextEvent();
             					gate.addOut(event.asCharacters().getData());
             				}
             			}
             			if(event.isEndElement()){
             				if(event.asEndElement().getName().getLocalPart() == (EXCLUSIVE_G) ||
             					event.asEndElement().getName().getLocalPart() == (INCLUSIVE_G) ||
             					event.asEndElement().getName().getLocalPart() == (PARALLEL_G) )
             					cont = false;         
             			}
            		} while ( cont );
             	 
// SEQUENCE FLOW    		
            }else if (startElement.getName().getLocalPart() == (SEQENCEFLOW)) {
            	seqflow = new sequenceFlow();
            	seqflow.settype("connection");
            	Iterator<Attribute> attributes = startElement.getAttributes();
                 while (attributes.hasNext()) {
                  Attribute attribute = attributes.next();
                  if (attribute.getName().toString().equals(ID)){
                	  seqflow.setbpmn_id(attribute.getValue());
                  }
                  if (attribute.getName().toString().equals(SOURCEREF)){
                	  seqflow.setSourceref(attribute.getValue());
                  }
                  if (attribute.getName().toString().equals(TARGETREF)) {
                	  seqflow.setTargetref(attribute.getValue());
                  }
                }

// BPMN Diagram Shape
            }           
            else if (startElement.getName().getLocalPart() == (BPMNDIAGRAMSHAPE)){
            	String localid = "";
            	Iterator<Attribute> attributes = startElement.getAttributes();
            	while (attributes.hasNext()) {
            		Attribute attribute = attributes.next();
               	 	if (attribute.getName().toString().equals(BPMNELEMENT)) {
               	 		localid = attribute.getValue(); 
               	 		break;
               	 	}
            	}
            	boolean cont = true;
            	do {
            			event = eventReader.nextEvent();
            			if(event.isStartElement()){
            				if(event.asStartElement().getName().getLocalPart() == (SHAPE)){
            					startElement = event.asStartElement();
            					cont = false;
            				}
            			}
           		}while ( cont );

            	if( startElement.getName().getLocalPart() == (SHAPE) ){
            		item = findItemById(localid);
            		if(item != null){
            			attributes = startElement.getAttributes();
                    	while (attributes.hasNext()) {
                    		Attribute attribute = attributes.next();
                    		if (attribute.getName().toString().equals("height")){
                    			item.setheight(Float.valueOf(attribute.getValue()));
                    		}else if (attribute.getName().toString().equals("width")){
                    			item.setwidth(Float.valueOf(attribute.getValue()));
                    		}else if (attribute.getName().toString().equals("x")){
                    			item.setX(Float.valueOf(attribute.getValue()));
                    		}else if (attribute.getName().toString().equals("y")){
                    			item.setY(Float.valueOf(attribute.getValue()));
                    		}
                    	}
                    		
            		}
            	}
            }
  
          // If we reach the end of an item element, we add it to the list
          }
          if (event.isEndElement()) {
            EndElement endElement = event.asEndElement();
            if (endElement.getName().getLocalPart() == (STARTEVENT) || 
            		endElement.getName().getLocalPart() == (ENDEVENT) ){
              items.add(item);
              System.out.println(item.getbpmn_id());
              System.out.println(item.getname());
              System.out.println("Koniec elementu");
            }else if(endElement.getName().getLocalPart() == (TASK)){
            	items.add(zadanie);
                System.out.println(zadanie.getbpmn_id());
                System.out.println(zadanie.getname());
                System.out.println("Koniec elementu");
            }
            else if(endElement.getName().getLocalPart() == (SEQENCEFLOW)){
            	items.add(seqflow);
                System.out.println(seqflow);
                System.out.println("Koniec elementu");
            }
            else if(event.asEndElement().getName().getLocalPart() == (EXCLUSIVE_G) ||
            		endElement.getName().getLocalPart() == (INCLUSIVE_G) ||
            		endElement.getName().getLocalPart() == (PARALLEL_G)){
            	items.add(gate);
                System.out.println(gate);
                System.out.println("Koniec elementu");
            }
          
          }
          length = items.size();          
        
      }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (XMLStreamException e) {
        e.printStackTrace();
      }
      System.out.println("tyle mamy elementów:"+length);
      return items;
    }

  
    private Item findItemById(String bpmnID){
    	for(Item i : items){
    		if(i.getbpmn_id().equals(bpmnID)){
    			return i;
    		}
    	}
    	return null;
    }


	public List<String> getGatewayID() {
		return gatewayID;
	}


	public void setGatewayID(List<String> gatewayID) {
		this.gatewayID = gatewayID;
	}

} 