/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package waypoints;

import back.PointGeographique;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;

/**
 *
 * @author donat
 */
public class CustomWaypoint extends DefaultWaypoint{
    private PointGeographique point;
    private JButton button;

    public CustomWaypoint(PointGeographique point) {
        super(point.getLatitude(), point.getLongitude());
        this.point = point;
        initButton();
    }

    private void initButton() {
        button = new ButtonWaypoint();
        button.addActionListener((e) -> {
            System.out.println(point + " pressed !");
        });
    }
    
    
    public PointGeographique getPoint() {
        return point;
    }

    public void setPoint(PointGeographique point) {
        this.point = point;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
    
    
}
