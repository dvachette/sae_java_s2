/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import back.Graph;
import back.Point;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
        mainPanel.setBackground(Color.red);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        GMapEucli map = new GMapEucli();
        map.setMap(g);
        //var jsp = DistanceTable.table(g);
        
        gc.gridx = 0; gc.gridy = 0;
        // mainPanel.add(jsp, gc);
        
        gc.gridx = 1;
        mainPanel.add(map, gc);
        this.setContentPane(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
