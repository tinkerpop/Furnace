package com.tinkerpop.furnace.algorithms;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.furnace.algorithms.shortestpath.DijkstrasAlgorithm;

public class DijkstrasAlgorithmTest {

	@Test
	public void dijkstrasSimpleTest() {
		Graph graph = new TinkerGraph();
		Vertex vertex1 = graph.addVertex(null);
		Vertex vertex2 = graph.addVertex(null);
		Vertex vertex3 = graph.addVertex(null);

		Edge edge1 = vertex1.addEdge("TO", vertex2);
		Edge edge2 = vertex2.addEdge("TO", vertex3);

		edge1.setProperty("weight", 1);
		edge2.setProperty("weight", 1);

		DijkstrasAlgorithm da = new DijkstrasAlgorithm(graph);
		Map<Vertex, Long> shortMap = da.compute(vertex1, "weight");
		assertEquals(shortMap.get(vertex1), new Long(0));
		assertEquals(shortMap.get(vertex2), new Long(1));
		assertEquals(shortMap.get(vertex3), new Long(2));
	}

	@Test
	public void dijkstrasSinkTest() {
		Graph graph = new TinkerGraph();
		Vertex vertex1 = graph.addVertex(null);
		Vertex vertex2 = graph.addVertex(null);
		Vertex vertex3 = graph.addVertex(null);

		Edge edge1 = vertex2.addEdge("TO", vertex1);
		Edge edge2 = vertex3.addEdge("TO", vertex1);

		edge1.setProperty("weight", 1);
		edge2.setProperty("weight", 1);

		DijkstrasAlgorithm da = new DijkstrasAlgorithm(graph);
		Map<Vertex, Long> shortMap = da.compute(vertex1, "weight");
		assertEquals(shortMap.get(vertex1), new Long(0));
		assertEquals(shortMap.get(vertex2), new Long(Long.MAX_VALUE));
		assertEquals(shortMap.get(vertex3), new Long(Long.MAX_VALUE));
	}
}
