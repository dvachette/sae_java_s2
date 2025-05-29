package sae_g3s2e2;

import back.*;

/**
 *
 * @author donat
 */
public class SAE_G3S2E2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph<PointEuclidien> graph = Graph.randomPointSet(10);
        System.out.println(graph.getPoint(2));
        System.out.println(graph.getPoint(4));
        System.out.println(graph.getDistances(2));

        //test voyage
        VoyageFactory voyageFactory = new VoyageFactory("sae_java_s2\\User_File\\test8.txt");
        Voyage voyage = voyageFactory.createVoyage();
        if (voyage instanceof Voyage_Eucli) {
            Voyage_Eucli voyageEucli = (Voyage_Eucli) voyage;
            System.out.println("Voyage Euclidien: " + voyageEucli.getName());
            System.out.println("Graph: " + voyageEucli.getGraph());
        } else if (voyage instanceof Voyage_Geo) {
            Voyage_Geo voyageGeo = (Voyage_Geo) voyage;
            System.out.println("Voyage Geographique: " + voyageGeo.getName());
            System.out.println("Graph: " + voyageGeo.getGraph());
        } else {
            System.out.println("Type de voyage inconnu.");
        }
        


    }

}
