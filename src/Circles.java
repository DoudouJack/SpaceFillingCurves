import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circles {

    GraphicsContext gc;
    double radius;      //radius of first circle
    double xMid, yMid;  //center point (x,y) of circle

    void drawCircle(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance) {

        this.gc = gc;
        radius = artwork.getWidth() / 5*scale;
        xMid = artwork.getWidth() / 2*scale;
        yMid = artwork.getWidth() / 2*scale;

        if (scale > 1){
            radius = (artwork.getWidth() / 5*scale);
            xMid = (artwork.getWidth() / 2*scale)-(scale-1)*400;
            yMid = (artwork.getWidth() / 2*scale)-(scale-1)*400;
        }

        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(3);
        gc.setFill(strColor);



        gc.strokeLine(0, yMid, artwork.getWidth()-1, yMid);
        gc.strokeOval(xMid-radius,yMid-radius,radius*2,radius*2);
        displayCircles(i-1,xMid,yMid,radius,hue,variance,opacity);



    }

    void displayCircles(int i, double xMid, double yMid, double radius, double hue, int variance, double opacity){
        if (i == 0) return;

        //used to position left and right circles
        double xLeft = xMid - radius;
        double yLeft = yMid;

        double xRight = xMid + radius;
        double yRight = yMid;

        if (variance == 1) {

            double newColor = hue - 50;
            Color color = Color.hsb(newColor, 1.0, 1.0, opacity / 100);
            gc.setStroke(color);

            //draw circle to the left
            gc.strokeOval(xLeft - radius / 2, yLeft - radius / 2, radius, radius);

            //draw circle to the right
            gc.strokeOval(xRight - radius / 2, yRight - radius / 2, radius, radius);


            displayCircles(i - 1, xLeft, yLeft, radius / 2, newColor, variance, opacity);
            displayCircles(i - 1, xRight, yRight, radius / 2, newColor, variance, opacity);
        } else {
            double newColor = hue;
            gc.strokeOval(xLeft - radius / 2, yLeft - radius / 2, radius, radius);

            //draw circle to the right
            gc.strokeOval(xRight - radius / 2, yRight - radius / 2, radius, radius);


            displayCircles(i - 1, xLeft, yLeft, radius / 2, newColor, variance, opacity);
            displayCircles(i - 1, xRight, yRight, radius / 2, newColor, variance, opacity);

        }

    }

}
