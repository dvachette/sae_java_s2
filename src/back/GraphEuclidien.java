/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.util.TreeMap;


/**
 *
 * @author donat
 */
public class GraphEuclidien {

    private TreeMap<Integer, PointEuclidien> points;

    public GraphEuclidien() {
        this.points = new TreeMap<>();
    }

    public TreeMap<Integer, PointEuclidien> getPoints() {
        return points;
    }

    public void setPoints(TreeMap<Integer, PointEuclidien> points) {
        this.points = points;
    }

    public PointEuclidien getPoint(int id) {
        return points.get(id);
    }

    public PointEuclidien getPoint(double x, double y) {
        PointEuclidien ans = null;
        for (PointEuclidien point : points.values()) {
            if ((point.getX() == x) && (point.getY() == y)) {
                ans = point;
            }
        }
        return ans;
    }

    public void addPoint(PointEuclidien p) {
        points.put(p.getId(), p);
    }

    public void addPoint(double x, double y, int id) {
        points.put(id, new PointEuclidien(x, y, id));
    }

    
    public TreeMap<Integer, Double> getDistances(PointEuclidien p) {
        TreeMap<Integer, Double> map = new TreeMap<Integer, Double>();
        for (int i : points.keySet()) {
            map.put(i, p.distanceOf(points.get(i)));
        }
        return map;
    }
    
    public TreeMap<Integer, Double> getDistances(int id) {
        return this.getDistances(this.getPoint(id));
    }
        
        
    public TreeMap<Integer, TreeMap<Integer, Double>> getDistancesTable() {
        TreeMap<Integer, TreeMap<Integer, Double>> map = new TreeMap<Integer, TreeMap<Integer, Double>>();
        for (int i : points.keySet()) {
            map.put(i, getDistances(i));
        }
        return map;
    }
    
    
    

}
