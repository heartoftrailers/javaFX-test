package sample.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.model.Task;

public class AddItemFormController {
    private DatabaseHandler databaseHandler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField taskField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button saveTaskButton;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();
        saveTaskButton.setOnAction(actionEvent -> {
            Task task = new Task();

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());



            task.setDatecreated(timestamp);
            task.setDescription("My first task");
            task.setTask("make this work");
            databaseHandler.insertTask(task);

        });
    }
}
