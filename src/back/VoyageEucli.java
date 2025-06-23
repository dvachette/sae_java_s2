package back;

/**
 * @author Ethan
 */

public class VoyageEucli extends Voyage {

    private Graph<PointEuclidien> graph;

    /**
     * @author Ethan
     * @brief Constructeur par défaut pour VoyageEucli
     * Initialise un graphe vide de type PointEuclidien
     */
    public VoyageEucli() {
        super();
        this.graph = new Graph<>();
    }

    /**
     * @author Ethan
     * @return Graph
     * @brief Getter pour le graphe de type PointEuclidien
     */
    public Graph<PointEuclidien> getGraph() {
        return graph;
    }
    
    /**
     * @author Ethan
     * @brief Setter pour le graphe de type PointEuclidien
     * @param graph
     */
    public void setGraph(Graph<PointEuclidien> graph) {
        this.graph = graph;
    }
}
