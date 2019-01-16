import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


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

    public Artwork( Artwork template ){

    }

    // getters and setters
    public int getNumberOfCanvases() {
        return numberOfCanvases;
    }

    public void setNumberOfCanvases(int numberOfCanvases) {
        this.numberOfCanvases = numberOfCanvases;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBgColor(){
        return bgColor;
    }

    // other Methods
    public void setBackground( Color color ){
        this.bgColor = color;
        canvasList[0].getGraphicsContext2D().setFill( color );
        canvasList[0].getGraphicsContext2D().fillRect(0,0, this.width, this.height);
    }

    public GraphicsContext getCurrentLayer(){
        return canvasList[0].getGraphicsContext2D();
    }

    // can be deleted...
   /* public void clearAll(){
        for(int i = 1; i <= this.numberOfCanvases; i++){
            //canvasList[i].getGraphicsContext2D().clearRect(0, 0, this.width, this.height);
            //canvasList[i] = null;
            //this.getChildren().remove(canvasList[i]);
        }

        canvasList[0] = new Canvas(width, height);
        canvasList[1] = new Canvas(width, height);
        this.getChildren().addAll(canvasList[0], canvasList[1] );
        setBackground( bgColor );
    }*/
}
