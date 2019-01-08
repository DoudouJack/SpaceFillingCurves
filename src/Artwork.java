import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Artwork extends Group{
    private int numberOfCanvases;
    private Canvas[] canvasList;
    private double width;
    private double height;
    private Color bgColor;

        // constructors
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

        // create layers:
        for(int i = 1; i <= numberOfCanvases; i++){
            canvasList[i] = new Canvas(width, height);
            this.getChildren().addAll( canvasList[i] );
        }
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

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // other Methods
    public void setBackground( Color color ){
        this.bgColor = color;
        canvasList[0].getGraphicsContext2D().setFill( color );
        canvasList[0].getGraphicsContext2D().fillRect(0,0, this.width, this.height);
    }

    public GraphicsContext getCurrentLayer(){
        return canvasList[1].getGraphicsContext2D();
    }

    public void clearAll(){
        for(int i = 1; i <= this.numberOfCanvases; i++){
            canvasList[i].getGraphicsContext2D().clearRect(0, 0, this.width, this.height);
            canvasList[i] = null;
            this.getChildren().remove(canvasList[i]);
        }

        canvasList[0] = new Canvas(width, height);
        canvasList[1] = new Canvas(width, height);
        this.getChildren().addAll(canvasList[0], canvasList[1] );
        setBackground( bgColor );
    }
}
