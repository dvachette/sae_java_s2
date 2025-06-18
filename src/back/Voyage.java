package back;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;


/**
 * @author Ethan
 */

public class Voyage {
    protected String name;
    protected String type;
    protected String comment;
    protected int dimension;
    protected String typeCoordinate;
    protected String displayType;
    protected String edgeWeightFormat;
    protected Parcours <? extends Point> parcours;


    public Voyage(){
        this.name = "";
        this.type = "";
        this.comment = "";
        this.dimension = 0;
        this.typeCoordinate = "";
        this.displayType = "";
        this.edgeWeightFormat = "";
        this.parcours = null;
    }

    public Voyage(String name, String type, String comment, int dimension, String typeCoordinate, String displayType, String edgeWeightFormat) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.dimension = dimension;
        this.typeCoordinate = typeCoordinate;
        this.displayType = displayType;
        this.edgeWeightFormat = edgeWeightFormat;
    }

    public void setParcours(Parcours<? extends Point> parcours) {
        this.parcours = parcours;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public String getTypeCoordinate() {
        return typeCoordinate;
    }

    public void setTypeCoordinate(String typeCoordinate) {
        this.typeCoordinate = typeCoordinate;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getEdgeWeightFormat() {
        return edgeWeightFormat;
    }

    public void setEdgeWeightFormat(String edgeWeightFormat) {
        this.edgeWeightFormat = edgeWeightFormat;
    }

    @Override
    public String toString() {
        return "Voyage{" + "name=" + name + ", type=" + type + ", comment=" + comment + ", dimension=" + dimension + ", typeCoordinate=" + typeCoordinate + ", displayType=" + displayType + ", edgeWeightFormat=" + edgeWeightFormat + '}';
    }

    public <T extends Point> void exportToFile(String filePath, Parcours<T> parcours) {
        try {
            FileWriter file = new FileWriter(filePath);
            file.write("NAME : " + name + "\n");
            file.write("TYPE : " + type + "\n");
            file.write("COMMENT : " + comment + "\n");
            file.write("DIMENSION : " + dimension + "\n");
            file.write("EDGE_WEIGHT_TYPE : " + edgeWeightFormat + "\n");
            file.write("DISPLAY_DATA_TYPE : " + displayType + "\n");
            file.write("NODE_COORD_SECTION\n");
            if (edgeWeightFormat.equalsIgnoreCase("EUC_2D")) {
                for (int i = 0; i < parcours.getPath().size(); i++) {
                    PointEuclidien point = (PointEuclidien) parcours.getPath().get(i);
                    file.write((i + 1) + " " + point.getX() + " " + point.getY() + "\n");
                }
                majCsv(filePath, parcours);
            }
            else if (edgeWeightFormat.equalsIgnoreCase("GEO")) {
                for (int i = 0; i < parcours.getPath().size(); i++) {
                    PointGeographique point = (PointGeographique) parcours.getPath().get(i);
                    file.write((i + 1) + " " + point.getLatitude() + " " + point.getLongitude() + "\n");
                }
                majCsv(filePath, parcours);
            }
            else {
                file.close();
                throw new IllegalArgumentException("Unsupported edge weight format: " + edgeWeightFormat);
            }
            file.write("EOF\n");
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Une Erreur Inconnue s'est produite lors de l'ouverture du fichier pour écriture.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Unsupported edge weight format: " + edgeWeightFormat);
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
    }

    public <T extends Point> void majCsv(String filepath, Parcours<T> parcours){
        try {
            FileWriter file = new FileWriter("ResultatX_Y.csv", true);
            String[] parts = filepath.split("/");
            String filename = parts[parts.length - 1];
            Graph<T> graph = parcours.getGraph();
            Parcours<T> MeilleurGlouton = Parcours.MeilleurGlouton(graph, graph.getPoint(1));
            Parcours<T> MeilleurInsertion = Parcours.MeilleurInsertion(graph, graph.getPoint(1));
            Parcours<T> MeilleurAll = Parcours.MeilleurAll(graph);
            file.write(filename + ";" + MeilleurGlouton.getLength() + ";" + MeilleurInsertion.getLength() + ";" + MeilleurAll.getLength() + "\n");
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Une Erreur Inconnue s'est produite lors de l'ouverture du fichier pour écriture.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Unsupported edge weight format: " + edgeWeightFormat);
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }

    }
}

