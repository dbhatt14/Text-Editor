package service;

import constant.EditorConstants;
import listener.TextChangeListener;
import model.FileModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
//import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;
//import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import controller.EditController;
import controller.SupportedKeywords;


public class FileOpenService {
	
	private static SupportedKeywords kw = new SupportedKeywords();

	public void open() {
		String filePath = FileModel.getInstance().getFilePath();
		JFileChooser fileChooser = new JFileChooser();
		setFileTypeChoicesForOpen(fileChooser);
		File fileToOpen = null;
		if (filePath != null && !filePath.equals("")) {
			fileChooser.setCurrentDirectory(new File(filePath));
		} else {
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		}
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().getAbsolutePath();
			fileToOpen = new File(filePath);

			try {
				
				if (FilenameUtils.isExtension(filePath, "ise")) {
					FileInputStream in = new FileInputStream(fileToOpen);
					ObjectInputStream inputStream = new ObjectInputStream(in);
					JTextPane pane = (JTextPane) inputStream.readObject();
					AbstractDocument paneDocument = (AbstractDocument) pane.getDocument();
//					paneDocument.setDocumentFilter(new TextChangeListener(pane));
					FileModel.getInstance().getTextArea().setDocument(paneDocument);
					inputStream.close();
				}else {	
				Tika tika = new Tika();
				InputStream stream = new FileInputStream(filePath);
                String plainText = tika.parseToString(stream);
                Reader reader = new StringReader(plainText);
//                Reader reader	= tika.parse(fileToOpen);
                
                FileModel.getInstance().getTextArea().read(reader, "");
                AbstractDocument updatedDocument = (AbstractDocument) FileModel.getInstance().getTextArea().getDocument();
                updatedDocument.setDocumentFilter(new TextChangeListener(FileModel.getInstance().getTextArea()));
                FileModel.getInstance().setContent(updatedDocument.getText(0, updatedDocument.getLength()));
                reader.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// } catch (BadLocationException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileModel.getInstance().setFilePath(filePath);
			FileModel.getInstance().setFilename(fileChooser.getSelectedFile().getName());
			//xiangwei for test filename
			//automatively highlight when open java/cpp/python file
			System.out.println(fileChooser.getSelectedFile().getName());
			String[] list = kw.getSupportedLanguages();
			for (int i = 0; i < list.length; i++) {
	            if (fileChooser.getSelectedFile().getName().endsWith(list[i])) {
	                switch (i) {
	                    case 0:
	                        EditController.setinitjavahighlight();
	                    case 1:
	                    	EditController.setinitcpphighlight();
	                    case 2:
	                    	EditController.setinitpythonhighlight();
	                    default:
	                    	System.out.println("not java/cpp/python file");
	                }
	            }
	        }
	    
			
			

		} else {
			System.out.println("User Cancelled Open");
		}
	}

	/**
	 * Set the choices for the types of files one can choose to open the document
	 * 
	 * @param fileChooser
	 *            The JFileChooser used to open the document
	 */
	private void setFileTypeChoicesForOpen(JFileChooser fileChooser) {
		FileNameExtensionFilter[] fileTypesChoices = new FileNameExtensionFilter[EditorConstants.FILE_TYPE_CHOICES.length];
		for (int i = 0; i < (EditorConstants.FILE_TYPE_CHOICES.length); i++) {
			fileTypesChoices[i] = new FileNameExtensionFilter(EditorConstants.FILE_TYPE_CHOICES[i][0],
					EditorConstants.FILE_TYPE_CHOICES[i][1]);
			fileChooser.addChoosableFileFilter(fileTypesChoices[i]);
		}
	}
	


}
