package main;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import staxparser.StaXParser;
import staxparser.createXML;
import GUI.GUI;
import bpmn_elements.Item;

public class Main{
//  public static void main(String args[])throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
//    StaXParser read = new StaXParser();
//    List<Item> readConfig = read.readConfig("inclusive_gateway.bpmn");
//    
//    for (Item item : readConfig) {
//      System.out.println(item);
//    } 
//    
//    System.out.println("Zaczynam tworzy� XML dla Alvis");
//    createXML process = new createXML();
//    process.creatingxml(readConfig, read.getGatewayID());
//    
//    System.out.println("powinno by� po wszystkim...");  
//  }
  
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

