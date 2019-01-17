import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {
    private double startX;
    private double startY;
    private double scale;
    private int stroke;
    private int iterations;
    private double cvariance;
    private double hue;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("I want to plant a tree...");
        Group root = new Group();
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

            // VARS
        startX = 20.0;
        startY = 600.0;
        scale = 5.0;
        stroke = 3;
        iterations = 20;
        cvariance = 3;
        hue = 1;

        gc.setLineWidth(stroke);

        drawBranch(gc, iterations, startX, startY, scale, hue);
    }

    private void drawBranch(GraphicsContext gc, int iterations, double startX, double startY, double scale, double hue){
        if(iterations==0) return;
        Color strColor = Color.hsb(hue, 1.0, 1.0);
        gc.setStroke( strColor );

        gc.strokeLine(startX, startY, startX+scale, startY-scale-iterations/2);
        gc.strokeLine(startX, startY, startX-scale, startY-scale-iterations/2);

        drawBranch(gc, iterations-1, startX+scale, startY-scale-iterations/2, scale/0.8, hue+cvariance);
        //drawBranch(gc, iterations-1, startX-scale, startY-scale-iterations/2, scale/2, hue+cvariance);
    }

//    private void drawBranch(GraphicsContext gc, int iterations, double startX, double startY, double scale, int red, int green, int blue){
//        if(iterations==0) return;
//
//        gc.setStroke( Color.rgb(red, green, blue) );
//
//        gc.strokeLine(startX, startY, startX+scale, startY-scale-iterations/2);
//        gc.strokeLine(startX, startY, startX-scale, startY-scale-iterations/2);
//
//        drawBranch(gc, iterations-1, startX+scale, startY-scale-iterations/2, scale/0.8, Math.abs( (red+cvariance*1)%256 ), Math.abs( (red+cvariance*90)%256 ), Math.abs( (red+cvariance*3)%256));
//        drawBranch(gc, iterations-1, startX-scale, startY-scale-iterations/2, scale/0.8, Math.abs( (red+cvariance*8)%256 ), Math.abs( (red+cvariance*50)%256), Math.abs( (red+cvariance*1)%256 ));
//    }
}