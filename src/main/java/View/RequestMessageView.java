package View;

import Controller.RequestMessageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View for the request message screen
 */
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

    /**
     * Activated by the accept request button, for cash payment
     */
    public void acceptRequest()
    {
        ((RequestMessageController)this.getController()).acceptRequest();
    }

//    /**
//     * View the details of the offered vacation in trade
//     */
//    public void checkOfferedVacation() {
//        ((RequestMessageController)this.getController()).checkOfferedVacation();
//    }

//    /**
//     * Confirm the payment / trade
//     */
//    public void confirmPayment() {
//        ((RequestMessageController)this.getController()).confirm();
//    }
}
