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
    public static int idVoyage = 1;

    /**
     * @author Ethan
     * @brief Constructeur par défaut
     */


    public Voyage(){
        this.name = "";
        this.type = "";
        this.comment = "";
        this.dimension = 0;
        this.typeCoordinate = "";
        this.displayType = "";
        this.edgeWeightFormat = "";
        this.parcours = null;
        idVoyage++;
    }

    /**
     * @author Ethan
     * @brief Constructeur avec paramètres
     * @param name Le nom du voyage
     * @param type Le type de voyage
     * @param comment Un commentaire sur le voyage
     * @param dimension La dimension du voyage
     * @param typeCoordinate Le type de coordonnées (EUC_2D, GEO, etc.)
     * @param displayType Le type d'affichage des données
     * @param edgeWeightFormat Le format du poids des arêtes
     */

    public Voyage(String name, String type, String comment, int dimension, String typeCoordinate, String displayType, String edgeWeightFormat) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.dimension = dimension;
        this.typeCoordinate = typeCoordinate;
        this.displayType = displayType;
        this.edgeWeightFormat = edgeWeightFormat;
    }

    /**
     * @author Ethan
     * @brief Setter pour le parcours
     * @param parcours
     */

    public void setParcours(Parcours<? extends Point> parcours) {
        this.parcours = parcours;
    }
    /**
     * @author Ethan
     * @brief Getter pour le nom du parcours
     * @return String
     */
    
    public String getName() {
        return name;
    }

    /**
     * @author Ethan
     * @brief Setter pour le nom du parcours
     * @param name Le nom du parcours
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @author Ethan
     * @brief Getter pour le type du parcours
     * @return String
     */

    public String getType() {
        return type;
    }

    /**
     * @author Ethan
     * @brief Setter pour le type du parcours
     * @param type Le type du parcours
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @author Ethan
     * @brief Getter pour le commentaire du parcours
     * @return String
     */

    public String getComment() {
        return comment;
    }

    /**
     * @author Ethan
     * @brief Setter pour le commentaire du parcours
     * @param comment Le commentaire du parcours
     */

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @author Ethan
     * @brief Getter pour la dimension du parcours
     * @return int
     */

    public int getDimension() {
        return dimension;
    }

    /**
     * @author Ethan
     * @brief Setter pour la dimension du parcours
     * @param dimension La dimension du parcours
     */

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * @author Ethan
     * @brief Getter pour le type de coordonnées du parcours
     * @return String
     */

    public String getTypeCoordinate() {
        return typeCoordinate;
    }

    /**
     * @author Ethan
     * @brief Setter pour le type de coordonnées du parcours
     * @param typeCoordinate Le type de coordonnées du parcours
     */

    public void setTypeCoordinate(String typeCoordinate) {
        this.typeCoordinate = typeCoordinate;
    }

    /**
     * @author Ethan
     * @brief Getter pour le type d'affichage des données du parcours
     * @return String
     */

    public String getDisplayType() {
        return displayType;
    }

    /**
     * @author Ethan
     * @brief Setter pour le type d'affichage des données du parcours
     * @param displayType Le type d'affichage des données du parcours
     */

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    /**
     * @author Ethan
     * @brief Getter pour le format du poids des arêtes du parcours
     * @return String
     */

    public String getEdgeWeightFormat() {
        return edgeWeightFormat;
    }

    /**
     * @author Ethan
     * @brief Setter pour le format du poids des arêtes du parcours
     * @param edgeWeightFormat Le format du poids des arêtes du parcours
     */

    public void setEdgeWeightFormat(String edgeWeightFormat) {
        this.edgeWeightFormat = edgeWeightFormat;
    }

    /**
     * @author Ethan
     * @brief Méthode toString pour afficher les informations du voyage
     * @return Une chaîne de caractères représentant le voyage
     */

    @Override
    public String toString() {
        return "Voyage{" + "name=" + name + ", type=" + type + ", comment=" + comment + ", dimension=" + dimension + ", typeCoordinate=" + typeCoordinate + ", displayType=" + displayType + ", edgeWeightFormat=" + edgeWeightFormat + '}';
    }

    /**
     * @author Ethan
     * @brief Méthode pour exporter le parcours vers un fichier
     * @param filePath Le chemin du fichier où exporter le parcours
     * @param parcours Le parcours à exporter
     */

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
            if (typeCoordinate.equalsIgnoreCase("EUC_2D")) {
                for (int i = 0; i < parcours.getPath().size(); i++) {
                    PointEuclidien point = (PointEuclidien) parcours.getPath().get(i);
                    file.write((i + 1) + " " + point.getX() + " " + point.getY() + "\n");
                }
                majCsv(filePath, parcours);
            }
            else if (typeCoordinate.equalsIgnoreCase("GEO")) {
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

    public <T extends Point> void exportforEval(Parcours<T> parcours, int i_voyage) {
        try {
            String filepath = "sae_java_s2/Result_File/G3S2BE2/voyage" + i_voyage + ".txt";
            FileWriter file = new FileWriter(filepath);
            int n = parcours.getPath().size();
            for(int i = 0; i < n; i++){
                int supposed_id = parcours.getPoint(i).getId();
                file.write(supposed_id + "\n");
            }
            this.majCsv(filepath, parcours);
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
        catch(Exception e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Une Erreur Inconnue s'est produite lors de l'exportation du parcours.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
    }

    /**
     * @author Ethan
     * @brief Méthode pour mettre à jour le fichier CSV avec les résultats du parcours
     * @param filepath Le chemin du fichier CSV
     * @param parcours Le parcours à ajouter au fichier CSV
     */

    public <T extends Point> void majCsv(String filepath, Parcours<T> parcours){
        try {
            FileWriter file = new FileWriter("sae_java_s2/Result_File/G3S2BE2/ResultatX_Y.csv", true);
            String[] parts = filepath.split("/");
            String filename = parts[parts.length - 1];
            Graph<T> graph = parcours.getGraph();
            Parcours<T> MeilleurGlouton = Parcours.MeilleurGlouton(graph, graph.getPoint(1));
            Parcours<T> MeilleurInsertion = Parcours.MeilleurInsertion(graph, graph.getPoint(1));
            Parcours<T> MeilleurAll = Parcours.MeilleurAll(graph, graph.getPoint(1));
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

