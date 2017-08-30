package es.upv.epsa.eda.lab5;

public class Node {
	private int data, value;
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	private Node next = null, prev = null;
	
	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node(int data, int value){
		this.data = data;
		this.value = value;
	}
}
