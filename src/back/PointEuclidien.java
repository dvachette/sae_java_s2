/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author donat
 */
public class PointEuclidien implements Point {
    private double x, y;
    private int id;

    public PointEuclidien(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    @Override
    public double distanceOf(Point p) {
        if (!(p instanceof PointEuclidien)) {
            return -1;
        }
        PointEuclidien pe = (PointEuclidien) p;
        return Math.sqrt(Math.pow(this.x - pe.getX(), 2) + Math.pow(this.y - pe.getY(), 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "PointEuclidien{" + "x=" + x + ", y=" + y + '}';
    }
    
    
    
}
