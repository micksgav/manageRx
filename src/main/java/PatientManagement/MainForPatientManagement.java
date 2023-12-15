package PatientManagement;

import java.util.*;

public class MainForPatientManagement {
	
	public static LinkedList<Patient> createPatient(String name, int age, String address, String dateOfBirth,
			LinkedList<Prescription> activePrescriptions, LinkedList<Prescription> pastPrescriptions, int phoneNumber,
			String email, int creditNum, int cardExp, LinkedList<String> allergiesAndDietary,
			LinkedList<String> medicalConditions, LinkedList<String> lifestyleHabits, FamilyDoctor familyDoctor,
			LinkedList<Insurance> insuranceInformation, int numInsurancePlans, LinkedList<Patient> patientList){
		
		
		patientList.add(new Patient(name, age, address, dateOfBirth, activePrescriptions, pastPrescriptions, phoneNumber, email, creditNum, cardExp, allergiesAndDietary, medicalConditions, lifestyleHabits, familyDoctor, insuranceInformation, numInsurancePlans));
		
		return patientList;
	}
	
	public static LinkedList<Patient> searchPatientByName(String name, LinkedList<Patient> patientList){
		
		
		
		return patientList; 
	}

	public static void main(String[] args) {
		
	}

}
