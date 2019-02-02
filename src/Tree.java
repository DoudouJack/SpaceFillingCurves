import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Made Tree its own thing.

public class Tree {
    void drawBranch(GraphicsContext gc, int iterations, double startX, double startY, double scale, double colorHue, double opacity, double cVariance){
        if(iterations==0) return;
        Color startCol = Color.hsb(colorHue,1.0,1.0, opacity/100);
        gc.setLineWidth(3);
        gc.setStroke( startCol );
        gc.strokeLine(startX, startY, startX+scale, startY-scale-iterations/2);
        gc.strokeLine(startX, startY, startX-scale, startY-scale-iterations/2);

        drawBranch(gc, iterations-1, startX+scale, startY-scale-iterations/2, scale/0.8, colorHue+cVariance, opacity, cVariance);
    }

}
