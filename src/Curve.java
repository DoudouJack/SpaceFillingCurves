import javafx.scene.canvas.GraphicsContext;
import org.w3c.dom.css.Rect;

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
            koch.drawKoch(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        } else if (curveType == 4) {
            Circles circle = new Circles();
            circle.drawCircle(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        }else if (curveType == 5) {
                Circles2 circle = new Circles2();
                circle.drawCircle(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        } else if (curveType == 6) {
            Spiral spiral = new Spiral();
            spiral.drawSpiral(gc, 300 * scale, colorHue, cVariance, opacity, iterations, strokeW);
        } else if (curveType == 7) {
            Cantor cantor = new Cantor();
            cantor.drawCantor(gc, artwork, iterations, colorHue, opacity, scale, cVariance, strokeW);
        }else if (curveType == 8) {
            Squares rect = new Squares();
            rect.drawSquares(gc, artwork, iterations, colorHue, scale, opacity,cVariance, strokeW);
        } else if (curveType == 9) {
            Rectcursive rect = new Rectcursive();
            rect.drawRect(gc, colorHue, opacity, cVariance, iterations, 0, 0, 800, 800, scale);
//        } else if (curveType == 9) {
//            SquareCurve squareCurve = new SquareCurve();
//            squareCurve.drawStart(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        } else if (curveType == 10){
            DragonCurve dragon = new DragonCurve(gc, artwork, iterations, colorHue, scale, opacity, cVariance, strokeW);
        }

    }
}