import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Edouard Jacques
 * */

class Koch {
    private GraphicsContext gc;
    private double x1, y1, x5, y5;

    /**
     * Calculates start size and position based on canvas size, draws the first line, then calls the recursive method
     */
    void drawKoch(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance, double strokeW){
        if (i>10) { i = 10; }

        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(strokeW);
        gc.setFill(strColor);

        scale = (artwork.getWidth()/2.0)-(scale*500.0);
        x1 = scale;
        y1 = artwork.getHeight()/3.0*2.0  -scale/2;
        x5 = artwork.getWidth() - scale;
        y5 = y1;

        if (i == 1){
            gc.strokeLine(x1, y1, x5, y5);
        } else {
            displayKochs(i-1, x1, y1, x5, y5, hue, opacity, variance, gc);
        }

    }

    /**
     * calculates each next point recursively then draws the lines
     */
    void displayKochs(int i, double x1, double y1, double x5, double y5, double hue, double opacity, int variance, GraphicsContext gc){

        if (i > 0) {

            double deltaX, deltaY, x2, y2, x3, y3, x4, y4;
            deltaX = x5 - x1;
            deltaY = y5 - y1;

            //Calculate new dots
            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            x3 = (int) (0.5 * (x1 + x5) - (Math.sqrt(3) / 6) * (y1 - y5));
            y3 = (int) (0.5 * (y1 + y5) - (Math.sqrt(3) / 6) * (x5 - x1));

            x4 = x1 + 2 * deltaX / 3;
            y4 = y1 + 2 * deltaY / 3;

            Color color = Color.hsb(hue, 1.0, 1.0, opacity / 100);
            gc.setStroke(color);

            displayKochs(i - 1, x1, y1, x2, y2, hue+variance, opacity, variance, gc);
            displayKochs(i - 1, x2, y2, x3, y3, hue+(2*variance), opacity, variance, gc);
            displayKochs(i - 1, x3, y3, x4, y4, hue+(3*variance), opacity, variance, gc);
            displayKochs(i - 1, x4, y4, x5, y5, hue+(4*variance), opacity, variance, gc);

        } else {
            gc.strokeLine(x1,y1,x5,y5);
        }
    }
}
