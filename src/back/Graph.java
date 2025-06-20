/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author donat
 * @param <T>
 * 
 * @brief Classe représentant un graphe
 * 
 */
public class Graph<T extends Point> {

    private TreeMap<Integer, T> points;
    public double[][] distance_Matrix;

    /**
     * @author donat
     * 
     * @brief Constructeur de base, génère un graphe vide
     */
    public Graph() {
        this.points = new TreeMap<>();
        createMatrix();
    }

    
    /**
     * @author donat
     * 
     * @return Renvoie la map des points, indexés par leurs ID
     */
    public TreeMap<Integer, T> getPoints() {
        return points;
    }

    /**
     * @author Ethan
     * @brief Crée une demi matrice de distance entre les points du graphe, puis copie la symétrie
     * @warning Cette méthode suppose que les points sont indexés de 1 à n, que les ids sont consécutifs et que la matrice voulu est symétrique.
     * 
     */

    public void createMatrix() {
        int size = points.size();
        distance_Matrix = new double[size][size];
        // On suppose que les IDs vont de 1 à size
        for (int i = 1; i <= size; i++) {
            T point1 = points.get(i);
            for (int j = i; j <= size; j++) { // Commence à i pour ne faire qu'une moitié
                T point2 = points.get(j);
                double dist = point1.distanceOf(point2);
                distance_Matrix[i - 1][j - 1] = dist;
                distance_Matrix[j - 1][i - 1] = dist; // Symétrie
            }
        }
    }

    /**
     * @author ethan
     * 
     * @brief Met à jour la matrice de distance en recalculant les distances entre tous les points
     * 
     * @warning Cette méthode suppose que les points sont indexés de 1 à n, que les ids sont consécutifs et que la matrice voulu est symétrique.
     */

    public void updateMatrix() {
        if (distance_Matrix == null || distance_Matrix.length != points.size()) {
            createMatrix();
        } else {
            for (int i = 0; i < distance_Matrix.length; i++) {
                for (int j = i; j < distance_Matrix[i].length; j++) {
                    distance_Matrix[i][j] = points.get(i + 1).distanceOf(points.get(j + 1));
                    distance_Matrix[j][i] = distance_Matrix[i][j]; // Symétrie
                }
            }
        }
    }

    /**
     * @author donat
     * 
     * @param points Map de points à passer
     * 
     * @warning À utiliser avec précautions
     */
    public void setPoints(TreeMap<Integer, T> points) {
        this.points = points;
    }

    /**
     * @author donat
     * 
     * @param id L'ID du point désiré
     * @return Point (T)
     */
    public T getPoint(int id) {
        return points.get(id);
    }

    /**
     * @author donat
     * 
     * @param x_or_latitude X ou latitude du point désiré
     * @param y_or_longitude Y ou longitude du point désiré
     * @return Point (T)
     * 
     * @brief Renvoie le point correspondant aux coordonnées passées en paramètre
     */

    public T getPoint(double x_or_latitude, double y_or_longitude) {
        // I know this code is horible but i can't figure a better way...
        T ans = null;
        for (T point : points.values()) {
            if (point instanceof PointEuclidien) {
                PointEuclidien pe = (PointEuclidien) point;
                if ((pe.getX() == x_or_latitude) && (pe.getY() == y_or_longitude)) {
                    ans = point;
                }
            } else if (point instanceof PointGeographique) {
                PointGeographique pg = (PointGeographique) point;
                if ((pg.getLatitude() == x_or_latitude) && (pg.getLongitude() == y_or_longitude)) {
                    ans = point;
                }
            }
        }

        return ans;
    }

    /**
     * @author donat
     * @param p point à ajouter
     * @brief Ajoute un point au graphe
     */
    public void addPoint(T p) {
        points.put(p.getId(), p);
    }

    /**
     * @author ethan
     * @brief Renvoie la distance entre deux points du graphe, en utilisant la matrice de distance
     * @param id1 point 1
     * @param id2 point 2
     * @return distance entre les deux points (double)
     */

    public double getDistanceMatrix(int id1, int id2) {
        return distance_Matrix[id1][id2];
    }

    /**
     * @author donat
     * @brief Renvoie la distance entre deux points du graphe, en utilisant la méthode distanceOf de Point
     * @param id1 point 1
     * @param id2 point 2
     * @return distance entre les deux points (double)
     */

    public TreeMap<Integer, Double> getDistances(T p) {
        TreeMap<Integer, Double> map = new TreeMap<>();
        for (int i : points.keySet()) {
            map.put(i, p.distanceOf(points.get(i)));
        }
        return map;
    }

    /**
     * @author donat
     * @brief Renvoie la distance entre un point du graphe et tous les autres points, en utilisant la méthode distanceOf de Point
     * @param id ID du point
     * @return Map des distances (ID, distance)
     */

    public TreeMap<Integer, Double> getDistances(int id) {
        return this.getDistances(this.getPoint(id));
    }

    /**
     * @author donat
     * @brief Renvoie une table de distances entre tous les points du graphe, en utilisant la méthode distanceOf de Point
     * @return Table de distances (ID, Map(ID, distance))
     */

    public TreeMap<Integer, TreeMap<Integer, Double>> getDistancesTable() {
        TreeMap<Integer, TreeMap<Integer, Double>> map = new TreeMap<>();
        for (int i : points.keySet()) {
            map.put(i, getDistances(i));
        }
        return map;
    }

    /**
     * @author donat
     * @brief Renvoie un graphe aléatoire de points euclidiens
     * @param count Nombre de points à générer
     * @param minX Valeur minimale de X
     * @param maxX Valeur maximale de X
     * @param minY Valeur minimale de Y
     * @param maxY Valeur maximale de Y
     * @return Graph<PointEuclidien>
     */
    
    public static Graph<PointEuclidien> randomPointSet(int count, double minX, double maxX, double minY, double maxY) {
        Graph<PointEuclidien> graph = new Graph<>();
        Random rng = new Random();
        for (int c = 1; c <= count; c++) { // Here is C++ in JAVA ;)
            graph.addPoint(new PointEuclidien(rng.nextDouble(minX, maxX), rng.nextDouble(minY, maxY), c));
        }
        return graph;
    }
    
    public static Graph<PointEuclidien> randomPointSet(int count) {
        return Graph.randomPointSet(count, 0, 100, 0, 100);
    }
    
    public int maxIdValue() {
        return Collections.max(points.keySet());
    }

    /**
     * @author donat
     * @brief Parcours glouton, part d'un point aléatoire et ajoute le point le plus proche à chaque étape
     * @return Parcours<T>
     */
    
    @SuppressWarnings("unchecked")
    public Parcours<T> parcoursGlouton() {
        ArrayList<T> pool = new ArrayList<>(points.values());
        ArrayList<T> path = new ArrayList<>();
        if (distance_Matrix == null || distance_Matrix.length == 0) {
            createMatrix();
        }
        
        Random rng = new Random();
        T current = pool.remove(rng.nextInt(pool.size()));
        path.add(current);
        double length = 0;
        T nextPoint;
        
        while (!pool.isEmpty()) { 
            nextPoint = (T) current.closest(pool, this);
            length += nextPoint.distanceOf(current);
            path.add(nextPoint);
            pool.remove(nextPoint);
            current = nextPoint;
        }
        length += current.distanceOf(path.get(0)); // Return to start
        return new Parcours<>(length, path);
    }

    /**
     * @author donat
     * @brief Parcours aléatoire, prend un point au hasard et ajoute les autres points dans un ordre aléatoire
     * @return Parcours<T>
     */
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Parcours<T> parcoursAleatoire() {
        ArrayList<T> pool = new ArrayList<>(points.values());
        Random rng = new Random();
        ArrayList<T> path = new ArrayList<>();
        int size = pool.size();
        for (int i = size; i > 0; i--) {
            path.add(pool.get(rng.nextInt(0, i)));
        }
        double length = path.getLast().distanceOf(path.getFirst());
        for (int i = 0; i < size - 1; i++) {
            length += path.get(i).distanceOf(path.get(i + 1));
        }
        return new Parcours(length, path);
    }

    /**
     * @author donat
     * @brief Parcours par insertion, part d'un point aléatoire et insère les autres points de manière à minimiser la distance totale
     * @return Parcours<T>
     */
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Parcours<T> parcoursInsertion() {
        ArrayList<T> pool = new ArrayList<>(points.values());
        ArrayList<T> path = new ArrayList<>();
        Random rng = new Random();
        T current = pool.remove(rng.nextInt(pool.size()));
        path.add(current);
        double length = 0;

        while (!pool.isEmpty()) {
            double minDistance = Double.MAX_VALUE;
            T nextPoint = null;
            int insertIndex = -1;

            for (int i = 0; i < path.size(); i++) {
                T p1 = path.get(i);
                T p2 = (i == path.size() - 1) ? path.get(0) : path.get(i + 1);
                for (T candidate : pool) {
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
     * @author donat
     * @brief Renvoie une représentation textuelle du graphe
     * @return String
     */

    @Override
    public String toString() {
        return "Graph{" + "points=" + points + '}';
    }
    

}
