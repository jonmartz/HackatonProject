package View;

import Controller.RequestMessageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestMessageView  extends MessageView  {

    @FXML
    public Button offeredVacationButton;
    public Button acceptRequestButton;
    public Button confirmPaymentButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequestMessageController requestMessageController = new RequestMessageController();
        this.setController(requestMessageController);
        requestMessageController.setView(this);
    }

    public void acceptRequest()
    {
        ((RequestMessageController)this.getController()).acceptRequest();
    }

    public void checkOfferedVacation() {
        ((RequestMessageController)this.getController()).checkOfferedVacation();
    }

    public void confirmPayment() {
        ((RequestMessageController)this.getController()).confirm();
    }
}
