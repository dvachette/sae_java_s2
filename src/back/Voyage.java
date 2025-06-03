package back;


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


    public Voyage(){
        this.name = "";
        this.type = "";
        this.comment = "";
        this.dimension = 0;
        this.typeCoordinate = "";
        this.displayType = "";
        this.edgeWeightFormat = "";
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
    
}
