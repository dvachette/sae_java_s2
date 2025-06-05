/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import back.Graph;
import back.Point;
import java.util.Iterator;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author donat
 */
public class DistanceTableModel extends AbstractTableModel {
    
    private Graph<Point> graph;
    private TreeMap<Integer, TreeMap<Integer, Double>> distances;
    
    public DistanceTableModel(Graph<Point> graph) {
        this.graph = graph;
        this.distances = graph.getDistancesTable();
    }
    
    public String getColumnName(int col) {
        Iterator<Integer> iter = this.distances.keySet().iterator();
        int nthKey = 0;

        for (int i = 0; i <= col && iter.hasNext(); i++) {
            nthKey = iter.next();
        }
        return String.valueOf(nthKey);
    }

    public int getRowCount() {
        return this.distances.size();
    }

    public int getColumnCount() {
        return this.distances.size();
    }

    public Object getValueAt(int row, int col) {
        return this.distances.get(col).get(row);
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setValueAt(Object value, int row, int col) {
        this.distances.get(col).replace(row, (Double) value);
        this.fireTableCellUpdated(row, col);
    }
    
    public void updateTable() {
        this.fireTableDataChanged();
    }
}

