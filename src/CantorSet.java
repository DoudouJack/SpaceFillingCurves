import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


class CantorSet{
    // TODO: find out why it breaks after the sixth iteration. for now, limit at 6 iterations

void cantor(GraphicsContext gc, double x, double y, float iter, double hue, double scale, double opacity, double cVariance ) {
    Color startCol = Color.hsb(hue,1.0,1.0, opacity/100);
    gc.setLineWidth(1+scale/8);
    gc.setStroke( startCol );
        if (iter >=6) {
        gc.strokeLine(x,y,x+iter,y);
        y += 20+scale/8;
        cantor(gc, x,y,iter/3, hue+cVariance, scale, opacity, cVariance);
        cantor(gc,x+iter*2/3,y,iter/3, hue+cVariance, scale, opacity, cVariance);
        }
        }
}
