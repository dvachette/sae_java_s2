package back;

/**
 * @author Ethan
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class VoyageFactory {

    private String chemin;

    public VoyageFactory(String chemin) {
        this.chemin = chemin;
    }

    public Voyage createVoyage() throws IllegalArgumentException, FileNotFoundException, IOException{
        String type = "";
        String name = "";
        String comment = "";
        int dimension = 0;
        String typeCoordinate = "";
        String displayType = "";
        String edgeWeightFormat = "";
            
            FileInputStream fileInputStream = new FileInputStream(chemin);
            Scanner scanner = new Scanner(fileInputStream);
            
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.trim().equalsIgnoreCase("NODE_COORD_SECTION")) {
                    break;
                }
                String[] parts = line.split(":");
                if ((parts[0].trim()).equalsIgnoreCase("NAME")) {
                    name = parts[1].trim();
                }
                else if ((parts[0].trim()).equalsIgnoreCase("TYPE")) {
                    type = parts[1].trim();
                }
                else if ((parts[0].trim()).equalsIgnoreCase("COMMENT")) {
                    comment = parts[1].trim();
                }
                else if ((parts[0].trim()).equalsIgnoreCase("DIMENSION")) {
                    dimension = Integer.parseInt(parts[1].trim());
                }
                else if ((parts[0].trim()).equalsIgnoreCase("EDGE_WEIGHT_TYPE")) {
                    typeCoordinate = parts[1].trim();
                }
                else if ((parts[0].trim()).equalsIgnoreCase("DISPLAY_TYPE")) {
                    displayType = parts[1].trim();
                }
                else if ((parts[0].trim()).equalsIgnoreCase("EDGE_WEIGHT_FORMAT")) {
                    edgeWeightFormat = parts[1].trim();
                }
            }
           

            if (typeCoordinate.equalsIgnoreCase("EUC_2D")) {
                VoyageEucli voyage = new VoyageEucli();
                voyage.setName(name);
                voyage.setType(type);
                voyage.setComment(comment);
                voyage.setDimension(dimension);
                voyage.setTypeCoordinate(typeCoordinate);
                voyage.setDisplayType(displayType);
                voyage.setEdgeWeightFormat(edgeWeightFormat);
                // Initialize the graph for Euclidean points
                line = scanner.nextLine();
                Graph<PointEuclidien> gr = new Graph<>();
                while (line != null && !line.trim().equals("EOF")) {
                    String[] parts = line.trim().split("\\s+");
                    int id = Integer.parseInt(parts[0]);
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    gr.addPoint(new PointEuclidien(x, y, id));
                    line = scanner.nextLine();
                }
                voyage.setGraph(gr);

                scanner.close();
                fileInputStream.close();
                return voyage;
            } else if (typeCoordinate.equalsIgnoreCase("GEO")) {
                VoyageGeo voyage = new VoyageGeo();
                voyage.setName(name);
                voyage.setType(type);
                voyage.setComment(comment);
                voyage.setDimension(dimension);
                voyage.setTypeCoordinate(typeCoordinate);
                voyage.setDisplayType(displayType);
                voyage.setEdgeWeightFormat(edgeWeightFormat);

                line = scanner.nextLine();
                Graph<PointGeographique> gr = new Graph<>();
                while (line != null && !line.trim().equals("EOF")) {
                    String[] parts = line.trim().split("\\s+");
                    int id = Integer.parseInt(parts[0]);
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    System.out.printf("Adding point x = %f, y = %f \n", x, y);
                    gr.addPoint(new PointGeographique(x, y, id));
                    line = scanner.nextLine();
                }
                voyage.setGraph(gr);

                scanner.close();
                fileInputStream.close();
                return voyage;
            } else {
                scanner.close();
                fileInputStream.close();
                throw new IllegalArgumentException("Unsupported coordinate type: " + typeCoordinate); 
            }
    }
}
