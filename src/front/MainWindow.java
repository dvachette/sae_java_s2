/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package front;

import back.Parcours;
import back.PointEuclidien;
import back.Voyage;
import back.VoyageEucli;
import back.VoyageFactory;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author donat
 */
public class MainWindow extends JFrame {

    private JScrollPane scrollPaneDistanceTable;
    private Voyage voyage;
    private GMapEucli euclidianMap;
    private boolean showTravel = false;

    // Variables declaration - do not modify                     
    private JToggleButton buttonShowTravel;
    private JComboBox<String> comboAlgorithmChoice;
    private JLabel labelTitleDistanceTable;
    private JMenuBar menuBar;
    private JMenuItem selectFolderMenuItem;
    private JPopupMenu.Separator jSeparator1;
    private JPopupMenu.Separator jSeparator3;
    private JToggleButton editModeToggleButton;
    private JMenu menuEvaluation;
    private JMenu menuFile;
    private JMenuItem menuFileClose;
    private JMenuItem menuFileExport;
    private JMenuItem menuFileGenerateRandomPointSet;
    private JMenuItem menuFileOpen;
    private JTable tableDistanceTable;
    private JFileChooser fileChoserOpen;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        this.setResizable(false);
        this.voyage = new Voyage();
        initComponents();
        setVisible(true);
    }

    // Code généré par l'éditeur graphique de NetBeans
    @SuppressWarnings("unchecked")
    private void initComponents() {
        euclidianMap = new GMapEucli();
        tableDistanceTable = new JTable();

        tableDistanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tableDistanceTable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setMinWidth(30);
        }
        tableDistanceTable.setColumnModel(columnModel);

        scrollPaneDistanceTable = new JScrollPane(tableDistanceTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        comboAlgorithmChoice = new JComboBox<>();
        buttonShowTravel = new JToggleButton();
        labelTitleDistanceTable = new JLabel();
        editModeToggleButton = new JToggleButton();
        menuBar = new JMenuBar();
        menuFile = new JMenu();
        menuFileOpen = new JMenuItem();
        menuFileClose = new JMenuItem();
        jSeparator3 = new JPopupMenu.Separator();
        menuFileGenerateRandomPointSet = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        menuFileExport = new JMenuItem();
        menuEvaluation = new JMenu();
        selectFolderMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        comboAlgorithmChoice.setModel(new DefaultComboBoxModel<>(new String[]{"Meilleur trajet", "Trajet glouton", "Trajet par insertion", "trajet aléatoire"}));
        comboAlgorithmChoice.setToolTipText("Choisi");
        comboAlgorithmChoice.addActionListener((java.awt.event.ActionEvent evt) -> {
            comboAlgorithmChoiceActionPerformed(evt);
        });

        buttonShowTravel.setText("Afficher le trajet");

        labelTitleDistanceTable.setText("Distances point à point");

        editModeToggleButton.setText("Mode edition");
        editModeToggleButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            editModeToggleButtonActionPerformed(evt);
        });

        menuFile.setText("Fichier");

        menuFileOpen.setText("Importer un fichier");
        menuFileOpen.addActionListener((ActionEvent e) -> {
            menuFileOpenActionPerformed(e);
        });
        menuFile.add(menuFileOpen);

        menuFileClose.setText("Fermer le fichier");
        menuFileClose.addActionListener((java.awt.event.ActionEvent evt) -> {
            menuFileCloseActionPerformed(evt);
        });
        menuFile.add(menuFileClose);
        menuFile.add(jSeparator3);

        menuFileGenerateRandomPointSet.setText("Générer des points aléatoires");
        menuFile.add(menuFileGenerateRandomPointSet);
        menuFile.add(jSeparator1);

        menuFileExport.setText("Exporter le meilleur trajet");
        menuFileExport.addActionListener((java.awt.event.ActionEvent evt) -> {
            menuFileExportActionPerformed(evt);
        });

        buttonShowTravel.addActionListener((ActionEvent evt) -> {
            showTravelToggleButtonActionPerformed();
        });
        menuFile.add(menuFileExport);

        menuBar.add(menuFile);

        menuEvaluation.setText("Evaluation");

        selectFolderMenuItem.setText("Selectionner un dossier");
        menuEvaluation.add(selectFolderMenuItem);

        menuBar.add(menuEvaluation);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(euclidianMap)
                        //.addGap(0, 1000, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPaneDistanceTable, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(labelTitleDistanceTable)
                                                .addGap(132, 132, 132)))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(editModeToggleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonShowTravel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(77, 77, 77)
                                        .addComponent(comboAlgorithmChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(euclidianMap)
                .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(labelTitleDistanceTable)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneDistanceTable, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(comboAlgorithmChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonShowTravel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(editModeToggleButton)
                        .addGap(57, 57, 57))
        );

        pack();
    }

    private void menuFileOpenActionPerformed(ActionEvent e) {
        fileChoserOpen = new JFileChooser();
        int returnVal = fileChoserOpen.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String pathToOpen = fileChoserOpen.getSelectedFile().getPath();
            VoyageFactory factory = new VoyageFactory(pathToOpen);
            try {
                voyage = factory.createVoyage();
                if (voyage instanceof VoyageEucli voyageEucli) {

                    euclidianMap.setMap(voyageEucli.getGraph());
                    euclidianMap.setParcours(null);
                    showTravelToggleButtonActionPerformed();
                    DistanceTableModel dtm = new DistanceTableModel(voyageEucli.getGraph());
                    tableDistanceTable.setModel(dtm);
                }
                if (!Objects.equals(voyage, null)) {

                    tableDistanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    TableColumnModel columnModel = tableDistanceTable.getColumnModel();
                    for (int i = 0; i < columnModel.getColumnCount(); i++) {
                        columnModel.getColumn(i).setMinWidth(50);
                    }
                    // Generated by chatGPT 4o and readapted by Donatien VACHETTE, for styling the first column
                    tableDistanceTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value,
                                boolean isSelected, boolean hasFocus, int row, int column) {
                            JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                            if (column == 0) {
                                c.setBackground(new Color(238, 238, 238));
                            } else {
                                // Add a tooltip
                                c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                                c.setForeground(isSelected ? table.getSelectionForeground() : Color.BLACK);
                                c.setToolTipText(String.format("Distance entre le point %s et le point %s : %s", table.getColumnName(column), table.getValueAt(row, 0), c.getText()));
                            }
                            return c;
                        }
                    });
                    // End of AI generated

                    scrollPaneDistanceTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPaneDistanceTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    getContentPane().revalidate();
                    scrollPaneDistanceTable.revalidate();
                }
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(rootPane, "Le fichier n'est pas au bon format", "Erreur - ouverture", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException exc) {
                JOptionPane.showMessageDialog(rootPane, "Le fichier n'existe pas ou a été déplacé", "Erreur - ouverture", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(rootPane, "Une erreur inatendue s'est produite", "Erreur - ouverture", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void menuFileExportActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void menuFileCloseActionPerformed(ActionEvent evt) {
        this.voyage = null;
        euclidianMap.setParcours(null);
        euclidianMap.setMap(null);
        tableDistanceTable.setModel(new DefaultTableModel());
    }

    private void comboAlgorithmChoiceActionPerformed(ActionEvent evt) {
        showTravelToggleButtonActionPerformed();
    }

    private void editModeToggleButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showTravelToggleButtonActionPerformed() {

        if (!(Objects.equals(voyage, null))) {

            if (voyage instanceof VoyageEucli voyageEucli) {

                if (buttonShowTravel.isSelected()) {

                    Parcours<PointEuclidien> parcours = null;
                    switch ((String) this.comboAlgorithmChoice.getSelectedItem()) {
                        case "Meilleur trajet" ->
                            parcours = Parcours.MeilleurAll(voyageEucli.getGraph());
                        case "Trajet glouton" ->
                            parcours = voyageEucli.getGraph().parcoursGlouton();
                        case "Trajet par insertion" ->
                            parcours = voyageEucli.getGraph().parcoursInsertion();
                        case "trajet aléatoire" ->
                            parcours = voyageEucli.getGraph().parcoursAleatoire();
                    }
                    euclidianMap.setParcours(parcours);

                } else {
                    euclidianMap.setParcours(null);
                }
                euclidianMap.repaint();

            }
        }

    }
}
