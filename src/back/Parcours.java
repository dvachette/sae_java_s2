/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author donat
 */
public class Parcours {
    private float length;
    private ArrayList<Point> path;

    public Parcours(float length, ArrayList<Point> path) {
        this.length = length;
        this.path = path;
    }
    
    public static Parcours parcoursGlouton(Graph g) {
        return null;
    }
    
    public static Parcours parcoursAleatoire(Graph g) {
        TreeMap<Integer, Point> points = g.getPoints();
        
    }
    
    public static Parcours parcoursInsertion(Graph g) {
        return null;
    }
    
    

    public float getLength() {
        return length;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Parcours{" + "length=" + length + ", path=" + path + '}';
    }
    
    
}
