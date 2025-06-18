package sae_g3s2e2;

import back.*;
import java.util.ArrayList;

/**
 *
 * @author donat
 */
public class SAE_G3S2E2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filePath = "sae_java_s2/User_File/test1.txt";
        // Test lecture de fichier
        Graph<PointEuclidien> graph = Graph.randomPointSet(10);
        System.out.println(graph.getPoint(2));
        System.out.println(graph.getPoint(4));
        System.out.println(graph.getDistances(2));
        // Test plus proche point
        
        PointEuclidien pe1 = new PointEuclidien(0, 0, 0);
        PointEuclidien pe2 = new PointEuclidien(2, 1, 1);
        PointEuclidien pe3 = new PointEuclidien(3, 0, 2);
        
        ArrayList<Point> points = new ArrayList<>();
        points.add(pe3);
        points.add(pe2);
        System.out.println(pe1.closest(points));
        
        //test export
        Voyage voyage = new Voyage("Test Voyage", "TSP", "Test Comment", 10, "EUC_2D", "Display Type", "EUC_2D");
        Graph<PointEuclidien> voyageGraph = Graph.randomPointSet(100);
        Parcours<PointEuclidien> parcours = voyageGraph.parcoursGlouton();
        voyageGraph.getPoint(0);
        voyage.setParcours(parcours);
        parcours.setGraph(voyageGraph);
        filePath = "sae_java_s2/User_File/test_voyage.txt";
        voyage.exportToFile(filePath, parcours);
        System.out.println("Voyage exported to " + filePath);
    }
}
