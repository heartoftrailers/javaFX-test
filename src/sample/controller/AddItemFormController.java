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

    private int userId;
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

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();
            if (!taskText.equals("") || !taskDescription.equals("")) {

                System.out.println("User Id: " + AddItemController.userId);
                task.setUserId(AddItemController.userId);

//                task.setUserId(getUserId());
                task.setDatecreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);
                databaseHandler.insertTask(task);

                System.out.println("Task added successfully!");

            }else {
                System.out.println("Nothing added!");

            }


        });
    }
    public int getUserId() {
        System.out.println("from getUserId() " + userId);

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("From setUserId " + this.userId);
    }
}