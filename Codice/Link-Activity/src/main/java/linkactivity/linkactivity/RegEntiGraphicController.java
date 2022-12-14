package linkactivity.linkactivity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;

public class RegEntiGraphicController {

    @FXML
    private Button backButton2;

    @FXML
    private Button entiRegisterButton; //todo parte di registrazione vera e propria senza la quale torna errore minore al passaggio di schermata

    @FXML
    private Button loginButton1;

    @FXML
    private Button signUpGoogleButton1; //todo forse va levato il button in quanto funzione dummy, potrebbe portare problemi su sonarcloud

    @FXML
    private Button updateLogoButton; //todo parte di updatelogo vera e propria senza la quale torna errore minore al passaggio di schermata

    @FXML
    private TextField nomeEnte;

    @FXML
    private TextField regEmailEnti;

    @FXML
    private PasswordField regPassEnti;

    @FXML
    private PasswordField regRepPassEnti;


    @FXML
    private void backToWhoAreU() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WhoAreU.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) backButton2.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public void switchToAzioniAzienda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AzioniAzienda.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void entiRegister(ActionEvent event) throws IOException {
        //tutto ok quindi push sul db
        Connection myConnection = DBConnection.getDBConnection();

        String emailText = regEmailEnti.getText();
        String usernameText = nomeEnte.getText();
        String checkpass = regPassEnti.getText();


        String insertFields = "INSERT INTO azienda_u(Email, NomeAzienda, Password) VALUES ('";
        String insertValues = emailText + "','" + usernameText + "','" + checkpass + "')";
        String insertToRegister = insertFields + insertValues;

        try {

            Statement statement = myConnection.createStatement();
            statement.executeUpdate(insertToRegister);
            switchToAzioniAzienda(event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    /*@FXML
    private void entiRegister() throws IOException{
        String str;
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        int e=0;
        String[] pars = new String[10];
        str= regEmailEnti.getText();
        pars[1]=str;

        String[] result = str.split("\\a");
        for (int i = 0;i < result[0].length(); i++){
            pars[0]= String.valueOf(result[0].charAt(i)); //parsa la parola in lettere per controllare se c'?? la @

            if(pars[0].equals("@")){
                a=1;
            }
            if(a==1) {
                if (pars[0].equals(".")) {
                    b = 1;
                }
            }
        }

        if(b==0){
            //System.out.println("Direi quello che direbbe Germano Mosconi");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Immettere una mail valida");
            alert.showAndWait();
        } else {
            c=1;
            //System.out.println("ye");
        }

        if(regPassEnti.getText().isEmpty()) {
            //System.out.println("PassVuota");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Password Vuota");
            alert.showAndWait();
        } else {
            d=1;
        }

        if(regRepPassEnti.getText().isEmpty()){
            //System.out.println("Repeat Password");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Il campo non pu?? essere vuoto");
            alert.showAndWait();

        } else if (!Objects.equals(regRepPassEnti.getText(), regPassEnti.getText())) {
            //System.out.println("Pass di conf non coincide");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Le password non coincidono");
            alert.showAndWait();

        } else{
            e=1;
        }

        if(c==1 && d==1 && e==1) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AzioniAzienda.fxml")));
            Scene scene = new Scene(root, 690, 518);
            Stage stage = (Stage) entiRegisterButton.getScene().getWindow();

            stage.setTitle("Link-Activity");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }*/

    @FXML
    private void login() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginEnti.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) loginButton1.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void signUpGoogle() /*throws IOException*/{
        //todo holy shit this shit has to be done SIGNUPGOOGLE
        System.out.println("Holy shit this shit has to be done SIGNUPGOOGLE");
    }

    @FXML
    private void updateLogo() /*throws IOException*/{
        //todo holy shit this shit has to be done UPDATELOGO
        System.out.println("Holy shit this shit has to be done UPDATELOGO");
    }

    @FXML
    void nomeEnte() {

    }

    @FXML
    void regEmailEnti() {

    }

    @FXML
    void regPassEnti() {

    }

    @FXML
    void regRepPassEnti() {

    }

}
