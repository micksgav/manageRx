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

public class DrugStock {
	private Drug drug; // drug
	private int numInStock; // current stock of drug
	private int stockThreshold; // when the drug's threshold is reached, alert is sent
	private int[][] stockChanges = new int[31][3];
	
	public DrugStock(int inStock, int threshold) {
		this.drug = new Drug();
		numInStock = inStock;
		stockThreshold = threshold;
	} // end DrugStock constructor
	
	public DrugStock() {
		this.drug = new Drug();
		numInStock = 0;
		stockThreshold = 0;
	} // end DrugStock blank constructor
	
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
	* @Returns N/A
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
	
	// somewhere in StockUI, I don't think there's anything for it now
	public void changeInStock(String change) {
		int date = -1;
		for(int i = 0; i < stockChanges.length; i++) {
			if(stockChanges[i][0] == 0) {
				date = i;
				break;
			}
		}
		if(date == -1) {
			
		}
		else {
			
		}
	}
	
	// will show record of the last month (last 31 days) of usage
	public void viewUsage() {
		
	}
	
} // end DrugStock