package back;


/**
 * @author Ethan
 */

public class Voyage {
    String name;
    String type;
    String comment;
    int dimension;
    String Type_Coordinate;
    String Display_Type;
    String EDGE_WEIGHT_FORMAT;


    public Voyage(){
        this.name = "";
        this.type = "";
        this.comment = "";
        this.dimension = 0;
        this.Type_Coordinate = "";
        this.Display_Type = "";
        this.EDGE_WEIGHT_FORMAT = "";
    }

     public String getName() { 
        return name;
    }
    public String getType() {
        return type;
    }
    public String getComment() {
        return comment;
    }
    public int getDimension() {
        return dimension;
    }
    public String getType_Coordinate() {
        return Type_Coordinate;
    }
    public String getDisplay_Type() {
        return Display_Type;
    }
    public String getEDGE_WEIGHT_FORMAT() {
        return EDGE_WEIGHT_FORMAT;
    }
}

