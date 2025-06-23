/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

import java.util.ArrayList;

/**
 *
 * @author donat
 */
public class PointEuclidien implements Point {
    private double x, y;
    private int id;

    /**
     * 
     * @param x
     * @param y
     * @param id 
     * @brief Constructeur
     */
    public PointEuclidien(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    /**
     * 
     * @param p
     * @return double
     * @brief Calcule la distance avec un point euclidien (avec le ✨Théorème de Pythagore✨)
     */
    @Override
    public double distanceOf(Point p) {
        if (!(p instanceof PointEuclidien)) {
            return -1;
        }
        PointEuclidien pe = (PointEuclidien) p;
        return Math.sqrt(Math.pow(this.x - pe.getX(), 2) + Math.pow(this.y - pe.getY(), 2));
    }

    /**
     * 
     * @return double
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @param x 
     */
    public void setX(double x) {
        this.x = x;
    }
    
    /**
     * 
     * @return double
     */
    public double getY() {
        return y;
    }

    /**
     * 
     * @param y 
     */
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * 
     * @return int
     */
    @Override
    public int getId() {
        return id;
    }
 
    /**
     * 
     * @param id 
     * @warning A utiliser avec précautions
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PointEuclidien{" + "x=" + x + ", y=" + y + ", id=" + id + '}';
    }
    
    
    /**
     * 
     * @param points Liste de points euclidien
     * @return PointEuclidien
     * @brief renvoie le point le plus proche dans la liste
     */
    @Override
    public Point closest(ArrayList<? extends Point> points) {
        double miniDist = this.distanceOf(points.getFirst());
        Point closest = points.getFirst();
        for (Point p : points) {
            if (!(p instanceof PointEuclidien)) {
                return null;
            }
            if (miniDist > this.distanceOf(p)) {
                miniDist = this.distanceOf(p);
                closest = p;
            }
        }
        return closest;
    }
}
