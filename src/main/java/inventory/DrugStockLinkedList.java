/**
***********************************************
 @Name: LinkedList
 @Author           : Christina Wong
 @Creation Date    : December 13, 2023
 @Modified Date	   : December 17, 2023
   @Description    : 
   
***********************************************
*/
package inventory;

public class DrugStockLinkedList {

	private static class Node {
		DrugStock drugStock;
		Node next;
	} // end Node
	
	private Node head;
	
	
	/** Method Name: find
	* @Author Kyle McKay
	* @Date Unknown
	* @Modified December 15, 2023
	* @Description This .
	* @Parameters  DrugStock searchDrug, the item that is being searched for
	* @Returns boolean true if found, false if not found
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public boolean find(DrugStock searchDrug) {
		Node runner; // A pointer for traversing the list.
		runner = head; // Start by looking at the head of the list.

		while (runner != null) {
			if (runner.drugStock.equals(searchDrug))
				return true;
			runner = runner.next;
		} // end while

		// if the drug is never found
		return false;
	} // end find()
	
	/** Method Name: checkStockDIN
	* @Author Christina Wong 
	* @Date December 15, 2023
	* @Modified December 15, 2023
	* @Description This .
	* @Parameters  String searchDIN, the DIN of the drug to search for 
	* @Returns boolean true if found, false if not found
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public boolean checkStockDIN(String searchDIN) {
		Node runner;
		runner = head;
		
		while(runner != null) {
			if(runner.drugStock.getDrugDIN().equals(searchDIN)) {
				return true;
			} // end if
			runner = runner.next;
		} // end while	
		
		return false;
	} // end checkStock
	
	/** Method Name: checkStockName
	* @Author Christina Wong 
	* @Date December 15, 2023
	* @Modified December 16, 2023
	* @Description This .
	* @Parameters  String searchName, the brand or generic name of the drug to search for 
	* @Returns boolean true if found, false if not found
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public String checkStockName(String searchName) {
		Node runner;
		runner = head;
		
		while(runner != null) {
			if(runner.drugStock.getDrugNameGen().equals(searchName) || runner.drugStock.getDrugNameBrand().equals(searchName)) {
				return runner.drugStock.getDrugDIN();
			} // end if
		} // end while
		
		return "";
	} // end checkStockName
	
	// will have to rewrite print statements to be displayed on UI
	// how will notification be set up for threshold reached?  email, pop-up box?
	/** Method Name: printDrugInfo
	* @Author Christina Wong 
	* @Date December 16, 2023
	* @Modified December 16, 2023
	* @Description This .
	* @Parameters  String DINString, DIN of drug being printed
	* @Returns void
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void printDrugInfo(String DINString) {
		Node runner = head;
		
		while(runner != null) {
			if(runner.drugStock.getDrugDIN().equals(DINString)) {
				System.out.print("Drug: " + runner.drugStock.getDrugNameGen());
				if(runner.drugStock.getDrugNameBrand() != "") {
					System.out.print(" (" + runner.drugStock.getDrugNameBrand() + ")");
				} // end if
				System.out.println();
				
				System.out.println("\nDIN: " + runner.drugStock.getDrugDIN());
				System.out.println("\nDrug Class: " + runner.drugStock.getClass());
				System.out.println("Current stock: " + runner.drugStock.getNumInStock());
				System.out.println("Current threshold: " + runner.drugStock.getStockThreshold());
				System.out.println("\nStock is " + (runner.drugStock.getNumInStock() - runner.drugStock.getStockThreshold()) + " away from threshold.  You will receive a notification when stock hits the minimum threshold");
				
			} // end if
		} // end while
	} // end printDrugInfo
	
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
	public void updateStock(int newStock, String arrivalDIN, String nameGen, String nameBrand, String classDrug, int dosage){
		boolean isStocked = checkStockDIN(arrivalDIN);
		
		// if inventory already has some of the drug in stock
		if(isStocked) {
			Node runner = head;
			while(runner != null) {
				if(runner.drugStock.getDrugDIN().equals(arrivalDIN)) {
					runner.drugStock.addToStock(newStock);
				} // end if
			} // end while
		} // end if
		
		// if this is the inventory's first shipment of the drug
		else {
			DrugStock newDrugStock = new DrugStock();
			Drug newDrug = newDrugStock.getDrug();
			newDrugStock.setDrugDIN(arrivalDIN);
			newDrugStock.setDrugNameGen(nameGen);
			newDrugStock.setDrugNameBrand(nameBrand);
			newDrug.setDrugClass(classDrug);
			
			// update to interact with setThresholdButton in StockUI
			int newThreshold = Integer.parseInt(""); // "" should be the input in the setThresholdNum JTextField
			newDrugStock.setStockThreshold(newThreshold);	
		} // end else
		
	} // end updateStock
	
	/** Method Name: insert
	* @Author Kyle McKay
	* @Date Unknown
	* @Modified December 16, 2023
	* @Description This .
	* @Parameters  DrugStock insertDrugStock, drug added to linked list
	* @Returns void
	* Dependencies: N/A
	* Throws/Exceptions: N/A
    */
	public void insert(DrugStock insertDrugStock) {
		Node newNode; // A Node to contain the new item.
		newNode = new Node();
		newNode.drugStock = insertDrugStock; // (N.B. newNode.next is null.)

		if (head == null) {
			head = newNode;
		} // end if

		if (head.drugStock.getDrugNameGen().compareTo(insertDrugStock.getDrugNameGen()) >= 0) {
			newNode.next = head;
			head = newNode;
		} // end if

		else {
			Node runner; // A node for traversing the list.
			Node previous; // Always points to the node preceding runner.
			runner = head.next; // Start by looking at the SECOND position.
			previous = head;
			while (runner != null
					&& runner.drugStock.getDrugNameGen().compareTo(insertDrugStock.getDrugNameGen()) < 0) {

				previous = runner;
				runner = runner.next;
			} // end while
			newNode.next = runner; // Insert newNode after previous.
			previous.next = newNode;

		} // end else

	} // end insert()
	
	// not sure if we will need this
    public DrugStock[] getElements() {

        int count;          // For counting elements in the list.
        Node runner;        // For traversing the list.
        DrugStock[] drugs;  // An array to hold the list elements.

        // First, go through the list and count the number
        // of elements that it contains.

        count = 0;
        runner = head;
        while (runner != null) {
            count++;
            runner = runner.next;
        } // end while

        // Create an array just large enough to hold all the
        // list elements.  Go through the list again and
        // fill the array with elements from the list.

        drugs = new DrugStock[count];
        runner = head;
        count = 0;
        while (runner != null) {
            drugs[count] = runner.drugStock;
            count++;
            runner = runner.next;
        } // end while

        // Return the array that has been filled with the list elements.

        return drugs;

    } // end getElements()
	
} // end LinkedList