import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.geometry.Point2D;

public class Sierpinski {

    GraphicsContext gc;
    double x1, y1, x2, y2, x3, y3;

    double spacing, a;

    void drawTriangle(GraphicsContext gc, Artwork artwork, int i, double hue, int variance, double scale, double opacity) {
        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(3);
        gc.setFill(strColor);

        double squareSize = Math.min(artwork.getWidth(), artwork.getHeight());

        spacing = squareSize * 0.025;



        x1 = (artwork.getWidth() / 2 - squareSize / 2 + spacing);
        y1 = (artwork.getHeight() / 2 + squareSize / 2 - spacing * 2);
        x2 = (artwork.getWidth() / 2 + squareSize / 2 - spacing);
        a = x2 - x1;
        y2 = y1;
        x3 = x1 + a / 2;
        y3 = y1 - (Math.sqrt(Math.pow(a, 2) - Math.pow(a / 2, 2)));

        double transformation = scale*0.025;

            Point2D A = new Point2D(x1 * scale-(scale-1)*400, y1 * scale-(scale-1)*400);
            Point2D B = new Point2D(x2 * scale-(scale-1)*400, y2 * scale-(scale-1)*400);
            Point2D C = new Point2D(x3 * scale-(scale-1)*400, y3 * scale-(scale-1)*400);

            if (i == 1){

                gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
                gc.strokeLine(A.getX(), A.getY(), C.getX(), C.getY());
                gc.strokeLine(B.getX(), B.getY(), C.getX(), C.getY());

            } else {
                displayTriangles(i, A, B, C, hue, variance, opacity );
            }

    }

    void displayTriangles(int iter, Point2D a, Point2D b, Point2D c, double hue, int variance, double opacity){

        if (variance == 1) {




            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
            gc.strokeLine(a.getX(), a.getY(), c.getX(), c.getY());
            gc.strokeLine(b.getX(), b.getY(), c.getX(), c.getY());

            double newColor = hue - 50;
            Color color = Color.hsb(newColor, 1.0, 1.0, opacity / 100);
            gc.setStroke(color);

            if (iter > 1) {

                displayTriangles(iter - 1, a, a.midpoint(b), a.midpoint(c), newColor, variance, opacity);
                displayTriangles(iter - 1, b, a.midpoint(b), b.midpoint(c), newColor, variance, opacity);
                displayTriangles(iter - 1, c, c.midpoint(b), a.midpoint(c), newColor, variance, opacity);
            }

        } else {
            double newColor = hue;
            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
            gc.strokeLine(a.getX(), a.getY(), c.getX(), c.getY());
            gc.strokeLine(b.getX(), b.getY(), c.getX(), c.getY());

            if (iter > 1) {
                displayTriangles(iter - 1, a, a.midpoint(b), a.midpoint(c), newColor, variance, opacity);
                displayTriangles(iter - 1, b, a.midpoint(b), b.midpoint(c), newColor, variance, opacity);
                displayTriangles(iter - 1, c, c.midpoint(b), a.midpoint(c), newColor, variance, opacity);
            }
        }
    }
}
