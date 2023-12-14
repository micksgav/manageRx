/**
***********************************************
 @Name: Drug
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 13, 2023
   @Description    : 
   
***********************************************
*/
package inventory;

// TO DO:
// comment all variables on all classes
// finish LinkedList methods
// check commenting, update dates

import java.text.*;
// add Gavin's import thing?

public class Drug {
	private String drugNameGen; // generic name of drug
	private String drugNameBrand; // brand name of drug
	private String drugClass; // drug class
	private int drugDosage; // dosage of drug
	private String[] sideEffects; // drug side effects // DON'T DO YET
	
	DecimalFormat dinFormat = new DecimalFormat("00000000"); // formats DIN to keep it 8 digits long 
	private int DIN; // drug identification number
	
	public Drug(String genName, String brandName, String classDrug, int dosage, String[] drugSideEffects, int drugDIN){
		drugNameGen = genName;
		drugNameBrand = brandName;
		drugClass = classDrug;
		drugDosage = dosage;
		sideEffects = drugSideEffects;
		DIN = drugDIN;
	} // end Drug constructor
	
	public Drug(){
		drugNameGen = "";
		drugNameBrand = "";
		drugClass = "";
		drugDosage = 0;
		sideEffects = null;
		DIN = 00000000;		
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
		return dinFormat.format(DIN);
	} // end getDIN

	public void setDIN(int drugDIN) {
		DIN = drugDIN;
	} // end setDIN
	
	public String checkInteractions(Drug drug2) {
		String interactions = "";
		
		
		
		return interactions;
	} // end checkInteractions
	
} // end Drug