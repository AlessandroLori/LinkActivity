package linkactivity.linkactivity;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardSecondViewGraphicController implements Initializable {

    @FXML
    private TextField dashboardCommandLine;

    @FXML
    private ListView<EventBean> dashboardSecondView;

    private final ArrayList<EventBean> elements= new ArrayList<>();

    @FXML
    void executeCommand() throws IOException, ParseException, IOExceptionHandler {
        String s= dashboardCommandLine.getText();
        dashboardCommandLine.setText("");

        if(s.compareTo("back")==0){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginSecondView.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) dashboardCommandLine.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else if(s.matches("goto .*")) {
            String ind = s.replace("goto ", "");
            int index = Integer.parseInt(ind);
            dashboardSecondView.scrollTo(index);



        } else if(s.matches("select .*")){
            String ind = s.replace("select ", "");
            int index = Integer.parseInt(ind);
            EventBean x= elements.get(index);

            Stage stage = new Stage();
            FXMLLoader root;
            root = new FXMLLoader(Objects.requireNonNull(getClass().getResource("JoinEventSecondView.fxml")));

            Scene scene;
            try {
                scene = new Scene(root.load(), 417, 359);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();
                JoinEventSecondViewGraphicController o = root.getController();
                o.setDescriptionText(x);
            } catch (IOException e) {
                throw new IOExceptionHandler("IOException error");
            }
            assert false;





        } else if(s.matches("filterBy .*")){
            String filter= s.replace("filterBy ", "");
            List<EventBean> y = new ItemController().item(filter);
            elements.clear();
            dashboardSecondView.refresh();
            while(!(y.isEmpty())){
                EventBean z= y.get(0);
                elements.add(z);
                y.remove(0);
            }
            dashboardSecondView.setItems(FXCollections.observableList(elements));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        elements.clear();
        dashboardSecondView.refresh();
        List<EventBean> y;
        y = new ItemController().item("");

        while(!(y.isEmpty())){
            EventBean z= y.get(0);
            elements.add(z);
            y.remove(0);
        }


        dashboardSecondView.setItems(FXCollections.observableList(elements));

        dashboardSecondView.setCellFactory(param -> new AddThingsListCellFactory());

    }
}


