import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Julia Filzinger
 * This class can show an Alert box that has to be closed or answered "okay".
 */

public class AlertBox {

    static void display( String title, String message ){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        Label alertMessage = new Label(message);
        alertMessage.getStyleClass().add("greyLabel");

        Button btnOkay = new Button("okay");
        Button btnNo = new Button("No");
        btnOkay.getStyleClass().add("hButton");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setPadding(new Insets( 40, 25, 40, 25));
        layout.getChildren().addAll(alertMessage, btnOkay);

        btnOkay.setOnAction( e -> {
            window.close();
        });

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/Style.css");
        window.setScene(scene);
        window.showAndWait();
    }
}

