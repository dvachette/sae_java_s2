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

    /**
     * 
     * @param length
     * @param path 
     * @brief Constructeur par défaut
     */
    public Parcours(double length, ArrayList<T> path) {
        this.length = length;
        this.path = path;
    }

    /**
     * @author Ethan
     * @param g
     * @param start
     * @return Parcours glouton [AUSSI IMPLEMENTÉ DANS GRAPH]
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
     * @return Parcours
     * @brief Renvoie un parcours aléatoire [AUSSI IMPLEMENTÉ DANS GRAPH]
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

    /**
     * @author Ethan
     * @param g
     * @param start
     * @return Parcours
     * @brief parcours par insertion [AUSSI IMPLEMENTÉ DANS GRAPH]
     */


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
     * @return Parcours
     * @brief meilleur parcours glouton, teste tous les départs [AUSSI IMPLEMENTÉ DANS GRAPH]
     */

    public static Parcours MeilleurGlouton(Graph<? extends Point> g, Point start) {
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
     * @return Parcours
     * @brief meilleur parcours séléction, teste tous les départs [AUSSI IMPLEMENTÉ DANS GRAPH]
     */

    public static Parcours MeilleurInsertion(Graph<? extends Point> g, Point start) {
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
        * @return Parcours
        * @brief renvoie le meilleur parcours entre le meilleur par insertion et par sélection
    */

    public static Parcours MeilleurAll(Graph<? extends Point> g){
        Random rng = new Random();
        Point start = g.getPoints().get(rng.nextInt(0, g.getPoints().size()));
        //Parcours best = MeilleurInsertion(g, start);
        Parcours best = g.parcoursInsertion();
        Parcours current = g.parcoursGlouton();
        if (current.getLength() < best.getLength()) {
            best = current;
        }
        return best;
    }

    /**
     * 
     * @return double
     * @brief renvoie la longueur du parcours
     */
    public double getLength() {
        return length;
    }
    
    /**
     * 
     * @return ArrayList
     * @brief Renvoie la liste des points ordonées dans l'ordre de parcours
     */
    public ArrayList<T> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Parcours{" + "length=" + length + ", path=" + path + '}';
    }
}
