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
 * @param <T>
 * 
 */
public class Parcours<T extends Point> {

    private double length;
    private ArrayList<T> path;
    public Graph<T> graph;
    private static Parcours<?> meilleurGlouton;
    private static Parcours<?> meilleurInsertion;

    public Parcours(double length, ArrayList<T> path) {
        this.length = length;
        this.path = path;
        meilleurGlouton = null;
        meilleurInsertion = null;
    }

    public void setGraph(Graph<T> graph) {
        this.graph = graph;
    }

    public Graph<T> getGraph() {
        return graph;
    }


@SuppressWarnings({ "rawtypes", "unchecked" })

    /**
     * @author Ethan
     * @param g
     * @param start
     * @return 
     */
    public static Parcours parcoursGlouton( Graph g, Point start) {
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

@SuppressWarnings({ "rawtypes", "unchecked" }) 
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

    /**
     * @author Ethan
     * @param g
     * @param start
     * @return 
     */

@SuppressWarnings({ "rawtypes", "unchecked" }) 

    public static Parcours parcoursInsertion(Graph g, Point start) {
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

    @SuppressWarnings("unchecked")
    public static <T extends Point> Parcours<T> MeilleurGlouton(Graph<T> g, T start) {
        Parcours<T> best = (Parcours<T>) parcoursGlouton(g, start);
        for (T p : g.getPoints().values()) {
            Parcours<T> current = (Parcours<T>) parcoursGlouton(g, p);
            if (current.getLength() < best.getLength()) {
                best = current;
            }
        }
        meilleurGlouton = best;
        return best;
    }

    /**
     * @Author Ethan
     * @param g
     * @return
     */

    @SuppressWarnings("unchecked")
    public static <T extends Point> Parcours<T> MeilleurInsertion(Graph<T> g, T start) {
        Parcours<T> best = (Parcours<T>) parcoursInsertion(g, start);
        for (T p : g.getPoints().values()) {
            Parcours<T> current = (Parcours<T>) parcoursInsertion(g, p);
            if (current.getLength() < best.getLength()) {
                best = current;
            }
        }
        meilleurInsertion = best;
        return best;
    }

    /**
        * @author Ethan 
        * @param g
        * @return
    */

    public static <T extends Point> Parcours<T> MeilleurAll(Graph<T> g, T start) {
        @SuppressWarnings("unchecked")
        Parcours<T> glouton = (Parcours<T>) meilleurGlouton;
        @SuppressWarnings("unchecked")
        Parcours<T> insertion = (Parcours<T>) meilleurInsertion;

        if (glouton == null) {
            glouton = MeilleurGlouton(g, start);
            meilleurGlouton = glouton;
        }
        if (insertion == null) {
            insertion = MeilleurInsertion(g, start);
            meilleurInsertion = insertion;
        }

        // Compare les deux et retourne le meilleur
        return (glouton.getLength() < insertion.getLength()) ? glouton : insertion;
    }


    public double getLength() {
        return length;
    }

    public ArrayList<T> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Parcours{" + "length=" + length + ", path=" + path + '}';
    }
}
