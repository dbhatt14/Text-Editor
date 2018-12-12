package view.ViewPanel;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.undo.UndoManager;

import listener.TextChangeListener;
import model.FileModel;
import view.AbstractViewPanel;

public class TextPanel extends AbstractViewPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane textPane;

	public TextPanel() {
		this.initialize();
	}

	@Override
	public JComponent getPanel() {
		return textPane;
	}

	@Override
	protected void initialize() {
		JTextPane editableArea = new JTextPane();
		AbstractDocument textDocument = (AbstractDocument) editableArea
				.getDocument();
		textDocument.setDocumentFilter(new TextChangeListener(editableArea));
		final UndoManager undo = FileModel.getInstance().getUndoManager();
		textDocument.addUndoableEditListener(new UndoableEditListener() {
		    public void undoableEditHappened(UndoableEditEvent evt) {
		        undo.addEdit(evt.getEdit());
		    }
		});
		textPane = new JScrollPane(editableArea);
		FileModel.getInstance().setTextArea(editableArea);
	}

}
