import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Julia Filzinger
 * This is what we save as image file on "save to file".
 * Artwork can hold several Canvases like layers.
 */


public class Artwork extends Group{
    private int numberOfCanvases;
    private Canvas[] canvasList;
    private int width;
    private int height;
    private Color bgColor;

        // constructors
    public Artwork( int width, int height, Color bgColor) {
        this.numberOfCanvases = 1;
        this.width = width;
        this.height = height;
        this.canvasList = new Canvas[numberOfCanvases];
        this.bgColor = bgColor;

        // create background:
        canvasList[0] = new Canvas(width, height);
        this.getChildren().add( canvasList[0] );
        setBackground( bgColor );
    }

    public Artwork(int numberOfCanvases, int width, int height, Color bgColor) {
        this.numberOfCanvases = numberOfCanvases;
        this.width = width;
        this.height = height;
        this.canvasList = new Canvas[numberOfCanvases];
        this.bgColor = bgColor;

        // create background:
        canvasList[0] = new Canvas(width, height);
        this.getChildren().add( canvasList[0] );
        setBackground( bgColor );
    }

    public Artwork() {
        this.numberOfCanvases = 1;
        this.width = 800;
        this.height = 800;
        this.canvasList = new Canvas[2];
        this.bgColor = Color.BLACK;

        // create background:
        canvasList[0] = new Canvas(width, height);
        canvasList[1] = new Canvas(width, height);
        this.getChildren().addAll(canvasList[0], canvasList[1] );
        setBackground( bgColor );
    }

    // getters and setters
    public int getNumberOfCanvases() {
        return numberOfCanvases;
    }

    public void setNumberOfCanvases(int numberOfCanvases) {
        this.numberOfCanvases = numberOfCanvases;
    }

    int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    Color getBgColor(){
        return bgColor;
    }

    // other Methods
    private void setBackground( Color color ){
        this.bgColor = color;
        canvasList[0].getGraphicsContext2D().setFill( color );
        canvasList[0].getGraphicsContext2D().fillRect(0,0, this.width, this.height);
    }

    GraphicsContext getCurrentLayer(){
        return canvasList[0].getGraphicsContext2D();
    }
}
