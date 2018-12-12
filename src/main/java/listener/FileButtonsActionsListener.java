package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constant.EditorConstants;
import controller.FileController;

/**
 * Creates listener functions for every button actions under file menu
 * 
 */
public class FileButtonsActionsListener implements ActionListener {

	private String fileMenuItemLabel;

	/**
	 * Parameter controller which takes the name of the button as its argument to
	 * decide the listener for the button
	 * 
	 * @param fileMenuItemLabel
	 */
	public FileButtonsActionsListener(String fileMenuItemLabel) {
		this.fileMenuItemLabel = fileMenuItemLabel;
	}

	/**
	 * Override action performed function of action listener which adds listeners to
	 * buttons of file menu
	 */
	public void actionPerformed(ActionEvent e) {
		FileController fileController = new FileController();
		if (fileMenuItemLabel.equalsIgnoreCase(EditorConstants.FILE_MENU_ITEM_LABELS[0])) {
			// TODO: New file stuff by calling controller
			System.out.println("New file clicked.");
		} else if (fileMenuItemLabel.equalsIgnoreCase(EditorConstants.FILE_MENU_ITEM_LABELS[1])) {
			// TODO: Open file stuff by calling controller
			fileController.openFile();
		} else if (fileMenuItemLabel.equalsIgnoreCase(EditorConstants.FILE_MENU_ITEM_LABELS[2])) {
			fileController.saveFile(false);
		} else if (fileMenuItemLabel.equalsIgnoreCase(EditorConstants.FILE_MENU_ITEM_LABELS[3])) {
			fileController.saveFile(true);
		} else if (fileMenuItemLabel.equalsIgnoreCase(EditorConstants.FILE_MENU_ITEM_LABELS[4])) {
			FileController.printFile();
		}
	}

}
