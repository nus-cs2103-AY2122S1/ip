package duke.util.windows;

import java.time.LocalDate;

import duke.util.controller.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Input window for tasks to be sent to the Duke.
 *
 */
public class TaskInputWindow extends AnchorPane {

    private static Stage currentStage;
    private static Duke duke;

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



    /**
     * Sets the stage for the window.
     *
     * @param stage The stage where the window is at.
     */
    public static void setStage(Stage stage) {
        TaskInputWindow.currentStage = stage;
        //make the input form not resizable
        stage.setResizable(false);
    }

    /**
     * Sets the Duke for the input window.
     *
     * @param duke The duke to put input via.
     */
    public static void setDuke(Duke duke) {
        TaskInputWindow.duke = duke;
    }


    /**
     * Handle the addition of tasks.
     */
    @FXML
    private void handleAddTodo() {
        String val = todoDescription.getText();
        duke.addTodo(val);
        currentStage.close();
        duke.printList();

    }

    /**
     * Handle the addition of tasks.
     */
    @FXML
    private void handleAddDeadline() {
        String val = deadlineDescription.getText();
        LocalDate date = deadlineDate.getValue();
        duke.addDeadline(val, date);
        currentStage.close();
        duke.printList();
    }

    /**
     * Handle the addition of tasks.
     */
    @FXML
    private void handleAddEvent() {
        String val = eventDescription.getText();
        LocalDate date = eventDate.getValue();
        duke.addEvent(val, date);
        currentStage.close();
        duke.printList();
    }


}
