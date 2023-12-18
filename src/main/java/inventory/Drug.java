package inventory; /**
***********************************************
 @Name: Drug
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 13, 2023
   @Description    : 
   
***********************************************
*/

// TO DO:
// comment all variables on all classes
// finish LinkedList methods
// check commenting, update dates

// add Gavin's import thing?

public class Drug {
	private String drugNameGen; // generic name of drug
	private String drugNameBrand; // brand name of drug
	private String drugClass; // drug class
	private int drugDosage; // dosage of drug
	private String[] sideEffects; // drug side effects // DON'T DO YET
	private final String schedule; // drug schedule
    private final String company; // drug company

	private final String description; // drug description

    private final String form; // drug form

    private final String[][] dosage; // dosage of drug
	private String DIN; // drug identification number
	private String RXCUI; // drug RXCUI
	private String DPC; // drug DPC
	private String ATC; // drug ATC
	
	public Drug (String drugDIN, String brandName, String classDrug, String schedule, String company, String description, String form, String[][] dosage, String RXCUI, String DPC, String ATC) {
		drugNameBrand = brandName;
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
	} // end Drug constructor
	
	public Drug(){
		drugNameGen = "";
		drugNameBrand = "";
		drugClass = "";
		drugDosage = 0;
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
	
	public String getDrugNameGen() {
		return drugNameGen;
	} // end getDrugNameGen

	public void setDrugNameGen(String genName) {
		this.drugNameGen = genName;
	} // end setDrugNameGen

	public String getDrugNameBrand() {
		return drugNameBrand;
	} // end getDrugNameBrand

	public void setDrugNameBrand(String brandName) {
		this.drugNameBrand = brandName;
	} // end setDrugNameBrand

	public String getDrugClass() {
		return drugClass;
	} // end getDrugClass

	public void setDrugClass(String classDrug) {
		this.drugClass = classDrug;
	} // end setDrugClass

	public int getDrugDosage() {
		return drugDosage;
	} // end getDrugDosage

	public void setDrugDosage(int dosage) {
		this.drugDosage = dosage;
	} // end setDrugDosage

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
	
	public String checkInteractions(Drug drug2) {
		String interactions = "";
		
		
		
		return interactions;
	} // end checkInteractions
	
} // end Drug