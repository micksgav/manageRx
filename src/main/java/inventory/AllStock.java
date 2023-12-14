/**
***********************************************
 @Name: AllStock
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 13, 2023
   @Description    : 
   
***********************************************
*/
package inventory;

public class AllStock {

	private int totalNum; // total stock
	private DrugStockLinkedList drugsList; // list of drugs in stock
	
	// total counts for stock
	private int numSmallContainers;
	private int numMediumContainers;
	private int numLargeContainers;
	private int numBags;
	
	public AllStock(int small, int medium, int large, int bags) {
		// set totalNum
		this.drugsList = new DrugStockLinkedList();
		numSmallContainers = small;
		numMediumContainers = medium;
		numLargeContainers = large;
		numBags = bags;
	} // end AllStock constructor
	
	public AllStock() {
		this.drugsList = new DrugStockLinkedList();
		totalNum = 0;
		numSmallContainers = 0;
		numMediumContainers = 0;
		numLargeContainers = 0;
		numBags = 0;
	} // end AllStock blank constructor
	
	/** Method Name: orderMore
	* @Author Christina Wong 
	* @Date December 12, 2023
	* @Modified December 13, 2023
	* @Description This .
	* @Parameters  String drug, the drug to be ordered 
	* @Returns N/A
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void orderMore(String drug) {
		// code here
	} // end orderMore
	
	public int getTotal() {
		return totalNum;
	} // end getTotal
	
	public void setTotal(int total) {
		totalNum = total;
	} // end setTotal
	
	public int getNumSmall() {
		return numSmallContainers;
	} // end getNumSmall
	
	public void setNumSmall(int small) {
		this.numSmallContainers = small;
	} // end setNumSmall
	
	public int getNumMedium() {
		return numMediumContainers;
	} // end getNumMedium
	
	public void setNumMedium(int medium) {
		this.numMediumContainers = medium;
	} // end setNumMedium
	
	public int getNumLarge() {
		return numLargeContainers;
	} // end getNumLarge
	
	public void setNumLarge(int large) {
		this.numLargeContainers = large;
	} // end setNumLarge
	
	public int getNumBags() {
		return numBags;
	} // end getNumBags
	
	public void setNumBags(int bags) {
		this.numBags = bags;
	} // end setNumBags
	
	public void searchStock() {
		
	}
	
} // end AllStock