import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


class CantorSet{
    GraphicsContext gc;

    // Went ahead and used the knowledge from The Nature of Code (chapter 8), then adjusted it to fit our needs. Not sure if it's correct, however.
    // TODO: actually make scaling work

void cantor(GraphicsContext gc, double x, double y, float iter, double hue, double scale) {
    this.gc = gc;
    Color strColor = Color.hsb(hue, 1.0, 1.0);
    gc.setStroke( strColor );
        if (iter >=1) {
        gc.strokeLine(x,y,x+iter,y);
        y += 20;
        cantor(gc, x,y,iter/3, hue, scale);
        cantor(gc,x+iter*2/3,y,iter/3, hue, scale);
        }
        }
}