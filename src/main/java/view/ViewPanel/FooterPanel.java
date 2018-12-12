package view.ViewPanel;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.AbstractViewPanel;


public class FooterPanel extends AbstractViewPanel {

    JPanel footerPanel;
    @Override
    public JComponent getPanel() {
        this.initialize();
        return footerPanel;
    }

    @Override
    protected void initialize() {
        JLabel footerLabel = new JLabel("UTF-8");
        footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.add(footerLabel,BorderLayout.EAST);
        footerPanel.setVisible(true);

    }
}
