package listener;

import java.io.Serializable;

import javax.swing.JEditorPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import model.FileModel;



/**
 * The listener class which acts whenever some data is changed in the text area.
 * This class updates the singleton class which stores the file information
 * along with updating any other model class that needs update whenever some
 * data is changed in the text area.
 *
 * This also can act as a Bi-directional editor for action events
 * 
 */


public class TextChangeListener extends DocumentFilter implements Serializable {
    /**
	 * generated serial version id
	 */
	private static final long serialVersionUID = -3771996191042890611L;
	public static boolean edit = false;
	private JEditorPane editedTextArea;
    public TextChangeListener(JEditorPane editableArea) {
        this.editedTextArea = editableArea;
        FileModel.getInstance().setTextArea(editableArea);
    }
    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
        updateTextValue();
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        super.insertString(fb, offset, string, attr);
        updateTextValue();
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        super.replace(fb, offset, length, text, attrs);
        updateTextValue();
    }
    

	private void updateTextValue() {
		edit = true;
        AbstractDocument document = (AbstractDocument)editedTextArea.getDocument();
        DocumentFilter df = document.getDocumentFilter();
        document.setDocumentFilter( null );
        FileModel.getInstance().setTextArea(editedTextArea);
        document.setDocumentFilter( df );
	}

}
