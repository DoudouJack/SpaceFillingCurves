import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for recursive rectangles.
 * @author Fee Di Mascio
 */

class Rectcursive {
    /**
     * Draws rectangles that progressively get smaller using the strokeRect() method.
     * @param w Width of the rectangle.
     * @param h Height of the rectangle.
     */
    void drawRect(GraphicsContext gc, double hue, double opacity, int variance, int iterations, double x, double y, double w, double h) {
        hue = hue + variance;
        Color strColor = Color.hsb(hue, 1.0, 1.0, opacity / 100);
        gc.setStroke(strColor);
        gc.setLineWidth(3);

        gc.strokeRect(x, y, w, h);

        if(iterations != 0){
        drawRect(gc, hue, opacity, variance, iterations-1, x+20, y+20, w-20, h-20);
    }
}
}