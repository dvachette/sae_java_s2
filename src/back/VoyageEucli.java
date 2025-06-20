package back;

/**
 * @author Ethan
 */

public class VoyageEucli extends Voyage {

    public Graph<PointEuclidien> graph;

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
     * @brief Constructeur pour VoyageEucli avec un graphe existant
     * @param graph Le graphe de type PointEuclidien à utiliser
     */

    //getters
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
