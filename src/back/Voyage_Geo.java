package back;

/**
 * @author Ethan
 */

public class Voyage_Geo extends Voyage {

    public Graph<PointGeographique> graph;

    public Voyage_Geo() {
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
