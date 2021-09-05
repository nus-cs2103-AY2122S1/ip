package duke.util.windows;

import java.time.LocalDate;

import duke.util.commons.Messages;
import duke.util.controller.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Input window for tasks to be sent to the Duke.
 *
 */
public class TaskInputWindow extends AnchorPane {

    private static final int ERROR_WRAPPING_WIDTH = 200;
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
    @FXML
    private Text deadlineErrorMessage;
    @FXML
    private Text eventErrorMessage;

    @FXML
    public void initialize() {
        deadlineErrorMessage.setWrappingWidth(ERROR_WRAPPING_WIDTH);
        eventErrorMessage.setWrappingWidth(ERROR_WRAPPING_WIDTH);
    }


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
        duke.printList();
        this.clearContent();

    }

    /**
     * Handle the addition of tasks.
     */
    @FXML
    private void handleAddDeadline() {
        if (deadlineDate.getValue() == null) {
            this.deadlineErrorMessage.setText(Messages.NO_DATE_SELECTED);
            return;
        }
        String val = deadlineDescription.getText();
        LocalDate date = deadlineDate.getValue();
        duke.addDeadline(val, date);
        duke.printList();
        this.clearContent();
    }


    /**
     * Handle the addition of tasks.
     */
    @FXML
    private void handleAddEvent() {
        if (eventDate.getValue() == null) {
            this.eventErrorMessage.setText(Messages.NO_DATE_SELECTED);
            return;
        }

        String val = eventDescription.getText();
        LocalDate date = eventDate.getValue();
        duke.addEvent(val, date);
        duke.printList();
        this.clearContent();
    }

    private void clearContent() {
        this.deadlineErrorMessage.setText("");
        this.eventErrorMessage.setText("");
        this.eventDescription.setText("");
        this.deadlineDescription.setText("");
        this.todoDescription.setText("");
        this.deadlineDate.getEditor().clear();
        this.eventDate.getEditor().clear();
    }





}
