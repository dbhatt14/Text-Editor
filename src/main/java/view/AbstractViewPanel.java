package view;

import javax.swing.JComponent;

/**
 * All common elements and functions for Panel Views go here
 **/
public abstract class AbstractViewPanel extends JComponent {
	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	public abstract JComponent getPanel();


	protected abstract void initialize();


}
