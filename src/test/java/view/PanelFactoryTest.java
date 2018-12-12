package view;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.junit.Before;
import org.junit.Test;

import constant.EditorConstants;

/**
 * Test cases to check proper initialization of UI components
 * 
 */
public class PanelFactoryTest {

	ViewFrame frame;

	@Before
	public void initFrame() {
		frame = new ViewFrame();
		frame.initializeView();
		// Hiding the JFrame while testing
		frame.setVisible(false);
	}

	@Test
	public void testFileMenuItemLength() throws AWTException {
		JPanel menuPanel = (JPanel) frame.getMenuPanel();
		JMenuBar fileMenuBar = (JMenuBar) menuPanel.getComponents()[0];
		// First element in menu bar is edit
		JMenu fileMenu = fileMenuBar.getMenu(0);
		// There should be same number of file menu items as there are items
		// defined in constant file
		assertEquals(EditorConstants.FILE_MENU_ITEM_LABELS.length,
				fileMenu.getMenuComponentCount());
	}

	@Test
	public void testEditMenuItemLength() throws AWTException {
		JPanel menuPanel = (JPanel) frame.getMenuPanel();
		JMenuBar fileMenuBar = (JMenuBar) menuPanel.getComponents()[0];
		// Second element in menu bar is edit
		JMenu fileMenu = fileMenuBar.getMenu(1);
		// There should be 6 menu items on Edit menu
		assertEquals(EditorConstants.EDIT_MENU_ITEM_LABELS.length, fileMenu.getMenuComponentCount());
	}

	@Test
	public void testToolBarPanelLength() {
		JPanel toolPanel = (JPanel) frame.getToolPanel();
		JToolBar toolbar = (JToolBar) toolPanel.getComponent(0);
		// Number of tool bar items should be equal to the labels defined in
		// constants file
		assertEquals(EditorConstants.TOOL_MENU_ITEM_LABELS.length,
				toolbar.getComponentCount());

	}

}
