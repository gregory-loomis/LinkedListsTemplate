package com.loomis;

public class LinkedList {
	StringNode head;
	
    /*
	REQUIRED METHODS:
	printList()
	insertAtBeginning(String newData)
	insertAfter(String targetValue, String newData)
	insertAtEnd(String newData)
	deleteNode(String data)
	search(String name)
	*/




	/* DO NOT TOUCH */
	@Override
	public String toString(){
		String output = "";
		StringNode n = head;
		while(n!=null) {
			output += (n.data +" -> ");
			n = n.next;
		}
		output = output.substring(0, output.length()-4);
		return output;
	}
}
