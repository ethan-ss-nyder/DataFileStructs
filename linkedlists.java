// 8/3/2024
// Ethan Snyder

// 1. Implement a class that uses a stack to determine if a string has balanced paranthesis
// 2. Implement a class that uses queues to determine if a string has balanced paranthesis (Hint: two queues can be used to simulate a stack's behavior)
// Must be well commented.

import java.util.Arrays; // This is for printing purposes, I'm not cheating
import java.util.Scanner;

/**
* Doubly linked list that only accepts integers.
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
	* @param index: Integer index of the node to be removed. Head node is 0.
	*/
	public void remove(int index) {

		// Protection against bad indices
		if (index < 0 || index > (nodes - 1)) {
			System.out.println("Invalid index.");
			return;
		}

		Node iterNode = this.head, prev = null; // Define node 0 for iteration purposes

		// If head = tail, this is the only node, remove it since we've already checked the index is fine
		if (iterNode == this. tail) {
			this.head = null;
			this.tail = null;
		} else if (index == 0) { // If the user wants to remove the head, handle it here
			this.head.next = this.head;
			this.head.prev = null;
		}

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
	 * @return tail node data as an integer.
	 */
	public int pop() {
		Node poppable = this.tail; // Record poppable node before we redefine it

		this.tail = poppable.prev; // New tail is previous node (exclude the popped tail)
		this.tail.next = null; // Tell new tail there is now no next node
		this.nodes--;

		return poppable.data;
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

/**
* Implementation of a stack data structure.
* Stacks are first-in last-out structures. push, pop, and peek are the main functions here.
*/
class Stack {
	LinkedList linkedList;

	Stack() {
		this.linkedList = new LinkedList();
	}

	public void push(int data) {
		this.linkedList.insert(data);
	}

	public int pop() {
		return this.linkedList.pop();
	}

	public boolean isEmpty() {
		if (this.linkedList.nodes == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		for (int i = 0; i < this.linkedList.nodes; i++) {
			this.linkedList.remove(i);
		}
	}

	public int peek() {
		return this.linkedList.tail.data;
	}
}

/**
* Implementation of a queue data structure.
* Queues are first-in first-out structures. addToQueue, popQueue and poll are the main functions here.
*/
class Queue {
	LinkedList linkedList;

	Queue() {
		this.linkedList = new LinkedList();
	}

	public void addToQueue(int data) {
		this.linkedList.insert(data);
	}

	public int popQueue() {
		int data = this.linkedList.head.data;
		this.linkedList.remove(0); // Remove from back of queue (first LL element)
		return data;
	}

	public boolean isEmpty() {
		if (this.linkedList.nodes == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		for (int i = 0; i < this.linkedList.nodes; i++) {
			this.linkedList.remove(i);
		}
	}

	public int poll() {
		return this.linkedList.tail.data;
	}
}

/**
* Utilizing stack structures, this provides a method that determines whether a string of
* parenthesis is balanced.
*/
class StackParenthesisChecker {
	Stack stack;

	StackParenthesisChecker(Stack x) {
		this.stack = x;
	}

	public boolean isBalanced() {
		int opens = 0, closes = 0;
		while (!this.stack.isEmpty()) { // While there are elements in the string...
			switch (this.stack.pop()) {
				case 0: opens++; // 0's are openers
				case 1: closes++; // 1's are closers
				default: break; // HOPEFULLY there's nothing else
			}
		}
		if (opens == closes) {
			return true;
		} else {
			return false;
		}
	}
}

/**
* Utilizing queue structures, this provides a method that determines whether a string of
* parenthesis is balanced.
*/
class QueueParenthesisChecker {
	Queue queue;

	QueueParenthesisChecker(Queue x) {
		this.queue = x;
	}

	public boolean isBalanced() {
		int opens = 0, closes = 0;
		while (!this.queue.isEmpty()) { // You know the drill
			switch (this.queue.popQueue()) {
				case 0: opens++;
				case 1: closes++;
				default: break;
			}
		}
		if (opens == closes) {
			return true;
		} else {
			return false;
		}
	}
}

class Main {

	public static Scanner scanner = new Scanner(System.in); // Open scanner for user input
	public static String userInput = null;

	public static Stack stack = new Stack();
	public static Queue queue = new Queue();

	public static void main(String args[]) {
		System.out.println("Use Ctrl + C to exit program at any time.");
		while (true) {
			System.out.print("Input any string of parenthesis: ");
			userInput = scanner.next(); // Collect user input
			for (int i = 0; i < userInput.length(); i++) {
				switch (Character.toString(userInput.charAt(i))) {
					case "(":
						stack.push(0);
						queue.addToQueue(0);
					case ")":
						stack.push(1);
						queue.addToQueue(1);
					default:
						break;
				}
			}
			StackParenthesisChecker stackChecker = new StackParenthesisChecker(stack);
			QueueParenthesisChecker queueChecker = new QueueParenthesisChecker(queue);
			if (stackChecker.isBalanced() && queueChecker.isBalanced()) {
				System.out.println("The input string " + userInput + "has balanced parenthesis.");
			} else {
				System.out.println("The input string " + userInput + "does not have balanced parenthesis.");
			}
		}
	}
}
