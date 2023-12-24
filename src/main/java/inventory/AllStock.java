/**
***********************************************
 @Name: AllStock
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 23, 2023
   @Description    : 
   
***********************************************
*/
package inventory;

import java.util.*;
import java.io.IOException;

public class AllStock {

	private int totalNum; // total stock
	private DrugStockLinkedList drugsList; // list of drugs in stock
	
	// total counts for stock
	private int numSmallContainers;
	private int numMediumContainers;
	private int numLargeContainers;
	private int numBags;
	Scanner ui = new Scanner(System.in); // delete when doing ui
	
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
		// hopefully this works with the OrderUI? may need adjustments
		// drugToOrder, containerToorder, dosage, nuofdrug, num of container, order drug or order container 
				
				
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
	
	// current UML for StockUI has an option to viewStock
	// adjust UI to have radio buttons or dropbox for user to choose how to search stock to view
	/** Method Name: searchByDIN
	* @Author Christina Wong 
	* @Date December 15, 2023
	* @Modified December 16, 2023
	* @Description This .
	* @Parameters String drugDIN, DIN of drug to find
	* @Returns void
	* Dependencies: DrugStockLinkedList
	* Throws/Exceptions: N/A
    */
	public void searchByDIN(String drugDIN) {
		boolean stockFound;
		stockFound = drugsList.checkStockDIN(drugDIN);
		if(stockFound) {
			drugSearch(drugDIN);			
		} // end if
		else {
			System.out.println("Drug is not found in inventory.");
		} // end else
	} // end searchByDIN
	
	/** Method Name: searchByName
	* @Author Christina Wong 
	* @Date December 15, 2023
	* @Modified December 16, 2023
	* @Description This .
	* @Parameters String drugName, the name of the drug to find
	* @Returns void
	* Dependencies: DrugStockLinkedList
	* Throws/Exceptions: N/A
    */
	public void searchByName(String drugName) {
		String searchDIN = "";
		searchDIN = drugsList.checkStockName(drugName);		
		if(searchDIN.equals("")) {
			System.out.println("Drug is not found in inventory.");
		} // end if
		else {
			drugSearch(searchDIN);
		} // end else
	} // end searchByName
	
	/** Method Name: drugSearch
	* @Author Christina Wong 
	* @Date December 16, 2023
	* @Modified December 16, 2023
	* @Description This .
	* @Parameters String printDrug, the name of the drug to print info for
	* @Returns void
	* Dependencies: DrugStockLinkedList
	* Throws/Exceptions: N/A
    */
	public void drugSearch(String printDrug) {
		System.out.println("\nInventory Information:");
		drugsList.printDrugInfo(printDrug);		
	} // end drugSearch
	
	// needs to interact with stock ui
	/** Method Name: shipmentArrival
	* @Author Christina Wong 
	* @Date December 16, 2023
	* @Modified December 16, 2023
	* @Description This .
	* @Parameters int newStock, the quantity of the shipment; String arrivalDIN, the DIN of the drug arriving; String nameGen, the generic name of the drug; String nameBrand, the brand name of the drug (could be ""); String classDrug, class of the drug; int dosage, dosage of drug
	* @Returns void
	* Dependencies: DrugStockLinkedList
	* Throws/Exceptions: N/A
    */
	public void shipmentArrival(int newStock, String arrivalDIN, String classDrug) throws IOException {
		updateStock(newStock, arrivalDIN, classDrug);
	} // end shipmentArrival	
	
	/** Method Name: updateStock
	* @Author Christina Wong 
	* @Date December 16, 2023
	* @Modified December 17, 2023
	* @Description This .
	* @Parameters int newStock, the quantity of the shipment; String arrivalDIN, the DIN of the drug arriving; String nameGen, the generic name of the drug; String nameBrand, the brand name of the drug (could be ""); String classDrug, class of the drug; int dosage, dosage of drug
	* @Returns void
	* Dependencies: DrugStock, Drug
	* Throws/Exceptions: N/A
    */
	public void updateStock(int newStock, String arrivalDIN, String classDrug) throws IOException{
		boolean isStocked = drugsList.checkStockDIN(arrivalDIN);
		if(isStocked == false) { 		// if this is the inventory's first shipment of the drug
			// update to interact with setThresholdButton in StockUI
			System.out.println("Enter threshold:");
			int newThreshold = Integer.parseInt(ui.nextLine()); // "" should be the input in the setThresholdNum JTextField
			DrugStock newDrugStock = new DrugStock(arrivalDIN, 0, newThreshold);	
			
			drugsList.insert(newDrugStock);
		} // end if
		
		drugsList.newShipment(arrivalDIN, newStock);
		
	} // end updateStock
	
	public void viewUsage(String DIN) {
		drugsList.viewStockUsage(DIN);
	} // end viewUsage
	
} // end AllStock