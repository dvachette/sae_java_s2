package back;

/**
 * @author Ethan
 */

public class Voyage_Eucli extends Voyage {

    public Graph<PointEuclidien> graph;

    public Voyage_Eucli() {
        super();
        this.graph = new Graph<>();
    }

    //getters
    public Graph<PointEuclidien> getGraph() {
        return graph;
    }
    public String getName() {
        return name;
    }
}
