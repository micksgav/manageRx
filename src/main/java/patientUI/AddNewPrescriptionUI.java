package patientUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import com.formdev.flatlaf.FlatLightLaf;

import PatientManagement.Patient;
import mainUI.loginUI;
import mainUI.settingsUI;
import swingHelper.AppIcon;

public class AddNewPrescriptionUI extends JFrame implements ActionListener {
	private JButton openSettings = new JButton();
	private JButton openPatientManager = new JButton();
	private JButton openStock = new JButton();
	private JButton openOrder = new JButton();
	private loginUI login = new loginUI();
	private settingsUI settings = new settingsUI();
	private patientManagerUI patientManager = new patientManagerUI();
	// private StockUI stock = new StockUI();
	// private OrderUI order = new OrderUI();

	Patient patient;

	// panels
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private JPanel leftMain;
	private JPanel rightMain;
	private JPanel bottomMain;
	private JPanel additionalInfoButtons;
	private JPanel bottomButtonsMain;
	private JPanel insuranceGrid;

	// header buttons
	private JButton btnOpenStock;
	private JButton btnOpenOrder;
	private JButton btnOpenSettings;
	private JButton btnOpenPatientManager;

	// main buttons
	private JButton cancel;
	private JButton saveGoBack;
	private JButton saveAddMore;

	// text elements
	private JLabel drugTitle = new JLabel("Drug Information");
	private JLabel drugNameLabel = new JLabel("Name");
	private JTextField drugNameField;
	private JLabel atcLabel = new JLabel("ATC");
	private JTextField atcField;
	private JLabel rxcuiLabel = new JLabel("RxCUI");
	private JTextField rxcuiField;
	private JLabel dinLabel = new JLabel("DIN");
	private JTextField dinField;
	private JLabel genInfoTitle = new JLabel("General Info");
	private JLabel datePrescribedLabel = new JLabel("Date Prescribed");
	private JTextField datePrescribedField = new JTextField("Current date");
	private JLabel numRefillsLabel = new JLabel("Number of Refills");
	private JTextField numRefillsField;
	private JLabel quantityLabel = new JLabel("Quantity");
	private JTextField quantityField;
	private JLabel dosageLabel = new JLabel("Dosage");
	private JTextField dosageField;
	private JLabel instructionsLabel = new JLabel("Instructrions");
	private JTextArea instructionsArea;
	private JLabel prescribedDurationLabel = new JLabel("Prescribed Duration");
	private JTextField prescribedDurationField;
	private JLabel patientName;
	private Insets gridBagPadding;
	private Insets leftButtonPadding;
	private Insets rightButtonsPadding;

	// icons
	public AppIcon stockIcon = new AppIcon("icons/box.png");// icon for stock
	public AppIcon orderIcon = new AppIcon("icons/clipboard.png");// icon for order
	public AppIcon settingsIcon = new AppIcon("icons/gear.png");// icon for settings
	public AppIcon patientsIcon = new AppIcon("icons/person.png");// icon for patients

	public AddNewPrescriptionUI(String title, Patient patient) {
		FlatLightLaf.setup();
		setTitle(title);
		Rectangle screenDims = GraphicsEnvironment.getLocalGraphicsEnvironment().getLocalGraphicsEnvironment()
				.getMaximumWindowBounds(); // dimensions of screen from https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
		//Rectangle screenDims = new Rectangle(1366, 768);
		setSize(screenDims.width, screenDims.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.patient = patient;
		// textFieldPadding = new Insets((int) (screenDims.height *0.15), (int)
		// (screenDims.width *0.02), (int) (screenDims.height *0.1), (int)
		// (screenDims.width *0.02));

		stockIcon = stockIcon.setScale(0.12);
		orderIcon = orderIcon.setScale(0.12);
		settingsIcon = settingsIcon.setScale(0.12);
		patientsIcon = patientsIcon.setScale(0.12);

		this.buttonPanel = new JPanel(new FlowLayout());
		this.buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));

		JLabel label = new JLabel("ManageRx");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		this.buttonPanel.add(label);

		btnOpenStock = new JButton("Stock");
		btnOpenStock.setIcon(stockIcon);
		btnOpenStock.setActionCommand("openStock");
		btnOpenStock.addActionListener(this);
		this.buttonPanel.add(btnOpenStock, BorderLayout.CENTER);

		btnOpenOrder = new JButton("Order");
		btnOpenOrder.setIcon(orderIcon);
		btnOpenOrder.setActionCommand("openOrder");
		btnOpenOrder.addActionListener(this);
		this.buttonPanel.add(btnOpenOrder, BorderLayout.CENTER);

		btnOpenSettings = new JButton("Settings");
		btnOpenSettings.setIcon(settingsIcon);
		btnOpenSettings.setActionCommand("openSettings");
		btnOpenSettings.addActionListener(this);
		this.buttonPanel.add(btnOpenSettings, BorderLayout.CENTER);

		btnOpenPatientManager = new JButton("Patients");
		btnOpenPatientManager.setIcon(patientsIcon);
		btnOpenPatientManager.setActionCommand("openPatientManager");
		btnOpenPatientManager.addActionListener(this);
		this.buttonPanel.add(btnOpenPatientManager, BorderLayout.CENTER);

		add(this.buttonPanel, BorderLayout.NORTH);

		mainPanel = new JPanel(new GridBagLayout()); // information about GridBagLayout from
														// https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

		Font genFont = new Font("Arial", Font.PLAIN, 25);
		Font nameFont = new Font("Arial", Font.PLAIN, 35);
		Color textBoxFill = new Color(204, 204, 204);
		Border textBoxBorderLine = BorderFactory.createLineBorder(new Color(89, 89, 89), screenDims.width / 700); // https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/components/border.html#:~:text=To%20put%20a%20border%20around,a%20variable%20of%20type%20Border%20.
		Border textFieldPadding = new EmptyBorder((int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01),
				(int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01));
		CompoundBorder textBoxBorder = new CompoundBorder(textBoxBorderLine, textFieldPadding);
		gridBagPadding = new Insets(0, (int) (screenDims.width * 0.07), 0, (int) (screenDims.width *0.07));
		leftButtonPadding = new Insets((int) (screenDims.height *0.01), 0, (int) (screenDims.height * 0.01), (int) (screenDims.width * 0.25));
		rightButtonsPadding = new Insets((int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01), (int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01));
		
		GridBagConstraints nameConstraints = new GridBagConstraints();
		
		nameConstraints.fill = GridBagConstraints.BOTH;
		nameConstraints.gridx = 0;
		nameConstraints.gridy = 0;
		nameConstraints.gridwidth = 2;
		nameConstraints.anchor = GridBagConstraints.NORTH;
		patientName = new JLabel(patient.getName());
		patientName.setFont(nameFont);
		patientName.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(patientName, nameConstraints);
		
		leftMain = new JPanel(new GridLayout(8, 1));
		
		GridBagConstraints drugTitleConstraints = new GridBagConstraints();
		
		drugTitleConstraints.fill = GridBagConstraints.BOTH;
		drugTitleConstraints.gridx = 0;
		drugTitleConstraints.gridy = 1;
		drugTitleConstraints.gridwidth = 1;
		drugTitleConstraints.anchor = GridBagConstraints.SOUTH;
		drugTitleConstraints.insets = gridBagPadding;
		drugTitle.setFont(nameFont);
		drugTitle.setHorizontalAlignment(JLabel.LEFT);
		mainPanel.add(drugTitle, drugTitleConstraints);
		
		
		drugNameField = new JTextField();
		drugNameField.setBorder(textBoxBorder);
		drugNameLabel.setFont(genFont);
		drugNameField.setFont(genFont);
		leftMain.add(drugNameLabel);
		leftMain.add(drugNameField);
		
		atcField = new JTextField();
		atcField.setBorder(textBoxBorder);
		atcLabel.setFont(genFont);
		atcField.setFont(genFont);
		leftMain.add(atcLabel);
		leftMain.add(atcField);
		
		rxcuiField = new JTextField();
		rxcuiField.setBorder(textBoxBorder);
		rxcuiField.setFont(genFont);
		rxcuiLabel.setFont(genFont);
		leftMain.add(rxcuiLabel);
		leftMain.add(rxcuiField);
		
		dinField = new JTextField();
		dinField.setBorder(textBoxBorder);
		dinLabel.setFont(genFont);
		dinField.setFont(genFont);
		leftMain.add(dinLabel);
		leftMain.add(dinField);
		
		leftMain.setBorder(textBoxBorder);
		
		GridBagConstraints leftPanelConstraints = new GridBagConstraints();
		
		leftPanelConstraints.fill = GridBagConstraints.BOTH;
		leftPanelConstraints.gridx = 0;
		leftPanelConstraints.gridy = 2;
		leftPanelConstraints.gridwidth = 1;
		leftPanelConstraints.anchor = GridBagConstraints.WEST;
		leftPanelConstraints.ipadx = (int) (screenDims.width * 0.1);
		leftPanelConstraints.insets = gridBagPadding;
		mainPanel.add(leftMain, leftPanelConstraints);
		
		rightMain = new JPanel(new GridLayout(12, 1));
		
		GridBagConstraints genInfoTitleConstraints = new GridBagConstraints();
		
		genInfoTitleConstraints.fill = GridBagConstraints.BOTH;
		genInfoTitleConstraints.gridx = 1;
		genInfoTitleConstraints.gridy = 1;
		genInfoTitleConstraints.gridwidth = 1;
		genInfoTitleConstraints.anchor = GridBagConstraints.SOUTH;
		genInfoTitleConstraints.insets = gridBagPadding;
		genInfoTitle.setFont(nameFont);
		genInfoTitle.setHorizontalAlignment(JLabel.LEFT);
		mainPanel.add(genInfoTitle, genInfoTitleConstraints);
		
		datePrescribedField = new JTextField();
		datePrescribedField.setBorder(textBoxBorder);
		datePrescribedLabel.setFont(genFont);
		datePrescribedField.setFont(genFont);
		rightMain.add(datePrescribedLabel);
		rightMain.add(datePrescribedField);
		
		numRefillsField = new JTextField();
		numRefillsField.setBorder(textBoxBorder);
		numRefillsLabel.setFont(genFont);
		numRefillsField.setFont(genFont);
		rightMain.add(numRefillsLabel);
		rightMain.add(numRefillsField);
		
		quantityField = new JTextField();
		quantityField.setBorder(textBoxBorder);
		quantityField.setFont(genFont);
		quantityLabel.setFont(genFont);
		rightMain.add(quantityLabel);
		rightMain.add(quantityField);
		
		dosageField = new JTextField();
		dosageField.setBorder(textBoxBorder);
		dosageField.setFont(genFont);
		dosageLabel.setFont(genFont);
		rightMain.add(dosageLabel);
		rightMain.add(dosageField);
		
		instructionsArea = new JTextArea();
		instructionsArea.setBorder(textBoxBorder);
		instructionsArea.setFont(genFont);
		instructionsLabel.setFont(genFont);
		rightMain.add(instructionsLabel);
		rightMain.add(instructionsArea);
		
		prescribedDurationField = new JTextField();
		prescribedDurationField.setBorder(textBoxBorder);
		prescribedDurationField.setFont(genFont);
		prescribedDurationLabel.setFont(genFont);
		rightMain.add(prescribedDurationLabel);
		rightMain.add(prescribedDurationField);
		
		rightMain.setBorder(textBoxBorder);
		
		GridBagConstraints rightPanelConstraints = new GridBagConstraints();
		
		rightPanelConstraints.fill = GridBagConstraints.BOTH;
		rightPanelConstraints.gridx = 1;
		rightPanelConstraints.gridy = 2;
		rightPanelConstraints.gridwidth = 1;
		rightPanelConstraints.gridheight = 2;
		rightPanelConstraints.anchor = GridBagConstraints.WEST;
		rightPanelConstraints.ipadx = (int) (screenDims.width * 0.15);
		rightPanelConstraints.insets = gridBagPadding;
		mainPanel.add(rightMain, rightPanelConstraints);
		
		bottomMain = new JPanel(new GridBagLayout());
		
		cancel = new JButton("Cancel");
		saveGoBack = new JButton("Save and Go Back");
		saveAddMore = new JButton("Save and Add Another Prescription");
		cancel.setFont(genFont);
		saveGoBack.setFont(genFont);
		saveAddMore.setFont(genFont);
		cancel.setBorder(textBoxBorder);
		saveGoBack.setBorder(textBoxBorder);
		saveAddMore.setBorder(textBoxBorder);
		
		GridBagConstraints cancelConstraints = new GridBagConstraints();
		
		cancelConstraints.fill = GridBagConstraints.BOTH;
		cancelConstraints.gridx = 0;
		cancelConstraints.gridy = 0;
		cancelConstraints.gridwidth = 1;
		cancelConstraints.anchor = GridBagConstraints.WEST;
		cancelConstraints.weightx = 0.1;
		cancelConstraints.insets = leftButtonPadding;
		bottomMain.add(cancel, cancelConstraints);
		
		
		GridBagConstraints saveGoBackConstraints = new GridBagConstraints();
		
		saveGoBackConstraints.fill = GridBagConstraints.BOTH;
		saveGoBackConstraints.gridx = 1;
		saveGoBackConstraints.gridy = 0;
		saveGoBackConstraints.gridwidth = 1;
		saveGoBackConstraints.anchor = GridBagConstraints.EAST;
		saveGoBackConstraints.insets = rightButtonsPadding;
		bottomMain.add(saveGoBack, saveGoBackConstraints);
		
		GridBagConstraints saveAddMoreConstraints = new GridBagConstraints();
		
		saveAddMoreConstraints.fill = GridBagConstraints.BOTH;
		saveAddMoreConstraints.gridx = 2;
		saveAddMoreConstraints.gridy = 0;
		saveAddMoreConstraints.gridwidth = 1;
		saveAddMoreConstraints.anchor = GridBagConstraints.EAST;
		saveAddMoreConstraints.insets = rightButtonsPadding;
		bottomMain.add(saveAddMore, saveAddMoreConstraints);
		
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		
		buttonConstraints.fill = GridBagConstraints.BOTH;
		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = 4;
		buttonConstraints.gridwidth = 3;
		buttonConstraints.anchor = GridBagConstraints.SOUTH;
		mainPanel.add(bottomMain, buttonConstraints);
	

		add(mainPanel, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("openStock")) {
			System.out.println("Stock");
		}
		if (e.getActionCommand().equals("openOrder")) {
			System.out.println("Order");
		}
		if (e.getActionCommand().equals("openSettings")) {
			System.out.println("Settings");
		}
		if (e.getActionCommand().equals("openPatientManager")) {
			System.out.println("Patients");
		}
	}
}
