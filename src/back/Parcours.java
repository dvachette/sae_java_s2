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
@SuppressWarnings("rawtypes")

    /**
     * @author Ethan
     * @param g
     * @param start
     * @return 
     */
    public static Parcours parcoursGlouton( Graph g, Point start) {
        @SuppressWarnings("unchecked")
        TreeMap<Integer, Point> points = g.getPoints();
        ArrayList<Point> pool = new ArrayList<>(points.values());
        ArrayList<Point> path = new ArrayList<>();
        Point current = start;
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

@SuppressWarnings("rawtypes") 
    public static Parcours parcoursAleatoire(Graph g) {
        @SuppressWarnings("unchecked")
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

    /**
     * @author Ethan
     * @param g
     * @param start
     * @return 
     */

@SuppressWarnings("rawtypes") 

    public static Parcours parcoursInsertion(Graph g, Point start) {
        @SuppressWarnings("unchecked")
        TreeMap<Integer, Point> points = g.getPoints();
        ArrayList<Point> pool = new ArrayList<>(points.values());
        ArrayList<Point> path = new ArrayList<>();
        Point current = start;
        path.add(current);
        double length = 0;

        while (!pool.isEmpty()) {
            double minDistance = Double.MAX_VALUE;
            Point nextPoint = null;
            int insertIndex = -1;

            for (int i = 0; i < path.size(); i++) {
                Point p1 = path.get(i);
                Point p2 = (i == path.size() - 1) ? path.get(0) : path.get(i + 1);
                for (Point candidate : pool) {
                    double distance = p1.distanceOf(candidate) + candidate.distanceOf(p2) - p1.distanceOf(p2);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nextPoint = candidate;
                        insertIndex = i + 1;
                    }
                }
            }

            if (nextPoint != null) {
                length += minDistance;
                path.add(insertIndex, nextPoint);
                pool.remove(nextPoint);
            }
        }
        length += path.getLast().distanceOf(path.getFirst()); // Return to start
        return new Parcours(length, path);
    }

    /**
     * @author Ethan
     * @param g
     * @param start
     * @return 
     */

    public static Parcours MeilleurGlouton(Graph<Point> g, Point start) {
        Parcours best = parcoursGlouton(g, start);
        for (Point p : g.getPoints().values()) {
            Parcours current = parcoursGlouton(g, p);
            if (current.getLength() < best.getLength()) {
                best = current;
            }
        }
        return best;
    }

    /**
     * @Author Ethan
     * @param g
     * @return
     */

     public static Parcours MeilleurInsertion(Graph<Point> g, Point start) {
        Parcours best = parcoursInsertion(g, start);
        for (Point p : g.getPoints().values()) {
            Parcours current = parcoursInsertion(g, p);
            if (current.getLength() < best.getLength()) {
                best = current;
            }
        }
        return best;
     }

    /**
        * @author Ethan 
        * @param g
        * @return
    */

    public static Parcours MeilleurAll(Graph<Point> g){
        Random rng = new Random();
        Point start = g.getPoints().get(rng.nextInt(0, g.getPoints().size()));
        Parcours best = MeilleurInsertion(g, start);
        Parcours current = MeilleurGlouton(g, start);
        if (current.getLength() < best.getLength()) {
            best = current;
        }
        return best;
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
