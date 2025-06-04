/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package front;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author anqna
 */
public class GMapEucli extends JComponent{
    private ArrayList<Point> liste;
    private double[] scaleX; //[a,b] qui permet de mettre à l'échelle X avec aX - b
    private double[] scaleY; //idem avec y
    private final int marge = 10;
    //a modifier pourgarder la plus grande etendue et centrer l'autre axe 

    public GMapEucli(ArrayList<Point> liste){
        super();
        this.liste = liste;
        
        setPreferredSize(new Dimension(400,400));    
    }
    
    private void setScales(){
        double maxX = liste.get(0).getX();
        double maxY = liste.get(0).getY();
        double minX = maxX;
        double minY = maxY;
        double x, y;
        int i, size = liste.size();
        for(i=0; i<size; i++){
            x = liste.get(i).getX();
            y = liste.get(i).getY();
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
        
        double etendueX = maxX - minX;
        double etendueY = maxY - minY;
        
        scaleX[0] = this.getPreferredSize().width/etendueX;
        scaleX[1] = minX;
        
        scaleY[0] = this.getPreferredSize().height/etendueY;
        scaleY[1] = minY;
        
        
    }
    
    @Override
    protected void paintComponent(Graphics gr){
        Graphics2D g = (Graphics2D)(gr);
        //ImageIcon imageIcon = new ImageIcon("texture.jpg"); //background
        //Image image = imageIcon.getImage();
        //g.drawImage(image, 0,0, this);
        g.setStroke(new BasicStroke(4));
        int diametre = 2;
        int i;
        int size = liste.size();
        for(i = 0; (i+1)<size; i++){
            //g.setColor(new Color(51,51,51));
            //g.drawLine((int)(liste.get(i).getX()), (int)(liste.get(i).getY()), (int)(liste.get(i+1).getX()), (int)(liste.get(i+1).getY()));
            g.setColor(new Color(122,122,122));
            g.drawOval((int)(liste.get(i).getX())-(diametre/2), (int)(liste.get(i).getY())-(diametre/2), diametre, diametre);
        }
        g.drawOval((int)(liste.get(i).getX())-(diametre/2), (int)(liste.get(i).getY())-(diametre/2), diametre, diametre);
    }
}
