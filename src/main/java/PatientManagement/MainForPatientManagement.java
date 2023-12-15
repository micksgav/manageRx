package PatientManagement;

import java.util.*;

public class MainForPatientManagement {
	
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
		
		createPatient("John", 17, "413 McMahen Street", "March 3, 2006", null, null, 1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, null, null, patients);
		patients.returnData(0).printPatientInfo();
		
	}

}
