package back;

/**
 * @author Ethan
 */

public class VoyageGeo extends Voyage {

    public Graph<PointGeographique> graph;

    /**
     * @author Ethan
     * @brief Constructeur par défaut pour VoyageGeo
     * Initialise un graphe vide de type PointGeographique
     */

    public VoyageGeo() {
        super();
        this.graph = new Graph<>();
    }

    /**
     * @author Ethan
     * @brief Constructeur pour VoyageGeo avec un graphe existant
     * @param graph Le graphe de type PointGeographique à utiliser
     */

    //getters
    public Graph<PointGeographique> getGraph() {
        return graph;
    }

    /**
     * @author Ethan
     * @brief Setter pour le graphe de type PointGeographique
     * @param graph Le graphe de type PointGeographique à définir
     */

    public void setGraph(Graph<PointGeographique> graph) {
        this.graph = graph;
    }
    
}
