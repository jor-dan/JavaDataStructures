package Graphs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Adjacency List Graph")
class AdjacencyListGraphTest {
    Graph<Integer> diGraph, udGraph;

    @BeforeEach
    void instantiate() {
        diGraph = new AdjacencyListGraph<>();
        udGraph = new AdjacencyListGraph<>(false);
    }

    @Test
    @DisplayName("should instantiate correctly")
    void postInstantiation() {
        postInstantiation(diGraph, true);
        postInstantiation(udGraph, false);
    }

    private void postInstantiation(Graph<?> graph, boolean directed) {
        assertEquals(directed, graph.isDirected());
        assertEquals(0, graph.vertices());
        assertEquals(0, graph.edges());
    }

    @Test
    @DisplayName("should add vertices")
    void addVertex() {
        AdjacencyListGraph<Integer> alg = (AdjacencyListGraph<Integer>) udGraph;
        assertThrows(NullPointerException.class, () -> alg.addVertex(null));
        assertTrue(alg.addVertex(1));
        assertTrue(alg.containsVertex(1));
        assertFalse(alg.addVertex(1));
        assertFalse(alg.containsVertex(0));
        assertEquals(1, alg.vertices());
    }

    @Test
    @DisplayName("should add directed edges")
    void addDirectedEdges() {
        assertFalse(diGraph.addEdge(null, null));
        assertFalse(diGraph.addEdge(1, null));
        assertFalse(diGraph.addEdge(null, 1));
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
        assertFalse(udGraph.addEdge(null, null));
        assertFalse(udGraph.addEdge(1, null));
        assertFalse(udGraph.addEdge(null, 1));
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
        AdjacencyListGraph<Integer> diG = (AdjacencyListGraph<Integer>) diGraph;
        AdjacencyListGraph<Integer> udG = (AdjacencyListGraph<Integer>) udGraph;
        assertFalse(udG.removeVertex(null));
        // Undirected Graph
        assertFalse(udG.removeVertex(0));
        udGraph.addEdge(1, 2);
        assertTrue(udG.containsVertex(1));
        assertEquals(2, udG.vertices());
        assertTrue(udG.removeVertex(1));
        assertFalse(udG.containsVertex(1));
        assertEquals(1, udG.vertices());
        assertEquals(0, udG.edges());
        // Directed Graph
        assertFalse(diG.removeVertex(0));
        diG.addEdge(1, 2);
        diG.addEdge(3, 1);
        assertTrue(diG.removeVertex(1));
        assertFalse(diG.containsVertex(1));
        assertEquals(2, diG.vertices());
        assertEquals(0, diG.edges());
    }

    @Test
    @DisplayName("should remove directed edges")
    void removeDirectedEdges() {
        assertFalse(diGraph.removeEdge(null, null));
        assertFalse(diGraph.removeEdge(1, null));
        assertFalse(diGraph.removeEdge(null, 1));
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
        assertFalse(udGraph.removeEdge(null, null));
        assertFalse(udGraph.removeEdge(1, null));
        assertFalse(udGraph.removeEdge(null, 1));
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
        assertEquals(-1, udGraph.degree(null));
        assertEquals(-1, diGraph.indegree(null));
        assertEquals(-1, diGraph.outdegree(null));
        assertEquals(-1, udGraph.degree(1));
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
