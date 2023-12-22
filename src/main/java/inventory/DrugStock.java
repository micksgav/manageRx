/**
***********************************************
 @Name: DrugStock
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 23, 2023
   @Description    : 
   
***********************************************
*/
package inventory;

import java.util.*; // just for testing purposes, will need to be replaced with UI stuff later
import java.io.IOException;
public class DrugStock {
	private Drug drug; // drug
	private int numInStock; // current stock of drug
	private int stockThreshold; // when the drug's threshold is reached, alert is sent
	private String[][] stockChanges = new String[31][3];
	Scanner ui = new Scanner(System.in);
	
	public DrugStock(String DIN, int inStock, int threshold) throws IOException {
		this.drug = new Drug();
		this.drug = drugFinder.getDrug(DIN);
		numInStock = inStock;
		stockThreshold = threshold;
		fillStockChanges();
	} // end DrugStock constructor with stock info
	
	public DrugStock(String DIN) throws IOException {
		this.drug = new Drug();
		this.drug = drugFinder.getDrug(DIN);
		numInStock = 0;
		stockThreshold = 0;
		fillStockChanges();
	} // end DrugStock constructor without stock info
	
	public Drug getDrug() {
		return drug;
	} // end getDrug
	
	public String getDrugNameGen() {
		return drug.getDrugNameGen();
	} // end getDrugNameGen
	
	public void setDrugNameGen(String genName) {
		drug.setDrugNameGen(genName);
	} // end setDrugNameGen
	
	public String getDrugNameBrand() {
		return drug.getDrugNameBrand();
	} // end getDrugNameBrand
	
	public void setDrugNameBrand(String brandName) {
		drug.setDrugNameBrand(brandName);
	} // end setDrugNameBrand
	
	public String getDrugDIN() {
		return drug.getDIN();
	} // end getDrugDIN
	
	public void setDrugDIN(String drugDIN) {
		drug.setDIN(drugDIN);
	} // end setDrugDIN
	
	public int getNumInStock() {
		return numInStock;
	} // end getNumInStock
	
	// will need to interact with Prescription class
	/** Method Name: removeFromStock
	* @Author Christina Wong 
	* @Date December 12, 2023
	* @Modified December 23, 2023
	* @Description This subtracts a filled prescription from the total stock of the drug.
	* @Parameters  int filled, the amount of of this drug removed from the stock to fill a prescription 
	* @Returns void
	* Dependencies: changeInStock
	* Throws/Exceptions: N/A
    */
	public void removeFromStock(int filled) {
		this.numInStock -= filled;
		changeInStock("Prescription filled:", filled);
	} // end removeFromStock
	
	/** Method Name: addToStock
	* @Author Christina Wong 
	* @Date December 12, 2023
	* @Modified December 22, 2023
	* @Description This adds a new shipment arrival to the total stock.
	* @Parameters  int added, the amount of this drug added to the current stock
	* @Returns void
	* Dependencies: changeInStock
	* Throws/Exceptions: N/A
    */
	public void addToStock(int added) {
		this.numInStock += added;
		changeInStock("Shipment arrival:", added);
	} // end addToStock
	
	public int getStockThreshold() {
		return stockThreshold;
	} // end getStockThreshold
	
	public void setStockThreshold(int threshold) {
		this.stockThreshold = threshold;
	} // end setStockThreshold
	
	/** Method Name: fillStockChanges
	* @Author Christina Wong 
	* @Date December 23, 2023
	* @Modified December 23, 2023
	* @Description This sets every stockChanges element to an empty string.
	* @Parameters  N/A 
	* @Returns void
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void fillStockChanges() {
		for(int row = 0; row < stockChanges.length; row++) {
			for(int col = 0; col < stockChanges[row].length; col++) {
				stockChanges[row][col] = "";
			} // end for
		} // end for
	} // end fillStockChanges
	
	// connects to somewhere in StockUI, I don't think there's anything for it now
	/** Method Name: changeInStock
	* @Author Christina Wong 
	* @Date December 18, 2023
	* @Modified December 20, 2023
	* @Description This .
	* @Parameters  int added, the amount of this drug added to the current stock
	* @Returns void
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void changeInStock(String change, int amount) {
		int date = -1;
		for(int i = 0; i < stockChanges.length; i++) {
			if(stockChanges[i][0] == null) {
				date = i;
				break;
			} // end if
		} // end for
		if(date == -1) {
			for(int row = 0; row < stockChanges.length - 1; row++) {
				for(int col = 0; col < stockChanges[row].length; col++) {
					stockChanges[row][col] = stockChanges[row + 1][col];
				} // end for
			} // end for
			// needs a text field for user to input info
			System.out.println("Enter date:");
			String changeDate = ui.nextLine();
			stockChanges[stockChanges.length - 1][0] = changeDate;
			stockChanges[stockChanges.length - 1][1] = change; 
			stockChanges[stockChanges.length - 1][2] = String.valueOf(amount);
			
		} // end if
		else {
			// needs a text field for user to input info
			System.out.println("enter the date:");
			String changeDate = ui.nextLine();
			stockChanges[date][0] = changeDate;
			stockChanges[date][1] = change;
			stockChanges[date][2] = String.valueOf(amount);
			
		} // end else
	} // end changeInStock
	
	// will show record of the last month (last 31 days) of usage, this will have to be adjusted for ui
	/** Method Name: viewUsage
	* @Author Christina Wong 
	* @Date December 18, 2023
	* @Modified December 20, 2023
	* @Description This .
	* @Parameters  N/A
	* @Returns void
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void viewUsage() {
		System.out.println("\nDATE:\t\tINVENTORY:\t\t\tAMOUNT");
		for (int row = 0; row < stockChanges.length; row++) {
			// if the row has information to print
			if(stockChanges[row][0].length() != 0) {
				System.out.print(stockChanges[row][0] + ":\t");
				System.out.print(stockChanges[row][1] + "\t\t");
				System.out.print(stockChanges[row][2]);
				System.out.println();
			} // end if

		} // end for
		System.out.println("\nInventory report complete");

	} // end viewUsage
} // end DrugStock