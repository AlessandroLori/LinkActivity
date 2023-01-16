package linkactivity.linkactivity;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AzioniAziendaGraphicController {

    @FXML
    private Button backButton4;

    @FXML
    private Button createNewEvent;

    @FXML
    private Button describeToFollowers; //todo boh, ma deve esse dummy? no ve?

    @FXML
    private Button viewEventInsights; //todo eh va fatto, stamo sempre la

    @FXML
    private void backToLogin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginEnti.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) backButton4.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void eventCreate() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EventCreate.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) createNewEvent.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void describeToFollowers() throws IOException{
        //not todo or yes
        System.out.println("NOT TODO or yes");
    }

    @FXML
    private void viewEventInsights() throws IOException{
        //todo or not?
        System.out.println("TODO or not");
    }

}
