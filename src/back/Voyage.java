package back;

import java.io.FileWriter;
import java.io.IOException;

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

    /**
     * @brief Constructeur par défaut
     */
    public Voyage() {
        this.name = "";
        this.type = "";
        this.comment = "";
        this.dimension = 0;
        this.typeCoordinate = "";
        this.displayType = "";
        this.edgeWeightFormat = "";
    }

    /**
     * Constructeur complet.
     *
     * @param name Nom du voyage
     * @param type Type de voyage
     * @param comment Commentaire associé
     * @param dimension Nombre de points
     * @param typeCoordinate Type de coordonnées utilisées
     * @param displayType Type d'affichage
     * @param edgeWeightFormat Format des poids d'arêtes
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
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return String
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @return int
     */
    public int getDimension() {
        return dimension;
    }

    /**
     *
     * @param dimension
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     *
     * @return String
     */
    public String getTypeCoordinate() {
        return typeCoordinate;
    }

    /**
     *
     * @param typeCoordinate
     */
    public void setTypeCoordinate(String typeCoordinate) {
        this.typeCoordinate = typeCoordinate;
    }

    /**
     *
     * @return String
     */
    public String getDisplayType() {
        return displayType;
    }

    /**
     *
     * @param displayType
     */
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    /**
     *
     * @return String
     */
    public String getEdgeWeightFormat() {
        return edgeWeightFormat;
    }

    /**
     *
     * @param edgeWeightFormat
     */
    public void setEdgeWeightFormat(String edgeWeightFormat) {
        this.edgeWeightFormat = edgeWeightFormat;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Voyage{" + "name=" + name + ", type=" + type + ", comment=" + comment + ", dimension=" + dimension + ", typeCoordinate=" + typeCoordinate + ", displayType=" + displayType + ", edgeWeightFormat=" + edgeWeightFormat + '}';
    }

    /**
     * @author Donatien
     * @param filePath
     * @param parcours
     * @throws IOException
     */
    public void exportToFile(String filePath, Parcours<? extends Point> parcours) throws IOException {
        System.out.println("exporting to " + filePath);
        FileWriter file = new FileWriter(filePath);
        for (int i = 0; i < parcours.getPath().size(); i++) {
            file.write(String.valueOf(parcours.getPath().get(i).getId()) + "\n");
        }
        file.close();
    }
}
