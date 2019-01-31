import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SierpinskiTriangle {

    GraphicsContext gc;

    void drawTriangle(GraphicsContext gc, double x, double y, float iter, double hue, double scale) {
        this.gc = gc;
        Color strColor = Color.hsb(170, 1.0, 1.0);
        gc.setStroke(strColor);
        gc.setLineWidth(3);
        gc.strokePolygon(new double[]{10,40,10,40}, new double[]{210,210,240,240}, 3);


    }
}
