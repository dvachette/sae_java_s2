package back;

/**
 * @author Ethan
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class VoyageFactory {
    String chemin;

    public VoyageFactory(String chemin) {
        this.chemin = chemin;
    }

    public Voyage createVoyage() {
        String type = "";
        String name = "";
        String comment = "";
        int dimension = 0;
        String typeCoordinate = "";
        String displayType = "";
        String edgeWeightFormat = "";

        try{
            FileInputStream fileInputStream = new FileInputStream(chemin);
            Scanner scanner = new Scanner(fileInputStream);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.trim().equals("NODE_COORD_SECTION")) {
                    break;
                }
                String[] parts = line.split(":");
                if ((parts[0].trim()).equals("NAME")) {
                    name = parts[1].trim();
                }
                else if ((parts[0].trim()).equals("TYPE")) {
                    type = parts[1].trim();
                }
                else if ((parts[0].trim()).equals("COMMENT")) {
                    comment = parts[1].trim();
                }
                else if ((parts[0].trim()).equals("DIMENSION")) {
                    dimension = Integer.parseInt(parts[1].trim());
                }
                else if ((parts[0].trim()).equals("EDGE_WEIGHT_TYPE")) {
                    typeCoordinate = parts[1].trim();
                }
                else if ((parts[0].trim()).equals("DISPLAY_TYPE")) {
                    displayType = parts[1].trim();
                }
                else if ((parts[0].trim()).equals("EDGE_WEIGHT_FORMAT")) {
                    edgeWeightFormat = parts[1].trim();
                }
            }
           

            if (typeCoordinate.equals("EUC_2D")) {
                VoyageEucli voyage = new VoyageEucli();
                voyage.name = name;
                voyage.type = type;
                voyage.comment = comment;
                voyage.dimension = dimension;
                voyage.Type_Coordinate = typeCoordinate;
                voyage.Display_Type = displayType;
                voyage.EDGE_WEIGHT_FORMAT = edgeWeightFormat;
                // Initialize the graph for Euclidean points
                voyage.graph = new Graph<PointEuclidien>(); // TODO Already initialized in the constructor
                // Read the NODE_COORD_SECTION

                if (scanner.hasNextLine() && scanner.nextLine().trim().equals("NODE_COORD_SECTION")) {
                scanner.nextLine(); // Skip the "NODE_COORD_SECTION" line
                line = scanner.nextLine();
                while (line != null && !line.trim().equals("EOF")) {
                    String[] parts = line.trim().split("\\s+");
                    int id = Integer.parseInt(parts[0]);
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    PointEuclidien point = new PointEuclidien(x, y, id);
                    voyage.graph.addPoint(point);
                }
            }
                scanner.close();
                fileInputStream.close();
                return voyage;
            } else if (typeCoordinate.equals("GEO")) {
                VoyageGeo voyage = new VoyageGeo();
                voyage.name = name;
                voyage.type = type;
                voyage.comment = comment;
                voyage.dimension = dimension;
                voyage.Type_Coordinate = typeCoordinate;
                voyage.Display_Type = displayType;
                voyage.EDGE_WEIGHT_FORMAT = edgeWeightFormat;
                voyage.graph = new Graph<PointGeographique>(); // TODO : Already initialized in the constructor

                if (scanner.hasNextLine() && scanner.nextLine().trim().equals("NODE_COORD_SECTION")) {
                scanner.nextLine(); // Skip the "NODE_COORD_SECTION" line
                line = scanner.nextLine();
                while (line != null && !line.trim().equals("EOF")) {
                    String[] parts = line.trim().split("\\s+");
                    int id = Integer.parseInt(parts[0]);
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    PointGeographique point = new PointGeographique(x, y, id);
                    voyage.graph.addPoint(point);
                }
            }



                scanner.close();
                fileInputStream.close();
                return voyage;
            } else {
                scanner.close();
                fileInputStream.close();
                throw new IllegalArgumentException("Unsupported coordinate type: " + typeCoordinate); //TODO : We are expected to handle errors
            }
        }
        catch (Exception e) {
            e.printStackTrace(); // TODO : better error handling
        }
        return null; 
    }
}
