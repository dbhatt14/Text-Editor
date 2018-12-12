package view.ViewPanel;

import constant.EditorConstants;
import listener.ToolButtonsActionListener;
import view.AbstractViewPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JComponent;


@SuppressWarnings("serial")
public class ToolBarPanel extends AbstractViewPanel {
    JToolBar toolbar;
    JPanel toolbarPanel = new JPanel();
    int toolBarPanelSizeWidth = 500;
    int toolBarPanelSizeHeight = 500;

	public ToolBarPanel() {
        this.initialize();

    }

    @Override
    public JComponent getPanel() {
        return toolbarPanel;
    }

    @Override
    protected void initialize() {
        toolbar = new JToolBar("Applications");
        this.initializeButtons();

        
        toolbarPanel.setLayout(new BorderLayout());
        toolbarPanel.add(toolbar, BorderLayout.SOUTH);
        toolbarPanel.setSize(toolBarPanelSizeWidth, toolBarPanelSizeHeight);
        toolbarPanel.setVisible(true);

    }








	private void initializeButtons() {
        for (int i = 0; i < EditorConstants.TOOL_MENU_RESOURCES.length; i++) {

            ClassLoader classLoader = getClass().getClassLoader();
            JButton button = new JButton();
            try {
                ImageIcon image = new ImageIcon(
                		classLoader.getResource("toolbar-icons/" + EditorConstants.TOOL_MENU_RESOURCES[i] + ".png"));
                button.setIcon(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
            button.setToolTipText(EditorConstants.TOOL_MENU_ITEM_LABELS[i]);
            ActionListener actionListener = new ToolButtonsActionListener(EditorConstants.TOOL_MENU_ITEM_LABELS[i]);
            button.addActionListener(actionListener);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setOpaque(true);

            toolbar.add(button);
        }

    }

}
