package PatientManagement;
import inventory.*;
import apiInteracting.*;

import java.util.*;

public class MainForPatientManagement {
	
	public static PrescriptionList addListOfPrescriptions() {
		PrescriptionList list = new PrescriptionList();
		String[] random = {"random", "random2"};
		Drug drug = new Drug("ibuprofen", "Advil", null, 200, random, "01933558"); 
		Prescription script = new Prescription(drug, random, "December 15, 2023", 3, 25, drug.getDrugDosage(), "lalala", "6 months");
		list.insert(script);
		
		return list;
		
	}
	
	public static PatientList createPatient(String name, int age, String address, String dateOfBirth,
			PrescriptionList activePrescriptions, PrescriptionList pastPrescriptions, int phoneNumber,
			String email, long creditNum, int cardExp, LinkedList<String> allergiesAndDietary,
			LinkedList<String> medicalConditions, LinkedList<String> lifestyleHabits, FamilyDoctor familyDoctor,
			LinkedList<Insurance> insuranceInformation, PatientList patientList){
		
		
		patientList.insert(new Patient(name, age, address, dateOfBirth, activePrescriptions, pastPrescriptions, phoneNumber, email, creditNum, cardExp, allergiesAndDietary, medicalConditions, lifestyleHabits, familyDoctor, insuranceInformation));
		
		return patientList;
	}
	
	public static PatientList searchPatientByName(String name, PatientList patientList){
		
		
		
		return patientList; 
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		PatientList patients = new PatientList();
		
		PrescriptionList addListOfPrescriptions = addListOfPrescriptions();
		createPatient("John", 17, "413 ABC Street", "March 3, 2006", addListOfPrescriptions, addListOfPrescriptions, 1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, null, null, patients);
		//addListOfPrescriptions.setHead(null);
		patients.returnData(0).printPatientInfo();
		
		int index = patients.findPatient("John", "413 ABC Street");
		patients.returnData(index).setName("Gavin");
		patients.returnData(index).setAddress("412 ABC Street");
		patients.returnData(index).printPatientInfo();
		
		//createPatient("Me", 18, "413 ABC Street", "March 3, 2006", null, null, 1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, null, null, patients);
		//index = patients.findPatient("Me", "413 ABC Street");
		//patients.returnData(index).printPatientInfo();

		
				
		scan.close();
	}

}
