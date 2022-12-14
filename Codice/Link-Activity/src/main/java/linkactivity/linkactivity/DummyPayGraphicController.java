package linkactivity.linkactivity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DummyPayGraphicController {

    @FXML
    private Button backButton7;

    @FXML
    private Button paymentButton;

    @FXML
    private void backToEventCreate() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EventCreate.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) backButton7.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

}

