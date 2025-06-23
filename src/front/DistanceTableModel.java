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
 * @brief modele pour la table des distances
 */
public class DistanceTableModel extends AbstractTableModel {
    
    private Graph<? extends Point> graph;
    private TreeMap<Integer, TreeMap<Integer, Double>> distances;
    
    /**
     * 
     * @param graph 
     */
    public DistanceTableModel(Graph<? extends Point> graph) {
        this.graph = graph;
        this.distances = this.graph.getDistancesTable();
    }
     /**
      * 
      * @param col
      * @return String
      * @brief Si la colonne est 0, renvoie l'id du point de la ligne, sinon la distance entre le point de la lgne et de la colonne
      */
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

    /**
     * 
     * @return int
     * @brief renvoie le nombre de lignes (nombre de points)
     */
    @Override
    public int getRowCount() {
        return this.distances.size();
    }

    /**
     * 
     * @return int
     * @brief renvoie le nombre de colonnes (nombre de points + 1 pour la colonne d'en tete)
     */
    @Override
    public int getColumnCount() {
        return this.distances.size()+1;
    }

    /**
     * 
     * @param row
     * @param col
     * @return Renvoie la distance entre deux points
     */
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

    /**
     * 
     * @param row
     * @param col
     * @return false 
     * @brief Laisse la table en readonly
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * 
     * @param value
     * @param row
     * @param col 
     * @brief permet de mettre a jour une cellule
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        this.fireTableCellUpdated(row, col);
    }
    
    /**
     * 
     * @param row
     * @param col 
     * @brief permet de mettre a jour une cellule
     */
    public void updateTable(int row, int col) {
        this.fireTableCellUpdated(row, col);
    }
    
    /**
     * 
     * @brief permet de mettre a jour la table
     */
    public void updateTable() {
        this.fireTableDataChanged();
    }
}

