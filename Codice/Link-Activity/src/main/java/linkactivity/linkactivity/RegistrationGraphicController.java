package linkactivity.linkactivity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RegistrationGraphicController {

    @FXML
    private Button backButton1;

    @FXML
    private TextField regEmailUser;

    @FXML
    private Button loginButton2;

    @FXML
    private Button signUpGoogleButton2; //todo forse va levato il button in quanto funzione dummy, potrebbe portare problemi su sonarcloud

    @FXML
    private Button userRegisterButton; //todo parte di registrazione vera e propria senza la quale torna errore minore al passaggio di schermata

    @FXML
    void backToWhoAreU() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WhoAreU.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) backButton1.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    private void userRegister() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CareerBona.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) userRegisterButton.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void login() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        Scene scene = new Scene(root, 690, 518);
        Stage stage = (Stage) loginButton2.getScene().getWindow();

        stage.setTitle("Link-Activity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void signUpGoogle() /*throws IOException*/{
        //todo holy shit this shit has to be done SIGNUPGOOGLE
        System.out.println("Holy shit this shit has to be done SIGNUPGOOGLE");
    }

    @FXML
    void regEmailUser() {
        String str;
        int a=0,b=0;
        String[] pars = new String[10];
        str= regEmailUser.getText();
        pars[1]=str;

        String[] result = str.split("\\a");
        for (int i = 0;i < result[0].length(); i++){
            pars[0]= String.valueOf(result[0].charAt(i)); //parsa la parola in lettere per controllare se c'è la @
            if(pars[0].equals("@")){
                a=1;
            }
            if(a==1){
                if(pars[0].equals(".")){
                    b=1;
                }
            }
            //System.out.println(pars[0]);
        }
        if(a==0 || b==0){
            System.out.println("Direi quello che direbbe Germano Mosconi");
        } else {
            System.out.println("ye");
        }
        //System.out.println(c[1]);
        //System.out.println(str);
    }

}

