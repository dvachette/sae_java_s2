package back;

/**
 * @author Ethan
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class VoyageFactory {

    private String chemin;

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

        try {
            FileInputStream fileInputStream = new FileInputStream(chemin);
            Scanner scanner = new Scanner(fileInputStream);
        ){
            
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
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Le type de coordonnées n'est pas supporté.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }

        catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Le fichier est mal formé ou incomplet.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        

        catch(FileNotFoundException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Le fichier n'a pas été trouvé.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }

        catch(NullPointerException e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Le fichier est vide ou mal formé.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }



        catch (Exception e) {
            e.printStackTrace();
            JDialog dialog = new JDialog();
            dialog.setType(java.awt.Window.Type.UTILITY);
            dialog.setTitle("Error");
            JLabel label = new JLabel("Une Erreur Inconnue s'est produite lors de la création du voyage.");
            dialog.add(label);
            dialog.setSize(400, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        return null;
    }
}
