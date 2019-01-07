import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.applet.*;

public class SierpinskiTriangle {
    private GraphicsContext c;
    private int dMin=8;    // limite à la récursion en pixels

    public void paint(GraphicsContext c) {
        this.c = c;
        int d  = 1024;    // base (largeur du triangle)
        int x0 =   50;    // distance de gauche
        int y0 =   50;    // distance du haut
        int h  = (int)(d*Math.sqrt(3)/2);    // hauteur
        // adapté à un triangle équilatéral

        //  spécification du triangle principal: points A, B, C
        int xA=x0,      yA=y0+h;    // (bas-gauche)
        int xB=x0+d,    yB=y0+h;    // (bas-droite)
        //  int xB=x0,      yB=y0;      // (haut-gauche)
        //  int xB=x0+d,    yB=y0;      // (haut-droite)
        int xC=x0+d/2,  yC=y0;    // triangle équilatéral (haut-milieu)
        //  int xC=x0,      yC=y0;    // (haut-gauche)
        // triangle perpendiculaire, angle droit près A
        //  int xC=x0+d,    yC=y0;    // (haut-droite)
        // triangle perpendiculaire, angle droit près B
        int[] x = { xA, xB, xC };
        int[] y = { yA, yB, yC };

        drawSierpinskiTriangle( x, y, d/2 );     // démarrer la récursion
    }

    private void drawSierpinskiTriangle ( int[] x, int[] y, int d ) {

            // milieus des cotés du triangle:
            int xMc = (x[0]+x[1])/2,    yMc = (y[0]+y[1])/2;
            int xMb = (x[0]+x[2])/2,    yMb = (y[0]+y[2])/2;
            int xMa = (x[1]+x[2])/2,    yMa = (y[1]+y[2])/2;

            int[] xNouveau1 = { x[0], xMc, xMb };
            int[] yNouveau1 = { y[0], yMc, yMb };
            drawSierpinskiTriangle ( xNouveau1, yNouveau1, d/2 );    // récursion

            int[] xNouveau2 = { x[1], xMc, xMa };
            int[] yNouveau2 = { y[1], yMc, yMa };
            drawSierpinskiTriangle ( xNouveau2, yNouveau2, d/2 );    // récursion

            int[] xNouveau3 = { x[2], xMb, xMa };
            int[] yNouveau3 = { y[2], yMb, yMa };
            drawSierpinskiTriangle ( xNouveau3, yNouveau3, d/2 );    // récursion

    }
}