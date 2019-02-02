import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Made Tree its own thing.

/*
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
*/
public class Tree {

    double x1, x2, y1, y2, angle;
    GraphicsContext gc;


    void drawTree(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity, int variance) {
        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(3);
        gc.setFill(strColor);

        x1 = artwork.getWidth()/2;
        y1 = 800;
        //x2 = artwork.getWidth()/2;
        //y2 = artwork.getHeight()/3;

        angle = -90;

        //gc.strokeLine(x1, y1, x2, y2);

        displayTrees(i, x1, y1, angle, hue, variance, opacity);

    }


    private void displayTrees(int i, double x1, double y1, double angle, double hue, int variance, double opacity) {




        if (i == 0) return;
        double x2 = (x1 + (int) (Math.cos(Math.toRadians(angle)) * i * 10.0));
        double y2 = (y1 + (int) (Math.sin(Math.toRadians(angle)) * i * 10.0));

        if (variance == 1) {

            double newColor = hue - 50;
            Color color = Color.hsb(newColor, 1.0, 1.0, opacity / 100);
            gc.setStroke(color);
            gc.strokeLine(x1, y1, x2, y2);
            displayTrees(i - 1, x2, y2, angle - 20, newColor, variance,  opacity);
            displayTrees(i - 1, x2, y2, angle + 20, newColor, variance, opacity);
        } else {
            double newColor = hue;
            gc.strokeLine(x1, y1, x2, y2);
            displayTrees(i - 1, x2, y2, angle - 20, newColor, variance,  opacity);
            displayTrees(i - 1, x2, y2, angle + 20, newColor, variance, opacity);
        }

    }

    /*public void displayTrees(int n, double x, double y, double a, double branchRadius) {

        if(n > 0){
            double cx = x + Math.cos(a) * branchRadius;
            double cy = y + Math.sin(a) * branchRadius;
            gc.strokeLine(x, y, cx, cy);
            if (n == 0) return;

            drawTree(n - 1, cx, cy, a + bendAngle - branchAngle, branchRadius * branchRatio);
            drawTree(n - 1, cx, cy, a + bendAngle + branchAngle, branchRadius * branchRatio);
            drawTree(n - 1, cx, cy, a + bendAngle, branchRadius * (1 - branchRatio));
        }*/
    }
