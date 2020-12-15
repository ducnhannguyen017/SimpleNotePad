package Notepad;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Scanner;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Notepad extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=200,29
	 */
	private javax.swing.JFileChooser jFile;
	private javax.swing.JTextArea textArea;
	private static Notepad frame;
	private UndoManager undoManager;
	FontChooser fontDialog = null;
	Find find = null;
	String FileName;
	String FileAdress;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Notepad();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("CÓ LỖI !");
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Notepad() {
		undoManager = new UndoManager();
		setBackground(Color.WHITE);
		setTitle("New- SimpleNotepad");
		setResizable(false);
		setBounds(100, 100, 800, 500);
		setName("Notepad");
		
//		String textTemp = textArea.getText();
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 240, 240));
		menuBar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem New = new JMenuItem("  New");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				New();
			}
		});
		New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		New.setIcon(new ImageIcon("D:\\DownLoad\\square.png"));
		mnNewMenu.add(New);
		
		JMenuItem NewWindow = new JMenuItem("  New Window");
		NewWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame = new Notepad();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("CÓ LỖI !");
				}
			}
		});
		NewWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnNewMenu.add(NewWindow);
		
		JSeparator separator_3 = new JSeparator();
		mnNewMenu.add(separator_3);
		
		JMenuItem Open = new JMenuItem("  Open...");
		Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenFunction();
			}
		});
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(Open);
		
		JSeparator separator_4 = new JSeparator();
		mnNewMenu.add(separator_4);
		
		JMenuItem Save = new JMenuItem("  Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Save();
			}
		});
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnNewMenu.add(Save);
		
		JMenuItem SaveAs = new JMenuItem("  Save as...");
		SaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveAsFunction();
			}
		});
		SaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnNewMenu.add(SaveAs);
		
		JSeparator separator_5 = new JSeparator();
		mnNewMenu.add(separator_5);
		
		JMenuItem Exit = new JMenuItem("  Exit");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Exit();
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("  Print");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrintFunction() ;
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		mnNewMenu.add(Exit);
		
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		
		JMenuItem Undo = new JMenuItem("Undo");
		Undo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Undo();
			}
		});
		Undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		Edit.add(Undo);
		
		JMenuItem Redo = new JMenuItem("Redo");
		Redo.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent arg0) {
				Redo();
			}
		});
		Redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		Edit.add(Redo);
		
		JSeparator separator = new JSeparator();
		Edit.add(separator);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("  Cut");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cut();
			}
		});
		mntmNewMenuItem_8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		Edit.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("  Copy");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Copy();
			}
		});
		mntmNewMenuItem_9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		Edit.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("  Paste");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Paste();
			}
		});
		mntmNewMenuItem_10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		Edit.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("  Delete");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Delete();
			}
		});
		Edit.add(mntmNewMenuItem_11);
		
		JSeparator separator_1 = new JSeparator();
		Edit.add(separator_1);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("  Find");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindText();
				
			}
		});
		mntmNewMenuItem_13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		Edit.add(mntmNewMenuItem_13);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("  Select All");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectAll();
			}
		});
		mntmNewMenuItem_14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		Edit.add(mntmNewMenuItem_14);
		
		JMenu Format = new JMenu("Format");
		menuBar.add(Format);
		
		JCheckBoxMenuItem WrapTextcheckItem = new JCheckBoxMenuItem("Wrap Text");
		WrapTextcheckItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(WrapTextcheckItem.isSelected()){
					textArea.setLineWrap(true);
		        }else {
		            textArea.setLineWrap(false);
		        }
			}
		});
		Format.add(WrapTextcheckItem);
		
		JMenuItem fontItem = new JMenuItem("Font...");
		fontItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Font();
			}
		});
		Format.add(fontItem);
		
		JMenu Help = new JMenu("Help");
		menuBar.add(Help);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 0, 784, 440);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(BorderFactory.createEmptyBorder() );
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setTabSize(5);
		scrollPane.setViewportView(textArea);
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						undoManager.addEdit(e.getEdit());
					}
				});
	}
	
	void OpenFunction() {
		JFileChooser fc=new JFileChooser();
		if(fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			try {
				FileName =fc.getSelectedFile().getName();
				FileAdress=fc.getSelectedFile().getAbsolutePath();//lấy đường dẫn của file được chọn
				frame.setTitle(FileName + "- SimpleNotepad");
				BufferedReader br=new BufferedReader(new FileReader(FileAdress));//đọc file
				textArea.setText("");
				String line=null;
				while((line=br.readLine())!=null) {
					textArea.append(line+"\n");
				}
				br.close();
			} 
			catch (Exception e) {
				System.out.println("CÓ LỖI !");
			}
		}
	}
	
	void SaveAsFunction() {
		JFileChooser fc=new JFileChooser();
		if(fc.showSaveDialog(this)==JFileChooser.APPROVE_OPTION) {
			try {
				FileName =fc.getSelectedFile().getName();
				FileAdress=fc.getSelectedFile().getAbsolutePath();
				frame.setTitle(FileName + "- SimpleNotepad");
				BufferedWriter out = new BufferedWriter(new FileWriter(FileAdress));
				out.write(textArea.getText());
				out.close();
			} 
			catch (Exception e) {
				System.out.println("CÓ LỖI !");
			}
		}
	}
	
	void Save() {
		if(FileName==null) {
			SaveAsFunction();
		}
		else {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(FileAdress));
				out.write(textArea.getText());
				frame.setTitle(FileName + "- SimpleNotepad");
				out.close();
			}
			catch(Exception e) {
				System.out.println("CÓ LỖI !");
			}
		}
	}
	
	void New() {
//		String text1=textArea.getText();
		if(undoManager.canUndo()||undoManager.canRedo()) {	
			Object[] options = {"Yes, please","No, thanks"};
			int x = JOptionPane.showOptionDialog(null, "Bạn có muốn lưu file này !",
	                "Click a button",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if(x==0) {
				SaveAsFunction();
			}
		}
		else {
			textArea.setText("");
			frame.setTitle("New- SimpleNotepad");
		}
	}
	
	void Exit() {
			System.exit(0);
	}

	void PrintFunction() {
		try {
		MessageFormat header = new MessageFormat("nguyenducnhan");
		MessageFormat footer = new MessageFormat("n");
		textArea.print(header, footer, true, null, null, true);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	void Delete() {
		String tmp = frame.textArea.getText();
		frame.textArea.setText(tmp.substring(0, frame.textArea.getSelectionStart()) 
				+ tmp.substring(frame.textArea.getSelectionEnd()));
	}
	
	void SelectAll() {
		frame.textArea.selectAll();
	}
	
	void Copy() {
		frame.textArea.copy();
	}
	
	void Cut() {
		frame.textArea.cut();
	}
	
	void Paste() {
		frame.textArea.paste();
	}
	
	void Undo() {
		if(undoManager.canUndo()) {
			try {
				frame.undoManager.undo();
			} catch (Exception e) {
				
			}
		}
	}
	
	void Redo() {
		if(undoManager.canRedo()) {
			try {
				frame.undoManager.redo();
			} catch (Exception e) {	
				
			}
		}
	}
	void FindText() {
		find = new Find();
		find.textArea = textArea;
		find.setVisible(true);
	}
	void Font() {
		if(fontDialog == null)
			fontDialog = new FontChooser();
		fontDialog.setVisible(true);
//		String tmp = frame.textArea.getSelectedText();
		fontDialog.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setFont(fontDialog.createFont());
				fontDialog.setVisible(false);
			}
		});
	}
}
