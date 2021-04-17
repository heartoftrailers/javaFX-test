package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import sample.Database.DatabaseHandler;
import sample.model.Task;

public class ListController {

    @FXML
    private JFXListView<Task> listTask;

    @FXML
    private URL location;

    @FXML
    private TextField listTaskField;

    @FXML
    private TextField listDescriptionField;

    @FXML
    private Button listSaveTaskButton;

    private ObservableList<Task> tasks;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {

        tasks = FXCollections.observableArrayList();
        databaseHandler = new DatabaseHandler();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);
        while (resultSet.next()) {
            Task task = new Task();
            task.setTastId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            System.out.println("User tasks: " + resultSet.getString("task"));
            tasks.addAll(task);
        }


        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new CellController());
        listSaveTaskButton.setOnAction(event -> {
            addNewTask();
        });




    }
    public void addNewTask(){
            if (!listTaskField.getText().equals("") || listDescriptionField.getText().equals("")) {
                Task myNewTask = new Task();

                Calendar calendar = Calendar.getInstance();

                java.sql.Timestamp timestamp =
                        new java.sql.Timestamp(calendar.getTimeInMillis());

                myNewTask.setUserId(AddItemController.userId);
                myNewTask.setTask(listTaskField.getText().trim());
                myNewTask.setDescription(listDescriptionField.getText().trim());
                myNewTask.setDatecreated(timestamp);
                databaseHandler.insertTask(myNewTask);
                listTaskField.setText("");
                listDescriptionField.setText("");


                // auto refresh tasks
                try {
                    initialize();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

    }

}

