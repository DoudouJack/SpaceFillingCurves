import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationBox {

    static boolean answer;

    public static boolean display( String title, String message ){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        Label alertMessage = new Label(message);
        alertMessage.getStyleClass().add("greyLabel");


        Button btnYes = new Button("Yes");
        Button btnNo = new Button("No");
        btnYes.getStyleClass().add("hButton");
        btnNo.getStyleClass().add("hButton");
        HBox btnHolder = new HBox(10);
        btnHolder.setAlignment(Pos.BASELINE_CENTER);
        btnHolder.getChildren().addAll(btnYes, btnNo);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setPadding(new Insets( 40, 25, 40, 25));
        layout.getChildren().addAll(alertMessage, btnHolder);

        btnYes.setOnAction( e -> {
            answer = true;
            window.hide();
        });

        btnNo.setOnAction( e -> {
            answer = false;
            window.hide();
        });

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/Style.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
