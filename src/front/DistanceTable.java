/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import javax.swing.JComponent;

import java.awt.event.ActionListener;

import back.Graph;
import back.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author donat
 */
public class DistanceTable extends JComponent {
    private Graph<? extends Point> graph;
    private JScrollPane scrollContainer;
    private DistanceTableModel tableModel;
    private JTable table;
    
    public DistanceTable(Graph<? extends Point> graph) {
        this.graph = graph;
        this.tableModel = new DistanceTableModel(graph);
        this.repaint();
        }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.table = new JTable(this.tableModel);
        System.out.println(this.table);
        this.add(this.table);
        Graphics2D gra2d = (Graphics2D) g;
        gra2d.setColor(Color.red);
        gra2d.fillRect(10, 10, 100, 100);
    }
}
