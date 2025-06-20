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
public class PointGeographique implements Point {
    private static final double EARTH_RADIUS = 6378; 
    private int id;
    private double latitude, longitude;

    /**
     * @author donat
     * @brief Constructeur par défaut
     * @param latitude La latitude du point géographique
     * @param longitude La longitude du point géographique
     * @param id L'identifiant du point géographique
     */

    public PointGeographique(double latitude, double longitude, int id) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    /**
     * @author donat
     * @brief getter pour l'identifiant du point géographique
     * @return L'identifiant du point géographique
     */

    public int getId() {
        return id;
    }

    /**
     * @author donat
     * @brief Setter pour l'identifiant du point géographique
     * @param id L'identifiant du point géographique
     */

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PointGeographique{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    @Override
    public double distanceOf(Point p) {
        if (!(p instanceof PointGeographique)) {
            return -1;
        }
        PointGeographique pg = (PointGeographique) p;
        
        Double otherLatitude = PointGeographique.degMinutesToRad(pg.getLatitude());
        Double otherLongitude = PointGeographique.degMinutesToRad(pg.getLongitude());
        Double selfLatitude = PointGeographique.degMinutesToRad(latitude);
        Double selfLongitude = PointGeographique.degMinutesToRad(longitude);
        
        // Le signe est perdu dans la conversion, on le rétablit ici
        otherLatitude = otherLatitude * (pg.getLatitude() >= 0 ? 1: -1);
        otherLongitude = otherLongitude * (pg.getLongitude() >= 0 ? 1: -1);
        selfLatitude = selfLatitude * (latitude >= 0 ? 1: -1);
        selfLatitude = selfLatitude * (longitude >= 0 ? 1: -1);
        
        return EARTH_RADIUS * Math.acos(
            (Math.sin(otherLatitude)*Math.sin(selfLatitude))+
            (Math.cos(otherLatitude)*Math.cos(selfLatitude)*
            Math.cos(otherLongitude - selfLongitude)));
        
    };

    private static Double degMinutesToRad(Double val) {
        String[] strVal = val.toString().split("\\.");
        Double valDec = Math.abs(Double.parseDouble(strVal[0])) + (Double.parseDouble(strVal[1]) / 60);
        return Math.PI * valDec/180;
    }
    
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
