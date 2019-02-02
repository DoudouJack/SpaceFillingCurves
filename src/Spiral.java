import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Spiral {
    void drawCircle(GraphicsContext gc, double x, double y, double radius, double colorHue, double cVariance, double opacity) {
        Color startCol = Color.hsb(colorHue,1.0,1.0, opacity/100);
        gc.setLineWidth(3);
        gc.setStroke( startCol );
        gc.strokeArc(x, y, radius, radius, 90.0, 360.0, ArcType.CHORD);
        if(radius > 2) {
            radius *= 0.75f;
                    drawCircle(gc, x, y, radius, colorHue+cVariance, cVariance, opacity);
        }
    }
}
