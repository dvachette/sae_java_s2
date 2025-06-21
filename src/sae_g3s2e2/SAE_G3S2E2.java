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
        SAE_G3S2E2 sae = new SAE_G3S2E2();
        sae.Evaluation();
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Execution time: " + duration + " milliseconds");
        

    }
    public void Evaluation() {
        for (int i = 0; i < 10; i++) {
            String filepath = "sae_java_s2/User_File/eval" + (i + 1) + ".txt";
            VoyageFactory voyageFactory = new VoyageFactory(filepath);
            Voyage voyage = voyageFactory.createVoyage();
            if (voyage instanceof VoyageEucli) {
                VoyageEucli voyageEucli = (VoyageEucli) voyage;
                voyageEucli.getGraph().createMatrix();
                Parcours<PointEuclidien> parcoursEucli = new Parcours<>();
                parcoursEucli.setGraph(voyageEucli.getGraph());
                parcoursEucli = Parcours.MeilleurAll(voyageEucli.getGraph(), voyageEucli.getGraph().getPoint(1));
                voyageEucli.setParcours(parcoursEucli);
                parcoursEucli.setGraph(voyageEucli.getGraph());
                voyageEucli.exportforEval(parcoursEucli,i + 1);
            } else if (voyage instanceof VoyageGeo) {
                VoyageGeo voyageGeo = (VoyageGeo) voyage;
                voyageGeo.getGraph().createMatrix();
                Parcours<PointGeographique> parcoursGeo = new Parcours<>();
                parcoursGeo.setGraph(voyageGeo.getGraph());
                parcoursGeo = Parcours.MeilleurAll(voyageGeo.getGraph(), voyageGeo.getGraph().getPoint(1));
                voyageGeo.setParcours(parcoursGeo);
                parcoursGeo.setGraph(voyageGeo.getGraph());
                voyageGeo.exportforEval(parcoursGeo, i + 1);
            }
        }
    }
}
