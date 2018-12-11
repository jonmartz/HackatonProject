package View;


import Controller.MailBoxController;
import Model.MailBox;
import Model.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class MailBoxView extends AbstractView {

    public TableColumn senderColumn;
    public TableColumn dateColumn;
    public TableColumn timeColumn;
    public TableColumn kindColumn;
    public TableView mailBoxTableView;
    private MailBox mailBox;
    public Button goBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MailBoxController mailBoxController = new MailBoxController();
        this.setController(mailBoxController);
        mailBoxController.setView(this);
    }

    public void displayTable()
    {
        senderColumn.setCellValueFactory(new PropertyValueFactory<TableContent,String>("sender"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<TableContent,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<TableContent,String>("time"));
        kindColumn.setCellValueFactory(new PropertyValueFactory<TableContent,String>("kind"));
        mailBoxTableView.setRowFactory(new Callback<TableView<TableContent>, TableRow<TableContent>>() {
            @Override
            public TableRow<TableContent> call(TableView<TableContent> param) {
                return new TableRow<TableContent>() {
                    @Override
                    protected void updateItem(TableContent item, boolean empty) {
                        if (item==null || item.hasBeenRead) {
                            setStyle("");
                        } else  {
                                setStyle("-fx-font-weight: bold");
                        }

                    }
                };
            }
        });
        mailBoxTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                TableContent tableContent=(TableContent)newSelection;
                Message message = this.mailBox.getMesseageById(tableContent.id);
                this.getController().setCurrentMessage(message);
                this.messageView(message.getViewName(),message.getKind());

            }
        });
        this.mailBox = ((MailBoxController)getController()).getMailBox();
        Message message;
        ObservableList<TableContent> items = FXCollections.observableArrayList();
        if(this.mailBox!=null)
        {

            for(int i=0;i<this.mailBox.size();i++)
            {
                message = mailBox.getMessage(i);
                items.add(new TableContent(message.getSender(),message.getDate(),message.getTime(),message.getKind(),message.getId(),message.hasbeenRead()));
            }
        }
        this.mailBoxTableView.setItems(items);

    }

    /**
     * This class will represent a content of a TableView
     */
    public class TableContent {
        public String sender;
        public String date;
        public String time;
        public String kind;
        public int id;
        public boolean hasBeenRead;

        public TableContent(String sender,String date,String time,String kind,int id,boolean hasBeenRead)
        {
            this.hasBeenRead = hasBeenRead;
            this.sender = sender;
            this.date = date;
            this.time = time;
            this.kind = kind;
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public String getKind() {
            return kind;
        }

        public String getDate() {
            return date;
        }

        public String getSender() {
            return sender;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setHasBeenRead(boolean hasBeenRead) {
            this.hasBeenRead = hasBeenRead;
        }

        public boolean isHasBeenRead() {
            return hasBeenRead;
        }
    }

    public void goBack()
    {
        ((MailBoxController)getController()).goBack();
    }


}
