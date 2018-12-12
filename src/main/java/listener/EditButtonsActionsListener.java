package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constant.EditorConstants;
import controller.EditController;
import model.FileModel;
import view.FindReplaceView;
import view.ViewFrame;

/**
 * Creates listener functions for every button actions under edit menu
 * 
 */
public class EditButtonsActionsListener implements ActionListener {

	private String editMenuItemLabel;

	/**
	 * Parameter controller which takes the name of the button as its argument
	 * to decide the listener for the button
	 * 
	 * @param editMenuItemLabel
	 */
	public EditButtonsActionsListener(String editMenuItemLabel) {
		this.editMenuItemLabel = editMenuItemLabel;
	}

	/**
	 * Override action performed function of action listener which adds
	 * listeners to buttons of file menu
	 */
	public void actionPerformed(ActionEvent e) {
		if (editMenuItemLabel
				.equalsIgnoreCase(EditorConstants.EDIT_MENU_ITEM_LABELS[0])) {
			// TODO: Cut Text by calling controller
			EditController.cutText();
		} else if (editMenuItemLabel
				.equalsIgnoreCase(EditorConstants.EDIT_MENU_ITEM_LABELS[1])) {
			// TODO: Copy Text stuff by calling
			EditController.copyText();
		} else if (editMenuItemLabel
				.equalsIgnoreCase(EditorConstants.EDIT_MENU_ITEM_LABELS[2])) {
			EditController.pasteText();
		} else if (editMenuItemLabel
				.equalsIgnoreCase(EditorConstants.EDIT_MENU_ITEM_LABELS[3])) {
			System.out.println(
					EditorConstants.EDIT_MENU_ITEM_LABELS[3] + " clicked.");
			new FindReplaceView(ViewFrame.getViewFrame(),
					FileModel.getInstance().getTextArea());
		} else if (editMenuItemLabel
				.equalsIgnoreCase(EditorConstants.EDIT_MENU_ITEM_LABELS[4])) {
			System.out.println(
					EditorConstants.EDIT_MENU_ITEM_LABELS[4] + " clicked");
			new FindReplaceView(ViewFrame.getViewFrame(),
					FileModel.getInstance().getTextArea());
		} else if (editMenuItemLabel
				.equalsIgnoreCase(EditorConstants.EDIT_MENU_ITEM_LABELS[5])) {
			EditController.undo();
		} else if (editMenuItemLabel
				.equalsIgnoreCase(EditorConstants.EDIT_MENU_ITEM_LABELS[6])) {
			EditController.redo();
		}
	}

}
