/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package front;

import back.Parcours;
import back.PointEuclidien;
import back.PointGeographique;
import back.Voyage;
import back.VoyageEucli;
import back.VoyageFactory;
import back.VoyageGeo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;
import waypoints.CustomWaypoint;
import waypoints.WaypointRender;

/**
 *
 * @author donat
 */
public class MainWindow extends JFrame {

    private Parcours<PointEuclidien> parcoursInsertionEuclidien, parcoursGloutonEuclidien;
    private Parcours<PointGeographique> parcoursInsertionGeographique, parcoursGloutonGeographique;
    private final HashSet<CustomWaypoint> waypoints = new HashSet<>();
    private JXMapViewer jxMapViewer;
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
        initJxMapViewer();
        setVisible(true);

    }

    private void initJxMapViewer() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jxMapViewer.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(45.7708737, 4.8913516);
        jxMapViewer.setAddressLocation(geo);
        jxMapViewer.setZoom(5);

        // Event listener
        MouseInputListener mouseInputListener = new PanMouseInputListener(jxMapViewer);
        jxMapViewer.addMouseListener(mouseInputListener);
        jxMapViewer.addMouseMotionListener(mouseInputListener);
        jxMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jxMapViewer));

    }

    // Code généré par l'éditeur graphique de NetBeans
    @SuppressWarnings("unchecked")
    private void initComponents() {
        euclidianMap = new GMapEucli(this);
        tableDistanceTable = new JTable();
        jxMapViewer = new JXMapViewer();
        jxMapViewer.setPreferredSize(euclidianMap.getPreferredSize());
        jxMapViewer.setVisible(false);
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

        comboAlgorithmChoice.setModel(new DefaultComboBoxModel<>(new String[]{"Meilleur trajet", "Trajet glouton", "Trajet par insertion", "Trajet aleatoire"}));
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
        menuFileClose.setEnabled(false);

        menuFile.add(menuFileClose);
        menuFile.add(jSeparator3);

        menuFileGenerateRandomPointSet.setText("Générer des points aléatoires");
        menuFileGenerateRandomPointSet.addActionListener((ActionEvent evt) -> {
            menuFileGenerateRandomPointSetActionPerformed(evt);
        });
        menuFile.add(menuFileGenerateRandomPointSet);
        menuFile.add(jSeparator1);

        menuFileExport.setText("Exporter le meilleur trajet");
        menuFileExport.addActionListener((java.awt.event.ActionEvent evt) -> {
            menuFileExportActionPerformed(evt);
        });
        menuFileExport.setEnabled(false);

        buttonShowTravel.addActionListener((ActionEvent evt) -> {
            showTravelToggleButtonActionPerformed();
        });
        buttonShowTravel.setEnabled(false);

        menuFile.add(menuFileExport);

        menuBar.add(menuFile);

        menuEvaluation.setText("Evaluation");

        selectFolderMenuItem.setText("Selectionner un dossier");
        selectFolderMenuItem.addActionListener((ActionEvent evt) -> {
            menuEvaluationButtonActionPerformed(evt);
        });
        menuEvaluation.add(selectFolderMenuItem);

        menuBar.add(menuEvaluation);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jxMapViewer)
                        .addComponent(euclidianMap)
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
                .addComponent(jxMapViewer)
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

    private void menuFileGenerateRandomPointSetActionPerformed(ActionEvent evt) {
        AskForCreatingRandomPointSet popup = new AskForCreatingRandomPointSet(this);
        popup.setVisible(true);
        if (popup.OK()) {
            VoyageEucli newVoyage = new VoyageEucli();
            newVoyage.setGraph(popup.getGeneratedGraph());
            voyage = newVoyage;
            jxMapViewer.setVisible(false);
            euclidianMap.setVisible(true);
            buttonShowTravel.setSelected(false);
            buttonShowTravel.setEnabled(true);
            euclidianMap.setMap(newVoyage.getGraph());
            euclidianMap.setParcours(null);
            menuFileClose.setEnabled(true);
            showTravelToggleButtonActionPerformed();
            DistanceTableModel dtm = new DistanceTableModel(newVoyage.getGraph());
            tableDistanceTable.setModel(dtm);
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
    }

    private void menuFileOpenActionPerformed(ActionEvent e) {
        fileChoserOpen = new JFileChooser();
        int returnVal = fileChoserOpen.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            buttonShowTravel.setSelected(false);
            buttonShowTravel.setEnabled(true);
            menuFileExport.setEnabled(true);
            menuFileClose.setEnabled(true);
            String pathToOpen = fileChoserOpen.getSelectedFile().getPath();
            VoyageFactory factory = new VoyageFactory(pathToOpen);
            try {
                voyage = factory.createVoyage();
                if (voyage instanceof VoyageEucli voyageEucli) {
                    jxMapViewer.setVisible(false);
                    euclidianMap.setVisible(true);
                    parcoursGloutonEuclidien = null;
                    parcoursInsertionEuclidien = null;
                    euclidianMap.setMap(voyageEucli.getGraph());
                    euclidianMap.setParcours(null);
                    showTravelToggleButtonActionPerformed();
                    DistanceTableModel dtm = new DistanceTableModel(voyageEucli.getGraph());
                    tableDistanceTable.setModel(dtm);
                    editModeToggleButton.setEnabled(true);
                    editModeToggleButton.setSelected(false);
                    euclidianMap.setEdit_mode(false);
                } else if (voyage instanceof VoyageGeo voyageGeo) {
                    euclidianMap.setVisible(false);
                    jxMapViewer.setVisible(true);

                    parcoursGloutonGeographique = null;
                    parcoursInsertionGeographique = null;
                    clearWaypoints();
                    for (PointGeographique p : voyageGeo.getGraph().getPoints().values()) {
                        waypoints.add(new CustomWaypoint(p));
                    }
                    // ProgressBar (ChatGPT 4o, reworked by Donatien VACHETTE)
                    JDialog dialog = new JDialog(this, "Traitement en cours...", true);
                    JProgressBar progressBar = new JProgressBar();
                    progressBar.setIndeterminate(true);
                    dialog.add(BorderLayout.CENTER, progressBar);
                    dialog.setUndecorated(true);
                    dialog.setSize(200, 50);
                    dialog.setLocationRelativeTo(this);

                    SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() {
                            initWaypoints();
                            System.out.println("Points placés");
                            DistanceTableModel dtm = new DistanceTableModel(voyageGeo.getGraph());
                            tableDistanceTable.setModel(dtm);
                            System.out.println("distances calculées");
                            return null;
                        }

                        @Override
                        protected void done() {
                            dialog.dispose(); // Fermer la boîte de dialogue à la fin
                        }
                    };

                    worker.execute();
                    dialog.setVisible(true);

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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Enregistrer sous");

        int userSelection = fileChooser.showSaveDialog(null); // null = centré

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            try {
                String filePathToSave = fileChooser.getSelectedFile().getAbsolutePath();
                if (voyage instanceof VoyageEucli voyageEucli) {
                    if (Objects.equals(parcoursGloutonEuclidien, null)) {
                        parcoursGloutonEuclidien = voyageEucli.getGraph().parcoursGlouton();
                    }
                    if (Objects.equals(parcoursInsertionEuclidien, null)) {
                        parcoursInsertionEuclidien = voyageEucli.getGraph().parcoursInsertion();
                    }
                    if (parcoursInsertionEuclidien.getLength() < parcoursGloutonEuclidien.getLength()) {
                        voyage.exportToFile(filePathToSave, parcoursInsertionEuclidien);
                    } else {
                        voyage.exportToFile(filePathToSave, parcoursGloutonEuclidien);
                    }
                } else if (voyage instanceof VoyageGeo voyageGeo) {
                    if (Objects.equals(parcoursGloutonGeographique, null)) {
                        parcoursGloutonGeographique = voyageGeo.getGraph().parcoursGlouton();
                    }
                    if (Objects.equals(parcoursInsertionGeographique, null)) {
                        parcoursInsertionGeographique = voyageGeo.getGraph().parcoursInsertion();
                    }
                    if (parcoursInsertionGeographique.getLength() < parcoursGloutonGeographique.getLength()) {
                        voyage.exportToFile(filePathToSave, parcoursInsertionGeographique);
                    } else {
                        voyage.exportToFile(filePathToSave, parcoursGloutonGeographique);
                    }
                }
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(rootPane, "Une erreur inatendue s'est produite", "Erreur - ouverture", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void menuFileCloseActionPerformed(ActionEvent evt) {
        this.voyage = null;
        euclidianMap.setParcours(null);
        euclidianMap.setMap(null);
        tableDistanceTable.setModel(new DefaultTableModel());
        buttonShowTravel.setSelected(false);
        buttonShowTravel.setEnabled(false);
        menuFileExport.setEnabled(false);
        menuFileClose.setEnabled(false);
        euclidianMap.setEdit_mode(false);
        editModeToggleButton.setEnabled(false);
    }

    private void comboAlgorithmChoiceActionPerformed(ActionEvent evt) {
        showTravelToggleButtonActionPerformed();
    }

    private void editModeToggleButtonActionPerformed(ActionEvent evt) {
        euclidianMap.setEdit_mode(editModeToggleButton.isSelected());
    }

    private void showTravelToggleButtonActionPerformed() {

        if (!(Objects.equals(voyage, null))) {

            if (voyage instanceof VoyageEucli voyageEucli) {

                if (buttonShowTravel.isSelected()) {

                    // ProgressBar (ChatGPT 4o, reworked by Donatien VACHETTE)
                    JDialog dialog = new JDialog(this, "Traitement en cours...", true);
                    JProgressBar progressBar = new JProgressBar();
                    progressBar.setIndeterminate(true);
                    dialog.add(BorderLayout.CENTER, progressBar);
                    dialog.setUndecorated(true);
                    dialog.setSize(200, 50);
                    dialog.setLocationRelativeTo(this);

                    SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() {

                            Parcours<PointEuclidien> parcours = null;
                            System.out.println("Appel de trajet");
                            switch ((String) comboAlgorithmChoice.getSelectedItem()) {
                                case "Meilleur trajet" -> {
                                    if (Objects.equals(parcoursGloutonEuclidien, null)) {
                                        parcoursGloutonEuclidien = voyageEucli.getGraph().parcoursGlouton();
                                    }
                                    if (Objects.equals(parcoursInsertionEuclidien, null)) {
                                        parcoursInsertionEuclidien = voyageEucli.getGraph().parcoursInsertion();
                                    }
                                    if (parcoursInsertionEuclidien.getLength() < parcoursGloutonEuclidien.getLength()) {
                                        parcours = parcoursInsertionEuclidien;
                                    } else {
                                        parcours = parcoursGloutonEuclidien;
                                    }
                                }

                                case "Trajet glouton" -> {
                                    if (Objects.equals(parcoursGloutonEuclidien, null)) {
                                        parcoursGloutonEuclidien = voyageEucli.getGraph().parcoursGlouton();
                                    }
                                    parcours = parcoursGloutonEuclidien;
                                }
                                case "Trajet par insertion" -> {
                                    if (Objects.equals(parcoursInsertionEuclidien, null)) {
                                        parcoursInsertionEuclidien = voyageEucli.getGraph().parcoursInsertion();
                                    }
                                    parcours = parcoursInsertionEuclidien;
                                }
                                case "Trajet aleatoire" ->
                                    parcours = voyageEucli.getGraph().parcoursAleatoire();
                            }
                            euclidianMap.setParcours(parcours);
                            return null;
                        }

                        @Override
                        protected void done() {
                            dialog.dispose(); // Fermer la boîte de dialogue à la fin
                        }
                    };

                    worker.execute();
                    dialog.setVisible(true);
                } else {
                    euclidianMap.setParcours(null);
                }
                euclidianMap.repaint();

            } else if (voyage instanceof VoyageGeo voyageGeo) {
                if (buttonShowTravel.isSelected()) {

                    // ProgressBar (ChatGPT 4o, reworked by Donatien VACHETTE)
                    JDialog dialog = new JDialog(this, "Traitement en cours...", true);
                    JProgressBar progressBar = new JProgressBar();
                    progressBar.setIndeterminate(true);
                    dialog.add(BorderLayout.CENTER, progressBar);
                    dialog.setUndecorated(true);
                    dialog.setSize(200, 50);
                    dialog.setLocationRelativeTo(this);

                    SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() {

                            Parcours<PointGeographique> parcours = null;

                            switch ((String) comboAlgorithmChoice.getSelectedItem()) {
                                case "Meilleur trajet" -> {
                                    if (Objects.equals(parcoursGloutonGeographique, null)) {
                                        parcoursGloutonGeographique = voyageGeo.getGraph().parcoursGlouton();
                                    }
                                    if (Objects.equals(parcoursInsertionGeographique, null)) {
                                        parcoursInsertionGeographique = voyageGeo.getGraph().parcoursInsertion();
                                    }
                                    if (parcoursInsertionGeographique.getLength() < parcoursGloutonGeographique.getLength()) {
                                        parcours = parcoursInsertionGeographique;
                                    } else {
                                        parcours = parcoursGloutonGeographique;
                                    }
                                }

                                case "Trajet glouton" -> {
                                    if (Objects.equals(parcoursGloutonGeographique, null)) {
                                        parcoursGloutonGeographique = voyageGeo.getGraph().parcoursGlouton();
                                    }
                                    parcours = parcoursGloutonGeographique;
                                }
                                case "Trajet par insertion" -> {
                                    if (Objects.equals(parcoursInsertionGeographique, null)) {
                                        parcoursInsertionGeographique = voyageGeo.getGraph().parcoursInsertion();
                                    }
                                    parcours = parcoursInsertionGeographique;
                                }
                                case "Trajet aleatoire" ->
                                    parcours = voyageGeo.getGraph().parcoursAleatoire();
                            }
                            ((WaypointRender) jxMapViewer.getOverlayPainter()).setParcours(parcours);
                            return null;
                        }

                        @Override
                        protected void done() {
                            dialog.dispose(); // Fermer la boîte de dialogue à la fin
                        }
                    };

                    worker.execute();
                    dialog.setVisible(true);
                } else {
                    ((WaypointRender) jxMapViewer.getOverlayPainter()).setParcours(null);
                }
                jxMapViewer.repaint();
            }
        }
    }

    private void menuEvaluationButtonActionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Sélectionner un dossier");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false); // désactive "Tous les fichiers"

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = chooser.getSelectedFile();
            System.out.println("Dossier sélectionné : " + selectedDirectory.getAbsolutePath());
            VoyageFactory vFacto;
            Voyage v;
            Parcours<PointEuclidien> pie;
            Parcours<PointEuclidien> pge;
            Parcours<PointGeographique> pig;
            Parcours<PointGeographique> pgg;
            File[] files = selectedDirectory.listFiles();
            try {
                FileWriter csvFile = new FileWriter("export/resultatsX_Y.csv");
                for (File f : files) {
                    vFacto = new VoyageFactory(f.getAbsolutePath());

                    v = vFacto.createVoyage();
                    if (v instanceof VoyageEucli ve) {
                        pie = ve.getGraph().parcoursInsertion();
                        pge = ve.getGraph().parcoursGlouton();

                        if (pie.getLength() < pge.getLength()) {
                            csvFile.write(f.getName() + ";" + pge.getLength() + ";" + pie.getLength() + ";" + pie.getLength() + "\n");
                            ve.exportToFile("export/" + f.getName().replace("eval", "voyage"), pie);
                        } else {
                            csvFile.write(f.getName() + ";" + pge.getLength() + ";" + pie.getLength() + ";" + pge.getLength() + "\n");
                            ve.exportToFile("export/" + f.getName().replace("eval", "voyage"), pge);
                        }
                    } else if (v instanceof VoyageGeo vg) {
                        pig = vg.getGraph().parcoursInsertion();
                        pgg = vg.getGraph().parcoursGlouton();
                        if (pig.getLength() < pgg.getLength()) {
                            csvFile.write(f.getName() + ";" + pgg.getLength() + ";" + pig.getLength() + ";" + pig.getLength() + "\n");
                            vg.exportToFile("export/" + f.getName().replace("eval", "voyage"), pig);
                        } else {
                            csvFile.write(f.getName() + ";" + pgg.getLength() + ";" + pig.getLength() + ";" + pgg.getLength() + "\n");
                            vg.exportToFile("export/" + f.getName().replace("eval", "voyage"), pgg);
                        }
                    }
                }
                csvFile.close();
            } catch (Exception exc) {

            }
        }
    }

    private void initWaypoints() {
        WaypointPainter<CustomWaypoint> wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        jxMapViewer.setOverlayPainter(wp);

    }

    public void updateGraph() {
        DistanceTableModel dtm = new DistanceTableModel(((VoyageEucli) voyage).getGraph());
        tableDistanceTable.setModel(dtm);
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
        this.euclidianMap.setParcours(null);
        this.parcoursGloutonEuclidien = null;
        this.parcoursInsertionEuclidien = null;
        this.buttonShowTravel.setSelected(false);
    }

    private void clearWaypoints() {

        waypoints.clear();
        initWaypoints();
    }
}
