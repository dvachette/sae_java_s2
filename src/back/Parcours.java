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

    /**
     * @author donat
     * @param length
     * @param path
     */

    public Parcours(double length, ArrayList<T> path) {
        this.length = length;
        this.path = path;
        meilleurGlouton = null;
        meilleurInsertion = null;
    }

    /**
     * @author donat
     * @param graph
     */

    public void setGraph(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     * @author donat
     * @return Graph<T>
     */

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
    int n = g.getPoints().size();
    boolean[] visited = new boolean[n];
    ArrayList<Point> points = new ArrayList<>(g.getPoints().values());
    ArrayList<Point> path = new ArrayList<>(n);
    Point current = start;
    path.add(current);
    visited[current.getId() - 1] = true;
    double length = 0;

    for (int step = 1; step < n; step++) {
        double minDist = Double.MAX_VALUE;
        int nextIdx = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                double dist = g.getDistanceMatrix(current.getId() - 1, i);
                if (dist < minDist) {
                    minDist = dist;
                    nextIdx = i;
                }
            }
        }
        visited[nextIdx] = true;
        Point next = points.get(nextIdx);
        length += minDist;
        path.add(next);
        current = next;
    }
    length += g.getDistanceMatrix(current.getId() - 1, start.getId() - 1);
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
                    double distance = g.getDistanceMatrix(p1.getId() - 1, candidate.getId() - 1) + g.getDistanceMatrix(candidate.getId() - 1, p2.getId() - 1) - g.getDistanceMatrix(p1.getId() - 1, p2.getId() - 1);
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
        length += g.getDistanceMatrix(path.getLast().getId() - 1, path.getFirst().getId() - 1 ); // Return to start
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

    /**
     * @author donat
     * 
     * @return double représentant la longueur du parcours
     */

    public double getLength() {
        return length;
    }

    /**
     * @author donat
     * 
     * @return ArrayList<T> représentant le parcours
     */

    public ArrayList<T> getPath() {
        return path;
    }

    /**
     * @author donat
     * 
     * @return Parcours<T> représentant le parcours
     */

    @Override
    public String toString() {
        return "Parcours{" + "length=" + length + ", path=" + path + '}';
    }
}
