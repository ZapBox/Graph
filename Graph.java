package es.upv.epsa.eda.lab5;

import java.util.List;

public interface Graph {
	public void addVertex(Integer v) throws RepeatedVertex;
	public void removeVertex(Integer v) throws VertexNotFound;
	public List<Integer> getVertices();
	public void addEdge(Integer src, Integer dst, Integer weight)
			throws RepeatedEdge;
	public void removeEdge(Integer src, Integer dst) throws EdgeNotFound;
	public boolean containsEdge(Integer src, Integer dst);
	public Integer getEdge(Integer src, Integer dst) throws EdgeNotFound;
}
