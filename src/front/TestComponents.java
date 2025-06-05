/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import back.Graph;
import back.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author donat
 */
public class TestComponents extends JFrame {
    public TestComponents(Graph<Point> g) {
        this.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        DistanceTable dt = new DistanceTable(g);
        mainPanel.add(dt);
        this.setContentPane(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
