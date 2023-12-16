package PatientManagement;
import inventory.*;
import apiInteracting.*;

public class Prescription {
	private Drug drug;
	private String[] interactions;
	private String datePrescribed;
	private int numRefills;
	private int quantity;
	private int dosage;
	private String instructions;
	private String prescribedDuration;
	
	public Prescription(Drug drug, String[] interactions, String datePrescribed, int numRefills, int quantity, int dosage, String instructions, String prescribedDuration) {
		this.drug = drug;
		this.interactions = interactions;
		this.datePrescribed = datePrescribed;
		this.numRefills = numRefills;
		this.quantity = quantity;
		this.dosage = dosage;
		this.instructions = instructions;
		this.prescribedDuration = prescribedDuration;
	}
	
	public Drug getDrug() {
		return drug;
	}


	public String getGenName() {
		return drug.getDrugNameGen();
	}

	public void setGenName(String genName) {
		drug.setDrugNameGen(genName);
	}

	public String getBrandName() {
		return drug.getDrugNameBrand();
	}

	public void setBrandName(String brandName) {
		drug.setDrugNameBrand(brandName);
	}

	public String getDate() {
		return datePrescribed;
	}

	public void setDate(String date) {
		datePrescribed = date;
	}

	public int getRefills() {
		return numRefills;
	}

	public void setRefills(int refills) {
		numRefills = refills;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getDuration() {
		return prescribedDuration;
	}

	public void setDuration(String duration) {
		prescribedDuration = duration;
	}
}
