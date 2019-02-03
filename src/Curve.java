import javafx.scene.canvas.GraphicsContext;

/**
 * This class is designed to handle parameters received from the GUI and then apply the correct drawing functions for the fractals.
 * @author Fee Di Mascio, Edouard Jacques, Julia Filzinger
 */

class Curve {
    private int scale;
    private int iterations;
    private int curveType;
    private double colorHue;
    private int cVariance;
    private double opacity;

    /**
     * Curve constructor containing all values necessary to draw a fractal.
     * */

    Curve(int type, int sca, int iter, double colHue, int cVar, double opac) {
        curveType = type;
        scale = sca;
        iterations = iter;
        colorHue = colHue;
        cVariance = cVar;
        opacity = opac;
    }

    // This is for testing whether the GUI passes all the right values

    /**
     * Prints out all relevant information for easier debugging.
     */
    void printValues() {
        System.out.printf("Type: %d \nScale: %d \nIterations: %d \nColorHue: %f \nColor Variance: %d \nOpacity: %f \n", curveType, scale, iterations, colorHue, cVariance, opacity);
    }
    // BITTE KEINE XPOS UND YPOS BENUTZEN DA DIE FUNKTIONEN DRAW SELBER CHECKEN, DASS DAS OBJEKT IN DER MITTE
    // SO GROSS WIE MOGLICH GEZEICHNET WIRD!
    // write a function that triggers the draw function depending on curveType (int)
    // depending on the choice, create new object from selected type
    // then draw

    /**
     * Creates a new object and calls its draw method depending on the item in the GUI's drop-down menu that was selected.
     */

    void mainDraw(Artwork artwork) {
        GraphicsContext gc = artwork.getCurrentLayer();
        if (curveType == 0) {
            Sierpinski triangle = new Sierpinski();
            triangle.drawTriangle(gc, artwork, iterations, colorHue, cVariance, scale, opacity);
        } else if (curveType == 3) {
            //BITTE DIE NEUE KLASSE CANTOR BENUTZEN!!
            Cantor cantor = new Cantor();
            cantor.drawCantor(gc, artwork, iterations, colorHue, opacity, scale, cVariance);
        } else if (curveType == 2) {
            Tree tree = new Tree();
            tree.drawTree(gc, artwork, iterations, colorHue, scale, opacity, cVariance);
        } else if (curveType == 1) {
            Spiral spiral = new Spiral();
            spiral.drawSpiral(gc, 300 * scale, colorHue, cVariance, opacity);
        } else if (curveType == 4) {
            Circles circle = new Circles();
            circle.drawCircle(gc, artwork, iterations, colorHue, scale, opacity, cVariance);
        } else if (curveType == 5) {
            Koch koch = new Koch();
            koch.drawKoch(gc, artwork, iterations, colorHue, scale, opacity, cVariance);
        } else if (curveType == 6) {
            Rectcursive rect = new Rectcursive();
            rect.drawRect(gc, colorHue, opacity, cVariance, iterations, 0, 0, 800, 800); //emergency function in case nobody can fix Koch in time, lol
        }
    }
}