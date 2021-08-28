package util.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import util.controller.Duke;

/**
 * Input window for tasks to be sent to the Duke.
 *
 */
public class TaskInputWindow extends AnchorPane {

    @FXML
    private TabPane tabs;
    @FXML
    private Tab todo;
    @FXML
    private Tab deadline;
    @FXML
    private Tab event;
    @FXML
    private TextField todoDescription;
    @FXML
    private TextField eventDescription;
    @FXML
    private TextField deadlineDescription;
    @FXML
    private DatePicker eventDate;
    @FXML
    private DatePicker deadlineDate;

    private static Duke duke;
    private static Stage currentStage;

    public static void setStage(Stage stage) {
        TaskInputWindow.currentStage = stage;
    }


    public static void setDuke(Duke duke) {
        TaskInputWindow.duke = duke;
    }



    /**
     * Handle the addition of tasks.
     *
     */
    @FXML
    private void handleAddTodo() {
        String val = todoDescription.getText();
        duke.addTodo(val);
        currentStage.close();

    }

    /**
     * Handle the addition of tasks.
     *
     */
    @FXML
    private void handleAddDeadline() {
        String val = deadlineDescription.getText();
        LocalDate date = deadlineDate.getValue();
        duke.addDeadline(val, date);
        currentStage.close();
    }

    /**
     * Handle the addition of tasks.
     *
     */
    @FXML
    private void handleAddEvent() {
        String val = eventDescription.getText();
        LocalDate date = eventDate.getValue();
        duke.addEvent(val, date);
        currentStage.close();
    }


}
