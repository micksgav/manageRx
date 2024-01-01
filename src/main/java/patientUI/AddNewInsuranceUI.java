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

public class AddNewInsuranceUI extends JFrame implements ActionListener {
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
	private JLabel insuranceTitle = new JLabel("Insurance Information");
	private JLabel insuranceCompanyLabel = new JLabel("Company");
	private JTextField insuranceCompanyField;
	private JLabel insuranceNumberLabel = new JLabel("Insurance Number");
	private JTextField insuranceNumberField;
	private JLabel notesLabel = new JLabel("Notes");
	private JTextArea notesArea;
	private JLabel patientName;
	private Insets gridBagPadding;
	private Insets leftButtonPadding;
	private Insets rightButtonsPadding;

	// icons
	public AppIcon stockIcon = new AppIcon("icons/box.png");// icon for stock
	public AppIcon orderIcon = new AppIcon("icons/clipboard.png");// icon for order
	public AppIcon settingsIcon = new AppIcon("icons/gear.png");// icon for settings
	public AppIcon patientsIcon = new AppIcon("icons/person.png");// icon for patients

	public AddNewInsuranceUI(String title, Patient patient) {
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
		
		leftMain = new JPanel(new GridLayout(6, 1));
		
		GridBagConstraints drugTitleConstraints = new GridBagConstraints();
		
		drugTitleConstraints.fill = GridBagConstraints.BOTH;
		drugTitleConstraints.gridx = 0;
		drugTitleConstraints.gridy = 1;
		drugTitleConstraints.gridwidth = 1;
		drugTitleConstraints.anchor = GridBagConstraints.SOUTH;
		drugTitleConstraints.insets = gridBagPadding;
		insuranceTitle.setFont(nameFont);
		insuranceTitle.setHorizontalAlignment(JLabel.LEFT);
		mainPanel.add(insuranceTitle, drugTitleConstraints);
		
		
		insuranceCompanyField = new JTextField();
		insuranceCompanyField.setBorder(textBoxBorder);
		insuranceCompanyLabel.setFont(genFont);
		insuranceCompanyField.setFont(genFont);
		leftMain.add(insuranceCompanyLabel);
		leftMain.add(insuranceCompanyField);
		
		insuranceNumberField = new JTextField();
		insuranceNumberField.setBorder(textBoxBorder);
		insuranceNumberField.setFont(genFont);
		insuranceNumberLabel.setFont(genFont);
		leftMain.add(insuranceNumberLabel);
		leftMain.add(insuranceNumberField);
		
		notesArea = new JTextArea();
		notesArea.setBorder(textBoxBorder);
		notesArea.setFont(genFont);
		notesLabel.setFont(genFont);
		leftMain.add(notesLabel);
		JScrollPane scrollBar = new JScrollPane(notesArea);
		
		leftMain.setBorder(textBoxBorder);
		
		GridBagConstraints leftPanelConstraints = new GridBagConstraints();
		
		leftPanelConstraints.fill = GridBagConstraints.BOTH;
		leftPanelConstraints.gridx = 0;
		leftPanelConstraints.gridy = 2;
		leftPanelConstraints.gridwidth = 2;
		leftPanelConstraints.anchor = GridBagConstraints.CENTER;
		leftPanelConstraints.ipadx = (int) (screenDims.width * 0.5);
		leftPanelConstraints.ipady = (int) (screenDims.height * 0.2);
		leftPanelConstraints.insets = gridBagPadding;
		mainPanel.add(leftMain, leftPanelConstraints);
		
		
		bottomMain = new JPanel(new GridBagLayout());
		
		cancel = new JButton("Cancel");
		saveGoBack = new JButton("Save and Go Back");
		saveAddMore = new JButton("Save and Add Another Insurance Plan");
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
		buttonConstraints.insets = new Insets((int) (screenDims.height * 0.1), 0, 0, 0);
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

