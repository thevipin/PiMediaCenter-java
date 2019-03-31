package compounds;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RenameController extends VPanel {
	public RenameController() {
		super();
		setBackground(Color.GRAY);
		setLayout(new BorderLayout(3, 10));
		
		textField = new JTextField();
		btnNewButton = new VButton("Save");	
		btnNewButton.setText("Rename");		
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		add(textField);
		textField.setColumns(10);
		add(btnNewButton, BorderLayout.EAST);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3194510991691986556L;
	public JTextField textField;
	public VButton btnNewButton;

}
