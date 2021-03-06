import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.geometry.Point2D;

/**
 * @author Edouard Jacques
 */

class Sierpinski {

    private GraphicsContext gc;
    private double x1, y1, x2, y2, x3, y3;

    private double spacing, a;


    /**
     * Calculates start size and position based on canvas size and calls the recursive method
     */
    void drawTriangle(GraphicsContext gc, Artwork artwork, int i, double hue, int variance, double scale, double opacity, double stroke) {
        if (i>12) { i = 12; }

        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(stroke);

        double squareSize = Math.min(artwork.getWidth(), artwork.getHeight());

        spacing = squareSize * 0.025;

        x1 = (artwork.getWidth() / 2.0 - squareSize / 2 + spacing);
        y1 = (artwork.getHeight() / 2.0 + squareSize / 2 - spacing * 2);
        x2 = (artwork.getWidth() / 2.0 + squareSize / 2 - spacing);
        a = x2 - x1;
        y2 = y1;
        x3 = x1 + a / 2;
        y3 = y1 - (Math.sqrt(Math.pow(a, 2) - Math.pow(a / 2, 2)));

        Point2D A = new Point2D(x1 * scale-(scale-1)*400, y1 * scale-(scale-1)*400);
        Point2D B = new Point2D(x2 * scale-(scale-1)*400, y2 * scale-(scale-1)*400);
        Point2D C = new Point2D(x3 * scale-(scale-1)*400, y3 * scale-(scale-1)*400);

        displayTriangles(i-1, A, B, C, hue, variance, opacity );
    }

    /**
     * draws the triangle then draws the next 9 lines recursively
     */
    private void displayTriangles(int iter, Point2D a, Point2D b, Point2D c, double hue, int variance, double opacity){

        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
        gc.strokeLine(a.getX(), a.getY(), c.getX(), c.getY());
        gc.strokeLine(b.getX(), b.getY(), c.getX(), c.getY());

        Color color = Color.hsb(hue , 1.0, 1.0, opacity / 100);
        gc.setStroke(color);

        if (iter > 0) {
            displayTriangles(iter - 1, a, a.midpoint(b), a.midpoint(c), hue + variance, variance, opacity);
            displayTriangles(iter - 1, b, a.midpoint(b), b.midpoint(c), hue + (2*variance), variance, opacity);
            displayTriangles(iter - 1, c, c.midpoint(b), a.midpoint(c), hue + (3*variance), variance, opacity);
        }
    }
}
