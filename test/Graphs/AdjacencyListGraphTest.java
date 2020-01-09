package Graphs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Adjacency List Graph")
public class AdjacencyListGraphTest {
    AdjacencyListGraph<Integer> diGraph, udGraph;

    @BeforeEach
    void instantiate() {
        diGraph = new AdjacencyListGraph<>();
        udGraph = new AdjacencyListGraph<>(false);
    }

    @Test
    @DisplayName("should instantiate correctly")
    void postInstantiation() {
        assertTrue(diGraph.isDirected());
        assertEquals(0, diGraph.vertices());
        assertEquals(0, diGraph.edges());
        assertFalse(udGraph.isDirected());
        assertEquals(0, udGraph.vertices());
        assertEquals(0, udGraph.edges());
    }

    @Test
    @DisplayName("should add vertices")
    void addVertex() {
        assertThrows(NullPointerException.class, () -> udGraph.addVertex(null));
        udGraph.addVertex(1);
        assertTrue(udGraph.containsVertex(1));
        assertFalse(udGraph.addVertex(1));
        assertFalse(udGraph.containsVertex(0));
        assertEquals(1, udGraph.vertices());
    }

    @Test
    @DisplayName("should add directed edges")
    void addDirectedEdges() {
        assertThrows(NullPointerException.class, () -> diGraph.addEdge(null, null));
        assertThrows(NullPointerException.class, () -> diGraph.addEdge(1, null));
        assertThrows(NullPointerException.class, () -> diGraph.addEdge(null, 1));
        assertTrue(diGraph.addEdge(1, 2));
        assertTrue(diGraph.containsEdge(1, 2));
        assertFalse(diGraph.containsEdge(2, 1));
        assertEquals(2, diGraph.vertices());
        assertEquals(1, diGraph.edges());
        assertFalse(diGraph.addEdge(1, 2));
        assertTrue(diGraph.addEdge(2, 3));
        assertEquals(2, diGraph.edges());
    }

    @Test
    @DisplayName("should add undirected edges")
    void addUndirectedEdges() {
        assertThrows(NullPointerException.class, () -> udGraph.addEdge(null, null));
        assertThrows(NullPointerException.class, () -> udGraph.addEdge(1, null));
        assertThrows(NullPointerException.class, () -> udGraph.addEdge(null, 1));
        assertTrue(udGraph.addEdge(1, 2));
        assertTrue(udGraph.containsEdge(1, 2));
        assertTrue(udGraph.containsEdge(2, 1));
        assertEquals(2, udGraph.vertices());
        assertEquals(1, udGraph.edges());
        assertFalse(udGraph.addEdge(2, 1));
        assertTrue(udGraph.addEdge(2, 3));
        assertEquals(2, udGraph.edges());
    }

    @Test
    @DisplayName("should remove vertices")
    void removeVertex() {
        assertThrows(NullPointerException.class, () -> udGraph.removeVertex(null));
        // Undirected Graph
        assertFalse(udGraph.removeVertex(0));
        udGraph.addEdge(1, 2);
        assertTrue(udGraph.containsVertex(1));
        assertEquals(2, udGraph.vertices());
        assertTrue(udGraph.removeVertex(1));
        assertFalse(udGraph.containsVertex(1));
        assertEquals(1, udGraph.vertices());
        assertEquals(0, udGraph.edges());
        // Directed Graph
        assertFalse(diGraph.removeVertex(0));
        diGraph.addEdge(1, 2);
        diGraph.addEdge(3, 1);
        assertTrue(diGraph.removeVertex(1));
        assertFalse(diGraph.containsVertex(1));
        assertEquals(2, diGraph.vertices());
        assertEquals(0, diGraph.edges());
    }

    @Test
    @DisplayName("should remove directed edges")
    void removeDirectedEdges() {
        assertThrows(NullPointerException.class, () -> diGraph.removeEdge(null, null));
        assertThrows(NullPointerException.class, () -> diGraph.removeEdge(1, null));
        assertThrows(NullPointerException.class, () -> diGraph.removeEdge(null, 1));
        assertFalse(diGraph.removeEdge(0, 1));
        assertEquals(0, diGraph.edges());
        diGraph.addEdge(1, 2);
        diGraph.addEdge(2, 1);
        assertTrue(diGraph.containsEdge(1, 2));
        assertTrue(diGraph.containsEdge(2, 1));
        assertEquals(2, diGraph.vertices());
        assertEquals(2, diGraph.edges());
        assertTrue(diGraph.removeEdge(2, 1));
        assertEquals(2, diGraph.vertices());
        assertEquals(1, diGraph.edges());
        assertTrue(diGraph.containsEdge(1, 2));
        assertFalse(diGraph.containsEdge(2, 1));
    }

    @Test
    @DisplayName("should remove undirected edges")
    void removeUndirectedEdges() {
        assertThrows(NullPointerException.class, () -> udGraph.removeEdge(null, null));
        assertThrows(NullPointerException.class, () -> udGraph.removeEdge(1, null));
        assertThrows(NullPointerException.class, () -> udGraph.removeEdge(null, 1));
        udGraph.addEdge(1, 2);
        assertTrue(udGraph.containsEdge(1, 2));
        assertTrue(udGraph.containsEdge(2, 1));
        assertEquals(2, udGraph.vertices());
        assertEquals(1, udGraph.edges());
        assertTrue(udGraph.removeEdge(1, 2));
        assertEquals(2, udGraph.vertices());
        assertEquals(0, udGraph.edges());
        assertFalse(udGraph.containsEdge(1, 2));
        assertFalse(udGraph.containsEdge(2, 1));
        // Doesn't remove an edge that doesn't exist
        assertFalse(udGraph.containsEdge(0, 1));
        assertFalse(udGraph.removeEdge(1, 0));
    }

    @Test
    @DisplayName("should get degree of vertices")
    void degree() {
        assertThrows(NullPointerException.class, () -> udGraph.degree(null));
        assertThrows(NullPointerException.class, () -> diGraph.indegree(null));
        assertThrows(NullPointerException.class, () -> diGraph.outdegree(null));
        assertEquals(0, udGraph.degree(1));
        udGraph.addEdge(1, 2);
        assertEquals(1, udGraph.degree(1));
        assertEquals(1, udGraph.indegree(1));
        assertEquals(1, udGraph.outdegree(1));
        diGraph.addEdge(1, 2);
        assertEquals(1, diGraph.degree(1));
        assertEquals(1, diGraph.degree(2));
        assertEquals(1, diGraph.indegree(2));
        assertEquals(1, diGraph.outdegree(1));
        assertEquals(0, diGraph.indegree(1));
        assertEquals(0, diGraph.outdegree(2));
    }
}
