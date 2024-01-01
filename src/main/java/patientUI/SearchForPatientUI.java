package patientUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import com.formdev.flatlaf.FlatLightLaf;

import PatientManagement.*;
import mainUI.loginUI;
import mainUI.settingsUI;
import swingHelper.AppIcon;

public class SearchForPatientUI extends JFrame implements ActionListener, FocusListener {

	// convert a month from int to String value
	public static String convertNumToMonth(int month) {
		switch (month) {
		case 1:
			return "january";
		case 2:
			return "february";
		case 3:
			return "march";
		case 4:
			return "april";
		case 5:
			return "may";
		case 6:
			return "june";
		case 7:
			return "july";
		case 8:
			return "august";
		case 9:
			return "september";
		case 10:
			return "october";
		case 11:
			return "november";
		case 12:
			return "december";
		default:
			return null;
		}
	}
	
	public static int searchPatient(PatientList patients, String birthday, String patientNameFieldText) {
		System.out.println(convertNumToMonth(Integer.parseInt((birthday.substring(birthday.indexOf(' '), birthday.indexOf(' ') + 3).trim()))));
		int index = patients.findPatientByBirthday(patientNameFieldText,
				convertNumToMonth(Integer
						.parseInt(birthday.substring(birthday.indexOf(' '), birthday.indexOf(' ') + 3).trim())),
				Integer.parseInt(birthday.substring(0, birthday.indexOf(' ')).trim()),
				Integer.parseInt(birthday.substring(birthday.indexOf(' ') + 3).trim()));
		return index;
	}

	private JButton openSettings = new JButton();
	private JButton openPatientManager = new JButton();
	private JButton openStock = new JButton();
	private JButton openOrder = new JButton();
	private loginUI login = new loginUI();
	private settingsUI settings = new settingsUI();
	private patientManagerUI patientManager = new patientManagerUI();
	// private StockUI stock = new StockUI();
	// private OrderUI order = new OrderUI();

	PatientList patients;

	// panels
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private JPanel mainGrid;
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
	private JButton search;

	// text elements
	private JLabel searchTitle = new JLabel("Search for a Patient");
	private JLabel patientNameLabel = new JLabel("Enter Patient's First and Last Name");
	private JTextField patientNameField;
	private JLabel patientBirthdayLabel = new JLabel("Enter Patient's Birthday (Month Day, Year)");
	private JTextField patientBirthdayField;
	private Insets gridBagPadding;
	private Insets leftButtonPadding;
	private Insets rightButtonsPadding;

	// icons
	public AppIcon stockIcon = new AppIcon("icons/box.png");// icon for stock
	public AppIcon orderIcon = new AppIcon("icons/clipboard.png");// icon for order
	public AppIcon settingsIcon = new AppIcon("icons/gear.png");// icon for settings
	public AppIcon patientsIcon = new AppIcon("icons/person.png");// icon for patients

	public SearchForPatientUI(String title, PatientList patients) {
		FlatLightLaf.setup();
		setTitle(title);
		Rectangle screenDims = GraphicsEnvironment.getLocalGraphicsEnvironment().getLocalGraphicsEnvironment()
				.getMaximumWindowBounds(); // dimensions of screen from
											// https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
		//screenDims.width /= 1.5;
		//screenDims.height /= 1.5;
		setSize(screenDims.width, screenDims.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
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

		Font genFont = new Font("Arial", Font.PLAIN, 25);
		Font nameFont = new Font("Arial", Font.PLAIN, 75);
		Color textBoxFill = new Color(204, 204, 204);
		Border textBoxBorderLine = BorderFactory.createLineBorder(new Color(89, 89, 89), screenDims.width / 700); // https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/components/border.html#:~:text=To%20put%20a%20border%20around,a%20variable%20of%20type%20Border%20.
		Border textFieldPadding = new EmptyBorder((int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01),
				(int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01));
		CompoundBorder textBoxBorder = new CompoundBorder(textBoxBorderLine, textFieldPadding);
		gridBagPadding = new Insets((int) (screenDims.height * 0.05), 0, (int) (screenDims.height * 0.08), 0);
		leftButtonPadding = new Insets((int) (screenDims.height * 0.01), 0, (int) (screenDims.height * 0.01),
				(int) (screenDims.width * 0.25));
		rightButtonsPadding = new Insets((int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01),
				(int) (screenDims.height * 0.01), (int) (screenDims.width * 0.01));

		mainPanel = new JPanel(new GridBagLayout());

		GridBagConstraints titleConstraints = new GridBagConstraints();

		titleConstraints.gridx = 0;
		titleConstraints.gridy = 0;
		titleConstraints.anchor = GridBagConstraints.NORTH;
		titleConstraints.insets = gridBagPadding;
		searchTitle.setFont(nameFont);
		searchTitle.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(searchTitle, titleConstraints);

		mainGrid = new JPanel(new GridLayout(4, 1, 0, (int) (screenDims.height * 0.01)));

		patientNameField = new JTextField();
		patientNameField.setFont(genFont);
		patientNameLabel.setFont(genFont);
		patientNameField.setBorder(textBoxBorder);
		mainGrid.add(patientNameLabel);
		mainGrid.add(patientNameField);

		patientBirthdayField = new JTextField("DD/MM/YYYY");
		patientBirthdayField.setFont(genFont);
		patientBirthdayLabel.setFont(genFont);
		patientBirthdayField.setBorder(textBoxBorder);
		patientBirthdayField.setForeground(textBoxFill);
		patientBirthdayField.addFocusListener(this);
		mainGrid.add(patientBirthdayLabel);
		mainGrid.add(patientBirthdayField);

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		mainPanel.add(mainGrid, gridConstraints);

		GridBagConstraints searchConstraints = new GridBagConstraints();

		searchConstraints.gridx = 0;
		searchConstraints.gridy = 2;
		searchConstraints.ipadx = (int) (screenDims.width * 0.05);
		searchConstraints.insets = gridBagPadding;
		searchConstraints.anchor = GridBagConstraints.SOUTH;
		search = new JButton("Search");
		search.setActionCommand("Search");
		search.addActionListener(this);
		search.setFont(genFont);
		search.setBorder(textBoxBorder);
		mainPanel.add(search, searchConstraints);

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
		if (e.getActionCommand().equals("Search")) { 
			int index = -1;
			if (patientNameField.getText().length() > 0 && patientBirthdayField.getText() != "DD/MM/YYYY" && patientBirthdayField.getText().length() > 0) {
			String birthday = patientBirthdayField.getText().replace('/', ' ');
			System.out.println(birthday.substring(birthday.indexOf(' '), birthday.indexOf(' ') + 3));
			System.out.println(birthday);
			index = searchPatient(patients, birthday, patientNameField.getText().trim());
			System.out.println(index);
			}
			if (index >= 0) {
				ManagePatientInfoUI openManage = new ManagePatientInfoUI("ManageRx", patients.returnData(index), patients);
				openManage.setVisible(true);
				setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(mainPanel, "Error, Patient Not Found.");
			}
		}
	}


	// https://stackoverflow.com/questions/18103839/display-disappear-default-text-in-textfield-when-user-enters-data-erase-the-text
	// used for focus event
	public void focusGained(FocusEvent e) {
		if (patientBirthdayField.getText().trim().equals("DD/MM/YYYY")) {
			patientBirthdayField.setText("");
			patientBirthdayField.setForeground(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}
}
