import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Julia Filzinger
 * */


public class Circles2 {

    GraphicsContext gc;
    double radius;
    double x, y;

    void drawCircle(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance, double stroke) {
        scale = artwork.getWidth()/2.0 - (scale*100);

        this.gc = gc;
        radius = (artwork.getHeight()/2.0)-scale;
        x = scale;
        y = scale;

        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(stroke);
        gc.setFill(strColor);

        gc.strokeOval(x, y, radius*2, radius*2);
        displayCircles(i-1, x, y, radius/2.0, hue+variance, variance, opacity);
    }

    void displayCircles(int i, double x, double y, double radius, double hue, int variance, double opacity){
        if (i == 0) return;

        double newX1 = x-radius;
        double newX2 = x+radius;
        double newX3 = x+(radius*3.0);

        double newY1 = y-radius;
        double newY2 = y+radius;
        double newY3 = y+(radius*3.0);


        Color color = Color.hsb(hue, 1.0, 1.0, opacity / 100);
        gc.setStroke(color);

        //draw circles
        double d = radius*2.0;
        gc.strokeOval(newX2, newY1, d, d);
        gc.strokeOval(newX1, newY2, d, d);
        gc.strokeOval(newX2, newY3, d, d);
        gc.strokeOval(newX3, newY2, d, d);

        displayCircles(i-1, newX2, newY1, radius/2.0, hue+variance, variance, opacity);
        displayCircles(i-1, newX1, newY2, radius/2.0, hue+(2*variance), variance, opacity);
        displayCircles(i-1, newX2, newY3, radius/2.0, hue+(3*variance), variance, opacity);
        displayCircles(i-1, newX3, newY2, radius/2.0, hue+(4*variance), variance, opacity);
    }

}
