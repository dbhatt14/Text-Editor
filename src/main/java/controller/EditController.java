package controller;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;


import model.FileModel;

/**
 * Controller for every action listener for edit menu buttons
 * 
 */
public class EditController implements ControllerInterface {
	/**
	 * Function edits and formats the user selected text.
	 */

	static JEditorPane textSpace = FileModel.getInstance().getTextArea();
	private static HighlightText languageHighlighter = new HighlightText(
			Color.LIGHT_GRAY);
	private static SupportedKeywords kw = new SupportedKeywords();

	static JEditorPane oldText;

	EditController(JEditorPane textSpace) {
		this.textSpace = textSpace;
	}

	public static void cutText() {
		oldText = textSpace;
		textSpace.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				textSpace.getSelectedText();
			}
		});
		textSpace.cut();
	}

	public static void copyText() {
		textSpace.copy();
	}

	public static void pasteText() {
		textSpace.paste();
	}

	// font type
	public static void setfonttypeText(String p) {

		StyledDocument document = (StyledDocument) textSpace.getDocument();

		StyleContext context = StyleContext.getDefaultStyleContext();
		AttributeSet attR = context.addAttribute(context.getEmptySet(),
				StyleConstants.FontFamily, p);
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		document.setCharacterAttributes(start, end - start, attR, false);

	}

	// font size
	public static void setfontsizeText(int sizeOfFont) {
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		StyleContext context = StyleContext.getDefaultStyleContext();
		AttributeSet attR = context.addAttribute(context.getEmptySet(),
				StyleConstants.FontSize, sizeOfFont);
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		document.setCharacterAttributes(start, end - start, attR, false);
	}

	// bold
	public static void setbold() {
		oldText = textSpace;
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		StyleContext context = StyleContext.getDefaultStyleContext();
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		Element element = document.getCharacterElement(start);
		AttributeSet attributeNew = element.getAttributes();
		AttributeSet attR;
		int diff = end - start;
		boolean isBold = false;
		if (diff > 0) {
			isBold = StyleConstants.isItalic(attributeNew);
		} else {
			isBold = FileModel.getInstance().isBold();
			start--;
			diff = 1;
		}
		if (isBold || StyleConstants.isBold(attributeNew)) {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.Bold, false);
			FileModel.getInstance().setBold(false);
		} else {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.Bold, true);
			FileModel.getInstance().setBold(true);
		}

		document.setCharacterAttributes(start, diff, attR, false);
	}

	// color
	public static void setfontcolorText(String colorvalue) {
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		StyleContext context = StyleContext.getDefaultStyleContext();
		AttributeSet attR = context.addAttribute(context.getEmptySet(),
				StyleConstants.Foreground, Color.decode(colorvalue));
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		document.setCharacterAttributes(start, end - start, attR, false);
		textSpace.setSelectedTextColor(Color.decode(colorvalue));
	}

	// italic
	public static void setitalic() {
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		StyleContext context = StyleContext.getDefaultStyleContext();
		int start = textSpace.getSelectionStart();
		Element element = document.getCharacterElement(start);
		AttributeSet attributeNew = element.getAttributes();
		AttributeSet attR;
		int end = textSpace.getSelectionEnd();
		int diff = end - start;
		boolean isItalicOn = false;
		if (diff > 0) {
			isItalicOn = StyleConstants.isItalic(attributeNew);
		} else {
			isItalicOn = FileModel.getInstance().isItalic();
			start--;
			diff = 1;
		}
		if (isItalicOn || StyleConstants.isItalic(attributeNew)) {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.Italic, false);
			FileModel.getInstance().setItalic(!isItalicOn); // Switched off
															// italic button
		} else {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.Italic, true);
			FileModel.getInstance().setItalic(!isItalicOn); // switched on
															// italic button
		}

		document.setCharacterAttributes(start, diff, attR, false);
	}

	// underline
	public static void setUnderline() {
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		StyleContext context = StyleContext.getDefaultStyleContext();
		int start = textSpace.getSelectionStart();
		Element element = document.getCharacterElement(start);
		AttributeSet attributeNew = element.getAttributes();
		AttributeSet attR;
		int end = textSpace.getSelectionEnd();
		int diff = end - start;
		boolean isUnderlined = false;
		if (diff > 0) {
			isUnderlined = StyleConstants.isUnderline(attributeNew);
		} else {
			isUnderlined = FileModel.getInstance().isUnderlined();
			start--;
			diff = 1;
		}
		if (isUnderlined || StyleConstants.isUnderline(attributeNew)) {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.Underline, false);
			FileModel.getInstance().setUnderlined(false);
		} else {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.Underline, true);
			FileModel.getInstance().setUnderlined(true);
		}
		document.setCharacterAttributes(start, diff, attR, false);
//		textSpace.requestFocusInWindow();
	}

	// strikethrough
	public static void setStrikethrough() {
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		StyleContext context = StyleContext.getDefaultStyleContext();
		int start = textSpace.getSelectionStart();
		Element element = document.getCharacterElement(start);
		AttributeSet attributeNew = element.getAttributes();
		AttributeSet attR;
		int end = textSpace.getSelectionEnd();
		int diff = end - start;
		boolean isStrikethrough = false;
		if (diff > 0) {
			isStrikethrough = StyleConstants.isStrikeThrough(attributeNew);
		} else {
			isStrikethrough = FileModel.getInstance().isStrikeThrough();
			start--;
			diff = 1;
		}
		if (isStrikethrough || StyleConstants.isStrikeThrough(attributeNew)) {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.StrikeThrough, false);
			FileModel.getInstance().setStrikethrough(false);
		} else {
			attR = context.addAttribute(context.getEmptySet(),
					StyleConstants.StrikeThrough, true);
			FileModel.getInstance().setStrikethrough(true);
		}
		document.setCharacterAttributes(start, diff, attR, false);
	}

	public static void setinitjavahighlight() {
		// TODO Auto-generated method stub
		languageHighlighter.highLight(textSpace, kw.getJavaKeywords());
		textSpace.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				languageHighlighter.highLight(textSpace, kw.getJavaKeywords());
				System.out.println("java highlight clicked");// for test only
			}
		});
	}

	public static void setinitcpphighlight() {
		// TODO Auto-generated method stub
		languageHighlighter.highLight(textSpace, kw.getCppKeywords());
		textSpace.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				languageHighlighter.highLight(textSpace, kw.getCppKeywords());
				System.out.println("cpp highlight clicked");// for test only
			}
		});
	}

	public static void setinitpythonhighlight() {
		// TODO Auto-generated method stub
		languageHighlighter.highLight(textSpace, kw.getPythonKeywords());
		textSpace.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				languageHighlighter.highLight(textSpace,
						kw.getPythonKeywords());
				System.out.println("python highlight clicked");// for test only
			}
		});
	}

	public static void setalignleft() {
		// TODO Auto-generated method stub
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		SimpleAttributeSet left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		document.setParagraphAttributes(start, end, left, false);
	}

	public static void setalignright() {
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		SimpleAttributeSet right = new SimpleAttributeSet();
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		document.setParagraphAttributes(start, end - 1, right, false);
	}

	public static void setaligncenter() {
		// TODO Auto-generated method stub
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		document.setParagraphAttributes(start, end - 1, center, false);
	}

	public static void setalignjustify() {
		// TODO Auto-generated method stub
		int start = textSpace.getSelectionStart();
		int end = textSpace.getSelectionEnd();
		StyledDocument document = (StyledDocument) textSpace.getDocument();
		SimpleAttributeSet justify = new SimpleAttributeSet();
		StyleConstants.setAlignment(justify, StyleConstants.ALIGN_JUSTIFIED);
		document.setParagraphAttributes(start, end - 1, justify, false);
	}

	/**
	 * (Plan to implement as a stack later) Undo most recent changes until
	 * returned to previous state
	 */
	public static void undo() {
		final UndoManager undo = FileModel.getInstance().getUndoManager();
		try {
			if (undo.canUndo()) {
				undo.undo();
			}
		} catch (CannotUndoException ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	/**
	 * Redo most changes until no more changes are present
	 */
	public static void redo() {
		final UndoManager undo = FileModel.getInstance().getUndoManager();
		try {
			if (undo.canRedo()) {
				undo.redo();
			}
		} catch (CannotUndoException ex) {
			System.out.println(ex.getStackTrace());
		}
	}


    public static void setHTMLtype() {
	    if(textSpace!=null) {
            textSpace.setEditorKit(new HTMLEditorKit());
        }
    }
}
