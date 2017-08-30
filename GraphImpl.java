package es.upv.epsa.eda.lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.synth.SynthStyle;

public class GraphImpl implements Graph{

	static List<Node> lst = new ArrayList<Node>();

	public GraphImpl(){
	}

	public void addVertex(Integer v) throws RepeatedVertex {
		// TODO Auto-generated method stub
		if (v > lst.size()){
			for(int i = 0; i <= v; i++){
				try{
					lst.get(i);
				}catch(Exception e){
					lst.add(null);
				}
			}
		}
		if (lst.get(v - 1) == null){
			lst.remove(v - 1);
			lst.set(v - 1, new Node(-1, -1));
		}else{
			throw new RepeatedVertex("This vertex " + v + " is already in the graph");
		}
	}

	public void removeVertex(Integer v) throws VertexNotFound {
		// TODO Auto-generated method stub

		try{
			lst.get(v - 1);
			lst.remove(v - 1);
			lst.add(v - 1, null);
		}catch (Exception e){
			throw new VertexNotFound("The vertex " + v + " has not been found on the Graph");
		}
	}

	public List<Integer> getVertices() {
		// TODO Auto-generated method stub
		List<Integer> returnLst = new ArrayList<Integer>();
		for (int i = 1; i <= lst.size(); i++){
			if (lst.get(i - 1) != null)
				returnLst.add(i);
		}
		return returnLst;
	}

	public void addEdge(Integer src, Integer dst, Integer weight) throws RepeatedEdge {
		// TODO Auto-generated method stub
		addEdge(lst.get(src - 1), dst, weight);
	}
	private void addEdge(Node node, Integer dst, Integer weight) throws RepeatedEdge{
		if (node.getData() != -1){
			if (node.getData() == dst && node.getValue() == weight)
				throw new RepeatedEdge("This edge " + dst + " is already on the graph");
			if (node.getNext() == null){
				node.setNext(new Node(dst, weight));
				node.getNext().setPrev(node);
			}
			else
				addEdge(node.getNext(), dst, weight);
		}else{
			node.setData(dst);
			node.setValue(weight);
		}
	}

	public void removeEdge(Integer src, Integer dst) throws EdgeNotFound {
		// TODO Auto-generated method stub
		Node root = lst.get(src - 1);
		if (root.getNext() == null){
			lst.remove(src - 1);
			lst.add(src - 1, new Node(-1, -1));
		}else
			removeEdge(root, dst);
	}
	private void removeEdge(Node node, Integer dst) throws EdgeNotFound{
		if (node.getData() == dst){
			node.getPrev().setNext(node.getNext());
			node.setPrev(null);
			node.setNext(null);
		}
		else{
			if (node.getNext() == null)
				throw new EdgeNotFound("This edge " + dst + " has not been found on the graph");
			else
				removeEdge(node.getNext(), dst);
		}

	}

	public boolean containsEdge(Integer src, Integer dst) {
		// TODO Auto-generated method stub

		return containsEdge(lst.get(src - 1), dst);
	}
	private boolean containsEdge(Node node, Integer dst){
		if (node.getData() != -1){
			if (node.getData() == dst){
				return true;
			}
			else{
				if (node.getNext() == null)
					return false;
				else
					return containsEdge(node.getNext(), dst);
			}
		}else{
			return false;
		}
	}

	public Integer getEdge(Integer src, Integer dst) throws EdgeNotFound {
		// TODO Auto-generated method stub

		return getEdge(lst.get(src - 1), dst);
	}
	private Integer getEdge(Node node, Integer dst) throws EdgeNotFound{
		if (node.getData() != -1){
			if (node.getData() == dst){
				return node.getValue();
			}
			else{
				if (node.getNext() == null)
					throw new EdgeNotFound("This edge " + dst + " has not been found on the graph");
				else
					return getEdge(node.getNext(), dst);
			}
		}else{
			throw new EdgeNotFound("This edge " + dst + " has not been found on the graph");
		}
	}
	public List<Integer> lstForNode(Node node, List<Integer> returnLst) {
		if (node.getNext() == null){
			returnLst.add(node.getValue());
			return returnLst;
		}else{
			returnLst.add(node.getValue());
			return lstForNode(node.getNext(), returnLst);
		}
	}
	public Integer extractMin (List<Integer> nLst){
		int minIndex = nLst.indexOf(Collections.min(nLst));
		int num = nLst.get(minIndex);
		nLst.remove(minIndex);
		return num;
	}
	public void  dijkstra(Integer vertex) throws EdgeNotFound{
		System.out.println(lst.size());
		List<Double> S = new ArrayList<Double>();
		List<Integer> Q = new ArrayList<Integer>();
		List<Double> D = new ArrayList<Double>();
		List<Double> P = new ArrayList<Double>();

		for (Node node : lst) {
			D.add(Double.POSITIVE_INFINITY);
			P.add(null);
		}
		D.set(vertex - 1, (double) 0);
		S.clear();
		Q = getVertices();
		while (!Q.isEmpty()){
			int u = extractMin(Q);

			S.add((double) u);
			Node node = lst.get(u - 1);
			while((node != null) && node.getData() != -1){
				if (D.get(node.getData() - 1) > D.get(u - 1) + (double) getEdge(u, node.getData())){
					D.set(node.getData() - 1, D.get(u - 1) + getEdge(u, node.getData()));
					P.set(node.getData() - 1, (double) u - 1);
				}
				node = node.getNext();
			}
		}
		System.out.println(D);

	}
	public static void main(String[] args) throws RepeatedVertex, VertexNotFound, RepeatedEdge, EdgeNotFound {
		// TODO Auto-generated method stub

		GraphImpl g = new GraphImpl();

		for (int i = 1; i <= 6; i++){
			g.addVertex(i);
		}
		System.out.println(g.getVertices());
		g.addEdge(1, 2, 10);
		g.addEdge(1, 4, 8);
		g.addEdge(4, 2, 12);
		g.addEdge(2, 5, 7);
		g.addEdge(5, 4, 9);
		g.addEdge(3, 5, 1);
		g.addEdge(3, 6, 15);
		g.addEdge(6, 6, 9);

		
		List<Integer> newLst = new ArrayList<Integer>();
		int i = 1;
		for (Node node : lst){
			System.out.println("The edges for " + i);
			System.out.println(g.lstForNode(node, newLst));
			newLst.clear();
			i++;
		}

		g.dijkstra(1);
		
		
		GraphImpl g2 = new GraphImpl();

		for (int y = 1; y <= 6; y++){
			g2.addVertex(y);
		}
		System.out.println(g2.getVertices());
		g2.addEdge(1, 6, 5);
		g2.addEdge(1, 5, 10);
		g2.addEdge(2, 4, 5);
		g2.addEdge(3, 2, 10);
		g2.addEdge(4, 3, 5);
		g2.addEdge(5, 4, 20);
		g2.addEdge(6, 2, 20);
		g2.addEdge(6, 5, 10);

		
		List<Integer> newLst2 = new ArrayList<Integer>();
		int y = 1;
		for (Node node : lst){
			System.out.println("The edges for " + y);
			System.out.println(g2.lstForNode(node, newLst2));
			newLst.clear();
			y++;
		}

		g2.dijkstra(1);
		
		

	}
}
