//This is a skeleton as the program is a work in progress. 
import java.util.*;
import java.io.*;

/* 
	This class is untested. Tests will commence elsewhen. Refer to commentary for information on accounted for issues.
*/

public class Curve{
    /* private float color;
        TODO: Determine handling of color with the group. */
    private int xPos;
    private int yPos;
    private float transparency;
    private int scale;
    private int iterations;
    private int curveType;

    public Curve(int x, int y, float trans, int sca, int iter, int type){ //NOTE: Remember to re-add color handling. TODO: Determine how to implement type handling via subclasses.
        xPos = x;
        yPos = y;
        transparency = trans;
        scale = sca;
        iterations = iter;
        curveType = type;
        //color = col;
    }

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

    public void draw() { //To be implemented within each subclass.
                         //TODO: Consider whether the Curve class should be abstract (in which case the readFromFile() method would need to be implemented elsewhere) or if an interface would be appropriate.
    }
}