import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 * Class for a spiral-like shape.
 * @author Fee Di Mascio
 */


class Spiral {
    /**
     * Draws a spiral using the strokeArc() method.
     * */
    void drawSpiral(GraphicsContext gc, double radius, double colorHue, double cVariance, double opacity, int iterations) {
        colorHue = colorHue + cVariance;
        Color startCol = Color.hsb(colorHue,1.0,1.0, opacity/100);
        gc.setLineWidth(3);
        gc.setStroke( startCol );
        gc.strokeArc(0, 0, radius, radius, 90.0, 360.0, ArcType.CHORD);
        if(radius > 2 && iterations != 0) {
            radius *= 0.75f;
                    drawSpiral(gc, radius, colorHue, cVariance, opacity, iterations-1);
        }
    }
}
