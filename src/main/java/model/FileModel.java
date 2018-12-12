package model;

import javax.swing.JEditorPane;
import javax.swing.undo.UndoManager;

/**
 * Model class to store all information regarding the current file.
 * Data includes - filename, filepath, content of the file
 */
public class FileModel implements ModelInterface {
    private static FileModel fileModel = new FileModel();
    private String filename;
    private String filePath;
    private String content;
    private boolean italic;
    private boolean bold;
    private boolean underlined;
    private boolean strike;
//  private boolean
    private JEditorPane textArea;
    private UndoManager undo;

    private FileModel() {
	content = "";
    	italic = false;
	bold = false;
	underlined = false;
	strike = false;
	undo = new UndoManager();
    }

    /**
     * Returns the instance of the model
     * @return
     */
    public static FileModel getInstance() {
    	if(fileModel == null) {
    		fileModel = new FileModel();
    	}
        return fileModel;
    }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public JEditorPane getTextArea() {
		return textArea;
	}

	public void setTextArea(JEditorPane editableArea) {
		this.textArea = editableArea;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public boolean isUnderlined() {
		return underlined;
	}

	public void setUnderlined(boolean underlined) {
		this.underlined = underlined;
	}

	public boolean isStrikeThrough() {
		return strike;
	}

	public void setStrikethrough(boolean strike) {
		this.strike = strike;

	}

	public UndoManager getUndoManager() {
		// TODO Auto-generated method stub
		return undo;
	}


}
