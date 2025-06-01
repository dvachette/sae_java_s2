package back;

/**
 * @author Ethan
 */

public class VoyageGeo extends Voyage {

    public Graph<PointGeographique> graph;

    public VoyageGeo() {
        super();
        this.graph = new Graph<>();
    }

    //getters
    public Graph<PointGeographique> getGraph() {
        return graph;
    }
    public String getName() { // TODO  : mettre la methode dans la superclasse Voyage
        return name;
    }
}
