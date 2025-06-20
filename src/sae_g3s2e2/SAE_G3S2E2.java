package sae_g3s2e2;

import back.*;

/**
 *
 * @author donat
 */
public class SAE_G3S2E2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // VoyageEucli voyageEucli = new VoyageEucli("Test Voyage Euclidien", "Euclidien", "Test de parcours Euclidien", 100, "Euclidien", "2D", "EUC_2D");
        // Graph<PointEuclidien> graphEucli = new Graph<>();
        // graphEucli = Graph.randomPointSet(100);
        // voyageEucli.setGraph(graphEucli);
        // graphEucli.createMatrix();
        // Parcours<PointEuclidien> parcoursEucli = new Parcours<>();
        // parcoursEucli.setGraph(graphEucli);
        // parcoursEucli = Parcours.MeilleurAll(voyageEucli.getGraph(),voyageEucli.getGraph().getPoint(1));
        // voyageEucli.setParcours(parcoursEucli);
        // String filepath = "sae_java_s2/User_File/test_voyage.txt";
        // voyageEucli.exportToFile(filepath, parcoursEucli);
        // voyageEucli.exportforEval(parcoursEucli);

        VoyageFactory voyageFactory = new VoyageFactory("sae_java_s2/User_File/text9.txt");
        Voyage voyage = voyageFactory.createVoyage();
        if (voyage instanceof VoyageEucli) {
            VoyageEucli voyageEucli = (VoyageEucli) voyage;
            voyageEucli.getGraph().createMatrix();
            Parcours<PointEuclidien> parcoursEucli = new Parcours<>();
            parcoursEucli.setGraph(voyageEucli.getGraph());
            parcoursEucli = Parcours.MeilleurAll(voyageEucli.getGraph(), voyageEucli.getGraph().getPoint(1));
            parcoursEucli.setGraph(voyageEucli.getGraph());
            voyageEucli.setParcours(parcoursEucli);
            String filepath = "sae_java_s2/User_File/test_voyage.txt";
            voyageEucli.exportToFile(filepath, parcoursEucli);
            voyageEucli.exportforEval(parcoursEucli);
        } else if (voyage instanceof VoyageGeo) {
            VoyageGeo voyageGeo = (VoyageGeo) voyage;
            voyageGeo.getGraph().createMatrix();
            Parcours<PointGeographique> parcoursGeo = new Parcours<>();
            parcoursGeo.setGraph(voyageGeo.getGraph());
            parcoursGeo = Parcours.MeilleurAll(voyageGeo.getGraph(), voyageGeo.getGraph().getPoint(1));
            parcoursGeo.setGraph(voyageGeo.getGraph());
            voyageGeo.setParcours(parcoursGeo);
            String filepath = "sae_java_s2/User_File/test_voyage_geo.txt";
            voyageGeo.exportToFile(filepath, parcoursGeo);
        }
        VoyageFactory voyageFactory2 = new VoyageFactory("sae_java_s2/User_File/test5.txt");
        Voyage voyage2 = voyageFactory2.createVoyage();
        if (voyage2 instanceof VoyageEucli) {
            VoyageEucli voyageEucli2 = (VoyageEucli) voyage2;
            voyageEucli2.getGraph().createMatrix();
            Parcours<PointEuclidien> parcoursEucli2 = new Parcours<>();
            parcoursEucli2.setGraph(voyageEucli2.getGraph());
            parcoursEucli2 = Parcours.MeilleurAll(voyageEucli2.getGraph(), voyageEucli2.getGraph().getPoint(1));
            parcoursEucli2.setGraph(voyageEucli2.getGraph());
            voyageEucli2.setParcours(parcoursEucli2);
            String filepath = "sae_java_s2/User_File/test_voyage_5.txt";
            voyageEucli2.exportToFile(filepath, parcoursEucli2);
            voyageEucli2.exportforEval(parcoursEucli2);
        } else if (voyage2 instanceof VoyageGeo) {
            VoyageGeo voyageGeo2 = (VoyageGeo) voyage2;
            voyageGeo2.getGraph().createMatrix();
            Parcours<PointGeographique> parcoursGeo2 = new Parcours<>();
            parcoursGeo2.setGraph(voyageGeo2.getGraph());
            parcoursGeo2 = Parcours.MeilleurAll(voyageGeo2.getGraph(), voyageGeo2.getGraph().getPoint(1));
            parcoursGeo2.setGraph(voyageGeo2.getGraph());
            voyageGeo2.setParcours(parcoursGeo2);
            String filepath = "sae_java_s2/User_File/test_voyage_geo_5.txt";
            voyageGeo2.exportToFile(filepath, parcoursGeo2);
        }
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Execution time: " + duration + " milliseconds");
        

    }
}
