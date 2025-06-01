package back;

/**
 * @author Ethan
 */

public class VoyageEucli extends Voyage {

    public Graph<PointEuclidien> graph;

    public VoyageEucli() {
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
