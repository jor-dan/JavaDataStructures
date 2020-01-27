package Graphs;

/**
 * Interface for graph data structures
 *
 * @author Jordan Owens
 * @param <T> type of elements in the Graph
 */
public interface Graph<T extends Comparable<T>> {

    /**
     * Returns whether the graph is directed or undirected
     *
     * @return {@code true} if the graph is directed
     *         {@code false} if the graph is undirected
     */
    boolean isDirected();

    /**
     * Gets the number of vertices in the graph
     *
     * @return the number of vertices in the graph
     */
    int vertices();

    /**
     * Gets the number of edges in the graph
     *
     * @return the number of edges in the graph
     */
    int edges();

    /**
     * Returns whether a vertex exists in the graph
     *
     * @param vertex the vertex being searched for in the graph
     * @return {@code true} if the vertex is in the graph
     */
    boolean containsVertex(T vertex);

    /**
     * Returns whether an edge exists in the graph
     *
     * @param from vertex that is the tail of the edge
     * @param to vertex that is the head of the edge
     * @return {@code true} if the edge (from, to) exists in the graph
     */
    boolean containsEdge(T from, T to);

    /**
     * Adds a directed edge between two vertices in a directed graph.
     * Adds an undirected edge between two vertices in an undirected graph.
     *
     * @param from vertex that is the tail of the edge being added
     * @param to vertex that is the head of the edge being added
     * @return {@code true} if an edge was added between the two vertices
     *         {@code false} if an edge already existed between the two vertices
     *                       or a vertex is not in the graph and can't be added.
     */
    boolean addEdge(T from, T to);

    /**
     * Removes a directed edge between two vertices in a directed graph.
     * Removes an undirected edge between two vertices in an undirected graph.
     *
     * @param from vertex that is the tail of the directed edge being removed
     * @param to vertex that is the head of the directed edge being removed
     * @return {@code true} if the edge between the two vertices was removed
     *         {@code false} if there wasn't an edge between the two vertices
     */
    boolean removeEdge(T from, T to);

    /**
     * Gets the degree of a vertex in the graph
     *
     * @param vertex the vertex to get the degree of
     * @return the number of edges incident to {@code vertex}
     *         or -1 if {@code vertex} is not a vertex in the graph
     */
    int degree(T vertex);

    /**
     * Gets the indegree of a vertex in the graph
     *
     * @param vertex the vertex to get the indegree of
     * @return the indegree of {@code vertex} if the graph is directed
     *         or the degree of {@code vertex} if the graph is undirected
     *         or -1 if {@code vertex} is not a vertex in the graph
     */
    int indegree(T vertex);

    /**
     * Gets the outdegree of a vertex in the graph
     *
     * @param vertex the vertex to get the outdegree of
     * @return the outdegree of {@code vertex} if the graph is directed
     *         or the degree of {@code vertex} if the graph is undirected
     *         or -1 if {@code vertex} is not a vertex in the graph
     */
    int outdegree(T vertex);
}
