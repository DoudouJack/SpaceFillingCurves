import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.embed.swing.SwingFXUtils;

/**
 * @author Julia Filzinger
 * This class' main function launches the application.
 * It displays the Graphical User Interface and can create a new Curve object and save the artwork to file
 */

public class CurveDraw extends Application {
    public enum CurveTypes {
        SIERPINSKI("Sierpinski Triangle"),
        TREE("Tree"),
        KOCH2("Koch Snowflake"),
        KOCH("Koch Curve"),
        CIRCLES("Horizontal Circles"),
        CIRCLES2("Space Filling Circles"),
        SPIRAL("Spiral"),
        CANTOR("Cantor Lines"),
        RECTCURSIVE ("Rectcursive");
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
    public void start(Stage primaryStage) {

        // S E T U P   W I N D O W + L A Y O U T
        BorderPane layout = new BorderPane();
        Stage window;
        window = primaryStage;
        window.setTitle("CurveDraw");
        window.getIcons().add(new Image( CurveDraw.class.getResourceAsStream("/resources/icons/appIcon.png")) );

        // B U I L D   C O N T R O L S
        VBox topBtns = new VBox(0);
        topBtns.setPadding(new Insets( 0, 0, 0, -10));
        Button btnNewDefault = new Button("new default");
        Button btnNewCustom = new Button("new custom");
        Button btnFromFile = new Button("read from file");
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
        Slider inputScale = new Slider(0.5, 4, 1);
        inputScale.setMajorTickUnit(0.5);
        inputScale.setMinorTickCount(0);
        inputScale.setSnapToTicks(true);
        inputScale.setShowTickMarks(true);
        setScale.getChildren().addAll(labelScale, inputScale);

        // input iterations
        VBox setIter = new VBox(10);
        Label labelIter = new Label("iterations");
        Slider inputIter = new Slider(1, 15, 5);
        inputIter.setShowTickLabels(true);
        inputIter.setShowTickMarks(true);
        inputIter.setMajorTickUnit(15);
        inputIter.setMinorTickCount(15);
        inputIter.setSnapToTicks(true);
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
        Slider cVariance = new Slider(0, 40, 20);
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

        // input stroke width
        VBox setStrokeWidth = new VBox(10);
        Label labelStroke = new Label("stroke width");
        Slider inputStrokeWidth = new Slider(0.5, 8, 1);
        inputStrokeWidth.setMajorTickUnit(0.5);
        inputStrokeWidth.setMinorTickCount(0);
        inputStrokeWidth.setShowTickMarks(true);
        inputStrokeWidth.setSnapToTicks(true);
        setStrokeWidth.getChildren().addAll(labelStroke, inputStrokeWidth);

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
        controls.getChildren().addAll(topBtns, setCurve, setIter, setStrokeWidth, setColor, setVariance, setOpacity, setScale, bottomBtns);

        Easel easel = new Easel(); // This will hold the artwork group (of canvases)
        easel.setStyle("-fx-background-color: #151515");

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
                Curve curve = getValues( inputCurve, inputScale, inputStrokeWidth, inputIter, inputColor,  cVariance, inputOpacity);

                curve.printValues(); // test method
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

        btnNewCustom.setOnAction( e -> PopUpNew.open(easel));

        // READ FROM FILE
        btnFromFile.setOnAction( e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("open resource file");

            // set extension filter
            FileChooser.ExtensionFilter extFilterTxt = new FileChooser.ExtensionFilter("txt files (.txt)", "*.txt");
            fileChooser.getExtensionFilters().addAll(extFilterTxt);

            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                System.out.println( "Read in file: " + file.getName() );
                try {
                    readFromFile(file, easel.getArtwork());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    // SAVE TO FILE
        btnSaveFile.setOnAction( e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("save your artwork");

            //Set extension filter
            FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter(".png", "*.png");
            FileChooser.ExtensionFilter extFilterJpg = new FileChooser.ExtensionFilter(".jpeg", "*.jpeg");
            fileChooser.getExtensionFilters().addAll(extFilterPng, extFilterJpg);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if(file != null){
                try {
                    Artwork myArtwork = easel.getArtwork();
                    WritableImage writableImage = new WritableImage(myArtwork.getWidth(), myArtwork.getHeight());
                    myArtwork.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.print("Error while writing file: " + ex);
                }
            }
        });


    }

    private Curve getValues( ChoiceBox<String> inputCurve, Slider inputScale, Slider inputStrokeWidth, Slider inputIter, Slider inputColor, Slider inputVariance, Slider inputOpacity){

        int curveType = getCurveTypeNumber( inputCurve.getValue() );

        return new Curve(curveType, inputScale.getValue(), inputStrokeWidth.getValue(), getIntValue(inputIter), inputColor.getValue(), getIntValue(inputVariance), inputOpacity.getValue() );
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

    /**
     * Reads a text file with a 'type/scale/strokeWidth/iterations/hue/variance/opacity' format and then proceeds to draw as many curves as the file has, all separated by line breaks.
     * @author Fee Di Mascio
     * */
    private void readFromFile(File file, Artwork artwork) {
        try {
            String ln;
            String[] parts;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                ln = sc.nextLine();
                parts = ln.split("/");
                int type = Integer.parseInt(parts[0]);
                int sca = Integer.parseInt(parts[1]);
                double strW = Double.parseDouble(parts[2]);
                int iter = Integer.parseInt(parts[3]);
                double hue = Double.parseDouble(parts[4]);
                int cVar = Integer.parseInt(parts[5]);
                double opac = Double.parseDouble(parts[6]);
                Curve curve = new Curve(type, sca, strW, iter, hue, cVar, opac);
                curve.mainDraw(artwork);
            }
        }
        catch(Exception e){
            System.out.println("File could not be interpreted: "+ e);
            AlertBox.display("problem in file", "There was an error with your file. Format has to be like 'type/scale/strokeWidth/iterations/hue/variance/opacity' with curves separated by newlines.");
        }
    }
}

