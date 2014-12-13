package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frmBpmnAlvis;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmBpmnAlvis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frmBpmnAlvis.setBounds(100, 100, 450, 300);
		frmBpmnAlvis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBpmnAlvis.getContentPane().setLayout(null);
		
		JLabel lblPodajciekPliku = new JLabel("Podaj \u015Bcie\u017Ck\u0119 pliku BPMN");
		lblPodajciekPliku.setBounds(27, 11, 118, 14);
		frmBpmnAlvis.getContentPane().add(lblPodajciekPliku);
		
		textField = new JTextField();
		textField.setBounds(27, 27, 187, 20);
		frmBpmnAlvis.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnGenerujAlvisXmp = new JButton("Generuj Alvis XML");
		btnGenerujAlvisXmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnGenerujAlvisXmp.setBounds(224, 26, 159, 23);
		frmBpmnAlvis.getContentPane().add(btnGenerujAlvisXmp);
	}
}
