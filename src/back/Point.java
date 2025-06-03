/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package back;

import java.util.ArrayList;

/**
 *
 * @author donat
 */
public interface Point {
    double distanceOf(Point p);
    int getId();
    Point closest(ArrayList<Point> points);
}
