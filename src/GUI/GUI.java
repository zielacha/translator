package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.stream.XMLStreamException;

import staxparser.StaXParser;
import staxparser.createXML;
import bpmn_elements.Item;

public class GUI {

	private JFrame frmBpmnAlvis;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBpmnAlvis = new JFrame();
		frmBpmnAlvis.setTitle("BPMN - Alvis");
		frmBpmnAlvis.setBounds(100, 100, 420, 300);
		frmBpmnAlvis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBpmnAlvis.getContentPane().setLayout(null);
		
		JLabel lblPodajciekPliku = new JLabel("Podaj sciezke pliku BPMN");
		lblPodajciekPliku.setBounds(27, 11, 173, 14);
		frmBpmnAlvis.getContentPane().add(lblPodajciekPliku);
		
		textField = new JTextField();
		textField.setBounds(27, 27, 187, 20);
		textField.setText("simple_task.bpmn");		
		frmBpmnAlvis.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnGenerujAlvisXmp = new JButton("Generuj Alvis XML");
		btnGenerujAlvisXmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    StaXParser read = new StaXParser();
			    List<Item> readConfig = null;
			    boolean proceed = true;
				try {
					readConfig = read.readConfig(textField.getText());
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(frmBpmnAlvis, "Nie ma takiego pliku.",  "B³¹d pliku", 
																							JOptionPane.ERROR_MESSAGE);
					proceed = false;
				} catch (XMLStreamException e) {
					proceed = false;
				}
			    if(proceed){
			    		for (Item item : readConfig) {
			    			System.out.println(item);
			    		} 

			    		System.out.println("Zaczynam tworzyæ XML dla Alvis");
			    		createXML process = new createXML();
			    		process.creatingxml(readConfig, read.getGatewayID(), textField.getText());

			    		System.out.println("powinno byæ po wszystkim...");  
			    }
				
			}
		});
		btnGenerujAlvisXmp.setBounds(224, 26, 159, 23);
		frmBpmnAlvis.getContentPane().add(btnGenerujAlvisXmp);
	}
	
	public JFrame getFrame(){
		return frmBpmnAlvis;
	}
}
