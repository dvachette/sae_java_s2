/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package waypoints;

import back.Parcours;
import back.PointGeographique;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Objects;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author donat
 */
public class WaypointRender extends WaypointPainter<CustomWaypoint> {

    private Parcours<PointGeographique> parcours = null;

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        for (CustomWaypoint wp : getWaypoints()) {
            Point2D p = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
            Rectangle rec = map.getViewportBounds();
            int x = (int) (p.getX() - rec.getX());
            int y = (int) (p.getY() - rec.getY());
            g.setColor(Color.BLUE);
            g.fillOval(x - 3, y - 3, 6, 6);
        }
        if (!Objects.equals(parcours, null)) {
            Rectangle rec = map.getViewportBounds();

            Point2D origin = map.getTileFactory().geoToPixel(new GeoPosition(parcours.getPath().getFirst().getLatitude(), parcours.getPath().getFirst().getLongitude()), map.getZoom());
            Point2D next;
            int xorigin = (int) (origin.getX() - rec.getX());
            int yorigin = (int) (origin.getY() - rec.getY());

            int xnext;
            int ynext;
            g.setColor(Color.BLACK);

            for (int i = 1; i < parcours.getPath().size(); i++) {
                next = map.getTileFactory().geoToPixel(new GeoPosition(parcours.getPath().get(i).getLatitude(), parcours.getPath().get(i).getLongitude()), map.getZoom());
                xnext = (int) (next.getX() - rec.getX());
                ynext = (int) (next.getY() - rec.getY());

                g.drawLine(xorigin, yorigin, xnext, ynext);

                origin = next;
                xorigin = xnext;
                yorigin = ynext;
            }

            // Get back to start
            next = map.getTileFactory().geoToPixel(new GeoPosition(parcours.getPath().get(0).getLatitude(), parcours.getPath().get(0).getLongitude()), map.getZoom());
            xnext = (int) (next.getX() - rec.getX());
            ynext = (int) (next.getY() - rec.getY());

            g.drawLine(xorigin, yorigin, xnext, ynext);

        }
    }

    public void setParcours(Parcours<PointGeographique> p) {
        this.parcours = p;
    }

    public Parcours<PointGeographique> getParcours() {
        return this.parcours;
    }

    public void resetParcours() {
        this.parcours = null;
    }

}
