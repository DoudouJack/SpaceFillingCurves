import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.geometry.Point2D;
import javafx.scene.transform.Scale;

public class Sierpinski {

    GraphicsContext gc;
    double x1, y1, x2, y2, x3, y3;
/*    Point2D A;
    Point2D B;
    Point2D C;*/
    double spacing, a;

    // Added opacity.
    //TODO: Variance and scale.


    void drawTriangle(GraphicsContext gc, Artwork artwork, int i, double hue, double scale, double opacity) {
        this.gc = gc;

        Color strColor = Color.hsb(hue,1.0,1.0, opacity/100);
        gc.setStroke(strColor);
        gc.setLineWidth(3);
        gc.setFill(strColor);
        //gc.fillPolygon(new double[]{5,10,0}, new double[]{8.66,0,0}, 3);


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

/*        Point2D A = new Point2D(x1, y1);
        Point2D B = new Point2D(x2, y2);
        Point2D C = new Point2D(x3, y3);*/

            Point2D A = new Point2D(x1 * scale-(scale-1)*400, y1 * scale-(scale-1)*400);
            Point2D B = new Point2D(x2 * scale-(scale-1)*400, y2 * scale-(scale-1)*400);
            Point2D C = new Point2D(x3 * scale-(scale-1)*400, y3 * scale-(scale-1)*400);

            if (i == 1){

                gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
                gc.strokeLine(A.getX(), A.getY(), C.getX(), C.getY());
                gc.strokeLine(B.getX(), B.getY(), C.getX(), C.getY());

            } else {
                displayTriangles(i, A, B, C);
            }

    }

    void displayTriangles(int iter, Point2D a, Point2D b, Point2D c){
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
        gc.strokeLine(a.getX(), a.getY(), c.getX(), c.getY());
        gc.strokeLine(b.getX(), b.getY(), c.getX(), c.getY());

        if (iter > 1){
         /*   Point2D pAB = A.midpoint(B);
            Point2D pBC = B.midpoint(C);
            Point2D pCA = C.midpoint(A);*/

            displayTriangles(iter-1,a,a.midpoint(b),a.midpoint(c));
            displayTriangles(iter-1,b,a.midpoint(b),b.midpoint(c));
            displayTriangles(iter-1,c,c.midpoint(b),a.midpoint(c));

        }
    }
/*
    void drawLevel0() {
        //Draw the 3 sides of the triangle as black lines
        gc.strokeLine((int)x1, (int)y1, (int)x2, (int)y2);
        gc.strokeLine((int)x1, (int)y1, (int)x3, (int)y3);
        gc.strokeLine((int)x2, (int)y2, (int)x3, (int)y3);
    }


    public void drawCurrentLevel(int iter){
        drawLevel0();
        //Call the recursive function that'll draw all the rest. The 3 corners of it are always the centers of sides, so they're averages
        if(iter > 0) {
            subTriangle
                    (
                            iter-1,
                             //This represents the first recursion
                            (x1 + x2) / 2, //x coordinate of first corner
                            (y1 + y2) / 2, //y coordinate of first corner
                            (x1 + x3) / 2, //x coordinate of second corner
                            (y1 + y3) / 2, //y coordinate of second corner
                            (x2 + x3) / 2, //x coordinate of third corner
                            (y2 + y3) / 2  //y coordinate of third corner
                    );
        }
    }


    void subTriangle(int iter, double x1, double x2, double x3, double y1, double y2, double y3)
    {

        //Draw the 3 sides as lines
        gc.strokeLine(x1, y1, x2, y2);
        gc.strokeLine(x1, y1, x3, y3);
        gc.strokeLine(x2, y2, x3, y3);

        if (iter > 1) {
            //Smaller triangle 1
            subTriangle
                    (
                            iter - 1,
                            //Number of recursions for the next call increased with 1
                            (x1 + x2) / 2 + (x2 - x3) / 2, //x coordinate of first corner
                            (y1 + y2) / 2 + (y2 - y3) / 2, //y coordinate of first corner
                            (x1 + x2) / 2 + (x1 - x3) / 2, //x coordinate of second corner
                            (y1 + y2) / 2 + (y1 - y3) / 2, //y coordinate of second corner
                            (x1 + x2) / 2, //x coordinate of third corner
                            (y1 + y2) / 2  //y coordinate of third corner
                    );


            //Smaller triangle 2
            subTriangle
                    (
                            iter - 1, //Number of recursions for the next call increased with 1
                            (x3 + x2) / 2 + (x2 - x1) / 2, //x coordinate of first corner
                            (y3 + y2) / 2 + (y2 - y1) / 2, //y coordinate of first corner
                            (x3 + x2) / 2 + (x3 - x1) / 2, //x coordinate of second corner
                            (y3 + y2) / 2 + (y3 - y1) / 2, //y coordinate of second corner
                            (x3 + x2) / 2, //x coordinate of third corner
                            (y3 + y2) / 2  //y coordinate of third corner
                    );
            //Smaller triangle 3
            subTriangle
                    (
                            iter - 1, //Number of recursions for the next call increased with 1
                            (x1 + x3) / 2 + (x3 - x2) / 2, //x coordinate of first corner
                            (y1 + y3) / 2 + (y3 - y2) / 2, //y coordinate of first corner
                            (x1 + x3) / 2 + (x1 - x2) / 2, //x coordinate of second corner
                            (y1 + y3) / 2 + (y1 - y2) / 2, //y coordinate of second corner
                            (x1 + x3) / 2, //x coordinate of third corner
                            (y1 + y3) / 2  //y coordinate of third corner
                    );
        }*/



                /*double newx1 = (x1 + x2) / 2; //x coordinate of first corner
                double newy1 = (y1 + y2) / 2; //y coordinate of first corner
                double newx2 = (x1 + x3) / 2; //x coordinate of second corner
                double newy2 = (y1 + y3) / 2; //y coordinate of second corner
                double newx3 = (x2 + x3) / 2; //x coordinate of third corner
                double newy3 = (y2 + y3) / 2; //y coordinate of third corner

        double[] xcoord = new double[]{newx1,newx2,newx3};
        double[] ycoord = new double[]{newy1,newy2,newy3};

        gc.strokePolygon(xcoord, ycoord, 3);*/

/*
        //Calls itself 3 times with new corners, but only if the current number of recursions is smaller than the maximum depth
        if(iter > 0)
        {
            //Smaller triangle 1
            subTriangle
                    (

                            iter-1, //Number of recursions for the next call increased with 1
                            (x1 + x2) / 2 + (x2 - x3) / 2, //x coordinate of first corner
                            (y1 + y2) / 2 + (y2 - y3) / 2, //y coordinate of first corner
                            (x1 + x2) / 2 + (x1 - x3) / 2, //x coordinate of second corner
                            (y1 + y2) / 2 + (y1 - y3) / 2, //y coordinate of second corner
                            (x1 + x2) / 2, //x coordinate of third corner
                            (y1 + y2) / 2  //y coordinate of third corner
                    );
            //Smaller triangle 2
            subTriangle
                    (

                            iter-1, //Number of recursions for the next call increased with 1
                            (x3 + x2) / 2 + (x2 - x1) / 2, //x coordinate of first corner
                            (y3 + y2) / 2 + (y2 - y1) / 2, //y coordinate of first corner
                            (x3 + x2) / 2 + (x3 - x1) / 2, //x coordinate of second corner
                            (y3 + y2) / 2 + (y3 - y1) / 2, //y coordinate of second corner
                            (x3 + x2) / 2, //x coordinate of third corner
                            (y3 + y2) / 2  //y coordinate of third corner
                    );
            //Smaller triangle 3
            subTriangle
                    (

                            iter-1, //Number of recursions for the next call increased with 1
                            (x1 + x3) / 2 + (x3 - x2) / 2, //x coordinate of first corner
                            (y1 + y3) / 2 + (y3 - y2) / 2, //y coordinate of first corner
                            (x1 + x3) / 2 + (x1 - x2) / 2, //x coordinate of second corner
                            (y1 + y3) / 2 + (y1 - y2) / 2, //y coordinate of second corner
                            (x1 + x3) / 2, //x coordinate of third corner
                            (y1 + y3) / 2  //y coordinate of third corner
                    );
        }*/

        /*gc.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
        gc.strokeLine(A.getX(), A.getY(), C.getX(), C.getY());
        gc.strokeLine(B.getX(), B.getY(), C.getX(), C.getY());*/



        //subTriangle(i,x1,x2,x3,y1,y2,y3);
        //drawCurrentLevel(i);
        //drawLevel0();
        //displayTriangles(0, A, B, C);

        /*double[] xcoord = new double[]{x1, x2, x3};
        double[] ycoord = new double[]{y1, y2, y3};

        System.out.printf(
                "x1: %f \nx2: %f \nx3 %f \ny1 %f \ny2 %f \ny3 %f \na %f \nspacing %f \n"
                , x1, x2, x3, y1, y2, y3, a, spacing
        );
        if (i == 1) {
            //gc.strokePolygon(xcoord, ycoord, 3);
            drawLevel0();
        } else {
            *//*drawLevel0();
            subTriangle(i, x1, x2, x3, y1, y2,y3);*//*
            drawCurrentLevel(i);
        }*/



}
