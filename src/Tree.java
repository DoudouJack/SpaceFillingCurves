import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class that draws a tree from the bottom center point of the canvas.
 * @author Julia Filzinger, Fee Di Mascio, Edouard Jacques
 */


class Tree {

    private double x1, y1, angle;
    GraphicsContext gc;

    /**
     * Calculates start size and position based on canvas size and calls the recursive method
     */
    void drawTree(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance, double stroke) {
        this.gc = gc;
        scale = scale*5;

        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(stroke);
        x1 = artwork.getWidth()/2.0;
        y1 = artwork.getHeight();
        angle = -90;
        displayTrees(i, x1, y1, angle, hue, variance, opacity, scale);
    }

    /**
     * calculates the next point, draws the line then draws the two next lines recursively
     */
    private void displayTrees(int i, double x1, double y1, double angle, double hue, int variance, double opacity, double scale) {
        if (i == 0) return;
        double x2 = (x1 + (int) (Math.cos(Math.toRadians(angle)) * i * scale));
        double y2 = (y1 + (int) (Math.sin(Math.toRadians(angle)) * i * scale));

        double newColor = hue + variance;
        Color color = Color.hsb(newColor, 1.0, 1.0, opacity / 100);
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
        displayTrees(i - 1, x2, y2, angle - 20, newColor, variance,  opacity, scale);
        displayTrees(i - 1, x2, y2, angle + 20, newColor, variance, opacity, scale);
    }
}
