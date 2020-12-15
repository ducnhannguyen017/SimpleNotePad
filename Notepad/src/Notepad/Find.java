package Notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

public class Find extends JFrame {

	private JPanel contentPane;
	private JTextField textFind;
	JTextArea textArea;
	int pos = 0;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Find frame = new Find();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Find() {
		setBounds(100, 100, 450, 188);
		setTitle("Find");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Find what:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 34, 77, 26);
		contentPane.add(lblNewLabel);
		
		textFind = new JTextField();
		textFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFind.setColumns(10);
		textFind.setBounds(127, 36, 159, 26);
		contentPane.add(textFind);
		
		JButton btnNewButton = new JButton("Find Next");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(314, 34, 112, 31);
		contentPane.add(btnNewButton);
		
		JButton btnCan = new JButton("Cancel");
		btnCan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCan.setBounds(314, 84, 112, 31);
		contentPane.add(btnCan);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Direction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(160, 84, 126, 57);
		contentPane.add(panel);
		
		JRadioButton radioUp = new JRadioButton("Up");
		buttonGroup.add(radioUp);
		panel.add(radioUp);
		
		JRadioButton radioDown = new JRadioButton("Down");
		buttonGroup.add(radioDown);
		panel.add(radioDown);
	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(radioUp.isSelected()) {
					FindText(textFind.getText(), textArea);
					pos += textFind.getText().length();
				}else if(radioDown.isSelected()) {
					FindText(textFind.getText(), textArea);
					int temp = pos - textFind.getText().length();
					pos = -temp;
				}
				
			}
		});
	}
	
	void FindText(String text, JTextArea textArea) {
		
		try {		
			String textFind = textArea.getText();	
			if((pos = textFind.indexOf(text,pos)) >= 0) {
				textArea.select(pos, pos+text.length());		
			}
		} catch (Exception e) {
			
		}

	}
	

}
