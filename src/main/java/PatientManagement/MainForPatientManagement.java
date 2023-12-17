/**
 ***********************************************
 * @Author : John Brown
 * @Originally made : December 12, 2023
 * @Last Modified: May 23, 2023
 * @Description: Main for managing patient information
 ***********************************************
 */


package PatientManagement;

import inventory.*;
import apiInteracting.*;

import java.util.*;

public class MainForPatientManagement {

	public static PrescriptionList addListOfPrescriptions() {
		PrescriptionList list = new PrescriptionList();
		String[] random = { "random", "random2" };
		Drug drug = new Drug("ibuprofen", "Advil", null, 200, random, "01933558");
		Prescription script = new Prescription(drug, random, "December 15, 2023", 3, 25, drug.getDrugDosage(), "lalala",
				"6 months");
		list.insert(script);

		return list;

	}

	// instantiate a new patient object and place it in the list of patients
	public static PatientList createPatient(String name, int age, String address, String dateOfBirth,
			PrescriptionList activePrescriptions, PrescriptionList pastPrescriptions, int phoneNumber, String email,
			long creditNum, int cardExp, LinkedList<String> allergiesAndDietary, LinkedList<String> medicalConditions,
			LinkedList<String> lifestyleHabits, FamilyDoctor familyDoctor, LinkedList<Insurance> insuranceInformation,
			PatientList patientList) {

		patientList.insert(new Patient(name, age, address, dateOfBirth, activePrescriptions, pastPrescriptions,
				phoneNumber, email, creditNum, cardExp, allergiesAndDietary, medicalConditions, lifestyleHabits,
				familyDoctor, insuranceInformation));

		return patientList;
	}

	// search for a patient in the list by name and phone number
	public static int searchPatientByNameAndPhoneNumber(String name, PatientList patients, int patientPhoneNumber) {
		if (name != null && patientPhoneNumber > 0) {
			int index = patients.findPatientByPhoneNumber(name, patientPhoneNumber);
			return index;
		}
		return -1;
	}

	// search for a patient in the list by name and address
	public static int searchPatientByNameAndAddress(String name, PatientList patients, String address) {
		if (name != null && address != null) {
			int index = patients.findPatientByAddress(name, address);
			return index;
		}
		return -1;
	}

	// search for a patient in the list by name and birthday
	public static int searchPatientByNameAndBirthday(String name, PatientList patients, String birthday) {
		if (name != null && birthday != null) {
			int index = patients.findPatientByBirthday(name, birthday);
			return index;
		}
		return -1;
	}

	// create a list of medical conditions manually before creating a new patient
	public static LinkedList<String> createListMedConditions(Scanner scan) {
		LinkedList<String> medicalConditions = new LinkedList<String>();
		String current = "";
		while (true) {
			System.out.println(
					"Press 1 to add new medical condition, 2 to save to the list, 3 to exit with saved list, 4 to exit and cancel changes");
			switch (Integer.parseInt(scan.nextLine())) {
			case 1:
				System.out.println("Enter condition to add");
				current = scan.nextLine();
				break;
			case 2:
				if (current != "") {
					medicalConditions.add(current);
				} else {
					System.out.println("Error, nothing to add");
				}
				break;
			case 3:
				return medicalConditions;
			case 4:
				return null;
			}
		}
	}

	// create a list of allergies/dietary manually before creating a new patient
	public static LinkedList<String> createListAllergiesDietary(Scanner scan) {
		LinkedList<String> allergiesDietary = new LinkedList<String>();
		String current = "";
		while (true) {
			System.out.println(
					"Press 1 to add new allergy/dietary, 2 to save to the list, 3 to exit with saved list, 4 to exit and cancel changes");
			switch (Integer.parseInt(scan.nextLine())) {
			case 1:
				System.out.println("Enter allergy/dietary to add");
				current = scan.nextLine();
				break;
			case 2:
				if (current != "") {
					allergiesDietary.add(current);
				} else {
					System.out.println("Error, nothing to add");
				}
				break;
			case 3:
				return allergiesDietary;
			case 4:
				return null;
			}
		}
	}
	
	// enter family doctor info before creating a new patient
	public static FamilyDoctor createDoctor(Scanner scan) {
		FamilyDoctor doc = new FamilyDoctor();
		System.out.println("Press 1 to add family doc name, 2 to add family doc address, 3 to add family doc phone number");
		switch (Integer.parseInt(scan.nextLine())) {
		case 1:
			doc.setName(scan.nextLine());
			break;
		case 2:
			doc.setAddress(scan.nextLine());
			break;
		case 3:
			doc.setPhoneNumber(Integer.parseInt(scan.nextLine()));
			break;
		}
		return doc;
	}

	// create a list of insurance information manually before creating a new patient
	public static LinkedList<Insurance> createInsuranceList(Scanner scan) {
		LinkedList<Insurance> insuranceList = new LinkedList<Insurance>();
		Insurance current = new Insurance();
		while (true) {
			System.out.println("Press 1 to enter insurance info, 2 to save and add to list, 3 to save list, 4 to discard");
			switch (Integer.parseInt(scan.nextLine())) {
			case 1:
				System.out.println("Enter company name");
				current.setCompany(scan.nextLine());
				System.out.println("Enter insurance number");
				current.setNumber(Integer.parseInt(scan.nextLine()));
				break;
			case 2:
				if (current != null) {
					insuranceList.add(current);
				}
				break;
			case 3:
				return insuranceList;
			case 4:
				return null;
			}
		}
	}
	
	// create a list of lifestyle habits manually before creating a new patient
		public static LinkedList<String> createListLifestyleHabits(Scanner scan) {
			LinkedList<String> lifestyleHabits = new LinkedList<String>();
			String current = "";
			while (true) {
				System.out.println(
						"Press 1 to add new allergy/dietary, 2 to save to the list, 3 to exit with saved list, 4 to exit and cancel changes");
				switch (Integer.parseInt(scan.nextLine())) {
				case 1:
					System.out.println("Enter allergy/dietary to add");
					current = scan.nextLine();
					break;
				case 2:
					if (current != "") {
						lifestyleHabits.add(current);
					} else {
						System.out.println("Error, nothing to add");
					}
					break;
				case 3:
					return lifestyleHabits;
				case 4:
					return null;
				}
			}
		}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		PatientList patients = new PatientList();

		PrescriptionList addListOfPrescriptions = addListOfPrescriptions();
		createPatient("John", 17, "413 ABC Street", "March 3, 2006", addListOfPrescriptions, addListOfPrescriptions,
				1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, null, null, patients);
		// addListOfPrescriptions.setHead(null);
		// patients.returnData(0).printPatientInfo();

		int index = searchPatientByNameAndPhoneNumber("JOhn", patients, patients.returnData(0).getPhoneNumber());

		if (index == -1) {
			System.out.println("Patient not found");
		} else {
			patients.returnData(index).printPatientInfo();
		}

		scan.close();
	}

}
