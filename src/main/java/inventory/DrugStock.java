/**
***********************************************
 @Name: DrugStock
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 19, 2023
   @Description    : 
   
***********************************************
*/
package inventory;

import java.io.IOException;
import java.util.*; // just for testing purposes, will need to be replaced with UI stuff later

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
	} // end DrugStock constructor with stock info
	
	public DrugStock(String DIN) throws IOException {
		this.drug = new Drug();
		this.drug = drugFinder.getDrug(DIN);
		numInStock = 0;
		stockThreshold = 0;
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
	* @Modified December 12, 2023
	* @Description This .
	* @Parameters  int filled, the amount of of this drug removed from the stock to fill a prescription 
	* @Returns N/A
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void removeFromStock(int filled) {
		this.numInStock -= filled;
	} // end removeFromStock
	
	/** Method Name: addToStock
	* @Author Christina Wong 
	* @Date December 12, 2023
	* @Modified December 12, 2023
	* @Description This .
	* @Parameters  int added, the amount of this drug added to the current stock
	* @Returns void
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void addToStock(int added) {
		this.numInStock += added;
	} // end addToStock
	
	public int getStockThreshold() {
		return stockThreshold;
	} // end getStockThreshold
	
	public void setStockThreshold(int threshold) {
		this.stockThreshold = threshold;
	} // end setStockThreshold
	
	// connects to somewhere in StockUI, I don't think there's anything for it now
	/** Method Name: changeInStock
	* @Author Christina Wong 
	* @Date December 18, 2023
	* @Modified December 19, 2023
	* @Description This .
	* @Parameters  int added, the amount of this drug added to the current stock
	* @Returns void
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void changeInStock(String change) {
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
			String changeDate = ui.nextLine();
			stockChanges[stockChanges.length - 1][0] = changeDate;
			stockChanges[stockChanges.length - 1][1] = change; 
			
		} // end if
		else {
			// needs a text field for user to input info
			String changeDate = ui.nextLine();
			stockChanges[date][0] = changeDate;
			stockChanges[date][1] = change;
			
		} // end else
	} // end changeInStock
	
	// will show record of the last month (last 31 days) of usage
	public void viewUsage() {
		
	}
} // end DrugStock