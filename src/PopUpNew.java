import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * @author Julia Filzinger
 * This class can show a pop up window for creating a custom new atrwork.
 */

public class PopUpNew {

    static void open( Easel easel ) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create a new artwork");
        window.setMinWidth(100);
        window.setMaxWidth(300);
        window.setMaxHeight(800);

        // prevent window from going into fullscreen
        window.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                window.setMaximized(false);
        });

        Label labelWidth = new Label("Width");
        Label labelHeight = new Label("Height");
        Label labelBG = new Label("Background color");
        Label labelHue = new Label("Hue");
        Label labelSaturation = new Label("Saturation");
        Label labelBrightness = new Label("Brightness");
        labelWidth.getStyleClass().add("greyLabel");
        labelHeight.getStyleClass().add("greyLabel");
        labelBG.getStyleClass().add("greyLabel");

        // inputs size
        TextField inputWidth = new TextField("800");
        TextField inputHeight = new TextField("800");

        // input color
        Slider inputColor = new Slider(0, 360, 0);
        inputColor.setShowTickMarks(true);
        inputColor.setMajorTickUnit(60);
        inputColor.setMinorTickCount(0);
        inputColor.getStyleClass().add("colorSlider");

        Slider inputSaturation = new Slider(0, 1, 1);
        Slider inputBrightness = new Slider(0, 100, 0);
        inputBrightness.setShowTickMarks(true);
        inputBrightness.setMajorTickUnit(50);
        inputBrightness.setMinorTickCount(0);
        inputBrightness.setShowTickLabels(true);


        // override-warning
        Label warning = new Label("Warning: Creating a new artwork will\ndelete any unsaved changes you made.");
        warning.getStyleClass().add("warningLabel");

        VBox size = new VBox(10);
        size.getChildren().addAll(labelWidth, inputWidth, labelHeight, inputHeight);
        size.setPadding(new Insets( 0, 0, 20, 0));

        VBox color = new VBox(10);
        color.getChildren().addAll( labelBG, labelHue, inputColor, labelSaturation, inputSaturation, labelBrightness, inputBrightness);
        color.setPadding(new Insets( 0, 0, 20, 0));

        // buttons
        Button btnCreate = new Button("Create");
        Button btnCancel = new Button("Cancel");
        btnCreate.getStyleClass().add("hButton");
        btnCancel.getStyleClass().add("hButton");

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        buttons.getChildren().addAll(btnCreate, btnCancel);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets( 40, 25, 40, 25));
        layout.getChildren().addAll(size, color, warning , buttons);

        btnCreate.setDefaultButton(true);

        // button functionality
        btnCreate.setOnAction( e -> {
            boolean check1 = isInt(inputHeight);
            boolean check2 = isInt(inputWidth);
            if( check1 && check2 ){
                Color bgColor = Color.hsb(inputColor.getValue(),inputSaturation.getValue(),inputBrightness.getValue()/100);
                easel.resetArtwork();
                Artwork newArtwork = new Artwork(1, getIntValue(inputWidth), getIntValue(inputHeight), bgColor);
                easel.setArtwork( newArtwork );
                window.close();
            }
        });

        btnCancel.setOnAction( e -> window.close());

        // display window
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/Style.css");
        window.setScene(scene);
        window.show();
    }

    private static boolean isInt( TextField field ){
        if( field.getText().equals("set to max value: 800") ) {
            return true;
        }
        else {
            try {
                int value = Integer.parseInt(field.getText());
                if (value <= 800) {
                    field.getStyleClass().clear();
                    field.getStyleClass().addAll("text-field", "text-input", "fieldCorrected");
                    return true;
                } else {
                    field.getStyleClass().clear();
                    field.setText("set to max value: 800");
                    field.getStyleClass().addAll("text-field", "text-input", "fieldAutoFilled");
                    return false;
                }
            } catch (NumberFormatException numEx) {
                field.getStyleClass().clear();
                field.getStyleClass().addAll("text-field", "text-input", "fieldError");
                numEx.printStackTrace();
                return false;
            }
        }
    }

    private static int getIntValue( TextField field ){

        try{
            if( field.getText().equals("set to max value: 800") ){
                return 800;
            }else{
                return Math.abs(Integer.parseInt(field.getText()));
            }
        }catch (NumberFormatException numEx ){
            field.getStyleClass().add("fieldError");
            System.out.println("Could not get integer value: "+numEx);
            return 800;
        }
    }
}
