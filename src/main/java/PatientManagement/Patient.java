package PatientManagement;
import java.util.LinkedList;

public class Patient {
	private String name;
	private int age;
	private String address;
	private String dateOfBirth;
	private LinkedList<Prescription> activePrescriptions;
	private LinkedList<Prescription> pastPrescriptions;
	private int phoneNumber;
	private String email;
	private LinkedList<Integer> creditNum;
	private LinkedList<Integer> cardExpDate;
	private LinkedList<String> allergiesAndDietary;
	private LinkedList<String> medicalConditions;
	private LinkedList<String> lifestyleHabits;
	private FamilyDoctor familyDoctor;
	private LinkedList<Insurance> insuranceInformation;
	private int numInsurancePlans;

	public Patient(String name, int age, String address, String dateOfBirth,
			LinkedList<Prescription> activePrescriptions, LinkedList<Prescription> pastPrescriptions, int phoneNumber,
			String email, int creditNum, int cardExp, LinkedList<String> allergiesAndDietary,
			LinkedList<String> medicalConditions, LinkedList<String> lifestyleHabits, FamilyDoctor familyDoctor,
			LinkedList<Insurance> insuranceInformation, int numInsurancePlans) {
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
	        this.numInsurancePlans = numInsurancePlans;
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

	public LinkedList<Prescription> getActivePrescriptions() {
		return activePrescriptions;
	}

	public void addActivePrescription(Prescription prescription) {
		activePrescriptions.add(prescription);
	}

	public void removeActivePrescription(Prescription prescription) {
		activePrescriptions.remove(prescription);
		pastPrescriptions.add(prescription);
	}

	public LinkedList<Prescription> getAllPrescriptions() {
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

	public LinkedList<Integer> getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(int creditNum) {
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

	public void addNewCard(int cardNum, int cardExp) {
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
