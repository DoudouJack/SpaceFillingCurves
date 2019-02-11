import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class to draw squares that fill up the space.
 * @author Julia Filzinger
 * */


public class Squares {

    GraphicsContext gc;
    double size;
    double x, y;

    /**
     * Draws the first square, size depending on scale.
     * @param gc GraphicsContext to draw in.
     * @param artwork current artwork to draw on.
     * @param i number of iterations from GUI
     * @param hue color hue from GUI
     * @param scale scale factor from GUI
     * @param opacity opacity from GUI
     * @param variance color variance from GUI.
     * @param stroke  stroke width from GUI.
     */

    void drawSquares(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance, double stroke) {
        scale = artwork.getWidth()/2.0 - (scale*100);

        this.gc = gc;
        size = (artwork.getHeight()/2.0)-scale;
        x = scale;
        y = scale;

        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(stroke);
        gc.setFill(strColor);

        gc.strokeRect(x, y, size*2, size*2);
        displaySquares(i-1, x, y, size/2.0, hue+variance, variance, opacity);
    }

    /**
     * Shows more squares calling itself recursively depending on the number of iterations (left).
     * @param i current iteration.
     * @param x last x coordinate of top left point.
     * @param y last y coordinate of top left point.
     * @param size height and width for this iteration's squares.
     * @param hue calculated new color hue.
     * @param variance color variance from GUI.
     * @param opacity opacity from GUI.
     */

    void displaySquares(int i, double x, double y, double size, double hue, int variance, double opacity){
        if (i == 0) return;

        double newX1 = x-size;
        double newX2 = x+size;
        double newX3 = x+(size*3.0);

        double newY1 = y-size;
        double newY2 = y+size;
        double newY3 = y+(size*3.0);


        Color color = Color.hsb(hue, 1.0, 1.0, opacity / 100);
        gc.setStroke(color);

        //draw squares
        double d = size*2.0;
        gc.strokeRect(newX2, newY1, d, d);
        gc.strokeRect(newX1, newY2, d, d);
        gc.strokeRect(newX2, newY3, d, d);
        gc.strokeRect(newX3, newY2, d, d);

        displaySquares(i-1, newX2, newY1, size/2.0, hue+variance, variance, opacity);
        displaySquares(i-1, newX1, newY2, size/2.0, hue+(2*variance), variance, opacity);
        displaySquares(i-1, newX2, newY3, size/2.0, hue+(3*variance), variance, opacity);
        displaySquares(i-1, newX3, newY2, size/2.0, hue+(4*variance), variance, opacity);
    }
}
