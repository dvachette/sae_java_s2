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
        Graph<PointEuclidien> graph = Graph.randomPointSet(10);
        System.out.println(graph.getPoint(2));
        System.out.println(graph.getPoint(4));
        System.out.println(graph.getDistances(2));
        


    }

}
