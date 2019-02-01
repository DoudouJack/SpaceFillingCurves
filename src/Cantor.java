import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cantor {
    GraphicsContext gc;
    double x1, x2;
    double y = 1.00;
    float lineSpacing = -30; //spacing between vertical lines



    void drawCantor(GraphicsContext gc, Artwork artwork, double x, double y, int i, double hue, double scale){
        this.gc = gc;
        Color strColor = Color.hsb(170, 1.0, 1.0);
        gc.setStroke(strColor);
        gc.setLineWidth(2);
        gc.setFill(strColor);

        double squareSize = Math.min(artwork.getWidth(), artwork.getHeight());

        this.x1 = artwork.getWidth()/2 - squareSize/2 + squareSize*0.1;
        this.x2 = artwork.getWidth()/2 + squareSize/2 - squareSize*0.1;
        //this.y = -500;


        if (i == 1) {
            gc.strokeLine(x1, y, x2, y);
        } else {
            displayCantors(i,x1,x2,y);
        }
    }

    void displayCantors(int iter, double x1, double x2, double y){
        gc.strokeLine(x1, y, x2, y);

            y += lineSpacing;
            double newLineLength = (x2 - x1)/3;

/*            //draw left line
            gc.strokeLine(x1,y,x1+newLineLength,y);

            //draw right line
            gc.strokeLine(x2-newLineLength,y,x2,y);*/

            if (iter > 1){
                displayCantors(iter-1, x1, x1+newLineLength, y);
                displayCantors(iter-1, x2-newLineLength, x2, y);
            }

    }
}
