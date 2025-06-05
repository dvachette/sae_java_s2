package sae_g3s2e2;

import back.*;
import front.TestComponents;
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
        Graph<PointEuclidien> graph = Graph.randomPointSet(10);
        System.out.println(graph.getPoint(2));
        System.out.println(graph.getPoint(4));
        System.out.println(graph.getDistances(2));
        // Test plus proche point
        
        PointEuclidien pe1 = new PointEuclidien(0, 0, 0);
        PointEuclidien pe2 = new PointEuclidien(2, 1, 1);
        PointEuclidien pe3 = new PointEuclidien(3, 0, 2);
        
        Graph<Point> g2 = new Graph<Point>();
        g2.addPoint(pe1);
        g2.addPoint(pe2);
        g2.addPoint(pe3);
        
        ArrayList<Point> points = new ArrayList<>();
        points.add(pe3);
        points.add(pe2);
        System.out.println(pe1.closest(points));
        
        //test voyage
        VoyageFactory voyageFactory = new VoyageFactory("User_File/test8.txt");
        Voyage voyage = voyageFactory.createVoyage();
        if (voyage instanceof VoyageEucli) {
            VoyageEucli voyageEucli = (VoyageEucli) voyage;
            System.out.println("Voyage Euclidien: " + voyageEucli.getName());
            System.out.println("Graph: " + voyageEucli.getGraph());
        } else if (voyage instanceof VoyageGeo) {
            VoyageGeo voyageGeo = (VoyageGeo) voyage;
            System.out.println("Voyage Geographique: " + voyageGeo.getName());
            System.out.println("Graph: " + voyageGeo.getGraph());
        } else {
            System.out.println("Type de voyage inconnu.");
        }
        
        
        new TestComponents(g2);


    }

}
