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
        long startTime = System.currentTimeMillis();
        String filePath = "sae_java_s2/User_File/test1.txt";
        //test export
        Voyage voyage = new Voyage("Test Voyage", "TSP", "Test Comment", 10, "EUC_2D", "Display Type", "EUC_2D");
        Graph<PointEuclidien> voyageGraph = Graph.randomPointSet(600);
        Parcours<PointEuclidien> parcours = voyageGraph.parcoursGlouton();
        voyageGraph.getPoint(0);
        voyage.setParcours(parcours);
        parcours.setGraph(voyageGraph);
        filePath = "sae_java_s2/User_File/test_voyage.txt";
        voyage.exportToFile(filePath, parcours);
        System.out.println("Voyage exported to " + filePath);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + " milliseconds");
    }
}
