package Notepad;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.print.event.PrintJobAttributeEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import Notepad.FontChooser;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollBar;

public class FontChooser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea textArea = new JTextArea();
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	/**
	 * Launch the application.
	 */
	@SuppressWarnings("rawtypes")
	//
	JList fontnameList, styleList, sizeList;
	String[] fontNames=	GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getAvailableFontFamilyNames();
	String fontSizes[];
	String[] fontStyles={"Plain","Italic","Bold","Bold Italic"};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FontChooser frame = new FontChooser();
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
	public FontChooser() {
		textArea.setEditable(false);
		
		//
		textArea.append("\nA quick brown fox jumps over the lazy dog.");
		textArea.append("\n0123456789");
		textArea.append("\n~!@#$%^&*()_+|?><\n");
		//
		createList();
		//
		addListActionListener();
		
		//
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		//
		inputUI();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createList() {
		//
		String[] fontNames=	GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		fontSizes=new String[51];
		for(int j=1; j<=50; j++)
			fontSizes[j]=new String(j*2+"");
		
		fontnameList = new JList(fontNames);
		styleList = new JList(fontStyles);
		sizeList = new JList(fontSizes);
	
		fontnameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		fontnameList.setSelectedIndex(0);
		styleList.setSelectedIndex(0);
		sizeList.setSelectedIndex(4);
	}
	public void addListActionListener() {
		fontnameList.addListSelectionListener(new ListSelectionListener(){	
			public void valueChanged(ListSelectionEvent ev){	
				textArea.setFont(createFont());
			}
		});
		styleList.addListSelectionListener(new ListSelectionListener(){	
			public void valueChanged(ListSelectionEvent ev){	
				textArea.setFont(createFont());
			}
		});
		sizeList.addListSelectionListener(new ListSelectionListener(){	
			public void valueChanged(ListSelectionEvent ev)
			{	
				textArea.setFont(createFont());
			}
		});
	}
	public Font createFont()
	{
		Font fnt;
	
		//
		int fontstyle = 0;
		int x=styleList.getSelectedIndex();
		
		switch(x)
		{
		case 0:
			fontstyle=Font.PLAIN;	break;
		case 1:
			fontstyle=Font.ITALIC;	break;
		case 2:
			fontstyle=Font.BOLD;	break;
		case 3:
			fontstyle=Font.BOLD+Font.ITALIC;	break;
		}
		
		//
		int fontsize=Integer.parseInt((String)sizeList.getSelectedValue());	
		//
		String fontname=(String)fontnameList.getSelectedValue();
		fnt=new Font(fontname,fontstyle,fontsize);	
		return fnt;
	
	}
	void inputUI() {
		setBounds(100, 100, 464, 458);
		setTitle("Choose a Font");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setBounds(132, 265, 243, 77);
		contentPane.add(textArea);
		//
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 38, 183, 204);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(fontnameList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(232, 38, 109, 138);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(styleList);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(366, 38, 54, 204);
		contentPane.add(scrollPane_1_1);
		scrollPane_1_1.setViewportView(sizeList);
		
		//
		okButton.setBounds(117, 377, 89, 23);
		contentPane.add(okButton);
		cancelButton.setBounds(252, 377, 89, 23);
		contentPane.add(cancelButton);
		//
		JLabel lblNewLabel_1 = new JLabel("Font Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 13, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Font Style");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(231, 14, 97, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Font Size:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(366, 14, 97, 14);
		contentPane.add(lblNewLabel_1_2);
		JLabel lblNewLabel = new JLabel("Example:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(53, 291, 69, 23);
		contentPane.add(lblNewLabel);
	}
	
}
