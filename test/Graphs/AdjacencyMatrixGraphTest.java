package Graphs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Adjacency Matrix Graph")
class AdjacencyMatrixGraphTest {
    private Graph<Integer> diGraph, udGraph;
    private static final int SIZE = 3;

    @BeforeEach
    void instantiate() {
        diGraph = new AdjacencyMatrixGraph(SIZE);
        udGraph = new AdjacencyMatrixGraph(SIZE, false);
    }

    @Test
    @DisplayName("should instantiate correctly")
    void postInstantiation() {
        assertThrows(IllegalArgumentException.class,
                () -> new AdjacencyMatrixGraph(-1));
        postInstantiation(diGraph, true);
        postInstantiation(udGraph, false);
    }

    private void postInstantiation(Graph<Integer> graph, boolean directed) {
        assertEquals(directed, graph.isDirected());
        // Has correct number of vertices
        assertEquals(SIZE, graph.vertices());
        // Has no edges
        assertEquals(0, graph.edges());
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                assertFalse(graph.containsEdge(i, j));
            }
        }
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
        assertEquals(1, diGraph.edges());
        assertFalse(diGraph.addEdge(1, 2));
        assertTrue(diGraph.addEdge(2, 0));
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
        assertEquals(1, udGraph.edges());
        assertFalse(udGraph.addEdge(2, 1));
        assertTrue(udGraph.addEdge(2, 0));
        assertEquals(2, udGraph.edges());
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
        assertEquals(2, diGraph.edges());
        assertTrue(diGraph.removeEdge(2, 1));
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
        assertEquals(1, udGraph.edges());
        assertTrue(udGraph.removeEdge(1, 2));
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
        assertEquals(-1, udGraph.degree(-1));
        assertEquals(-1, diGraph.indegree(3));
        assertEquals(-1, diGraph.outdegree(3));
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

    @Test
    @DisplayName("should expand matrix")
    void expand() {
        AdjacencyMatrixGraph graph = (AdjacencyMatrixGraph) diGraph;
        // Can't "expand" to a smaller size
        assertFalse(graph.expand(SIZE - 1));
        // Expands existing graph properly
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        assertTrue(graph.expand(SIZE * 2));
        assertEquals(SIZE * 2, graph.vertices());
        assertEquals(2, graph.edges());
        assertTrue(graph.containsEdge(0, 1));
        assertTrue(graph.containsEdge(1, 2));
    }
}
