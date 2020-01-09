package Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Adjacency list representation of a graph.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the adjacency list
 */
public class AdjacencyListGraph<T extends Comparable<T>> {

    /** Maps each vertex to its adjacent vertices */
    private HashMap<T, HashSet<T>> vertices;
    /** The number of edges in the graph */
    private int edges;
    /** Whether the graph is directed or undirected */
    private final boolean directed;

    /**
     * Constructs an adjacency list graph
     *
     * @param directed {@code true} if the graph should be directed,
     *                 {@code false} if the graph should be undirected
     */
    public AdjacencyListGraph(boolean directed) {
        this.vertices = new HashMap<>();
        this.edges = 0;
        this.directed = directed;
    }

    /** Constructs a directed adjacency list graph */
    public AdjacencyListGraph() {
        this(true);
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
        return vertices.size();
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
     * Returns whether a vertex exists in the graph
     *
     * @param vertex the vertex being searched for in the graph
     * @return {@code true} if the vertex is in the graph
     */
    public boolean containsVertex(T vertex) {
        return vertices.containsKey(vertex);
    }

    /**
     * Returns whether an edge exists in the graph
     *
     * @param from vertex that is the tail of the edge
     * @param to vertex that is the head of the edge
     * @return {@code true} if the edge (from, to) exists in the graph
     */
    public boolean containsEdge(T from, T to) {
        Set<T> edges = vertices.get(from);
        return edges != null && edges.contains(to);
    }

    /**
     * Adds a vertex to the graph
     *
     * @param vertex the vertex being added to the graph
     * @return {@code true} if the vertex was added to the graph
     *         {@code false} if the vertex already existed in the graph
     * @throws NullPointerException if {@code vertex} is {@code null}
     */
    public boolean addVertex(T vertex) {
        if (vertex == null) throw new NullPointerException();
        if (containsVertex(vertex)) return false;
        vertices.put(vertex, new HashSet<>());
        return true;
    }

    /**
     * Adds a directed edge between two vertices in a directed graph.
     * Adds an undirected edge between two vertices in an undirected graph.
     * Adds the two vertices to the graph if they aren't present.
     *
     * @param from vertex that is the tail of the directed edge being added
     * @param to vertex that is the head of the directed edge being added
     * @return {@code true} if an edge was added between the two vertices
     *         {@code false} if an edge already existed between the two vertices
     * @throws NullPointerException if from or to are @{code null}
     */
    public boolean addEdge(T from, T to) {
        if (from == null || to == null) throw new NullPointerException();
        if (!vertices.computeIfAbsent(from, k -> new HashSet<>()).add(to)) {
            return false;
        }
        if (!directed) {
            vertices.computeIfAbsent(to, k -> new HashSet<>()).add(from);
        } else if (!containsVertex(to)) {
            vertices.put(to, new HashSet<>());
        }
        this.edges++;
        return true;
    }

    /**
     * Removes a vertex and edges incident to the vertex from the graph.
     *
     * @param vertex the vertex being removed from the graph
     * @return {@code true} if the vertex was removed from the graph
     *         {@code false} if the vertex is not in the graph
     * @throws NullPointerException if vertex is {@code null}
     */
    public boolean removeVertex(T vertex) {
        if (vertex == null) throw new NullPointerException();
        Set<T> removed = vertices.remove(vertex);
        if (removed == null) return false;
        this.edges -= removed.size();
        for (Set<T> adjacent : vertices.values()) {
            if (adjacent.remove(vertex) && directed) this.edges--;
        }
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
     * @throws NullPointerException if from or to are @{code null}
     */
    public boolean removeEdge(T from, T to) {
        if (from == null || to == null) throw new NullPointerException();
        Set<T> edges = vertices.get(from);
        boolean removed = edges != null && edges.remove(to);
        if (!directed) {
            edges = vertices.get(to);
            if (edges != null) edges.remove(from);
        }
        if (removed) this.edges--;
        return removed;
    }

    /**
     * Gets the degree of a vertex in the graph
     *
     * @param vertex the vertex to get the degree of
     * @return the number of edges incident to {@code vertex}
     * @throws NullPointerException if {@code vertex} is {@code null}
     */
    public int degree(T vertex) {
        int outdegree = outdegree(vertex);
        return directed ? outdegree + indegree(vertex) : outdegree;
    }

    /**
     * Gets the indegree of a vertex in the graph
     *
     * @param vertex the vertex to get the indegree of
     * @return the indegree of {@code vertex} if the graph is directed
     *         or the degree of {@code vertex} if the graph is undirected
     * @throws NullPointerException if {@code vertex} is {@code null}
     */
    public int indegree(T vertex) {
        if (vertex == null) throw new NullPointerException();
        if (!directed) return outdegree(vertex);
        int indegree = 0;
        for (Set<T> adjacent : vertices.values()) {
            if (adjacent.contains(vertex)) indegree++;
        }
        return indegree;
    }

    /**
     * Gets the outdegree of a vertex in the graph
     *
     * @param vertex the vertex to get the outdegree of
     * @return the outdegree of {@code vertex} if the graph is directed
     *         or the degree of {@code vertex} if the graph is undirected
     * @throws NullPointerException if {@code vertex} is {@code null}
     */
    public int outdegree(T vertex) {
        if (vertex == null) throw new NullPointerException();
        Set<T> edges = vertices.get(vertex);
        return edges != null ? edges.size() : 0;
    }
}
