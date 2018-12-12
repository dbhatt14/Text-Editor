package view.ViewPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import constant.EditorConstants;
import listener.EditButtonsActionsListener;
import listener.FileButtonsActionsListener;
import view.AbstractViewPanel;

public class MenuBarPanel extends AbstractViewPanel {
	/**
	 * Default serial id
	 */
	private static final long serialVersionUID = 1L;
	JPanel menuBarPanel = new JPanel();
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem[] fileMenuItems;

	int fileMenuItemsNumber = 5;
	int menuBarPanelSizeWidth = 800;
	int menuBarPanelSizeHeight = 800;
    JMenu editMenu;
    JMenuItem[] editMenuItems;
//    String[] editMenuItemLabels =
    int editMenuItemsNumber = 7;

	public MenuBarPanel() {
		this.initialize();
	}

	@Override
	public JComponent getPanel() {
		return menuBarPanel;
	}
	@Override
	protected void initialize() {
		menuBarPanel.setLayout(new BorderLayout());
		this.createMenuBar();
		menuBarPanel.add(menuBar, BorderLayout.NORTH);
		menuBarPanel.setSize(menuBarPanelSizeWidth, menuBarPanelSizeHeight);
		menuBarPanel.setVisible(true);
	}

	private void createMenuBar() {
		menuBar = new JMenuBar();
		this.setFileMenu();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
	}

	private void setFileMenu() {
		fileMenu = new JMenu(EditorConstants.FILE_MENU_TITLE);
		fileMenuItems = new JMenuItem[fileMenuItemsNumber];

		for (int i = 0; i < fileMenuItemsNumber; i++) {
			fileMenuItems[i] = new JMenuItem(EditorConstants.FILE_MENU_ITEM_LABELS[i]);
			ActionListener actionListener = new FileButtonsActionsListener(EditorConstants.FILE_MENU_ITEM_LABELS[i]);
			// Adding shortcut keys for the file menu
			KeyStroke keyStroke = KeyStroke.getKeyStroke(EditorConstants.FILE_MENU_ITEMS_SHORTCUTS[i],
					KeyEvent.CTRL_DOWN_MASK);
			fileMenuItems[i].setAccelerator(keyStroke);
			fileMenuItems[i].addActionListener(actionListener);
		}

		for (int i = 0; i < fileMenuItemsNumber; i++) {
			fileMenu.add(fileMenuItems[i]);
		}
		
	editMenu = new JMenu(EditorConstants.EDIT_MENU_TITLE);
        editMenuItems = new JMenuItem[editMenuItemsNumber];
        for (int i = 0; i < editMenuItemsNumber; i++) {
            editMenuItems[i] = new JMenuItem(EditorConstants.EDIT_MENU_ITEM_LABELS[i]);
			ActionListener actionListener = new EditButtonsActionsListener(EditorConstants.EDIT_MENU_ITEM_LABELS[i]);
			// Adding shortcut keys for the file menu
			KeyStroke keyStroke = KeyStroke.getKeyStroke(EditorConstants.EDIT_MENU_ITEMS_SHORTCUTS[i],
					KeyEvent.CTRL_DOWN_MASK);
			editMenuItems[i].setAccelerator(keyStroke);
			editMenuItems[i].addActionListener(actionListener);
			editMenu.add(editMenuItems[i]);
        }
        
//        for (int i = 0; i < editMenuItemsNumber; i++) {
//
//        }
	}
}
