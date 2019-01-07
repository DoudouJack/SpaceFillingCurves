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
    private Stage window;
    private BorderPane layout;
    private Scene scene;
    Curve curve;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //SierpinskiTriangle testTriangle = new SierpinskiTriangle();

        // S E T U P   W I N D O W + L A Y O U T
        window = primaryStage;
        window.setTitle("Space Filling Curves");
        layout = new BorderPane();
        StackPane canvasHolder = new StackPane();

        // B U I L D   C O N T R O L S
        VBox topBtns = new VBox(0);
        topBtns.setPadding(new Insets( 0, 0, 0, -10));
        Button btnNewEmpt = new Button("new empty");
        Button btnNewRand = new Button("new random");
        Button btnFromFile = new Button("new from file");
        topBtns.getChildren().addAll(btnNewEmpt, btnNewRand, btnFromFile);
        btnNewEmpt.getStyleClass().add("menuLinkItem");
        btnNewRand.getStyleClass().add("menuLinkItem");
        btnFromFile.getStyleClass().add("menuLinkItem");
        btnNewEmpt.setMaxWidth(Double.MAX_VALUE);
        btnNewRand.setMaxWidth(Double.MAX_VALUE);
        btnFromFile.setMaxWidth(Double.MAX_VALUE);
        btnNewEmpt.setId("newEmpty");
        btnNewRand.setId("newRand");
        btnFromFile.setId("fromFile");

        VBox bottomBtns = new VBox(10);
        Button btnStart = new Button("Start drawing");
        Button btnSaveFile = new Button("Save image to file");
        //btnHolder2.getChildren().addAll(btnStart, btnPause);
        bottomBtns.getChildren().addAll(btnStart, btnSaveFile);
        btnStart.getStyleClass().add("bigButton");
        btnSaveFile.setId("menuLink");
        btnStart.setMaxWidth(Double.MAX_VALUE);
        btnSaveFile.setMaxWidth(Double.MAX_VALUE);

        // inputCurve curve type
        VBox setCurve = new VBox(10);
        Label labelCurve = new Label("curve type");
        ChoiceBox<String> inputCurve = new ChoiceBox<>();
        inputCurve.getItems().addAll("Sierpinski Triangle", "Tree", "Koch Curve", "Cantor Lines");
        inputCurve.getSelectionModel().select(0);
        setCurve.getChildren().addAll(labelCurve, inputCurve);

        // input Scale
        VBox setScale = new VBox(10);
        Label labelScale = new Label("Scale");
        Slider inputScale = new Slider();
        inputScale.setMin(1);
        inputScale.setMax(100);
        setScale.getChildren().addAll(labelScale, inputScale);

        // input iterations
        VBox setIter = new VBox(10);
        Label labelIter = new Label("Iterations");
        Slider inputIter = new Slider();
        inputIter.setMin(1);
        inputIter.setMax(200);
        inputIter.setShowTickLabels(true);
        inputIter.setShowTickMarks(true);
        inputIter.setMajorTickUnit(200);
        inputIter.setMinorTickCount(1);
        setIter.getChildren().addAll(labelIter, inputIter);

        // input color
        VBox setColor = new VBox(10);
        Label labelColor = new Label("Color");
        Slider inputColor = new Slider();
        inputColor.setMin(1);
        inputColor.setMax(10);
        inputColor.setShowTickMarks(true);
        inputColor.setMajorTickUnit(1);
        inputColor.setMinorTickCount(0);
        inputColor.setSnapToTicks(true);
        setColor.getChildren().addAll(labelColor, inputColor);
        inputColor.setId("colorSlider");

        // input color variance
        VBox setVariance = new VBox(10);
        Label labelVariance = new Label("Color variance");
        Slider cVariance = new Slider();
        cVariance.setMin(0);
        cVariance.setMax(40);
        cVariance.setMajorTickUnit(5);
        setVariance.getChildren().addAll(labelVariance, cVariance);

        // input opacity
        VBox setOpacity = new VBox(10);
        Label labelOpacity = new Label("Opacity");
        Slider inputOpacity = new Slider();
        inputOpacity.setMin(1);
        inputOpacity.setMax(100);
        inputOpacity.setShowTickLabels(true);
        inputOpacity.setShowTickMarks(true);
        inputOpacity.setMajorTickUnit(100);
        inputOpacity.setMinorTickCount(1);
        setOpacity.getChildren().addAll(labelOpacity, inputOpacity);

        // Canvas
        final Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvasHolder.getChildren().add(canvas);
        canvasHolder.setStyle("-fx-background-color: black");


        // F I L L   L A Y O U T
        VBox controls = new VBox(20);
        controls.setPrefWidth(250);
        controls.setPadding(new Insets( 40, 25, 40, 25));
        controls.getChildren().addAll(topBtns, setCurve, setScale, setIter, setColor, setVariance, setOpacity, bottomBtns);

        layout.setRight(canvasHolder);
        layout.setLeft(controls);

        layout.getChildren().addAll();
        scene = new Scene(layout);
        scene.getStylesheets().add("resources/Style.css");

        window.setScene(scene);
        window.show();






        //for later: COLOR PICKER
//        private ColorPicker createConvergenceColorPicker() {
//            ColorPicker colorPicker = new ColorPicker(Color.WHITE);
//
//            colorPicker.valueProperty().addListener(new ChangeListener<Color>() {
//                @Override
//                public void changed(ObservableValue ov, Color oldColorSchema, Color newColorSchema) {
//                    curve.setConvergenceColor(newColorSchema);
//                    paintSet(canvas.getGraphicsContext2D(), curve);
//                }
//            });
//
//            return colorPicker;
//        }


    }
}

