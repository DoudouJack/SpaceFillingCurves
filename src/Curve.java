//This is a skeleton as the program is a work in progress. 
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;

/* 
	This class is untested. Tests will commence elsewhen. Refer to commentary for information on accounted for issues.
*/

public class Curve{



    /* private float color;
        TODO: Determine handling of color with the group. */
    private double xPos; //Edited positions so they're double.
    private double yPos;
    private double scale;
    private int iterations;
    private int curveType;
    private double colorHue;
    private int cVariance;
    private double opacity;
    private Artwork artwork;
//    private Color convergenceColor = Color.WHITE;
//    private ColorSchema colorSchema = ColorSchema.GREEN;


/*
    public enum ColorSchema {

        GREEN, RED, YELLOW, BLUE, CYAN, MAGENTA
    }

    public void setConvergenceColor(Color convergenceColor) {
        this.convergenceColor = convergenceColor;
    }

    public Color getConvergenceColor() {
        return convergenceColor;
    }

    public ColorSchema getColorSchema() {
        return colorSchema;
    }

    public void setColorSchema(ColorSchema colorSchema) {
        this.colorSchema = colorSchema;
    }
*/


// write a function that triggers the drawfunction depending on curveType (int)
    // depending on the choice, create new object from selected type
    // then draw

    public Curve(double x, double y, int type, double sca, int iter, double colHue, int cVar, double opac){ //NOTE: Remember to re-add color handling. TODO: Determine how to implement type handling via subclasses.
        xPos = x;
        yPos = y;
        curveType = type;
        scale = sca;
        iterations = iter;
        colorHue = colHue;
        cVariance = cVar;
        opacity = opac;
    }

    /*
    TODO: Adapt to changed set of attributes after setting up the GUI
    public Curve readFromFile() throws FileNotFoundException { // TODO: Implement a way to read multiple curves, presumably with a loop. Color handling.
        String ln;
        String[] parts;
        Scanner sc = new Scanner( new File("text.in") );
        ln = sc.nextLine();
        parts = ln.split("/");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        float trans = Float.parseFloat(parts[2]);
        int sca = Integer.parseInt(parts[3]);
        int iter = Integer.parseInt(parts[4]);
        int type = Integer.parseInt(parts[5]);
        return new Curve(x, y, trans, sca, iter, type);
    }
    */
    public void draw() { //To be implemented within each subclass.
                         //TODO: Consider whether the Curve class should be abstract (in which case the readFromFile() method would need to be implemented elsewhere) or if an interface would be appropriate.
    }

    //Moved test function into Curve function for testing
    private void drawBranch(GraphicsContext gc, int iterations, double startX, double startY, double scale, double hue){
        if(iterations==0) return;
        Color startCol = Color.hsb(colorHue,1.0,1.0, opacity/100);
        gc.setStroke( startCol );

        gc.strokeLine(startX, startY, startX+scale, startY-scale-iterations/2);
        gc.strokeLine(startX, startY, startX-scale, startY-scale-iterations/2);

        drawBranch(gc, iterations-1, startX+scale, startY-scale-iterations/2, scale/0.8, hue+cVariance);
    }

    // This is for testing whether the GUI passes all the right values
    void printValues(){
        System.out.printf(
                "xPos: %f \nxPos: %f \nType: %d \nScale: %f \nIterations: %d \nColorHue: %f \nColor Variance: %d \nOpacity: %f \n"
                , xPos, yPos, curveType, scale, iterations, colorHue, cVariance, opacity
        );
    }
// Testing the usage of a branch.
/*
    void testDrawing(Artwork artwork){
        GraphicsContext gc = artwork.getCurrentLayer();
        xPos = 20.0;
        yPos = 600.0;
        gc.setLineWidth(3);
        //drawBranch(gc, iterations, xPos, yPos, scale, colorHue);
        CantorSet a = new CantorSet();
        a.cantor(gc, xPos, yPos, iterations, colorHue, scale);
    }
*/

    /*
    void test2Drawing(Artwork artwork){

        GraphicsContext gc = artwork.getCurrentLayer();
        xPos = 600.0;
        yPos = 600.0;
        SierpinskiTriangleOLD triangle = new SierpinskiTriangleOLD();
        triangle.drawTriangle(gc, xPos, yPos, iterations, colorHue, scale);
    }
    */

    void mainDraw(Artwork artwork){
        GraphicsContext gc = artwork.getCurrentLayer();
        xPos = 600.0;
        yPos = 600.0;
        if (curveType == 0){
            Sierpinski triangle = new Sierpinski();
            triangle.drawTriangle(gc, artwork, xPos, yPos, iterations, colorHue, scale);

        } else if (curveType == 3){
            Cantor cantor = new Cantor();
            cantor.drawCantor(gc, artwork, xPos, yPos, iterations, colorHue, scale);
            /*CantorSet a = new CantorSet();
            a.cantor(gc, xPos, yPos, iterations, colorHue, scale);*/
        }
    }
}