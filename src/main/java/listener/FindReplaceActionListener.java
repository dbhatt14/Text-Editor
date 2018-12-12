package listener;

import model.FileModel;
import view.FindReplaceView;

import javax.swing.JEditorPane;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.BadLocationException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**+
 * This class is the action listener class for the Find and Replace Pop-up
 * box with action events declared for all cases of selection.
 * Source Word : What
 * Checking for 2 states i.e.
 * 1.Match whole word only
 * 2.Match Case
 */

public class FindReplaceActionListener implements ActionListener {
    Pattern p;
    Matcher m;
    int singleFindPos = 0;
    Highlighter highlighter;
    DefaultHighlighter.DefaultHighlightPainter highlightPainter;

    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == FindReplaceView.getWhat()) {
            FindReplaceView.getWith().requestFocusInWindow();
        }
        /**
         * Find Click Action Events go here
         */
        if(e.getSource() == FindReplaceView.getFind()) {
            if(!FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Find Case 1 stuff by calling controller
                p = Pattern.compile(FindReplaceView.getWhat().getText(),Pattern.CASE_INSENSITIVE);
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

                addSingleHighlight(FindReplaceView.getPane());
            } else if(!FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Find Case 2 stuff by calling controller
                p = Pattern.compile(FindReplaceView.getWhat().getText());
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                addSingleHighlight(FindReplaceView.getPane());
            } else if(FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Find Case 3 stuff by calling controller
                p = Pattern.compile("\\b"+FindReplaceView.getWhat().getText()+"\\b",Pattern.CASE_INSENSITIVE);
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                addSingleHighlight(FindReplaceView.getPane());
            } else if(FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Find Case 4 stuff by calling controller
                p = Pattern.compile("\\b"+FindReplaceView.getWhat().getText()+"\\b");
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                addSingleHighlight(FindReplaceView.getPane());
            }
        }
        /**
         * Find All Click Action Events go here
         */
        if(e.getSource() == FindReplaceView.getFindAll()) {
            if(!FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Find All Case 1 stuff by calling controller
                p = Pattern.compile(FindReplaceView.getWhat().getText(),Pattern.CASE_INSENSITIVE);
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

                addHighlight();
            } else if(!FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                p = Pattern.compile(FindReplaceView.getWhat().getText());
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

                addHighlight();



            } else if(FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Find All Case 3 stuff by calling controller
                p = Pattern.compile("\\b"+FindReplaceView.getWhat().getText()+"\\b",Pattern.CASE_INSENSITIVE);
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

                addHighlight();
            } else if(FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Find All Case 4 stuff by calling controller
                p = Pattern.compile("\\b"+FindReplaceView.getWhat().getText()+"\\b");
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

                addHighlight();
            }
        } else if(e.getSource() == FindReplaceView.getReplace()) {
            if(!FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Replace Case 1 stuff by calling controller
                p = Pattern.compile(FindReplaceView.getWhat().getText(),Pattern.CASE_INSENSITIVE);
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

                addSingleHighlight(FindReplaceView.getPane());
                String replacement = FindReplaceView.getWith().getText();
                String text = FindReplaceView.getPane().getText();
                text = text.replaceFirst(p.toString(), replacement);
                FindReplaceView.getPane().setText(text);

            } else if(!FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Replace Case 2 stuff by calling controller
                p = Pattern.compile(FindReplaceView.getWhat().getText());
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                addSingleHighlight(FindReplaceView.getPane());
                String replacement = FindReplaceView.getWith().getText();
                String text = FindReplaceView.getPane().getText();
                text = text.replaceFirst(p.toString(), replacement);
                FindReplaceView.getPane().setText(text);
            } else if(FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Replace Case 3 stuff by calling controller
                p = Pattern.compile("\\b"+FindReplaceView.getWhat().getText()+"\\b",Pattern.CASE_INSENSITIVE);
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                addSingleHighlight(FindReplaceView.getPane());
                String replacement = FindReplaceView.getWith().getText();
                String text = FindReplaceView.getPane().getText();
                text = text.replaceFirst(p.toString(), replacement);
                FindReplaceView.getPane().setText(text);
            } else if(FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Replace Case 4 stuff by calling controller
                p = Pattern.compile("\\b"+FindReplaceView.getWhat().getText()+"\\b");
                try {
                    m = p.matcher(FindReplaceView.getPane().getDocument().getText(0,FindReplaceView.getPane().getDocument().getLength()));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                highlighter = FindReplaceView.getPane().getHighlighter();
                highlighter.removeAllHighlights();
                highlightPainter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                addSingleHighlight(FindReplaceView.getPane());
                String replacement = FindReplaceView.getWith().getText();
                String text = FindReplaceView.getPane().getText();
                text = text.replaceFirst(p.toString(), replacement);
                FindReplaceView.getPane().setText(text);
            }
        } else if(e.getSource() == FindReplaceView.getReplaceAll()) {
            if(!FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                //TODO: Replace All Case 1 stuff by calling controller
                String toReplace = FindReplaceView.getWhat().getText();
                String replacement = FindReplaceView.getWith().getText();
                String text = FileModel.getInstance().getTextArea().getText();
                text = text.replaceAll("(?i)"+toReplace, replacement);
                FileModel.getInstance().getTextArea().setText(text);
            } else if(!FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                String toReplace = FindReplaceView.getWhat().getText();
                String replacement = FindReplaceView.getWith().getText();
                String text = FindReplaceView.getPane().getText();
                text = text.replaceAll(toReplace, replacement);
                FindReplaceView.getPane().setText(text);
            } else if(FindReplaceView.getWord().isSelected() && !FindReplaceView.getMatchCase().isSelected()) {
                String toReplace = "\\b" + FindReplaceView.getWhat().getText() + "\\b";
                String replacement = FindReplaceView.getWith().getText();
                String text = FindReplaceView.getPane().getText();
                text = text.replaceAll("(?i)"+toReplace, replacement);
                FindReplaceView.getPane().setText(text);
            } else if(FindReplaceView.getWord().isSelected() && FindReplaceView.getMatchCase().isSelected()) {
                String toReplace = "\\b" + FindReplaceView.getWhat().getText() + "\\b";
                String replacement = FindReplaceView.getWith().getText();
                String text = FindReplaceView.getPane().getText();
                text = text.replaceAll(toReplace, replacement);
                FindReplaceView.getPane().setText(text);
            }
        } else if(e.getSource() == FindReplaceView.getClose()){
                closeOperations();
                FindReplaceView.onClose();
        }

    }



    private void addSingleHighlight(JEditorPane pane) {
        removeHighlights(pane);

        if(m.find(singleFindPos)) {
            try {
                highlighter.addHighlight(m.start(), m.end(), highlightPainter);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            if(singleFindPos < pane.getText().length()) {
                singleFindPos = m.end();
            } else {
                singleFindPos = 0;
            }
        }else{
            if(singleFindPos >0) {
                singleFindPos = 0;
            }
        }
    }


    public static void closeOperations() {
        removeHighlights(FindReplaceView.getPane());
    }

    private static void removeHighlights(JEditorPane pane) {
        Highlighter highlight = pane.getHighlighter();
        Highlighter.Highlight[] highlights = highlight.getHighlights();

        for (int i = 0; i < highlights.length; i++) {
            if (highlights[i].getPainter() instanceof DefaultHighlighter.DefaultHighlightPainter) {
                highlight.removeHighlight(highlights[i]);
            }
        }
    }

    private void addHighlight() {
        int pos = 0;
        while (m.find(pos)) {
            int start = m.start();
            int end   = m.end();
            try {
                highlighter.addHighlight(start, end, highlightPainter);
               } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            pos = end;
        }
    }
}
