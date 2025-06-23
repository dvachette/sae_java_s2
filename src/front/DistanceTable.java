/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import back.Graph;
import back.Point;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author donat
 * @brief Classe utilisée pour afficher les distances entre les points
 */
public class DistanceTable extends JScrollPane {

    private DistanceTableModel model;
    private JTable dt;

    /**
     * @brief Constructeur
     */
    public DistanceTable() {
        super();
        initComponent();
    }

    /**
     * 
     * @return Dimension
     * @brief Fixer la taille à 400x400
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }


    private void initComponent() {

        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.revalidate();
    }
    
    /**
     * 
     * @param g 
     * @brief initialise le graphe a représenter
     */
    public void setGraph(Graph<? extends Point> g) {
        model = new DistanceTableModel(g);

        dt = new JTable();
        dt.setPreferredSize(new Dimension(400, 400));
        dt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = dt.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setMinWidth(30);
        }
        dt.setColumnModel(columnModel);
        dt.setModel(model);
        add(dt);
        this.revalidate();
    }
}
