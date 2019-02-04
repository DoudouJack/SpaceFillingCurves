import javafx.scene.canvas.GraphicsContext;

/**
 * This class is designed to handle parameters received from the GUI and then apply the correct drawing functions for the fractals.
 * @author Fee Di Mascio, Edouard Jacques, Julia Filzinger
 */

class Curve {
    private double scale;
    private double strokeW;
    private int iterations;
    private int curveType;
    private double colorHue;
    private int cVariance;
    private double opacity;

    /**
     * Curve constructor containing all values necessary to draw a fractal.
     * */

    Curve(int type, double sca, double strW, int iter, double colHue, int cVar, double opac) {
        curveType = type;
        strokeW = strW;
        scale = sca;
        iterations = iter;
        colorHue = colHue;
        cVariance = cVar;
        opacity = opac;
    }

    /**
     * Prints out all relevant information for easier debugging.
     */
    void printValues() {
        System.out.printf("Type: %d \nScale: %f \nStroke: %f \nIterations: %d \nColorHue: %f \nColor Variance: %d \nOpacity: %f \n", curveType, scale, strokeW, iterations, colorHue, cVariance, opacity);
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
            triangle.drawTriangle(gc, artwork, iterations, colorHue, cVariance, scale, opacity, strokeW);
        } else if (curveType == 1) {
            Tree tree = new Tree();
            tree.drawTree(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        } else if (curveType == 2) {
            KochSnowflake koch = new KochSnowflake();
            koch.drawKochSnowflake(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        } else if (curveType == 3) {
            Koch koch = new Koch();
            koch.drawKoch(gc, artwork, iterations, colorHue, scale, opacity, cVariance);
        } else if (curveType == 4) {
            Circles circle = new Circles();
            circle.drawCircle(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        } else if (curveType == 5) {
            Spiral spiral = new Spiral();
            spiral.drawSpiral(gc, 300 * scale, colorHue, cVariance, opacity, iterations, strokeW);
        } else if (curveType == 6) {
            Cantor cantor = new Cantor();
            cantor.drawCantor(gc, artwork, iterations, colorHue, opacity, scale, cVariance, strokeW);
        } else if (curveType == 7) {
            Rectcursive rect = new Rectcursive();
            rect.drawRect(gc, colorHue, opacity, cVariance, iterations, 0, 0, 800, 800, scale);
        }
    }
}