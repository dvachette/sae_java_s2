/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import back.Graph;
import back.Point;
import back.PointEuclidien;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

//modifier pour pouvoir mettre les numeros id et la nombres de mesure du repère

/**
 *
 * @author anqna
 */
public class GMapEucli extends JComponent{
    private ArrayList<PointEuclidien> listePoints;
    private ArrayList<PointEuclidien> listePointsConvertis;
    private double[] scaleX; //[a,b] qui permet de mettre à l'échelle X avec (X -b)*a + marges
    private double[] scaleY; //idem avec y
    private final int margeInit = 50;
    private int margeX;
    private int margeY;
    //a modifier pourgarder la plus grande etendue et centrer l'autre axe 
    
    //graph.getPoints() -> tree map de integer et t extends point
    //new ArrayList<Point>(graph.getPoints().values());
    // convertir point en point euclidien if (pt instanceof PointEuclidien)

    public GMapEucli(Graph<? extends Point> gr){
        super();
        ArrayList listeTemp = new ArrayList(gr.getPoints().values());
        this.listePoints = new ArrayList();
        for (int i=0; i<listeTemp.size(); i++){
            if(listeTemp.get(i) instanceof PointEuclidien){
                listePoints.add((PointEuclidien) listeTemp.get(i));
            }
        }
        
        this.listePointsConvertis = new ArrayList();
        setPreferredSize(new Dimension(400+margeInit,400+margeInit));
        this.setScales();
        this.miseEnEchelle();
           
    }
    
    private void setScales(){
        double maxX = listePoints.get(0).getX();
        double maxY = listePoints.get(0).getY();
        double minX = maxX;
        double minY = maxY;
        double x, y;
        scaleY = new double[2];
        scaleX = new double[2];
        int i, size = listePoints.size();
        for(i=0; i<size; i++){ //récupération des abscisses et ordoonnées maximales et minimales
            x = listePoints.get(i).getX();
            y = listePoints.get(i).getY();
            if(maxX < x){
                maxX = x;
            } else if (minX > x){
                minX = x;
            }
            if(maxY < y){
                maxY = y;
            } else if (minY > y){
                minY = y;
            }
        }
        
        double etendue;
        //coef de proportion déterminé sur l'etendue la plus grande
        //marges X et Y fixées pour centrer les points
        if ((maxX-minX) >= (maxY - minY)){
            etendue = maxX - minX;
            margeX = margeInit;
            margeY = (int) (margeInit + ((this.getPreferredSize().height-(maxY-minY))/2 ));
            scaleX[0] = scaleY[0] = (this.getPreferredSize().width-(margeInit*2))/etendue;
        } else {
            etendue = maxY - minY;
            scaleY[0] = scaleX[0] = (this.getPreferredSize().height-(margeInit*2))/etendue;
            margeY = margeInit;
            margeX = (int) ((this.getPreferredSize().width-((maxX-minX)*scaleX[0]))/2 );
        }
        
        scaleX[1] = minX;
        scaleY[1] = minY;
        
        
        
        
        
    }
    
    /**
     * remplit la listePointsConvertis avec des points représentant les différents points de listePoints mais mis à l'échelle de la carte
     */
    private void miseEnEchelle(){
        listePointsConvertis.clear();
        int i;
        double x, y;
        for(i=0; i<listePoints.size(); i++){
            x = listePoints.get(i).getX()-scaleX[1];
            x = x*(scaleX[0]);
            x = x + margeX;
            
            y = listePoints.get(i).getY()-scaleY[1];
            y = y*(scaleY[0]);
            y = y + margeY;
            listePointsConvertis.add(new PointEuclidien(x, y, listePoints.get(i).getId()));
        }   
    }
    
    @Override
    protected void paintComponent(Graphics gr){
        Graphics2D g = (Graphics2D)(gr);
        g.setColor(Color.cyan);
        g.fillRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);
        
        g.setColor(Color.gray);
        g.fillRect(margeInit-10, margeInit-10, this.getPreferredSize().width-(margeInit*2)+20, this.getPreferredSize().height-(margeInit*2)+20);
        
        double origineX = scaleX[1] - (10/scaleX[0]);
        double origineY = scaleY[1] - (10/scaleY[0]);
        
        g.setColor(Color.black);
        g.drawLine(margeInit-10, this.getPreferredSize().height-(margeInit/2), this.getPreferredSize().width-(margeInit-10), this.getPreferredSize().height-(margeInit/2));
        for(int i=0; i<(GMapEucli.this.getPreferredSize().width-(margeInit-10))-30 ; i+=30){
            g.drawLine((margeInit-10)+i, (this.getPreferredSize().height-(margeInit/2))-5, (margeInit-10)+i, (this.getPreferredSize().height-(margeInit/2))+5);
        }
        g.drawLine((margeInit-10)/2, margeInit-10, (margeInit-10)/2, this.getPreferredSize().height-(margeInit-10));
        for(int i=0; i<(GMapEucli.this.getPreferredSize().height-(margeInit-10))-30 ; i+=30){
           g.drawLine(((margeInit-10)/2)-5, this.getPreferredSize().height-(margeInit-10)-i, ((margeInit-10)/2)+5, this.getPreferredSize().height-(margeInit-10)-i);
        }
        
        g.setStroke(new BasicStroke(4));
        int diametre = 1;
        int i;
        int size = listePoints.size();
        for(i = 0; (i+1)<size; i++){
            //g.drawLine((int)(liste.get(i).getX()), (int)(liste.get(i).getY()), (int)(liste.get(i+1).getX()), (int)(liste.get(i+1).getY()));
            System.out.println(listePoints.get(i).getX()+" "+listePoints.get(i).getY());
            System.out.println(listePointsConvertis.get(i).getX()+" "+listePointsConvertis.get(i).getY());
            g.drawOval((int)(listePointsConvertis.get(i).getX())-(diametre/2), (int)(this.getPreferredSize().height - listePointsConvertis.get(i).getY())-(diametre/2), diametre, diametre);
        }
        System.out.println(listePoints.get(i).getX()+" "+listePoints.get(i).getY());
        System.out.println(listePointsConvertis.get(i).getX()+" "+listePointsConvertis.get(i).getY());
        g.drawOval((int)(listePointsConvertis.get(i).getX())-(diametre/2), (int)(this.getPreferredSize().height - listePointsConvertis.get(i).getY())-(diametre/2), diametre, diametre);
    }
}
