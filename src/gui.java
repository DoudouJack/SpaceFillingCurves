import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.shape.Polygon;

import java.awt.*;
import java.lang.Math;


public class gui extends Application {
    Curve curve;

    public enum CurveTypes {
        SIERPINSKI("Sierpinski Triangle"),
        KOCH("Koch Curve"),
        TREE("Tree"),
        CANTOR("Cantor Lines");

        private final String curveName;

        CurveTypes(String curveName) {
            this.curveName = curveName;
        }

        public String getCurveName() {
            return this.curveName;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // S E T U P   W I N D O W + L A Y O U T
        BorderPane layout = new BorderPane();
        //easel.setStyle("-fx-background-color: black");
        Stage window = new Stage();
        window = primaryStage;
        window.setTitle("Space Filling Curves");

        // B U I L D   C O N T R O L S
        VBox topBtns = new VBox(0);
        topBtns.setPadding(new Insets( 0, 0, 0, -10));
        Button btnNewDefault = new Button("new default");
        Button btnNewCustom = new Button("new custom");
        Button btnFromFile = new Button("read in file");
        topBtns.getChildren().addAll(btnNewDefault, btnNewCustom, btnFromFile);
        btnNewDefault.getStyleClass().add("menuLinkItem");
        btnNewCustom.getStyleClass().add("menuLinkItem");
        btnFromFile.getStyleClass().add("menuLinkItem");
        btnNewDefault.setMaxWidth(Double.MAX_VALUE);
        btnNewCustom.setMaxWidth(Double.MAX_VALUE);
        btnFromFile.setMaxWidth(Double.MAX_VALUE);
        btnNewDefault.setId("newEmpty");
        btnNewCustom.setId("newRand");
        btnFromFile.setId("fromFile");

        VBox bottomBtns = new VBox(10);
        Button btnStart = new Button("start drawing");
        Button btnClear = new Button("clear all");
        Button btnSaveFile = new Button("save image to file");
        btnStart.getStyleClass().add("bigButton");
        btnSaveFile.getStyleClass().add("menuLink");
        btnSaveFile.setId("saveFile");
        btnClear.setId("clearAll");
        btnClear.getStyleClass().add("menuLink");
        bottomBtns.setAlignment(Pos.BASELINE_CENTER);
        bottomBtns.getChildren().addAll(btnStart, btnClear, btnSaveFile);

        // inputCurve curve type
        VBox setCurve = new VBox(10);
        Label labelCurve = new Label("curve type");
        ChoiceBox<String> inputCurve = new ChoiceBox<>();
        for(CurveTypes ct : CurveTypes.values()) {
            inputCurve.getItems().add( ct.getCurveName() );
        }
        inputCurve.getSelectionModel().select(0);
        setCurve.getChildren().addAll(labelCurve, inputCurve);

        // input Scale
        VBox setScale = new VBox(10);
        Label labelScale = new Label("scale");
        Slider inputScale = new Slider(1, 100, 50);
        setScale.getChildren().addAll(labelScale, inputScale);

        // input iterations
        VBox setIter = new VBox(10);
        Label labelIter = new Label("iterations");
        Slider inputIter = new Slider(1, 100, 50);
        inputIter.setShowTickLabels(true);
        inputIter.setShowTickMarks(true);
        inputIter.setMajorTickUnit(200);
        inputIter.setMinorTickCount(1);
        setIter.getChildren().addAll(labelIter, inputIter);

        // input color
        VBox setColor = new VBox(10);
        Label labelColor = new Label("color");
        Slider inputColor = new Slider(0, 359, 0);
        inputColor.setShowTickMarks(true);
        inputColor.setMajorTickUnit(60);
        inputColor.setMinorTickCount(0);
        setColor.getChildren().addAll(labelColor, inputColor);
        inputColor.getStyleClass().add("colorSlider");

        // input color variance
        VBox setVariance = new VBox(10);
        Label labelVariance = new Label("color variance");
        Slider cVariance = new Slider(0, 40, 0);
        cVariance.setMajorTickUnit(5);
        setVariance.getChildren().addAll(labelVariance, cVariance);

        // input opacity
        VBox setOpacity = new VBox(10);
        Label labelOpacity = new Label("opacity");
        Slider inputOpacity = new Slider(1, 100, 50);
        inputOpacity.setShowTickLabels(true);
        inputOpacity.setShowTickMarks(true);
        inputOpacity.setMajorTickUnit(100);
        inputOpacity.setMinorTickCount(1);
        setOpacity.getChildren().addAll(labelOpacity, inputOpacity);

        // create tooltips
        Tooltip tooltipDefault = new Tooltip("Create a new artwork with default values.");
        btnNewDefault.setTooltip(tooltipDefault);
        Tooltip tooltipCustom = new Tooltip("Create a new artwork with custom size and background.");
        btnNewCustom.setTooltip(tooltipCustom);
        Tooltip tooltipFile = new Tooltip("Create one or more curves from input file.");
        btnFromFile.setTooltip(tooltipFile);
        Tooltip tooltipSave = new Tooltip("Save your artwork to an image file.");
        btnSaveFile.setTooltip(tooltipSave);
        Tooltip tooltipClear = new Tooltip("Delete the artwork.");
        btnClear.setTooltip(tooltipClear);


        // C R E A T E  L A Y O U T
        VBox controls = new VBox(20);
        controls.setPrefWidth(250);
        controls.setPadding(new Insets( 40, 25, 40, 25));
        controls.getChildren().addAll(topBtns, setCurve, setScale, setIter, setColor, setVariance, setOpacity, bottomBtns);

        Easel easel = new Easel(); // This will hold the artwork group (of canvases)
        easel.setStyle("-fx-background-color: #111111");
        easel.setMinWidth(800);
        easel.setMinHeight(800);

        layout.setRight(easel);
        layout.setLeft(controls);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/Style.css");

        window.setScene(scene);
        window = primaryStage;
        window.show();


        // G U I   F U N C T I O N S
        btnStart.setOnAction( e -> {
                Curve curve = getValues( inputCurve, inputScale, inputIter, inputColor,  cVariance, inputOpacity);
                // call test methods:
                curve.printValues();
                //curve.testDrawing( easel.getArtwork() );
            //Polygon triangle = new Polygon();
            //triangle.getPoints().addAll(50.0, 0.0,  0.0, 50.0,100.0, 50.0);
            //triangle.setFill(Color.WHITE);
            //triangle.setStroke( strColor );
            //curve.test2Drawing(easel.getArtwork());
            curve.mainDraw(easel.getArtwork());


        });

        btnClear.setOnAction( e -> {
            boolean result = ConfirmationBox.display("Confirm", "Are you sure you want to delete your artwork?");
            if(result){
                easel.resetArtwork( easel.getArtwork() );
            }
        });

        btnNewDefault.setOnAction( e -> {
            boolean result = ConfirmationBox.display("Confirm", "Discard all changes made to the current artwork?");
            if(result){
                easel.resetArtwork();
            }
        });

        btnNewCustom.setOnAction( e -> {
            PopUpNew.open(easel);
        });



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

    private Curve getValues( ChoiceBox<String> inputCurve, Slider inputScale, Slider inputIter, Slider inputColor, Slider inputVariance, Slider inputOpacity){

        int curveType = getCurveTypeNumber( inputCurve.getValue() );

        return new Curve(0, 0, curveType, getIntValue(inputScale), getIntValue(inputIter), inputColor.getValue(), getIntValue(inputVariance), inputOpacity.getValue() );
    }


    private int getCurveTypeNumber( String typeName ){
        int typeNumber = 0;

        for(CurveTypes ct : CurveTypes.values()) {
            if( typeName.equals( ct.getCurveName() ) ){
                break;
            }
            typeNumber++;
        }

        return typeNumber;
    }

    private int getIntValue( Slider slider ){
        return (int) Math.round( slider.getValue() );
    }
}

