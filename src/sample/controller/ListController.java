package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
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

public class ListController {

    @FXML
    private JFXListView<String> listTask;

    @FXML
    private URL location;


    @FXML
    private TextField listTaskField;

    @FXML
    private TextField listDescriptionField;

    @FXML
    private Button listSaveTaskButton;
    ObservableList<String> listview = FXCollections.observableArrayList(
            "alex",
            "alex1", "Java", "Android", "JavaFX", "Prem");

    @FXML
    void initialize() {

        listTask.setItems(listview);
        listTask.setCellFactory(param -> new JFXCell());


    }
    static class JFXCell extends JFXListCell<String> {
        //Hbox = horizontal box

        HBox hBox = new HBox();
        JFXButton helloButton = new JFXButton ("hello");
        Label task = new Label();
        Pane pane = new Pane();
        Image icon = new Image("/sample/assets/gender-neutral-user.png");
        ImageView iconImg = new ImageView(icon);

        public JFXCell() {
            super();

            hBox.getChildren().addAll(iconImg, task, helloButton);
            hBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(String taskName, boolean empty) {
            super.updateItem(taskName, empty);
            setText(null);
            setGraphic(null);

            if (taskName != null && !empty) {
                task.setText(taskName);
                setGraphic(hBox);
            }
        }
    }
}

