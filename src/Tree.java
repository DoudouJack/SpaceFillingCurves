import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Tree {

    private double x1, x2, y1, y2, angle;
    GraphicsContext gc;


    void drawTree(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance) {
        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(2);
        gc.setFill(strColor);
        x1 = artwork.getWidth()/2;
        y1 = 800;
        angle = -90;
        displayTrees(i, x1, y1, angle, hue, variance, opacity);

    }


    private void displayTrees(int i, double x1, double y1, double angle, double hue, int variance, double opacity) {
        if (i == 0) return;
        double x2 = (x1 + (int) (Math.cos(Math.toRadians(angle)) * i * 10.0));
        double y2 = (y1 + (int) (Math.sin(Math.toRadians(angle)) * i * 10.0));

        double newColor = hue + variance;
        Color color = Color.hsb(newColor, 1.0, 1.0, opacity / 100);
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
        displayTrees(i - 1, x2, y2, angle - 20, newColor, variance,  opacity);
        displayTrees(i - 1, x2, y2, angle + 20, newColor, variance, opacity);
    }

}
