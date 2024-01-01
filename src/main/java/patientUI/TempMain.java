package patientUI;

import PatientManagement.*;
import inventory.*;

import java.util.*;

public class TempMain {

	public static void main(String[] args) {
		String[][] idk = {{"h", "h"} ,{"h", "h"}};
		String[] alts = {"h", "h"}; 
		Drug drug = new Drug("00015", "Advil", "a class", "idk", "Pfizer", "Advil 400 mg", "Pill", idk, "RXCUI", "DPC", "ATC", alts);
		FamilyDoctor doc = new FamilyDoctor("Joe Mama", "123 Address Street", 5191234566L);
		Prescription script = new Prescription(drug, "December 28, 2023", 3, 25, 400, "Take this", "6 Months");
		Prescription script2 = new Prescription(drug, "December 28, 2023", 3, 25, 400, "Take this\n take\n take", "6 Months");
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
		Insurance insurance = new Insurance("123 ABC CORP", 12345);
		Insurance insurance2 = new Insurance("ABC DEF", 123);
		Insurance insurance3 = new Insurance("XYZ Company", 12543);
		LinkedList<Insurance> insuranceList= new LinkedList<Insurance>();
		insuranceList.add(insurance);
		insuranceList.add(insurance2);
		insuranceList.add(insurance3);
		
		Patient patient = new Patient("John", 17, "413 ABC Street", "March", 3, 2006, scripts, scripts,
				1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, doc, insuranceList, "0000-000-000-AB");
		Patient patient2 = new Patient("Brayden", 17, "123 CDE Road", "October", 12, 2006, scripts, scripts,
				1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, doc, insuranceList, "0000-000-000-AB");
		PatientList patients = new PatientList();
		patients.insert(patient);
		patients.insert(patient2);
		SearchAddUI oui = new SearchAddUI("ManageRx", patient, patients);
		
		
		oui.setVisible(true);
	}
}
