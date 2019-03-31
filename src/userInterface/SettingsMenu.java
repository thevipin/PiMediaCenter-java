package userInterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class SettingsMenu extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8882012217699018356L;
	private JTextField textFieldServerPort;
	private JTextField textFieldDataDormat;
	private JTextField textFieldTimeFormat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsMenu frame = new SettingsMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SettingsMenu() {
		setBounds(100, 100, 450, 300);
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		JLabel lblMainServerPort = new JLabel("Main Server Port");
		lblMainServerPort.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(lblMainServerPort, "cell 0 0,alignx trailing");
		
		textFieldServerPort = new JTextField();
		textFieldServerPort.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(textFieldServerPort, "cell 1 0,growx");
		textFieldServerPort.setColumns(10);
		
		JLabel lblAudioOutput = new JLabel("Audio Output");
		lblAudioOutput.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(lblAudioOutput, "cell 0 1,alignx trailing");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"both", "hdmi", "local"}));
		comboBox.setMaximumRowCount(3);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(comboBox, "cell 1 1,growx");
		
		JLabel lblDateFormat = new JLabel("Date Format");
		lblDateFormat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(lblDateFormat, "cell 0 2,alignx trailing");
		
		textFieldDataDormat = new JTextField();
		textFieldDataDormat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(textFieldDataDormat, "cell 1 2,growx");
		textFieldDataDormat.setColumns(10);
		
		JLabel lblTimeFormat = new JLabel("Time Format");
		lblTimeFormat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(lblTimeFormat, "cell 0 3,alignx trailing");
		
		textFieldTimeFormat = new JTextField();
		textFieldTimeFormat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenter.add(textFieldTimeFormat, "cell 1 3,growx");
		textFieldTimeFormat.setColumns(10);
		
		JButton btnSaveRestart = new JButton("Save & Restart");
		panelCenter.add(btnSaveRestart, "cell 1 5");
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
		this.setOpaque(false);

	}

}
