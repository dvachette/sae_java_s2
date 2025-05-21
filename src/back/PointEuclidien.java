/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author donat
 */
public class PointEuclidien {
    private double x, y;
    
    public PointEuclidien(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public PointEuclidien() {
        this.x = 0;
        this.y = 0;
    }
    
    public double distanceOf(PointEuclidien p) {
        return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
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

    @Override
    public String toString() {
        return "PointEuclidien{" + "x=" + x + ", y=" + y + '}';
    }
    
    
    
}
