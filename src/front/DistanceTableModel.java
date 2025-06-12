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
    
    private Graph<? extends Point> graph;
    private TreeMap<Integer, TreeMap<Integer, Double>> distances;
    
    public DistanceTableModel(Graph<? extends Point> graph) {
        this.graph = graph;
        this.distances = this.graph.getDistancesTable();
    }
    
    @Override
    public String getColumnName(int col) {
        if (col == 0) {
            return "";
        }
        col --;
        Iterator<Integer> iter =  graph.getPoints().keySet().iterator();
        for (int i = 0; i < col && iter.hasNext(); i++) {
            iter.next();
        }
        return String.valueOf(iter.hasNext() ? iter.next() : null);
    }

    @Override
    public int getRowCount() {
        return this.distances.size();
    }

    @Override
    public int getColumnCount() {
        return this.distances.size()+1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            Iterator<Integer> iter =  graph.getPoints().keySet().iterator();
            for (int i = 0; i < row && iter.hasNext(); i++) {
                iter.next();
            }
            return String.valueOf(iter.next());
        }
        return this.distances.get(Integer.valueOf(getColumnName(col))).get(Integer.valueOf((String) getValueAt(row, 0)));
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        this.fireTableCellUpdated(row, col);
    }
    
    
    public void updateTable(int row, int col) {
        this.fireTableCellUpdated(row, col);
    }
    public void updateTable() {
        this.fireTableDataChanged();
    }
}

