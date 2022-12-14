package linkactivity.linkactivity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginGraphicController {

    @FXML
    public PasswordField passLogin;
    @FXML
    private TextField emailUsernameLogin;

    @FXML
    private Button backButton3;

    @FXML
    private Button loginButton3;

    @FXML
    private Button loginWithGoogleButton;

    @FXML
    private Button registerButton;

    @FXML
    public TextField Username;

    @FXML
    public PasswordField Password;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private void backToWhoAreU() throws IOException { //todo levare shortcut register su login
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WhoAreU.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) backButton3.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public void switchToUserProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CareerBona.fxml")));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void validateLogin(ActionEvent event) {


        Connection myConnection = DBConnection.getDBConnection();
        //Connection connectDB = (Connection) myConnection.getInstance();

        String verifyLoginQuery = "SELECT count(1)  FROM user WHERE Username = '" + emailUsernameLogin.getText() + "' AND password = '" + passLogin.getText() + "'";

        try {
            Statement statement = myConnection.createStatement();
            ResultSet queryLoginResult = statement.executeQuery(verifyLoginQuery);

            while (queryLoginResult.next()) {

                if (queryLoginResult.getInt(1) == 1) {
                    System.out.println("Benvenuto USER");
                    switchToUserProfile(event);
                }
                else { System.out.println("Errore nel login");}
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



                @FXML
                private void loginGoogle () {
                    //dummy
                }
            }