
package view;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import constant.EditorConstants;

import controller.FileController;

import listener.TextChangeListener;


/**
 * An Abstract Factory Pattern layout for the views all the View Frame has to do
 * is call the PanelFactory, get the Panel items and lay it out on the fram
 * 
 */
public class ViewFrame extends JFrame {

	/**
	 * Default Serial version id
	 */
	private static final long serialVersionUID = 1L;

	private JComponent menuPanel;
	private JComponent toolPanel;
	private JComponent toolPanel2;
	private JPanel headPanel;
	private JComponent textPanel;
	private JComponent footerPanel;
	private JLabel text;
	private JPanel panel;
	private JFrame frame;
	public static JFrame thisFrame;
	public static JComponent thisPane;
	
	private static int headPanelHeight = 125;
	private static int headPanelWidth = 125;

	
	
	
	/**
	 * Initializes all the component of the text editor and sets up the layout
	 */
	public void initializeView() {
		setTitle("TextEditor");
		headPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(headPanel, BoxLayout.Y_AXIS);
		headPanel.setLayout(boxlayout);

		menuPanel = PanelFactory.createPanel(EditorConstants.MENUPANEL);
		toolPanel =  PanelFactory.createPanel(EditorConstants.TOOLPANEL);
		toolPanel2 =  PanelFactory.createPanel(EditorConstants.TOOLPANEL2);
		textPanel = PanelFactory.createPanel(EditorConstants.TEXTPANEL);
		footerPanel = PanelFactory.createPanel(EditorConstants.FOOTERPANEL);
		headPanel.add(menuPanel,"Menu Panel");
		headPanel.add(toolPanel,"Tool Panel");
		headPanel.add(toolPanel2,"Tool Panel 2");
        

   
		
		
        headPanel.setPreferredSize(new Dimension(headPanelWidth,headPanelHeight));
		
		
		
		
        layoutHelper();
        thisFrame = this;
        thisPane = this.textPanel;
	}

	/**
	 * Helper function to create layout and put components in their places as per the layout
	 */
	private void layoutHelper() {

		setLayout(new BorderLayout());
		add(headPanel, BorderLayout.PAGE_START);
		add(textPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.PAGE_END);
		setMinimumSize(new Dimension(2*EditorConstants.FRAME_WIDTH, 2*EditorConstants.FRAME_HEIGHT));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	closingCheck();
            }
         });
	}

	public JComponent getMenuPanel() {
		return menuPanel;
	}

	public JComponent getToolPanel() {
		return toolPanel;
	}

	public static JFrame getViewFrame(){
	    return thisFrame;
	}

	public void softwareInfo() {
		
		text = new JLabel();
		panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500,300);
        frame.setTitle("About Software");

        String contentText = "<html><body><p><center>" +
        "Name: " + "ISE Text Editor" + "<br />" +
        "\n" + "<br />" +
        "Version: " + "1.0" + "<br />" +
        "\n" + "<br />" +
        "This text editor allows you to create, edit and format text." + "<br />" +
        "In addition to all the basic text editor functions, it has an" + "<br />" +
        "added feature of keyword highlighting for Java, C++ and Python" + "<br />" +
        "language programs." +
        "</center></p></body></html>";

        text.setText(contentText);
        panel.add(text);
        frame.add(panel);
        frame.setLocationRelativeTo(thisFrame);
    }

	public void softwareHelp() {
		
		text = new JLabel();
		panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1100,150);
        frame.setTitle("Help for Software");
        JLabel hyperlink = new JLabel("\nVisit to access Help for Text Editor\n");
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ev) {
            	try {
                    
                    Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Text_editor"));
                     
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        
        String helpText = "This application serves as a text editor which enables you to create, edit and format text.\n"
        				+ "For more information on how to use the different features in this editor please visit the below link.";
               				
        String contentText = "<html><body><p>" +
        helpText + "<br />" +
        "</p></body></html>";

        text.setText(contentText);
        panel.add(text);
        panel.add(hyperlink);
        frame.add(panel);
        frame.setLocationRelativeTo(thisFrame);
	}
	
	public void closingCheck() {
		if(TextChangeListener.edit) {
            Object[] options = {"Save and exit", "Don't Save and exit", "Return"};
            int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == 0) {
            	FileController controller = new FileController();
            	controller.saveFile(true);
                this.dispose();
            } else if (n == 1) {
                this.dispose();
            }
		} else {
			System.exit(99);
		}			
	}
}
