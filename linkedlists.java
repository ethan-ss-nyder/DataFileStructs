// 8/3/2024
// Ethan Snyder

// 1. Implement a class that uses a stack to determine if a string has balanced paranthesis
// 2. Implement a class that uses queues to determine if a string has balanced paranthesis (Hint: two queues can be used to simulate a stack's behavior)
// Must be well commented.

import java.util.Arrays; // This is for printing purposes, I'm not cheating

/**
* Doubly linked list.
*/
class LinkedList {

	int nodes; // # of nodes
	Node head, tail; // head is the first node created, tail is the last

	/**
	* Node object, for use by LinkedList.
	*
	* @param data: The data to be stored at this node.
	*/
	static class Node {
		Node prev, next;
		int data;

		// Node class constructor
		Node(int x) {
			this.prev = null;
			this.next = null;
			this.data = x;
		}
	}

	LinkedList() {
		this.nodes = 0;
		this.head = null;
		this.tail = null;
	}

	/**
	* Inserts a new node in the given linked list, given data.
	*
	* @param data: Integer data to be inserted at the node.
	*/
	public void insert(int data) {

		Node newNode = new Node(data);

		// Place the node in the LL
		if (this.head == null) { // If this is the first node, make it head and tail
			this.head = newNode;
			this.tail = newNode;
		} else { // Otherwise put it after the tail
			newNode.prev = this.tail; // Define prev pointer
			this.tail.next = newNode;
			this.tail = newNode; // and redefine the tail as this new node
		}

		this.nodes++; // +1 to the node count
	}

	/**
	* Remove a node at specified index.
	*
	* @param index: Integer index of the node to be removed.
	*/
	public void remove(int index) {

		// Protection against bad indices
		if (index < 0 || index > (nodes - 1)) {
			System.out.println("Invalid index.");
			return;
		}

		Node iterNode = this.head, prev = null; // Define node 0 for iteration purposes

		// Iterate through nodes in the LL
		for (int i = 0; i < nodes; i++) {
			if (i == index) { // If we're at the undesireable index...
				prev.next = iterNode.next; // Link previous node to next node
				break;
			} else { // Otherwise define the next node for next iteration
				prev = iterNode;
				iterNode = iterNode.next;
			}
		}
	}

	/**
	 * Removes the tail node, returns it.
	 *
	 * @return tail node.
	 */
	public Node pop() {
		Node poppable = this.tail; // Record poppable node before we redefine it

		this.tail = poppable.prev; // New tail is previous node (exclude the popped tail)
		this.tail.next = null; // Tell new tail there is now no next node
		this.nodes--;

		return poppable;
	}

	/**
	 * Prints the linked list.
	 */
	public void print() {
		int[] tempList = new int[nodes];

		Node iterNode = this.head; // Define head node as node 0

		// Iterate through each node
		for (int i = 0; i < nodes; i++) {
			tempList[i] = iterNode.data; // Add node data to a temp printable list
			iterNode = iterNode.next; // Iterate to next node
		}

		System.out.println("Nodes: " + this.nodes);
		System.out.println(Arrays.toString(tempList));
	}

	/**
	 * Returns the linked list as an array.
	 * 
	 * @return an array, each element is data of the linked list nodes.
	 */
	public int[] getList() {
		int[] tempList = new int[nodes];

		Node iterNode = this.head; // Define head node as node 0

		// Iterate through each node
		for (int i = 0; i < nodes; i++) {
			tempList[i] = iterNode.data; // Add node data to a temp printable list
			iterNode = iterNode.next; // Iterate to next node
		}

		return tempList;
	}
}

class Stack {
	LinkedList linkedList;
	LinkedList.Node top;

	Stack() {
		
	}
}

class Main {
	public static void main(String args[]) {
		LinkedList list = new LinkedList();
		list.insert(5);
		list.insert(10);
		list.pop();
		list.insert(15);
		list.print();
	}
}
