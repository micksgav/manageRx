package patientUI;

import swingHelper.*;

import javax.swing.*;
import javax.swing.border.*;

import com.formdev.flatlaf.*;

import mainUI.loginUI;
import mainUI.settingsUI;
import PatientManagement.*;

import java.awt.*;
import java.awt.event.*;

public class CurrentPrescriptions extends JFrame implements ActionListener {
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
	private JPanel[] prescriptionPanels;
	private JPanel[] editArchive;
	private JPanel mainWithTopBar;

	// header buttons
	private JButton btnOpenStock;
	private JButton btnOpenOrder;
	private JButton btnOpenSettings;
	private JButton btnOpenPatientManager;

	// main buttons
	private JButton[] editPrescription;
	private JButton[] archivePrescription;
	private JButton createNewPrescription;

	// text elements
	private JLabel patientName;
	private JTextArea[] prescriptionInfo;
	private JLabel currentPrescriptions = new JLabel("Active Prescriptions");

	// icons
	public AppIcon stockIcon = new AppIcon("icons/box.png");// icon for stock
	public AppIcon orderIcon = new AppIcon("icons/clipboard.png");// icon for order
	public AppIcon settingsIcon = new AppIcon("icons/gear.png");// icon for settings
	public AppIcon patientsIcon = new AppIcon("icons/person.png");// icon for patients

	public CurrentPrescriptions(String title, Patient patient) {
		FlatLightLaf.setup();
		setTitle(title);
		Rectangle screenDims = GraphicsEnvironment.getLocalGraphicsEnvironment().getLocalGraphicsEnvironment()
				.getMaximumWindowBounds(); // dimensions of screen from https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
		// Rectangle screenDims = new Rectangle(1366, 768);
		setSize(screenDims.width, screenDims.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.patient = patient;
		String[] drugBrandName = new String[patient.getActivePrescriptions().length()];
		String[] drugGenName = new String[patient.getActivePrescriptions().length()];
		String[] datePrescribed = new String[patient.getActivePrescriptions().length()];
		String[] numRefills = new String[patient.getActivePrescriptions().length()];
		String[] quantity = new String[patient.getActivePrescriptions().length()];
		String[] dosage = new String[patient.getActivePrescriptions().length()];
		String[] instructions = new String[patient.getActivePrescriptions().length()];
		String[] prescribedDuration = new String[patient.getActivePrescriptions().length()];
		prescriptionPanels = new JPanel[patient.getActivePrescriptions().length()];

		for (int i = 0; i < drugBrandName.length; i++) {
			drugBrandName[i] = "Brand Name: " + patient.getActivePrescriptions().atIndex(i).getBrandName();
			// drugGenName[i] = new JLabel("Generic Name: " +
			// patient.getActivePrescriptions().atIndex(i).getGenName());
			datePrescribed[i] = "Date Prescribed: " + patient.getActivePrescriptions().atIndex(i).getDate();
			numRefills[i] = "Number of Refills: "
					+ String.valueOf(patient.getActivePrescriptions().atIndex(i).getRefills());
			quantity[i] = "Quantity: " + String.valueOf(patient.getActivePrescriptions().atIndex(i).getQuantity());
			dosage[i] = "Dosage: " + String.valueOf(patient.getActivePrescriptions().atIndex(i).getDosage());
			instructions[i] = "Instructions: " + patient.getActivePrescriptions().atIndex(i).getInstructions();
			prescribedDuration[i] = "Prescribed Duration: " + patient.getActivePrescriptions().atIndex(i).getDuration();
		}
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
		Border textBoxBorderLine = BorderFactory.createLineBorder(new Color(89, 89, 89), screenDims.width / 700); // https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/components/border.html#:~:text=To%20put%20a%20border%20around,a%20variable%20of%20type%20Border%20.
		Border textFieldPadding = new EmptyBorder((int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01),
				(int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01));
		CompoundBorder textBoxBorder = new CompoundBorder(textBoxBorderLine, textFieldPadding);
		Border simpleLine = BorderFactory.createLineBorder(new Color(89, 89, 89), screenDims.width / 700);

		// panel to hold name, create button, and all prescriptions
		mainWithTopBar = new JPanel(new GridBagLayout());

		// add patient name to screen
		GridBagConstraints nameConstraints = new GridBagConstraints();

		nameConstraints.fill = GridBagConstraints.BOTH;
		nameConstraints.gridx = 0;
		nameConstraints.gridy = 0;
		nameConstraints.gridwidth = 2;
		nameConstraints.anchor = GridBagConstraints.NORTH;
		patientName = new JLabel(patient.getName());
		patientName.setFont(nameFont);
		patientName.setHorizontalAlignment(JLabel.CENTER);
		mainWithTopBar.add(patientName, nameConstraints);

		// use a gridlayout for the first row with name in it. Should fix the button
		// size **********

		// add page title to screen
		GridBagConstraints titleConstraints = new GridBagConstraints();

		titleConstraints.fill = GridBagConstraints.BOTH;
		titleConstraints.gridx = 0;
		titleConstraints.gridy = 0;
		titleConstraints.gridwidth = 1;
		titleConstraints.anchor = GridBagConstraints.SOUTH;
		titleConstraints.weightx = 0.01;
		currentPrescriptions.setFont(nameFont);
		currentPrescriptions.setHorizontalAlignment(JLabel.LEFT);
		mainWithTopBar.add(currentPrescriptions, titleConstraints);

		// add create prescription button to screen

		GridBagConstraints createConstraints = new GridBagConstraints();

		createConstraints.fill = GridBagConstraints.BOTH;
		createConstraints.gridx = 1;
		createConstraints.gridy = 0;
		createConstraints.gridwidth = 1;
		createConstraints.anchor = GridBagConstraints.SOUTHEAST;
		createNewPrescription = new JButton("Add New Prescription");
		createNewPrescription.setFont(genFont);
		createNewPrescription.setHorizontalAlignment(JButton.RIGHT);
		createNewPrescription.setBorder(textBoxBorder);
		createNewPrescription.setMaximumSize(new Dimension((int) (screenDims.width * 0.1), screenDims.height));
		mainWithTopBar.add(createNewPrescription, createConstraints);

		for (int i = 0; i < prescriptionPanels.length; i++) {
			prescriptionPanels[i] = new JPanel(new GridLayout(2, 1));
		}

		prescriptionInfo = new JTextArea[drugBrandName.length];
		editArchive = new JPanel[drugBrandName.length];
		editPrescription = new JButton[drugBrandName.length];
		archivePrescription = new JButton[drugBrandName.length];

		for (int i = 0; i < drugBrandName.length; i++) {
			editPrescription[i] = new JButton("Edit Prescription");
			archivePrescription[i] = new JButton("Archive Prescription");
			editPrescription[i].setFont(genFont);
			archivePrescription[i].setFont(genFont);
			editPrescription[i].setBorder(textBoxBorder);
			archivePrescription[i].setBorder(textBoxBorder);
			prescriptionInfo[i] = new JTextArea();
			editArchive[i] = new JPanel(new GridLayout(1, 2, (int) (screenDims.width * 0.005), 0));
			editArchive[i].add(editPrescription[i]);
			editArchive[i].add(archivePrescription[i]);
			prescriptionInfo[i].setText(drugBrandName[i] + "\n" + datePrescribed[i] + "\n" + numRefills[i] + "\n"
					+ quantity[i] + "\n" + dosage[i] + "\n" + instructions[i] + "\n" + prescribedDuration[i]);

			prescriptionPanels[i].setBorder(simpleLine);
			prescriptionInfo[i].setEditable(false);
			prescriptionPanels[i].add(prescriptionInfo[i]);
			prescriptionPanels[i].add(editArchive[i]);
		}

		System.out.println(drugBrandName.length);
		System.out.println((double) drugBrandName.length / 2);
		System.out.println(Math.ceil((double) drugBrandName.length / 2));
		if ((double) drugBrandName.length / 2 - (int) drugBrandName.length / 2 < 0.5) {
			mainPanel = new JPanel(new GridLayout((int) Math.floor((double) drugBrandName.length / 2), 2,
					(int) (screenDims.width * 0.01), (int) (screenDims.height * 0.01)));
		} else {
			mainPanel = new JPanel(new GridLayout((int) Math.ceil((double) drugBrandName.length / 2), 2,
					(int) (screenDims.width * 0.01), (int) (screenDims.height * 0.01)));
		}

		for (int i = 0; i < prescriptionPanels.length; i++) {
			mainPanel.add(prescriptionPanels[i]);
		}

		mainPanel.setBorder(textBoxBorder);
		
		JScrollPane mainScroll = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainScroll.setMinimumSize(new Dimension((int) (screenDims.width * 0.75), (int) (screenDims.height * 0.75)));

		GridBagConstraints prescriptionConstraints = new GridBagConstraints();

		prescriptionConstraints.fill = GridBagConstraints.BOTH;
		prescriptionConstraints.gridx = 0;
		prescriptionConstraints.gridy = 1;
		prescriptionConstraints.gridwidth = 2;
		prescriptionConstraints.anchor = GridBagConstraints.NORTH;
		mainWithTopBar.add(mainScroll, prescriptionConstraints);

		add(mainWithTopBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}