//This is a skeleton as the program is a work in progress. 
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;

import java.util.*;
import java.io.*;

/* 
	This class is untested. Tests will commence elsewhen. Refer to commentary for information on accounted for issues.
*/

public class Curve{



    /* private float color;
        TODO: Determine handling of color with the group. */
    private int xPos;
    private int yPos;
    private int scale;
    private int iterations;
    private int curveType;
    private double colorHue;
    private int cVariance;
    private double opacity;
//    private Color convergenceColor = Color.WHITE;
//    private ColorSchema colorSchema = ColorSchema.GREEN;


/*
    public enum ColorSchema {

        GREEN, RED, YELLOW, BLUE, CYAN, MAGENTA
    }

    public void setConvergenceColor(Color convergenceColor) {
        this.convergenceColor = convergenceColor;
    }

    public Color getConvergenceColor() {
        return convergenceColor;
    }

    public ColorSchema getColorSchema() {
        return colorSchema;
    }

    public void setColorSchema(ColorSchema colorSchema) {
        this.colorSchema = colorSchema;
    }
*/

    public Curve(int x, int y, int type, int sca, int iter, double colHue, int cVar, double opac){ //NOTE: Remember to re-add color handling. TODO: Determine how to implement type handling via subclasses.
        xPos = x;
        yPos = y;
        curveType = type;
        scale = sca;
        iterations = iter;
        colorHue = colHue;
        cVariance = cVar;
        opacity = opac;
    }

    /*
    TODO: Adapt to changed set of attributes after setting up the GUI
    public Curve readFromFile() throws FileNotFoundException { // TODO: Implement a way to read multiple curves, presumably with a loop. Color handling.
        String ln;
        String[] parts;
        Scanner sc = new Scanner( new File("text.in") );
        ln = sc.nextLine();
        parts = ln.split("/");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        float trans = Float.parseFloat(parts[2]);
        int sca = Integer.parseInt(parts[3]);
        int iter = Integer.parseInt(parts[4]);
        int type = Integer.parseInt(parts[5]);
        return new Curve(x, y, trans, sca, iter, type);
    }
    */
    public void draw() { //To be implemented within each subclass.
                         //TODO: Consider whether the Curve class should be abstract (in which case the readFromFile() method would need to be implemented elsewhere) or if an interface would be appropriate.

    }

    // This is for testing whether the GUI passes all the right values
    public void printValues(){
        System.out.printf(
                "xPos: %d \nxPos: %d \nType: %d \nScale: %d \nIterations: %d \nColorHue: %f \nColor Variance: %d \nOpacity: %f \n"
                , xPos, yPos, curveType, scale, iterations, colorHue, cVariance, opacity
        );
    }
    // This is for testing if input of color and opacity works, by drawing a rectangle in the middle of the canvas
    public void testDrawing( Artwork artwork ){
        GraphicsContext gc = artwork.getCurrentLayer();
        Color startCol = Color.hsb(colorHue,1.0,1.0, opacity/100);
        gc.setStroke(startCol);
        gc.strokeRect(artwork.getWidth()/3, artwork.getHeight()/3, artwork.getWidth()/3, artwork.getHeight()/3);
    }
}