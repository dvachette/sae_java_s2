/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

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

/**
 *
 * @author anqna
 */
public class GMapEucli extends JComponent{
    private ArrayList<PointEuclidien> listePoints;
    private ArrayList<PointEuclidien> listePointsConvertis;
    private double[] scaleX; //[a,b] qui permet de mettre à l'échelle X avec (X -b)*a + marges
    private double[] scaleY; //idem avec y
    private final int margeInit = 10;
    private int margeX;
    private int margeY;
    //a modifier pourgarder la plus grande etendue et centrer l'autre axe 

    public GMapEucli(ArrayList<PointEuclidien> liste){
        super();
        this.listePoints = liste;
        setPreferredSize(new Dimension(400,400));
        this.setScales();
        this.miseEnEchelle();
           
    }
    
    private void setScales(){
        double maxX = listePoints.get(0).getX();
        double maxY = listePoints.get(0).getY();
        double minX = maxX;
        double minY = maxY;
        double x, y;
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
            margeY = margeInit;
            margeX = (int) ((this.getPreferredSize().width-(maxX-minX))/2 );
            scaleY[0] = (this.getPreferredSize().height-(margeInit*2))/etendue;
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
            x = (listePoints.get(i).getX()-scaleX[1])*scaleX[0];
            y = (listePoints.get(i).getX()-scaleY[1])*scaleY[0];
            listePointsConvertis.add(new PointEuclidien(x, y, listePoints.get(i).getId()));
        }   
    }
    
    @Override
    protected void paintComponent(Graphics gr){
        Graphics2D g = (Graphics2D)(gr);
        //ImageIcon imageIcon = new ImageIcon("texture.jpg"); //background
        //Image image = imageIcon.getImage();
        //g.drawImage(image, 0,0, this);
        g.setStroke(new BasicStroke(4));
        int diametre = 1;
        int i;
        int size = listePoints.size();
        for(i = 0; (i+1)<size; i++){
            
            //g.setColor(new Color(51,51,51));
            //g.drawLine((int)(liste.get(i).getX()), (int)(liste.get(i).getY()), (int)(liste.get(i+1).getX()), (int)(liste.get(i+1).getY()));
            g.setColor(new Color(122,122,122));
            g.drawOval((int)(listePointsConvertis.get(i).getX())-(diametre/2), (int)(listePointsConvertis.get(i).getY())-(diametre/2), diametre, diametre);
        }
        g.drawOval((int)(listePointsConvertis.get(i).getX())-(diametre/2), (int)(listePointsConvertis.get(i).getY())-(diametre/2), diametre, diametre);
    }
}
