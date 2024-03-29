package linkactivity.linkactivity;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import linkactivity.linkactivity.utilities.ApplyCouponAid;
import linkactivity.linkactivity.utilities.GUISwtichAid;
import linkactivity.linkactivity.utilities.ShowAlertAid;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DummyPaySecondViewGraphicController {

    @FXML
    private Text fiftPCouponAvailable;

    @FXML
    private Text fiftPCouponToUse;

    @FXML
    private Text fivePCouponAvailable;

    @FXML
    private Text fivePCouponToUse;

    @FXML
    private TextField dummyPayCommandLine;

    @FXML
    private Text tenPCouponAvailable;

    @FXML
    private Text tenPCouponToUse;

    @FXML
    private Text total;

    private CompanyBean y;

    private Integer fivep;
    private Integer tenp;
    private Integer fiftp;
    private final EventCreateController eventCreateController= new EventCreateController();

    @FXML
    void executeCommand(ActionEvent event) throws FileNotFoundException, IOExceptionHandler {
        String s= dummyPayCommandLine.getText();
        dummyPayCommandLine.setText("");

        if(s.matches("use 5% coupon .*")){
            String fivepcoup= s.replace("use 5% coupon ","");
            fivePCouponToUse.setText(fivepcoup);

        } else if(s.matches("use 10% coupon .*")){
            String tenpcoup= s.replace("use 10% coupon ", "");
            tenPCouponToUse.setText(tenpcoup);

        } else if(s.matches("use 15% coupon .*")) {
            String fiftpcoup = s.replace("use 15% coupon ", "");
            fiftPCouponToUse.setText(fiftpcoup);

        } else if(s.compareTo("submit coupons and pay")==0){
            int x;
            int j;
            int z;

            try {
                if (fivePCouponToUse.getText().isEmpty() || tenPCouponToUse.getText().isEmpty() || fiftPCouponToUse.getText().isEmpty()) {
                    throw new NotNullCouponToUseException("Coupon fields can't be null");

                } else {
                    x = Integer.parseInt(fivePCouponToUse.getText());
                    j = Integer.parseInt(tenPCouponToUse.getText());
                    z = Integer.parseInt(fiftPCouponToUse.getText());

                    ApplyCouponAid applyCouponAid= new ApplyCouponAid();
                    Double finalPrice= applyCouponAid.applycoupnaid(x,j,z,y,fivep,tenp,fiftp);
                    nullfinalpriceaid(finalPrice, event);
                }
            } catch(NotNullCouponToUseException e) {
                ShowAlertAid.showalerterror("Complete all coupon fields");
            } catch (NotEnoughCouponAvailableException e) {
                ShowAlertAid.showalerterror("Not Enough Coupon Available");
            } catch (IOException e) {
                throw new IOExceptionHandler("IOException error");
            }

        } else if(s.compareTo("back")==0){
            FXMLLoader root = new FXMLLoader(Objects.requireNonNull(getClass().getResource("EventCreateSecondView.fxml")));
            GUISwtichAid.eventcreatesecondviewguiswitch(event,y,root);
        }
    }

    private void nullfinalpriceaid(Double finalPrice, Event event) throws IOException, IOExceptionHandler {
        if(finalPrice != null) {
            setCurrentCompanyCoupons();
            eventCreateController.addPoints(y,"add");
            total.setText("Total: " + finalPrice);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Event creation successfully completed");
            alert.showAndWait();
            dummyPayCommandLine.setText("back");
            dummyPayCommandLine.fireEvent(event);
        }
    }

    public void currentCompany(CompanyBean x) throws IOExceptionHandler, IOException {
        y=x;
        setCurrentCompanyCoupons();
    }

    private void setCurrentCompanyCoupons() throws IOExceptionHandler, IOException {
        List<CouponBean> coupList= eventCreateController.getAvailableCoupons(y);
        fivep= coupList.get(0).getQuantity();
        tenp= coupList.get(1).getQuantity();
        fiftp= coupList.get(2).getQuantity();
        fivePCouponAvailable.setText("Available 5% coupons: "+ fivep);
        tenPCouponAvailable.setText("Available 10% coupons: "+ tenp);
        fiftPCouponAvailable.setText("Available 15% coupons: "+ fiftp);
    }

}

