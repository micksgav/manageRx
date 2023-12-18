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
import java.time.*;

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
	public static PatientList createPatient(String name, int age, String address, String monthOfBirth, int dayOfBirth,
			int birthYear, PrescriptionList activePrescriptions, PrescriptionList pastPrescriptions, int phoneNumber,
			String email, long creditNum, int cardExp, LinkedList<String> allergiesAndDietary,
			LinkedList<String> medicalConditions, LinkedList<String> lifestyleHabits, FamilyDoctor familyDoctor,
			LinkedList<Insurance> insuranceInformation, PatientList patientList) {

		patientList.insert(new Patient(name, age, address, monthOfBirth, dayOfBirth, birthYear, activePrescriptions,
				pastPrescriptions, phoneNumber, email, creditNum, cardExp, allergiesAndDietary, medicalConditions,
				lifestyleHabits, familyDoctor, insuranceInformation));

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
		System.out.println(
				"Press 1 to add family doc name, 2 to add family doc address, 3 to add family doc phone number");
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
			System.out.println(
					"Press 1 to enter insurance info, 2 to save and add to list, 3 to save list, 4 to discard");
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

	// convert a month from the String form to an int 1-12
	public static int convertMonthToNumber(String month) {
		switch (month.toLowerCase()) {
		case "january":
			return 1;
		case "february":
			return 2;
		case "march":
			return 3;
		case "april":
			return 4;
		case "may":
			return 5;
		case "june":
			return 6;
		case "july":
			return 7;
		case "august":
			return 8;
		case "september":
			return 9;
		case "october":
			return 10;
		case "november":
			return 11;
		case "december":
			return 12;
		default:
			return -1;
		}
	}

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

	// automatically update the age of the patient on their birthday
	public static void updatePatientAge(PatientList patients, int indexInList) {

		LocalDateTime date = LocalDateTime.now();
		if (date.getMonthValue() == convertMonthToNumber(patients.returnData(indexInList).getDateOfBirthMonth())
				&& date.getDayOfMonth() == patients.returnData(indexInList).getDateOfBirthDay()) {
			patients.returnData(indexInList).setAge(patients.returnData(indexInList).getAge() + 1);
		}
	}

	// update the address of an existing patient
	public static void updatePatientAddress(Scanner scan, PatientList patients) {
		System.out.println("Enter the name and birthday of the patient whose address you would like to change");
		String name = scan.nextLine();
		String birthday = scan.nextLine();
		int index = searchPatientByNameAndBirthday(name, patients, birthday);
		if (index >= 0) {
			System.out.println("Enter new address of patient");
			patients.returnData(index).setAddress(scan.nextLine());
		} else {
			System.out.println("Unable to find patient");
		}
	}

	// update the phone number of an existing patient
	public static void updatePatientNumber(Scanner scan, PatientList patients) {
		System.out.println("Enter the name and birthday of the patient whose phone number you would like to change");
		String name = scan.nextLine();
		String birthday = scan.nextLine();
		int index = searchPatientByNameAndBirthday(name, patients, birthday);
		if (index >= 0) {
			System.out.println("Enter new phone number of patient");
			patients.returnData(index).setPhoneNumber(Integer.parseInt(scan.nextLine()));
		} else {
			System.out.println("Unable to find patient");
		}
	}

	// update the email of an existing patient
	public static void updatePatientEmail(Scanner scan, PatientList patients) {
		System.out.println("Enter the name and birthday of the patient whose email you would like to change");
		String name = scan.nextLine();
		String birthday = scan.nextLine();
		int index = searchPatientByNameAndBirthday(name, patients, birthday);
		if (index >= 0) {
			System.out.println("Enter new email of patient");
			patients.returnData(index).setEmail(scan.nextLine());
		} else {
			System.out.println("Unable to find patient");
		}
	}

	// add a prescription to the patient
	public static void addActivePrescription(PatientList patients, Scanner scan) {
		LocalDateTime date = LocalDateTime.now();
		System.out.println("Enter the name and birthday of the patient");
		String name = scan.nextLine();
		String birthday = scan.nextLine();
		int index = searchPatientByNameAndBirthday(name, patients, birthday);
		// add a new prescription to the patient
		if (index >= 0) {
			Prescription newRx = new Prescription();
			System.out.println("Enter brand name of drug");
			newRx.setBrandName(scan.nextLine());
			// set generic name based on DIN gathered from brand name
			System.out.println("Enter number of refills");
			newRx.setRefills(Integer.parseInt(scan.nextLine()));
			System.out.println("Enter quantity");
			newRx.setQuantity(Integer.parseInt(scan.nextLine()));
			System.out.println("Enter dosage");
			newRx.setDosage(Integer.parseInt(scan.nextLine()));
			System.out.println("Enter instructions");
			newRx.setInstructions(scan.nextLine());
			int year = date.getYear();
			int month = date.getMonthValue();
			int day = date.getDayOfMonth();
			newRx.setDate(convertNumToMonth(month) + " " + day + ", " + year);
			System.out.println("Enter prescribed duration for prescription");
			newRx.setDuration(scan.nextLine());
			patients.returnData(index).addActivePrescription(newRx);
		} else {
			System.out.println("Unable to find patient");
		}
	}
	
	// remove a prescription from a patient's active prescriptions
	public static void archivPrescription(PatientList patients, Scanner scan) {
		System.out.println("Enter the name and birthday of the patient");
		String name = scan.nextLine();
		String birthday = scan.nextLine();
		int index = searchPatientByNameAndBirthday(name, patients, birthday);
		// add a new prescription to the patient
		if (index >= 0) {
			System.out.println("Enter the name of the drug for the prescription you would like to remove");
			
		} else {
			System.out.println("Unable to find patient");
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Date date = new Date();

		PatientList patients = new PatientList();

		PrescriptionList addListOfPrescriptions = addListOfPrescriptions();
		createPatient("John", 17, "413 ABC Street", "March", 3, 2006, addListOfPrescriptions, addListOfPrescriptions,
				1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, null, null, patients);
		// addListOfPrescriptions.setHead(null);
		// patients.returnData(0).printPatientInfo();

		int index = searchPatientByNameAndPhoneNumber("John", patients, patients.returnData(0).getPhoneNumber());

		if (index == -1) {
			System.out.println("Patient not found");
		} else {
			patients.returnData(index).printPatientInfo();
		}

		scan.close();
	}

}
