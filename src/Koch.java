import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Koch {
    GraphicsContext gc;
    double x1, y1, x5, y5;


    void drawKoch(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance){
        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(3);
        gc.setFill(strColor);


        x1 = 10;
        y1 = artwork.getHeight()/3*2;
        x5 = artwork.getWidth() - 10;
        y5 = y1;

        if (i == 1){
            gc.strokeLine(x1, y1, x5, y5);
        } else {
            displayKochs(i, x1, y1, x5, y5, hue, opacity, variance);
        }


    }

    void displayKochs(int i, double x1, double y1, double x5, double y5, double hue, double opacity, int variance){

        if (i > 0) {

            //JULIA bitte checken :))))
            //2. Punkt bei 10 iterations geht's, bei mehr bricht das ab.
            //Soll ich einfach if (i>10) {return;} machen?
            /*double newColor = hue + variance;

            Color color = Color.hsb(newColor, 1.0, 1.0, opacity / 100);
            gc.setStroke(color);*/

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



            //JULIA: hue wird durch newColor ersetzt, habe es unten in block comments
            displayKochs(i - 1, x1, y1, x2, y2, hue, opacity, variance);
            displayKochs(i - 1, x2, y2, x3, y3, hue, opacity, variance);
            displayKochs(i - 1, x3, y3, x4, y4, hue, opacity, variance);
            displayKochs(i - 1, x4, y4, x5, y5, hue, opacity, variance);

            /*displayKochs(i - 1, x1, y1, x2, y2, newColor, opacity, variance);
            displayKochs(i - 1, x2, y2, x3, y3, newColor, opacity, variance);
            displayKochs(i - 1, x3, y3, x4, y4, newColor, opacity, variance);
            displayKochs(i - 1, x4, y4, x5, y5, newColor, opacity, variance);*/

        } else {
            gc.strokeLine(x1,y1,x5,y5);
        }
    }
}
