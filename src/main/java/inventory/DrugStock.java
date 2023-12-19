/**
***********************************************
 @Name: DrugStock
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 13, 2023
   @Description    : 
   
***********************************************
*/
package inventory;

public class DrugStock {
	private Drug drug;
	private int numInStock; // current stock of drug
	private int stockThreshold; // when the drug's threshold is reached, alert is sent
	
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
	
	public void setDrugDIN(int drugDIN) {
		drug.setDIN(
		"1");
	} // end setDrugDIN
	
	public int getNumInStock() {
		return numInStock;
	} // end getNumInStock
	
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
	
} // end DrugStock