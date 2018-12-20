import javafx.application.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class gui extends Application {
    Stage window;
    BorderPane layout;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Space Filling Curves");

    }
}

