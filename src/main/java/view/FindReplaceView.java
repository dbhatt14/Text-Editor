package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import listener.FindReplaceActionListener;

/**+
 * This class initializes the view for the Find And Replace Text Box
 *
 */
public class FindReplaceView extends JDialog {

    /**
	 * Generated serial id
	 */
	private static final long serialVersionUID = -3592116911553468000L;


    public static JTextField getWhat() {
        return what;
    }

    public static JTextField getWith() {
        return with;
    }


    public static JCheckBox getWord() {
        return word;
    }


    public static JCheckBox getMatchCase() {
        return matchCase;
    }


    public static JButton getFind() {
        return find;
    }


    public static JButton getFindAll() {
        return findAll;
    }


    public static JButton getReplace() {
        return replace;
    }


    public static JButton getReplaceAll() {
        return replaceAll;
    }

    public static JButton getClose() {
        return close;
    }

    @Override
    public JFrame getOwner() {
        return owner;
    }

    public static JEditorPane getPane() {
        return pane;
    }

    private JPanel wordPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    private JPanel casePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    private JPanel inputs = new JPanel(new GridLayout(6, 1));
    private JPanel buttons = new JPanel(new GridLayout(5, 1));
    private JLabel findWhat = new JLabel("Find What");
    private JLabel replaceWith = new JLabel("Replace With");
    private JLabel wordLabel = new JLabel("Match whole word only");
    private JLabel matchCaseLabel = new JLabel("Match case");
    private JFrame owner;
    private static JDialog instance;

    /**+
     * Static Fields Initialization
     */
    private static JTextField what = new JTextField();
    private static JTextField with = new JTextField();
    private static JCheckBox word = new JCheckBox();
    private static JCheckBox matchCase = new JCheckBox();
    private static JButton find = new JButton("Find");
    private static JButton findAll = new JButton("Find All");
    private static JButton replace = new JButton("Replace");
    private static JButton replaceAll = new JButton("Replace All");
    private static JButton close = new JButton("Close");
    private static JEditorPane pane;



    public FindReplaceView(JFrame owner, JEditorPane jEditorPane) {
        super(owner, "Find & Replace", true);
        this.owner = owner;
        this.pane = jEditorPane;

        instance = this;
        initComponents();

        //setSize(360, 135);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(inputs, "Center");
        c.add(buttons, "East");
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("On Close");
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                FindReplaceActionListener.closeOperations();
                System.out.println("On Deactivated");
            }
        });


    }


    private void initComponents() {

        what.addActionListener(new FindReplaceActionListener());
        find.addActionListener(new FindReplaceActionListener());
        findAll.addActionListener(new FindReplaceActionListener());
        replace.addActionListener(new FindReplaceActionListener());
        replaceAll.addActionListener(new FindReplaceActionListener());
        close.addActionListener(new FindReplaceActionListener());
        wordPanel.add(word);
        wordPanel.add(wordLabel);
        casePanel.add(matchCase);
        casePanel.add(matchCaseLabel);
        inputs.add(findWhat);
        inputs.add(what);
        inputs.add(replaceWith);
        inputs.add(with);
        inputs.add(wordPanel);
        inputs.add(casePanel);
        buttons.add(find);
        buttons.add(findAll);
        buttons.add(replace);
        buttons.add(replaceAll);
        buttons.add(close);
    }

    public static void onClose(){
        instance.dispose();
    }

}
