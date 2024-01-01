package patientUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLightLaf;

import PatientManagement.*;
import mainUI.loginUI;
import mainUI.settingsUI;
import swingHelper.AppIcon;

public class EditPatientInfoUI extends JFrame implements ActionListener {
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
	PatientList patients;

	// panels
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private JPanel leftMain;
	private JPanel midMain;
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
	private JButton saveRecord;
	private JButton allMedicalConditions;
	private JButton allLifestyleHabits;
	private JButton allAllergies;
	private JButton prescriptions;

	// text elements
	private JLabel familyDoc = new JLabel("Family Doctor");
	private JLabel personalInfo = new JLabel("Personal Information");
	private JLabel contactInfo = new JLabel("Contact Information");
	private JLabel insuranceInfo = new JLabel("Insurance");
	private JLabel dateOfBirthLabel = new JLabel("Date of Birth");
	private JTextField dateOfBirthField = new JTextField("0000-00-00");
	private JLabel healthCardNumLabel = new JLabel("Health Card Number");
	private JTextField healthCardNumField = new JTextField("0000-000-000-AB");
	private JLabel emailLabel = new JLabel("Email");
	private JTextField emailField = new JTextField("example");
	private JLabel phoneLabel = new JLabel("Phone Number");
	private JTextField phoneField = new JTextField("(000) 000-0000");
	private JLabel addressLabel = new JLabel("Address");
	private JTextField addressField = new JTextField("123 ABC St.");
	private JButton addInsurance;
	private JButton removeInsurance;
	private JLabel additionalNotesLabel = new JLabel("Additional Notes");
	private JTextArea additionalNotesArea = new JTextArea();
	private JScrollPane additionalNotes;
	private JLabel docNameLabel = new JLabel("Name");
	private JTextField docNameField = new JTextField();
	private JLabel docPhoneNumberLabel = new JLabel("Phone Number");
	private JTextField docPhoneNumberField;
	private JLabel docAddressLabel = new JLabel("Address");
	private JTextField docAddressField = new JTextField("123 ABC Rd.");
	private JLabel patientNameLabel = new JLabel("Name");
	private JLabel prescriptionsLabel = new JLabel("Prescriptions");
	private JTextField patientNameField;
	private Insets textFieldPadding;
	

	// icons
	public AppIcon stockIcon = new AppIcon("icons/box.png");// icon for stock
	public AppIcon orderIcon = new AppIcon("icons/clipboard.png");// icon for order
	public AppIcon settingsIcon = new AppIcon("icons/gear.png");// icon for settings
	public AppIcon patientsIcon = new AppIcon("icons/person.png");// icon for patients

	public EditPatientInfoUI(String title, Patient patient, PatientList patients) {
		FlatLightLaf.setup();
		setTitle(title);
		Rectangle screenDims = GraphicsEnvironment.getLocalGraphicsEnvironment().getLocalGraphicsEnvironment()
				.getMaximumWindowBounds(); // dimensions of screen from https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
//		screenDims.width /= 1.5;
//		screenDims.height /=1.5;
		setSize(screenDims.width, screenDims.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.patient = patient;
		this.patients = patients;
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

		GridBagConstraints personalInfoConstraints = new GridBagConstraints();

		personalInfoConstraints.fill = GridBagConstraints.HORIZONTAL;
		personalInfoConstraints.gridx = 0;
		personalInfoConstraints.gridy = 0;
		personalInfoConstraints.gridwidth = 1;
		personalInfoConstraints.anchor = GridBagConstraints.NORTH;
		personalInfo.setFont(nameFont);
		personalInfo.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(personalInfo, personalInfoConstraints);
		
		GridBagConstraints contactInfoConstraints = new GridBagConstraints();
		contactInfoConstraints.fill = GridBagConstraints.HORIZONTAL;
		contactInfoConstraints.gridx = 1;
		contactInfoConstraints.gridy = 0;
		contactInfoConstraints.gridwidth = 1;
		contactInfoConstraints.anchor = GridBagConstraints.NORTH;
		contactInfo.setFont(nameFont);
		contactInfo.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(contactInfo, contactInfoConstraints);
		
		GridBagConstraints familyDocTitleConstraints = new GridBagConstraints();
		familyDocTitleConstraints.fill = GridBagConstraints.HORIZONTAL;
		familyDocTitleConstraints.gridx = 2;
		familyDocTitleConstraints.gridy = 0;
		familyDocTitleConstraints.anchor = GridBagConstraints.NORTH;
		familyDoc.setHorizontalAlignment(JLabel.CENTER);
		familyDoc.setFont(nameFont);
		mainPanel.add(familyDoc, familyDocTitleConstraints);

		leftMain = new JPanel(new GridLayout(6, 1));
		
		
		patientNameField = new JTextField(patient.getName());
		patientNameField.setBorder(textBoxBorder);
		patientNameLabel.setFont(genFont);
		patientNameField.setFont(genFont);
		leftMain.add(patientNameLabel);
		leftMain.add(patientNameField);
		
		dateOfBirthField = new JTextField(
				patient.getDateOfBirthMonth() + " " + patient.getDateOfBirthDay() + ", " + patient.getBirthYear());
		dateOfBirthField.setBorder(textBoxBorder);
		dateOfBirthLabel.setFont(genFont);
		dateOfBirthField.setFont(genFont);
		leftMain.add(dateOfBirthLabel);
		leftMain.add(dateOfBirthField);

		healthCardNumField = new JTextField("0000-000-000-AB");
		healthCardNumField.setBorder(textBoxBorder);
		healthCardNumLabel.setFont(genFont);
		healthCardNumField.setFont(genFont);
		leftMain.add(healthCardNumLabel);
		leftMain.add(healthCardNumField);

		


		GridBagConstraints lConstraints = new GridBagConstraints();

		lConstraints.fill = GridBagConstraints.HORIZONTAL;
		lConstraints.gridx = 0;
		lConstraints.gridy = 1;
		lConstraints.gridheight = 1;
		lConstraints.anchor = GridBagConstraints.NORTH;
		lConstraints.insets = new Insets(0, 0, 0, (int) (screenDims.width * 0.01));
		lConstraints.ipadx = screenDims.width / 5;
		mainPanel.add(leftMain, lConstraints);
		
		insuranceInfo.setFont(nameFont);
		addInsurance = new JButton("Add New Insurance");
		addInsurance.setBorder(textBoxBorder);
		removeInsurance = new JButton("Remove Existing Insurance");
		removeInsurance.setBorder(textBoxBorder);
		addInsurance.setFont(genFont);
		removeInsurance.setFont(genFont);
		addInsurance.setFocusPainted(false);
		removeInsurance.setFocusPainted(false);

		insuranceGrid = new JPanel(new GridLayout (3, 1, 0, (int) (screenDims.height * 0.01)));
		
		insuranceInfo.setHorizontalAlignment(JLabel.CENTER);
		insuranceGrid.add(insuranceInfo);
		insuranceGrid.add(addInsurance);
		insuranceGrid.add(removeInsurance);
		
		GridBagConstraints insuranceConstraints = new GridBagConstraints();
		
		insuranceConstraints.fill = GridBagConstraints.HORIZONTAL;
		insuranceConstraints.gridx = 0;
		insuranceConstraints.gridy = 2;
		insuranceConstraints.gridheight = 1;
		insuranceConstraints.anchor = GridBagConstraints.NORTH;
		insuranceConstraints.insets = new Insets(0, 0, 0, (int) (screenDims.width * 0.01));
		insuranceConstraints.ipadx = screenDims.width / 5;
		insuranceConstraints.ipady = screenDims.height / 10;
		mainPanel.add(insuranceGrid, insuranceConstraints);
		

		midMain = new JPanel(new GridLayout(6, 1));
		
		emailField = new JTextField(patient.getEmail());
		emailField.setBorder(textBoxBorder);
		emailLabel.setFont(genFont);
		emailField.setFont(genFont);
		midMain.add(emailLabel);
		midMain.add(emailField);

		phoneField = new JTextField(String.valueOf(patient.getPhoneNumber()));
		phoneField.setBorder(textBoxBorder);
		phoneLabel.setFont(genFont);
		phoneField.setFont(genFont);
		midMain.add(phoneLabel);
		midMain.add(phoneField);

		addressField = new JTextField(patient.getAddress());
		addressField.setBorder(textBoxBorder);
		addressLabel.setFont(genFont);
		addressField.setFont(genFont);
		midMain.add(addressLabel);
		midMain.add(addressField);

		GridBagConstraints mConstraints = new GridBagConstraints();

		mConstraints.fill = GridBagConstraints.HORIZONTAL;
		mConstraints.gridx = 1;
		mConstraints.gridy = 1;
		mConstraints.gridheight = 2;
		mConstraints.anchor = GridBagConstraints.NORTH;
		mConstraints.insets = new Insets(0, (int) (screenDims.width * 0.01), 0, (int) (screenDims.width * 0.01));
		mConstraints.ipadx = screenDims.width / 5;
		mainPanel.add(midMain, mConstraints);

		rightMain = new JPanel(new GridLayout(6, 1));

		docNameField = new JTextField(patient.getFamilyDoctorName());
		docNameField.setBorder(textBoxBorder);
		docNameLabel.setFont(genFont);
		docNameField.setFont(genFont);
		rightMain.add(docNameLabel);
		rightMain.add(docNameField);

		docPhoneNumberField = new JTextField(String.valueOf(patient.getFamilyDoctorNumber()));
		docPhoneNumberField.setBorder(textBoxBorder);
		docPhoneNumberLabel.setFont(genFont);
		docPhoneNumberField.setFont(genFont);
		rightMain.add(docPhoneNumberLabel);
		rightMain.add(docPhoneNumberField);

		docAddressField = new JTextField(patient.getFamilyDoctorAddress());
		docAddressField.setBorder(textBoxBorder);
		docAddressLabel.setFont(genFont);
		docAddressField.setFont(genFont);
		rightMain.add(docAddressLabel);
		rightMain.add(docAddressField);

		GridBagConstraints rConstraints = new GridBagConstraints();

		rConstraints.fill = GridBagConstraints.HORIZONTAL;
		rConstraints.gridx = 2;
		rConstraints.gridy = 1;
		rConstraints.gridheight = 1;
		rConstraints.anchor = GridBagConstraints.NORTH;
		rConstraints.insets = new Insets(0, (int) (screenDims.width * 0.01), 0, 0);
		rConstraints.ipadx = screenDims.width / 7;
		mainPanel.add(rightMain, rConstraints);

		bottomMain = new JPanel(new GridLayout(3, 1));

		additionalInfoButtons = new JPanel(new GridLayout(1, 3, (int) (screenDims.width * 0.01), 0));
		allMedicalConditions = new JButton("View Medical Condititions");
		allLifestyleHabits = new JButton("View Lifestyle Habits");
		allAllergies = new JButton("View Allergies/Dietary Restrictions");
		allMedicalConditions.setBorder(textBoxBorder);
		allLifestyleHabits.setBorder(textBoxBorder);
		allAllergies.setBorder(textBoxBorder);
		allMedicalConditions.setFont(genFont);
		allLifestyleHabits.setFont(genFont);
		allAllergies.setFont(genFont);
		additionalInfoButtons.add(allMedicalConditions);
		additionalInfoButtons.add(allLifestyleHabits);
		additionalInfoButtons.add(allAllergies);
		

		additionalNotesArea = new JTextArea("lalalala\nlalalalala\nla\nla\nla\nla");
		additionalNotesArea.setBorder(textBoxBorder);
		additionalNotesLabel.setFont(genFont);
		additionalNotesLabel.setVerticalAlignment(JLabel.BOTTOM);
		additionalNotesArea.setFont(genFont);
		additionalNotesArea.setLineWrap(true);
		additionalNotes = new JScrollPane(additionalNotesArea);
		additionalNotes.setMinimumSize(new Dimension(0, screenDims.height / 8));
		bottomMain.add(additionalNotesLabel);
		// bottomMain.add(additionalInfoButtons);
		bottomMain.add(additionalNotes);

		GridBagConstraints bConstraints = new GridBagConstraints();

		bConstraints.fill = GridBagConstraints.HORIZONTAL;
		bConstraints.gridx = 1;
		bConstraints.gridy = 2;
		bConstraints.gridheight = 1;
		bConstraints.gridwidth = 2;
		bConstraints.anchor = GridBagConstraints.NORTH;
		bConstraints.insets = new Insets(0, (int) (screenDims.width * 0.01), 0, (int) (screenDims.width * 0.01));
		mainPanel.add(bottomMain, bConstraints);

		bottomButtonsMain = new JPanel(new GridLayout(1, 2, (int) (screenDims.width * 0.05), 0));

		cancel = new JButton("Cancel");
		cancel.setBorder(textBoxBorder);
		saveRecord = new JButton("Save Record");
		saveRecord.setBorder(textBoxBorder);
		cancel.setFont(genFont);
		saveRecord.setFont(genFont);
		bottomButtonsMain.add(cancel);
		bottomButtonsMain.add(saveRecord);

		GridBagConstraints buttonConstraints = new GridBagConstraints();

		buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
		buttonConstraints.gridx = 2;
		buttonConstraints.gridy = 4;
		buttonConstraints.gridheight = 1;
		buttonConstraints.anchor = GridBagConstraints.NORTH;
		buttonConstraints.insets = new Insets(0, (int) (screenDims.width * 0.01), 0, (int) (screenDims.width * 0.01));
		mainPanel.add(bottomButtonsMain, buttonConstraints);

		add(mainPanel, BorderLayout.CENTER);
		
		prescriptions = new JButton("Edit Prescriptions");
		prescriptions.setFont(genFont);
		prescriptions.setBorder(textBoxBorder);
		prescriptionsLabel.setFont(nameFont);
		
		GridBagConstraints prescriptionsLabelConstraints = new GridBagConstraints();
		
		prescriptionsLabelConstraints.fill = GridBagConstraints.BOTH;
		prescriptionsLabelConstraints.gridx = 0;
		prescriptionsLabelConstraints.gridy = 3;
		prescriptionsLabelConstraints.anchor = GridBagConstraints.NORTH;
		prescriptionsLabelConstraints.insets = new Insets(0, 0, (int) (screenDims.height * 0.01), 0);
		prescriptionsLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(prescriptionsLabel, prescriptionsLabelConstraints); 
		
		GridBagConstraints prescriptionsConstraints = new GridBagConstraints();
		
		prescriptionsConstraints.fill = GridBagConstraints.BOTH;
		prescriptionsConstraints.gridx = 0;
		prescriptionsConstraints.gridy = 4;
		prescriptionsConstraints.anchor =GridBagConstraints.SOUTH;
		mainPanel.add(prescriptions, prescriptionsConstraints);
		

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
