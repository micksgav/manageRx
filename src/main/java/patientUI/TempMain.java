package patientUI;

import PatientManagement.*;
import inventory.*;

public class TempMain {

	public static void main(String[] args) {
		String[][] idk = {{"h", "h"} ,{"h", "h"}};
		String[] alts = {"h", "h"}; 
		Drug drug = new Drug("00015", "Advil", "a class", "idk", "Pfizer", "Advil 400 mg", "Pill", idk, "RXCUI", "DPC", "ATC", alts);
		FamilyDoctor doc = new FamilyDoctor("Joe Mama", "123 Address Street", 5191234566L);
		Prescription script = new Prescription(drug, "December 28, 2023", 3, 25, 400, "Take this", "6 Months");
		PrescriptionList scripts = new PrescriptionList();
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		scripts.insert(script);
		System.out.println(scripts.length());
		Patient patient = new Patient("John", 17, "413 ABC Street", "March", 3, 2006, scripts, null,
				1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, doc, null);
		AddNewPrescriptionUI oui = new AddNewPrescriptionUI("ManageRx", patient);
		
		oui.setVisible(true);
	}
}
