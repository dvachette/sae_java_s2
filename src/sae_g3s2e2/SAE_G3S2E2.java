/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
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
        PointEuclidien p1 = new PointEuclidien(0, 0, 1);
        PointEuclidien p2 = new PointEuclidien(4, 3, 2);
        PointEuclidien p3 = new PointEuclidien(1, 1, 3);
        PointEuclidien p4 = new PointEuclidien(0, -3, 4);
        PointEuclidien p5 = new PointEuclidien(-40, 30, 5);
        
        GraphEuclidien graph = new GraphEuclidien();
        graph.addPoint(p1);
        graph.addPoint(p2);
        graph.addPoint(p3);
        graph.addPoint(p4);
        graph.addPoint(p5);
        
        System.out.println(graph.getDistancesTable());
    }
    
}
