package controller;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.Color;

public class HighlightText extends DefaultHighlighter.DefaultHighlightPainter{

    public HighlightText(Color color) {
        super(color);
    }

    public void highLight(JTextComponent textComp, String[] pattern) {
        removeHighlights(textComp);
        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            for (int i = 0; i < pattern.length; i++) {
                int pos = 0;
                //System.out.println(pattern+"choose pattern");//for test only

                while ((pos = text.indexOf(pattern[i], pos)) >= 0) {
                	hilite.addHighlight(pos, pos + pattern[i].length(), this);
                	System.out.println(pattern[i]+"start"+pos+"end"+(pos + pattern[i].length()));//for test only
                    pos += pattern[i].length();
                }
            }
        } catch (BadLocationException e) {
        	System.out.println("highlight error, please check configuration");//for test only
        }

    }

    public void removeHighlights(JTextComponent textComp) {

        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i = 0; i < hilites.length; i++) {
            if (hilites[i].getPainter() instanceof HighlightText) {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
}
