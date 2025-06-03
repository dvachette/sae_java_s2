/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author donat
 */
public class Parcours {

    private double length;
    private ArrayList<Point> path;

    public Parcours(double length, ArrayList<Point> path) {
        this.length = length;
        this.path = path;
    }

    public static Parcours parcoursGlouton(Graph g) {
        TreeMap<Integer, Point> points = g.getPoints();
        ArrayList<Point> pool = new ArrayList<>(points.values());
        ArrayList<Point> path = new ArrayList<>();
        Random rng = new Random();
        Point current = pool.remove(rng.nextInt(pool.size()));
        path.add(current);
        double length = 0;
        Point nextPoint;
        while (!pool.isEmpty()) { 
            nextPoint = current.closest(pool);
            length += nextPoint.distanceOf(current);
            path.add(nextPoint);
            pool.remove(nextPoint);
            current = nextPoint;
        }
        length += current.distanceOf(path.get(0)); // Return to start
        return new Parcours(length, path);
    }
    /**
     * @author donat
     * @param g
     * @return 
     */
    public static Parcours parcoursAleatoire(Graph g) {
        TreeMap<Integer, Point> points = g.getPoints();
        ArrayList<Point> pool = new ArrayList<>(points.values());
        Random rng = new Random();
        ArrayList<Point> path = new ArrayList<>();
        int size = pool.size();
        for (int i = size; i > 0; i++) {
            path.add(pool.get(rng.nextInt(0, i)));
        }
        double length = path.getLast().distanceOf(path.getFirst());
        for (int i = 0; i < size - 1; i++) {
            length += path.get(i).distanceOf(path.get(i + 1));
        }
        return new Parcours(length, path);
    }

    public static Parcours parcoursInsertion(Graph g) {
        return null;
    }

    public double getLength() {
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
