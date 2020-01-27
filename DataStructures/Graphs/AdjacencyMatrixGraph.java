package Graphs;

/**
 * Adjacency matrix representation of a graph.
 *
 * @author Jordan Owens
 */
public class AdjacencyMatrixGraph implements Graph<Integer> {

    /** Adjacency matrix */
    private boolean[][] graph;
    /** The number of edges in the graph */
    private int edges;
    /** Whether the graph is directed or undirected */
    private final boolean directed;

    /**
     * Constructs an adjacency matrix graph with vertices numbered [0, vertices)
     *
     * @param vertices the number of vertices the graph should have
     * @param directed {@code true} if the graph should be directed,
     *                 {@code false} if the graph should be undirected
     * @throws IllegalArgumentException if {@code vertices} < 1
     */
    public AdjacencyMatrixGraph(int vertices, boolean directed) {
        if (vertices < 1) {
            throw new IllegalArgumentException("Vertices must be positive");
        }
        this.graph = new boolean[vertices][vertices];
        this.edges = 0;
        this.directed = directed;
    }

    /**
     * Constructs a directed adjacency matrix graph
     * with vertices numbered [0, vertices)
     *
     * @param vertices the number of vertices the graph should have
     * @throws IllegalArgumentException if {@code vertices} < 1
     */
    public AdjacencyMatrixGraph(int vertices) {
        this(vertices, true);
    }

    /**
     * Returns whether the graph is directed or undirected
     *
     * @return {@code true} if the graph is directed
     *         {@code false} if the graph is undirected
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * Gets the number of vertices in the graph
     *
     * @return the number of vertices in the graph
     */
    public int vertices() {
        return graph.length;
    }

    /**
     * Gets the number of edges in the graph
     *
     * @return the number of edges in the graph
     */
    public int edges() {
        return edges;
    }

    /**
     * Expands the adjacency matrix to hold more vertices
     *
     * @param vertices the new number of vertices for the matrix to hold
     * @return {@code true} if the adjacency matrix expanded
     */
    public boolean expand(int vertices) {
        if (vertices <= graph.length) return false;
        boolean[][] newGraph = new boolean[vertices][vertices];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, newGraph[i], 0, graph[i].length);
        }
        graph = newGraph;
        return true;
    }

    /**
     * Returns whether a vertex exists in the graph
     *
     * @param vertex the vertex being searched for in the graph
     * @return {@code true} if the vertex is in the graph
     */
    public boolean containsVertex(Integer vertex) {
        return vertex != null && vertex >= 0 && vertex < graph.length;
    }

    /**
     * Returns whether an edge exists in the graph
     *
     * @param from vertex that is the tail of the edge
     * @param to vertex that is the head of the edge
     * @return {@code true} if the edge (from, to) exists in the graph
     */
    public boolean containsEdge(Integer from, Integer to) {
        return containsVertex(from) && containsVertex(to) && graph[from][to];
    }

    /**
     * Adds a directed edge between two vertices in a directed graph.
     * Adds an undirected edge between two vertices in an undirected graph.
     *
     * @param from vertex that is the tail of the edge being added
     * @param to vertex that is the head of the edge being added
     * @return {@code true} if an edge was added between the two vertices
     *         {@code false} if an edge already existed between the two vertices
     *                       or at least one vertex isn't in [0, vertices)
     */
    public boolean addEdge(Integer from, Integer to) {
        if (!containsVertex(from) || !containsVertex(to) || graph[from][to]) {
            return false;
        }
        graph[from][to] = true;
        if (!directed) graph[to][from] = true;
        this.edges++;
        return true;
    }

    /**
     * Removes a directed edge between two vertices in a directed graph.
     * Removes an undirected edge between two vertices in an undirected graph.
     *
     * @param from vertex that is the tail of the directed edge being removed
     * @param to vertex that is the head of the directed edge being removed
     * @return {@code true} if the edge between the two vertices was removed
     *         {@code false} if there wasn't an edge between the two vertices
     */
    public boolean removeEdge(Integer from, Integer to) {
        if (!containsEdge(from, to)) return false;
        graph[from][to] = false;
        if (!directed) graph[to][from] = false;
        this.edges--;
        return true;
    }

    /**
     * Gets the degree of a vertex in the graph
     *
     * @param vertex the vertex to get the degree of
     * @return the number of edges incident to {@code vertex}
     *         or -1 if {@code vertex} is not a vertex in the graph
     */
    public int degree(Integer vertex) {
        int outdegree = outdegree(vertex);
        return directed ? outdegree + indegree(vertex) : outdegree;
    }

    /**
     * Gets the indegree of a vertex in the graph
     *
     * @param vertex the vertex to get the indegree of
     * @return the indegree of {@code vertex} if the graph is directed
     *         or the degree of {@code vertex} if the graph is undirected
     *         or -1 if {@code vertex} is not a vertex in the graph
     */
    public int indegree(Integer vertex) {
        if (!containsVertex(vertex)) return -1;
        int indegree = 0;
        for (boolean[] edges : graph) {
            if (edges[vertex]) indegree++;
        }
        return indegree;
    }

    /**
     * Gets the outdegree of a vertex in the graph
     *
     * @param vertex the vertex to get the outdegree of
     * @return the outdegree of {@code vertex} if the graph is directed
     *         or the degree of {@code vertex} if the graph is undirected
     *         or -1 if {@code vertex} is not a vertex in the graph
     */
    public int outdegree(Integer vertex) {
        if (!containsVertex(vertex)) return -1;
        int outdegree = 0;
        for (boolean edge : graph[vertex]) {
            if (edge) outdegree++;
        }
        return outdegree;
    }
}
