package mainUI;
import patientUI.*;
import swingHelper.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainUI extends JFrame implements ActionListener {
	
    private JButton openSettings = new JButton();
    private JButton openPatientManager = new JButton();
    private JButton openStock = new JButton();
    private JButton openOrder = new JButton();
    private loginUI login = new loginUI();
    private settingsUI settings = new settingsUI();
    private patientManagerUI patientManager = new patientManagerUI();
    //private StockUI stock = new StockUI();
    //private OrderUI order = new OrderUI();
    
	//panels
	private JPanel headerPanel;
	private JPanel buttonPanel;
	
	//buttons
	private JButton btnOpenStock;
	private JButton btnOpenOrder;
	private JButton btnOpenSettings;
	private JButton btnOpenPatientManager;
  
	//icons
	public AppIcon stockIcon = new AppIcon("icons/box.png");//icon for stock
	public AppIcon orderIcon = new AppIcon("icons/clipboard.png");//icon for order
	public AppIcon settingsIcon = new AppIcon("icons/gear.png");//icon for settings
	public AppIcon patientsIcon = new AppIcon("icons/person.png");//icon for patients

    public MainUI(String title) {
        setTitle(title);
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
    
    /*private void login(String username, String password) {
        // Method body goes here
    }

    private void updateSaveFile() {
        // Method body goes here
    }

    private DrugStock suggestAlt(AllStock allStock, String drugName) {
        // Method body goes here
    }

    private String findDrugInfo(String drugName) {
        // Method body goes here
    }*/
}
