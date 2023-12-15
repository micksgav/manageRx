package PatientManagement;

import inventory.*;
import apiInteracting.*;
import java.util.*;

public class Patient {
	private String name;
	private int age;
	private String address;
	private String dateOfBirth;
	PrescriptionList activePrescriptions;
	PrescriptionList pastPrescriptions;
	private int phoneNumber;
	private String email;
	private LinkedList<Long> creditNum;
	private LinkedList<Integer> cardExpDate;
	private LinkedList<String> allergiesAndDietary;
	private LinkedList<String> medicalConditions;
	private LinkedList<String> lifestyleHabits;
	private FamilyDoctor familyDoctor;
	private LinkedList<Insurance> insuranceInformation;

	public Patient(String name, int age, String address, String dateOfBirth, PrescriptionList activePrescriptions,
			PrescriptionList pastPrescriptions, int phoneNumber, String email, long creditNum, int cardExp,
			LinkedList<String> allergiesAndDietary, LinkedList<String> medicalConditions,
			LinkedList<String> lifestyleHabits, FamilyDoctor familyDoctor, LinkedList<Insurance> insuranceInformation) {
		this.creditNum = new LinkedList<Long>();
		this.cardExpDate = new LinkedList<Integer>();
		this.allergiesAndDietary = new LinkedList<String>();
		this.medicalConditions = new LinkedList<String>();
		this.lifestyleHabits = new LinkedList<String>();
		this.insuranceInformation = new LinkedList<Insurance>();
		
		
		this.name = name;
		this.age = age;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.activePrescriptions = activePrescriptions;
		this.pastPrescriptions = pastPrescriptions;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.creditNum.addFirst(creditNum);
		cardExpDate.addFirst(cardExp);
		this.allergiesAndDietary = allergiesAndDietary;
		this.medicalConditions = medicalConditions;
		this.lifestyleHabits = lifestyleHabits;
		this.familyDoctor = familyDoctor;
		this.insuranceInformation = insuranceInformation;
	}
	
	public void printPatientInfo() {
		System.out.println(name + "\n" + age + "\n" + creditNum);
	}

	public ArrayList<String[]> drugInteractions(Drug newDrug) {

		ArrayList<String[]> allInteractions = new ArrayList<String[]>(); // list containing all interaction data for the two drugs
		String[] interactions; // list containing interactions between the current drug in the list and the new drug
		for (int i = 0; i < activePrescriptions.length(); i++) {
			String currentDIN = activePrescriptions.atIndex(i).getDrug().getDIN();
			interactions = getInteractions.search(currentDIN, newDrug.getDIN());
			allInteractions.add(interactions);
		} 
		return allInteractions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public PrescriptionList getActivePrescriptions() {
		return activePrescriptions;
	}

	public void addActivePrescription(Prescription prescription) {
		activePrescriptions.insert(prescription);
	}

	public void removeActivePrescription(Prescription prescription) {
		activePrescriptions.delete(prescription.getGenName());
		pastPrescriptions.insert(prescription);
	}

	public PrescriptionList getAllPrescriptions() {
		return activePrescriptions;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LinkedList<Long> getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(long creditNum) {
		this.creditNum.add(creditNum);
	}

	public LinkedList<Integer> getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(int cardExpDate, int whichCard) {
		this.cardExpDate.add(cardExpDate); // make so works with multiple cards
	}

	public void removeCard(int cardNum) {
		this.creditNum.remove(cardNum);
	}

	public void addNewCard(long cardNum, int cardExp) {
		this.creditNum.add(cardNum);
		this.cardExpDate.add(cardExp);
	}

	public LinkedList<String> getAllergiesAndDietary() {
		return allergiesAndDietary;
	}

	public void addAllergyOrDietary(String allergyOrDietary) {
		allergiesAndDietary.add(allergyOrDietary);
	}

	public void removeAllergyOrDietary(String allergyOrDietary) {
		allergiesAndDietary.remove(allergyOrDietary);
	}

	public void addMedicalCondition(String medicalCondition) {
		medicalConditions.add(medicalCondition);
	}

	public void removeMedicalCondition(String medicalCondition) {
		medicalConditions.remove(medicalCondition);
	}

	public void addLifestyleHabit(String lifestyleHabit) {
		lifestyleHabits.add(lifestyleHabit);
	}

	public void removeLifestyleHabit(String lifestyleHabit) {
		lifestyleHabits.remove(lifestyleHabit);
	}

	public String getFamilyDoctorName() {
		return familyDoctor.getName();
	}

	public void setFamilyDoctorName(String docName) {
		familyDoctor.setName(docName);
	}

	public int getFamilyDoctorNumber() {
		return familyDoctor.getPhoneNumber();
	}

	public void setFamilyDoctorNumber(int number) {
		familyDoctor.setPhoneNumber(number);
	}

	public String getFamilyDoctorAddress() {
		return familyDoctor.getAddress();
	}

	public void setFamilyDoctorAddress(String Address) {
		familyDoctor.setAddress(address);
	}

	public LinkedList<Insurance> getInsuranceInformation() {
		return insuranceInformation;
	}

	public void addNewInsuranceInfo(int numOfInsurance, Insurance newInsurance) {
		insuranceInformation.add(newInsurance);
	}

}
