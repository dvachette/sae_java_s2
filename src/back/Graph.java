/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

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

    /**
     * @author donat
     * 
     * @brief Constructeur de base, génère un graphe vide
     */
    public Graph() {
        this.points = new TreeMap<>();
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

    public void addPoint(T p) {
        points.put(p.getId(), p);
    }

    public TreeMap<Integer, Double> getDistances(T p) {
        TreeMap<Integer, Double> map = new TreeMap<>();
        for (int i : points.keySet()) {
            map.put(i, p.distanceOf(points.get(i)));
        }
        return map;
    }

    public TreeMap<Integer, Double> getDistances(int id) {
        return this.getDistances(this.getPoint(id));
    }

    public TreeMap<Integer, TreeMap<Integer, Double>> getDistancesTable() {
        TreeMap<Integer, TreeMap<Integer, Double>> map = new TreeMap<>();
        for (int i : points.keySet()) {
            map.put(i, getDistances(i));
        }
        return map;
    }
    
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
}
