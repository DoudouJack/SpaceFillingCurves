import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class to draw a Koch snowflake. Subclass of Koch.
 * @author Julia Filzinger
 *
 */

class KochSnowflake extends Koch{
    private GraphicsContext gc;
    private double x1, y1, x5, x3, y3, h;


    void drawKochSnowflake(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance, double strokeW){
        if (i>11) { i = 11; }

        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(strokeW);
        gc.setFill(strColor);

        scale = (artwork.getWidth()/2.0)-(scale*100.0);
        x1 = scale;
        x5 = artwork.getWidth()-scale;
        x3 = x5-((x5-x1)/2);
        h = Math.sqrt( Math.pow( (x5-x1), 2) - Math.pow( ((x5-x1)/2), 2 ) );
        y1 = scale+(h/3.6);
        y3 = y1 + h;

        if (i == 1){
            gc.strokeLine(x1, y1, x5, y1);
            gc.strokeLine(x1, y1, x3, y3);
            gc.strokeLine(x3, y3, x5, y1);
        } else {
            displayKochs(i-1, x1, y1, x5, y1, hue, opacity, variance, gc);
            displayKochs(i-1, x3, y3, x1, y1, hue, opacity, variance, gc);
            displayKochs(i-1, x5, y1, x3, y3, hue, opacity, variance, gc);
        }

    }
}

