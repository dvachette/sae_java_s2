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
     * @author donat
     * @brief Constructeur par défaut
     * @param id L'identifiant du point
     * @param x La coordonnée x du point
     * @param y La coordonnée y du point
     */

    public PointEuclidien(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    /**
     * @author donat
     * @brief Méthode pour calculer la distance entre deux points euclidiens
     * @param p Le point à comparer
     * @return La distance entre les deux points, ou -1 si le point n'est pas de type PointEuclidien
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
     * @author donat
     * @brief Getter pour la coordonnée x
     * @return La coordonnée x du point
     */

    public double getX() {
        return x;
    }
    /**
     * @author donat
     * @brief Setter pour la coordonnée x
     * @param x La nouvelle coordonnée x du point
     */

    public void setX(double x) {
        this.x = x;
    }
    /**
     * @author donat
     * @brief Getter pour la coordonnée y
     * @return La coordonnée y du point
     */

    public double getY() {
        return y;
    }
    /**
     * @author donat
     * @brief Setter pour la coordonnée y
     * @param y La nouvelle coordonnée y du point
     */

    public void setY(double y) {
        this.y = y;
    }
    /**
     * @author donat
     * @brief Getter pour l'identifiant du point
     * @return L'identifiant du point
     */
    
    public int getId() {
        return id;
    }
    /**
     * @author donat
     * @brief Setter pour l'identifiant du point
     * @param id Le nouvel identifiant du point
     */

    public void setId(int id) {
        this.id = id;
    }
    /**
     * @author donat
     * @brief Méthode pour obtenir une représentation textuelle du point
     * @return Une chaîne de caractères représentant le point
     */
    
    @Override
    public String toString() {
        return "PointEuclidien{" + "x=" + x + ", y=" + y + '}';
    }
    /**
     * @author donat
     * @brief Méthode pour trouver le point le plus proche dans une liste de points
     * @param points La liste de points à comparer
     * @param graph Le graphe utilisé pour obtenir les distances
     * @return Le point le plus proche de ce point
     */

    @Override
    public Point closest(ArrayList<? extends Point> points, Graph<?> graph) {
        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point point : points) {
            double distance = graph.getDistanceMatrix(this.getId() - 1, point.getId() - 1);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
        
    }
}
