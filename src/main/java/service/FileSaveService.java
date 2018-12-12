package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import constant.EditorConstants;
import model.FileModel;
import listener.TextChangeListener;

/**
 * Service methods to save file.
 * 
 */
public class FileSaveService {
	private boolean userConfirmedSave;
	private boolean fileAlreadyExists;
	private boolean keepExistingFile;
	private File fileToSave;
	private boolean isSaveAs;
	private boolean saveToCurrent;
	JFileChooser jFileChooser;
	String fileExtension;
	private final static String DEFAULT_EXTENSION = "ise";

	public FileSaveService(boolean isSaveAs) {
		userConfirmedSave = true;
		fileAlreadyExists = true;
		keepExistingFile = true;
		saveToCurrent = false;
		this.isSaveAs = isSaveAs;
	}

	/**
	 * service method to save the file to user defined location
	 */
	public void save() {
		// Tries to save unsaved file into the file system
		updateFileToSave();

		// Check if the file already exists
		fileAlreadyExists = checkIfFileExists(fileToSave);
		if (fileAlreadyExists && !saveToCurrent) {
			prompUserToUpdateOrOverride();
		}

		// if everything checks out, start saving to the physical location in
		// the file system
		try {
			saveFile();
			TextChangeListener.edit = false;
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Writes the content to the user defined path
	 * 
	 * @throws BadLocationException
	 * @throws DocumentException
	 */
	private void saveFile() throws BadLocationException, DocumentException {
		if (userConfirmedSave && (!fileAlreadyExists || !keepExistingFile || saveToCurrent)) {
			try {
				if (fileExtension.equalsIgnoreCase("PDF")) {
					writePdf();
				} else if (fileExtension.equalsIgnoreCase("DOCX")) {
					writeDocx();
				} else if (fileExtension.equalsIgnoreCase("TXT")) {
					writeTxt();
				} else {
					writeISE();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Save Cancelled");
			}
		} else {
			System.out.println("Cancelled save");
		}

	}

	/**
	 * Saves the text in the default extension format
	 * 
	 * @throws IOException
	 * @throws BadLocationException
	 */
	private void writeISE() throws IOException, BadLocationException {
		saveObectToFile(FileModel.getInstance().getTextArea(), fileToSave);
	}

	/**
	 * Saves the text in docx format.
	 */
	private void writeDocx() {
		XWPFDocument wordDocument = new XWPFDocument();
		try {
			FileOutputStream wordFileOutputStream = new FileOutputStream(fileToSave);
			String text = FileModel.getInstance().getTextArea().getText();
			this.separateParagraphsForDocx(text, wordDocument);
			wordDocument.write(wordFileOutputStream);
			wordFileOutputStream.close();
			wordDocument.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the text in the txt format
	 * 
	 * @throws IOException
	 * @throws BadLocationException
	 */	
	private void writeTxt() throws IOException, BadLocationException {
		System.out.println("Saving Text File");
		BufferedWriter out = new BufferedWriter(new FileWriter(fileToSave.getPath()));
        out.write(FileModel.getInstance().getTextArea().getText());
        out.close();
        //TextChangeListener.edit = false;
	}

	/**
	 * Helper method to format Word documents to preserve formatting of multiple 
	 * paragraphs
	 * @param input
	 * @param wordDoc
	 */
	private void separateParagraphsForDocx(String input, XWPFDocument wordDoc) {
		char returnChar = '\r';
		while (input.length() != 0) {
			XWPFParagraph currentParagraph = wordDoc.createParagraph();
			XWPFRun run = currentParagraph.createRun();
			
			int returnCharIndex = input.indexOf(returnChar);
			if (returnCharIndex == -1) {
				run.setText(input);
				break;
			} else {
				String updatedPriorParagraph = input.substring(0, returnCharIndex);
				run.setText(updatedPriorParagraph);
				input = input.substring(returnCharIndex + 1);
			}
		}
	}
	
	/**
	 * Writing the text content of the editor into a new pdf file.
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void writePdf() throws DocumentException, IOException {
		com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document(PageSize.A4);
		PdfWriter.getInstance(pdfDoc, new FileOutputStream(fileToSave)).setPdfVersion(PdfWriter.PDF_VERSION_1_7);
		pdfDoc.open();
		
		Paragraph para = new Paragraph(FileModel.getInstance().getTextArea().getText());
		pdfDoc.add(para);
		pdfDoc.close();
	}

	/**
	 * displays the dialog to ask user to either change file name or override
	 */
	private void prompUserToUpdateOrOverride() {
		String filePath = FileModel.getInstance().getFilePath();

		while (fileAlreadyExists && keepExistingFile) {
			int saveResponse = JOptionPane.showConfirmDialog(null, "File already exists. Do you wish to replace it?",
					"Replace file?", JOptionPane.YES_NO_OPTION);
			if (saveResponse == JOptionPane.YES_OPTION) {
				keepExistingFile = false;
				break;
			} else if (saveResponse == JOptionPane.NO_OPTION) {
				jFileChooser = new JFileChooser();
				if (filePath != null && !filePath.equals("")) {
					jFileChooser.setCurrentDirectory(new File(filePath));
				} else {
					jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				}
				int showSaveDialog = jFileChooser.showSaveDialog(null);
				if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
					filePath = jFileChooser.getSelectedFile().getAbsolutePath();
					fileExtension = getFileFilterFromDescription(jFileChooser);
					fileToSave = new File(filePath + "." + fileExtension);
					FileModel.getInstance().setFilePath(filePath);
					FileModel.getInstance().setFilename(jFileChooser.getSelectedFile().getName());
				} else {
					userConfirmedSave = false;
					break; // To exit the while loop if user says no and cancels
				}

				fileAlreadyExists = checkIfFileExists(fileToSave);

			}
		}

	}

	/**
	 * Update file to save object
	 */
	private void updateFileToSave() {
		String fileName = FileModel.getInstance().getFilename();
		String filePath = FileModel.getInstance().getFilePath();
		if (fileName == null && filePath == null || isSaveAs) {
			jFileChooser = new JFileChooser();
			setFileTypeChoicesForSave(jFileChooser);
			if (filePath != null && !filePath.equals("")) {
				jFileChooser.setCurrentDirectory(new File(filePath));
			} else {
				jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			}
			int showSaveDialog = jFileChooser.showSaveDialog(null);
			if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
				filePath = jFileChooser.getSelectedFile().getAbsolutePath();
				fileExtension = getFileFilterFromDescription(jFileChooser);
				System.out.println(fileExtension);
				fileToSave = new File(filePath + "." + fileExtension);
				FileModel.getInstance().setFilePath(filePath);
				FileModel.getInstance().setFilename(jFileChooser.getSelectedFile().getName());
			} else {
				userConfirmedSave = false;
			}

		} else {
			fileToSave = new File(filePath);
			String[] extension = fileName.split("\\.");
			fileExtension = extension[extension.length-1];
			saveToCurrent = true;

		}

	}

	/**
	 * Returns the extension type from the list of extensions by their description.
	 * By default returns txt
	 * 
	 * @param chooser
	 * @return
	 */
	private String getFileFilterFromDescription(JFileChooser chooser) {
		for (int i = 0; i < EditorConstants.FILE_TYPE_CHOICES.length; i++) {
			if (EditorConstants.FILE_TYPE_CHOICES[i][0].equalsIgnoreCase(chooser.getFileFilter().getDescription())) {
				return EditorConstants.FILE_TYPE_CHOICES[i][1];
			}
		}
		return DEFAULT_EXTENSION;
	}

	/**
	 * Sets the choices for the types of files one can choose to save the document
	 * 
	 * @param fileChooser
	 *            The JFileChooser used to save the document
	 */
	private void setFileTypeChoicesForSave(JFileChooser fileChooser) {
		FileNameExtensionFilter[] fileTypesChoices = new FileNameExtensionFilter[EditorConstants.FILE_TYPE_CHOICES.length];
		for (int i = 0; i < (EditorConstants.FILE_TYPE_CHOICES.length); i++) {
			fileTypesChoices[i] = new FileNameExtensionFilter(EditorConstants.FILE_TYPE_CHOICES[i][0],
					EditorConstants.FILE_TYPE_CHOICES[i][1]);
			fileChooser.addChoosableFileFilter(fileTypesChoices[i]);
		}
	}

	/**
	 * Checks if a file is already saved in the system
	 * 
	 * @param fileToSaveName
	 *            File name given as input to use for filepath
	 * @return whether the file already exists
	 */
	private boolean checkIfFileExists(File fileToSaveName) throws NullPointerException {

		boolean exists = false;
		if (fileToSaveName == null) {
			return exists;
		} else {
			Path filePathName = fileToSaveName.toPath();
			exists = Files.exists(filePathName);

			return exists;
		}
	}

	/**
	 * Saving the JEditorPane directly as an object to preserve styling
	 * 
	 * @param obj
	 * @param filepath
	 * @throws IOException
	 */
	private void saveObectToFile(Object obj, File fileToSave) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(fileToSave);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(obj);
		objectOutputStream.close();
	}

}
