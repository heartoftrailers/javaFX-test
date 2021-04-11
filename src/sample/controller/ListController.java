package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @FXML
    void initialize() {

        Task myTask = new Task();
        myTask.setTask("Clean Car");
        myTask.setDescription("need to be cleaned");
        myTask.setDatecreated(Timestamp.valueOf(LocalDateTime.now()));

        Task myTask2 = new Task();
        myTask2.setTask("Pro");
        myTask2.setDescription("Bye Turnu");
        myTask2.setDatecreated(Timestamp.valueOf(LocalDateTime.now()));


        tasks = FXCollections.observableArrayList();

        tasks.addAll(myTask,myTask2);

        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new CellController());



    }

}

