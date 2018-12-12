package listener;

import constant.EditorConstants;
import controller.EditController;
import controller.FileController;
import model.FileModel;
import view.FindReplaceView;
import view.ViewFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButtonsActionListener implements ActionListener{
    private String toolItemLabel;

    /**
     * Parameter controller which takes the name of the button as its argument to decide the listener for the button
     * @param toolItemLabel
     */
    public ToolButtonsActionListener(String toolItemLabel){
        this.toolItemLabel = toolItemLabel;
    }

    /**
     * Override action performed function of action listener which adds listeners to buttons of toolbar menu
     */
    public void actionPerformed(ActionEvent e) {
    	FileController fileController = new FileController();
        if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[0])) {
            //TODO: New file stuff by calling controller
            System.out.println("New file clicked.");
        } else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[1])) {
            //TODO: Open file stuff by calling controller
        	fileController.openFile();
        } else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[2])) {
        	fileController.saveFile(false);
        } else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[3])) {
        	fileController.saveFile(true);
        } else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[4])) {
        	EditController.undo();
        } else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[5])) {
        	EditController.redo();
        } else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[6])) {
            System.out.println("Close clicked");
        } else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[7])) {
            System.out.println("Print clicked");
        }else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[8])) {
        	EditController.cutText();
        }else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[9])) {
        	EditController.copyText();
        }else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[10])) {
        	EditController.pasteText();
        }else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[11])) {
            System.out.println("Find clicked");
            new FindReplaceView(ViewFrame.getViewFrame(), FileModel.getInstance().getTextArea());
        }else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[12])) {
            System.out.println("Replace clicked");
            new FindReplaceView(ViewFrame.getViewFrame(), FileModel.getInstance().getTextArea());
        }else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[13])) {
        	FileController.displayInfo();
        }else if(toolItemLabel.equalsIgnoreCase(EditorConstants.TOOL_MENU_ITEM_LABELS[14])) {
        	FileController.displayHelp();
        }
    }
}
