/**
***********************************************
 @Name: LinkedList
 @Author           : Christina Wong
 @Creation Date    : December 12, 2023
 @Modified Date	   : December 13, 2023
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
	
	
    /**
     * Searches the list for a specified item.  (Note: for demonstration
     * purposes, this method does not use the fact that the items in the
     * list are ordered.)
     * @param searchItem the item that is to be searched for
     * @return true if searchItem is one of the items in the list or false if
     *    searchItem does not occur in the list.
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
        }

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
        }

        // Return the array that has been filled with the list elements.

        return drugs;

    } // end getElements()
	
} // end LinkedList