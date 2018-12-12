
package controller;

import java.awt.print.PrinterException;

import model.FileModel;
import service.FileOpenService;
import service.FileSaveService;
import view.ViewFrame;

/**
 * Controller for every action listener for file menu buttons
 * 
 */
public class FileController implements ControllerInterface {
    
	/**
     * Opens the user defined file.
     */
    public void openFile() {
    	FileOpenService fileOpenService = new FileOpenService();
    	fileOpenService.open();
    }

    /**
     * Save the file as a new file or as an existing file
     * 
     * @param isSaveAs
     *            flag which denotes if the save as button was clicked or just save
     *            button
     */
    public void saveFile(boolean isSaveAs) {
    	FileSaveService fileSaveService = new FileSaveService(isSaveAs);
    	fileSaveService.save();
    }
    
    /**
     * Displays the software info
     */
    public static void displayInfo() {
    	ViewFrame viewframe = new ViewFrame();
    	viewframe.softwareInfo();
    }
    
    /**
     * Displays the software help
     */
    public static void displayHelp() {
    	ViewFrame viewframe = new ViewFrame();
    	viewframe.softwareHelp();
    }

	public static void printFile() {
		// TODO Auto-generated method stub
		try {
			FileModel.getInstance().getTextArea().print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
