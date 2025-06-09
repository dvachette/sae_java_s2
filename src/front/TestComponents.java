/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import back.Graph;
import back.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author donat
 */
public class TestComponents extends JFrame {
    public TestComponents(Graph<? extends Point> g) {
        this.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel();
        var dtm = new DistanceTableModel(g);
        var dt = new JTable(dtm);
        var jsp = new JScrollPane(dt);
        
        mainPanel.add(jsp);
        this.setContentPane(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
