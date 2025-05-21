/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back;

/**
 *
 * @author donat
 */
public class LinkEuclidien {
    private PointEuclidien point1, point2;

    public LinkEuclidien(PointEuclidien point1, PointEuclidien point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public PointEuclidien getPoint1() {
        return point1;
    }

    public void setPoint1(PointEuclidien point1) {
        this.point1 = point1;
    }

    public PointEuclidien getPoint2() {
        return point2;
    }

    public void setPoint2(PointEuclidien point2) {
        this.point2 = point2;
    }

    @Override
    public String toString() {
        return "LinkEuclidien{" + "point1=" + point1 + ", point2=" + point2 + '}';
    }
    
    public double length() {
        return point1.distanceOf(point2);
    }
    

}
