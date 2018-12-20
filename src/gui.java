import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;


public class gui extends Application {
    Stage window;
    BorderPane layout;
    Scene scene;
    Curve curve;


    public static void main(String[] args) {


        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SierpinskiTriangle testTriangle = new SierpinskiTriangle();


        window = primaryStage;
        window.setTitle("Space Filling Curves");

        layout = new BorderPane();
        StackPane canvasHolder = new StackPane();
        HBox btnHolder1 = new HBox(10);
        HBox btnHolder2 = new HBox(10);


        Button btnNew = new Button("Create new");
        Button btnFile = new Button("Save to file");
        btnHolder1.getChildren().addAll(btnNew, btnFile);

        Button btnStart = new Button("Start drawing");
        Button btnPause = new Button("Pause drawing");
        btnHolder2.getChildren().addAll(btnStart, btnPause);

        Label colorL = new Label("Color:");
        Slider slColor = new Slider();
        slColor.setMin(1);
        slColor.setMax(12);
        slColor.setValue(1);
        slColor.setShowTickMarks(true);
        slColor.setMajorTickUnit(1);
        slColor.setMinorTickCount(0);
        slColor.setBlockIncrement(1);

        Label scaleL = new Label("Scale:");
        Slider slScale = new Slider();
        slScale.setMin(1);
        slScale.setMax(10);

        Label IterationsL = new Label("Iterations:");
        Slider slIterations = new Slider();
        slIterations.setMin(1);
        slIterations.setMax(200);


        final Canvas canvas = new Canvas(500, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvasHolder.getChildren().add(canvas);
        canvasHolder.setStyle("-fx-background-color: black");

        VBox controls = new VBox(20);
        controls.setPadding(new Insets(20, 10, 20, 10));
        controls.getChildren().addAll(btnHolder1, scaleL, slScale, colorL, slColor, IterationsL, slIterations, btnHolder2);


        layout.setRight(canvasHolder);
        layout.setLeft(controls);

        layout.getChildren().addAll();
        scene = new Scene(layout);

        window.setScene(scene);
        window.show();






/*        //for later: COLOR PICKER
        private ColorPicker createConvergenceColorPicker() {
            ColorPicker colorPicker = new ColorPicker(Color.WHITE);

            colorPicker.valueProperty().addListener(new ChangeListener<Color>() {
                @Override
                public void changed(ObservableValue ov, Color oldColorSchema, Color newColorSchema) {
                    curve.setConvergenceColor(newColorSchema);
                    paintSet(canvas.getGraphicsContext2D(), curve);
                }
            });

            return colorPicker;
        }*/


    }
}

