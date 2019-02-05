import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Edouard Jacques
 */

public class DragonCurve {

    GraphicsContext gc;
    double x1, y1, x3, y3;
    double angleDiff;


    public DragonCurve(GraphicsContext gc, Artwork artwork, int i, double hue, double variance, double scale, double opacity, double stroke){
        this.gc = gc;
        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(stroke);

        double square = Math.min(artwork.getWidth(), artwork.getHeight());


                x1 = artwork.getWidth()/2 - square/2 + square/2 * 0.3;
                y1 = artwork.getHeight()/2;
                x3 = artwork.getWidth()/2 + square/2 - square/2 * 0.3;


        y3 = y1;
        angleDiff = 45;


        if (i == 1){
            gc.strokeLine(x1, y1, x3, y3);
        } else {
            displayDragons(i, x1,y1,x3,y3,true,hue,variance,opacity,stroke);
        }
    }

    void displayDragons(int iter, double x1, double y1, double x3, double y3, boolean isClockwise, double hue, double variance, double opacity, double stroke) {
        //displayDragons(iter-1, x1, y1, x3, y3, true, hue, variance, opacity, stroke);
        //displayDragons(iter-1, x1, y1, x3, y3, false, hue, variance, opacity, stroke);

        this.gc = gc;
        Color strColor = Color.hsb(hue, 1.0, 1.0, opacity / 100);
        gc.setStroke(strColor);
        gc.setLineWidth(stroke);

        if (iter > 0) {
            // find vector of original line
            double deltaX = x3 - x1;
            double deltaY = y3 - y1;

            // represent in polar coordinates
            double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double angle = Math.toDegrees(Math.atan(deltaX / deltaY));

            // calculate new distance from origin
            magnitude /= Math.sqrt(2);

            // adjust results of trig function for negative angles
            if (deltaY < 0) { // if y1 is below y3
                angle += 180;
            }
            if (deltaX < 0) { // if x1 is left of x3
                angle += 360;
            }

            // when isClockwise is true, increase angle size (generally by 45deg),
            // else decrease, wrapping around 360deg if result is negative, resulting
            // in polar coordinates for third point


            if (isClockwise) {
                angle += angleDiff;
            } else {
                angle -= angleDiff;
            }


            if (angle < 0) {
                angle += 360;
            }

            // convert back to cartesian vector
            double x2 = magnitude * Math.sin(Math.toRadians(angle));
            double y2 = magnitude * Math.cos(Math.toRadians(angle));

            // recursive calls to repeat process with new coordinates
            displayDragons(iter - 1, x1, y1, x1 + x2, y1 + y2, true, hue, variance, opacity, stroke);
            displayDragons(iter - 1, x1 + x2, y1 + y2, x3, y3, false, hue, variance, opacity, stroke);
        } else {
            gc.setStroke(strColor);
            gc.strokeLine((int) x1, (int) y1, (int) (x3), (int) (y3));
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(stroke);
        }
    }
}

/*    @Override
    void drawLevel0() {gc.strokeLine(x1, y1, x3, y3);}

    @Override
    public void drawCurrentLevel() {
        if(getCurrentDepth() == 0) drawLevel0();
        else {
            if (mode1.equals("single")){
                drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, true, Color.BLACK);
            }
            else {
                drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, true, Color.GREENYELLOW);
                drawDragonCurve(getCurrentDepth(), x1, y1, x3, y3, false, Color.BLUE);
            }
        }
        updateFractalDimension(mathMl);
    }

    private void drawDragonCurve(int n, double x1, double y1, double x3, double y3, boolean isClockwise, Color color) {

        if(n > 0) {
            // find vector of original line
            double deltaX = x3 - x1;
            double deltaY = y3 - y1;

            // represent in polar coordinates
            double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double angle = Math.toDegrees(Math.atan(deltaX / deltaY));

            // calculate new distance from origin
            magnitude /= Math.sqrt(2);

            // adjust results of trig function for negative angles
            if (deltaY < 0) { // if y1 is below y3
                angle += 180;
            }
            if (deltaX < 0) { // if x1 is left of x3
                angle += 360;
            }

            // when isClockwise is true, increase angle size (generally by 45deg),
            // else decrease, wrapping around 360deg if result is negative, resulting
            // in polar coordinates for third point
            if(mode2.equals("dragon")){

                if (isClockwise) {
                    angle += angleDiff;
                }
                else {
                    angle -= angleDiff;
                }
            }
            else {
                angle += angleDiff;
            }

            if (angle < 0) {
                angle += 360;
            }

            // convert back to cartesian vector
            double x2 = magnitude * Math.sin(Math.toRadians(angle));
            double y2 = magnitude * Math.cos(Math.toRadians(angle));

            // recursive calls to repeat process with new coordinates
            drawDragonCurve(n-1, x1, y1, x1 + x2, y1 + y2, true, color);
            drawDragonCurve(n-1, x1 + x2, y1 + y2, x3, y3, false, color);
        }
        else {
            gContext.setStroke(color);
            gContext.strokeLine((int)x1, (int)y1, (int)(x3), (int)(y3));
            gContext.setStroke(Color.BLACK);
        }*/

