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

    public Parcours(double length, ArrayList<T> path) {
        this.length = length;
        this.path = path;
    }
    
    public double getLength() {
        return length;
    }

    public ArrayList<T> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Parcours{" + "length=" + length + ", path=" + path + '}';
    }

}
