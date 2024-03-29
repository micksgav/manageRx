/**
***********************************************
 @Name: Drug
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 22, 2023
   @Description    : 
   
***********************************************
*/
package inventory;
import java.io.IOException;

import utilities.getInteractions;


public class Drug {
	private String[] drugAlternatives; // DIN possible alternatives
	private String drugName; // brand name of drug
	private String drugClass; // drug class
	private String[] sideEffects; // drug side effects // DON'T DO YET
	private String DIN; // drug identification number
	private final String schedule; // drug schedule
	private final String company; // drug company

	private final String description; // drug description

	private final String form; // drug form

	private final String[][] dosage; // dosage of drug
	private String RXCUI; // drug RXCUI
	private String DPC; // drug DPC
	private String ATC; // drug ATC

	public Drug(String drugDIN, String brandName, String classDrug, String schedule, String company, String description, String form, String[][] dosage, String RXCUI, String DPC, String ATC, String[] alts) {
		drugName = brandName;
		drugClass = classDrug;
		DIN = drugDIN;
		this.schedule = schedule;
		this.company = company;
		this.description = description;
		this.form = form;
		this.dosage = dosage;
		this.RXCUI = RXCUI;
		this.DPC = DPC;
		this.ATC = ATC;
		drugAlternatives = alts;
	} // end Drug constructor

	public Drug() {
		drugAlternatives = null;
		drugName = "";
		drugClass = "";
		sideEffects = null;
		DIN = "";
		schedule = "";
		company = "";
		description = "";
		form = "";
		dosage = null;
		RXCUI = "";
		DPC = "";
		ATC = "";
	} // end blank constructor

	public String[] getAlternatives() {
		return drugAlternatives;
	} // end getAlternatives

	public void setAlternatives(String[] alts) {
		this.drugAlternatives = alts;
	} // end setAlternatives

	public String getDrugName() {
		return drugName;
	} // end getDrugName

	public void setDrugName(String brandName) {
		this.drugName = brandName;
	} // end setDrugNameBrand

	public String getDrugClass() {
		return drugClass;
	} // end getDrugClass

	public void setDrugClass(String classDrug) {
		this.drugClass = classDrug;
	} // end setDrugClass

	public String[][] getDosage() {
		return dosage;
	} // end getDrugDosage

	public String[] getSideEffects() {
		return sideEffects;
	} // end getSideEffects

	public void setSideEffects(String[] drugSideEffects) {
		this.sideEffects = drugSideEffects;
	} // end setSideEffects

	// returns formatted drug DIN
	public String getDIN() {
		return DIN;
	} // end getDIN

	public void setDIN(String drugDIN) {
		DIN = drugDIN;
	} // end setDIN

	public void checkInteractions(Drug drug2) throws IOException {
		String din2 = drug2.getDIN();
		String[] interactions = getInteractions.search(this.DIN, din2);
		for(int i = 0; i < interactions.length; i++) {
			System.out.println(interactions[i]);
		} // end for

	} // end checkInteractions
} // end Drug