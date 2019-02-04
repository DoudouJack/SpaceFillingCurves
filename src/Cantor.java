import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Class for Cantor Set
 * @author Edouard Jacques
 */

public class Cantor {

    GraphicsContext gc;
    double x1, x2, y;
    double lineSpacing = 20; //spacing between vertical lines

    /**
     * Calculates start size and position based on canvas size and calls the recursive method
     */
    void drawCantor(GraphicsContext gc, Artwork artwork, int i, double hue, double opacity, double scale, double variance, double stroke){
        this.gc = gc;

        Color startCol = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setLineWidth(scale*stroke);
        gc.setStroke( startCol );

        lineSpacing = lineSpacing*scale;

        double squareSize = Math.min(artwork.getWidth(), artwork.getHeight());

        this.x1 = (artwork.getWidth()/2 - squareSize/2 + squareSize*0.1) * scale;
        this.x2 = (artwork.getWidth()/2 + squareSize/2 - squareSize*0.1) * scale;
        this.y = squareSize/3;

        displayCantors(i-2,x1,x2,y, variance, hue, opacity);
    }

    /**
     * draws first and two additional smaller lines recursively
     */
    void displayCantors(int iter, double x1, double x2, double y, double variance, double hue, double opacity){
        gc.strokeLine(x1, y, x2, y);

        y += lineSpacing;
        double newLineLength = (x2 - x1)/3;

        //draw left line
        gc.strokeLine(x1,y,x1+newLineLength,y);

        //draw right line
        gc.strokeLine(x2-newLineLength,y,x2,y);

        double newColor = hue + variance;
        Color color = Color.hsb(newColor, 1.0, 1.0, opacity / 100);
        gc.setStroke(color);

        if (iter > 0){
            displayCantors(iter-1, x1, x1+newLineLength, y, variance, newColor, opacity);
            displayCantors(iter-1, x2-newLineLength, x2, y, variance, newColor, opacity);
        }
    }
}
