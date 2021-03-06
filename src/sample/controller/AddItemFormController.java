package sample.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.model.Task;

public class AddItemFormController {

    private int userId;
    private DatabaseHandler databaseHandler;

    @FXML
    private Label successLabel ;

    @FXML
    private JFXButton todosButton;


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


                successLabel.setVisible(true);
                todosButton.setVisible(true);
                int taskNuber = 0;
                try {
                    taskNuber = databaseHandler.getAllTasks(AddItemController.userId);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                todosButton.setText("My 2Do's: " + "(" + taskNuber +")");


                taskField.setText("");
                descriptionField.setText("");

                todosButton.setOnAction(actionEvent1 -> {
                    // send users to the list screen

                });
//                System.out.println("Task added successfully!");

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