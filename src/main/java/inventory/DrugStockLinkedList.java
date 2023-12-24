/**
***********************************************
 @Name: DrugStockLinkedList
 @Author           : Christina Wong
 @Creation Date    : December 13, 2023
 @Modified Date	   : December 23, 2023
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
		System.out.println("searching for DIN " + searchDIN);
		while(runner != null) {
			if(runner.drugStock.getDrugDIN().equals(searchDIN)) {
				System.out.println("found");
				return true;
			} // end if
			runner = runner.next;
		} // end while	
		System.out.println("not found");
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
			if(runner.drugStock.getDrugName().equals(searchName)) {
				return runner.drugStock.getDrugDIN();
			} // end if
			runner = runner.next;
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
				System.out.print("Drug: " + runner.drugStock.getDrugName());
				System.out.println();
				
				System.out.println("\nDIN: " + runner.drugStock.getDrugDIN());
				System.out.println("\nDrug Class: " + runner.drugStock.getDrug().getDrugClass());
				System.out.println("Current stock: " + runner.drugStock.getNumInStock());
				System.out.println("Current threshold: " + runner.drugStock.getStockThreshold());
				System.out.println("\nStock is " + (runner.drugStock.getNumInStock() - runner.drugStock.getStockThreshold()) + " away from threshold.  You will receive a notification when stock hits the minimum threshold");
				
			} // end if
			runner = runner.next;
		} // end while
	} // end printDrugInfo
	
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

		else if (Integer.parseInt(head.drugStock.getDrugDIN()) > Integer.parseInt(newNode.drugStock.getDrugDIN())) {
			newNode.next = head;
			head = newNode;
		} // end if

		else {
			Node runner; // A node for traversing the list.
			Node previous; // Always points to the node preceding runner.
			runner = head.next; // Start by looking at the SECOND position.
			previous = head;
			while (runner != null && Integer.parseInt(runner.drugStock.getDrugDIN()) < Integer.parseInt(insertDrugStock.getDrugDIN())) {
				previous = runner;
				runner = runner.next;
			} // end while
			newNode.next = runner; // Insert newNode after previous.
			previous.next = newNode;

		} // end else
		
		System.out.println("new node inserted");
		printDINs();

	} // end insert()
	
	public void newShipment(String arrivalDIN, int newStock) {
		// if inventory already has some of the drug in stock
		System.out.println("adding new shipment");
		Node runner;
		runner = head;
		while(runner != null) {
			if(runner.drugStock.getDrugDIN().equals(arrivalDIN)) {
				runner.drugStock.addToStock(newStock);
				System.out.println("shipment added");
				break;
			} // end if
			runner = runner.next;
		} // end while
	} // end newShipment
	
	public void printDINs() {
		Node runner;
		runner = head;
		while(runner != null) {
			System.out.println(runner.drugStock.getDrugDIN());
			runner = runner.next;
		} // end while
	} // end printDINS
	
	public void viewStockUsage(String DIN) {
		System.out.println("Viewing stock usage");
		Node runner;
		runner = head;
		boolean found = false;
		while(runner != null) {
			if(runner.drugStock.getDrugDIN().equals(DIN)) {
				runner.drugStock.viewUsage();
				found = true;
				break;
			} // end if
			runner = runner.next;
		} // end while
		if(found == false) {
			System.out.println("Drug not found in inventory");
		} // end if
	} // end viewStockUsage
	
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