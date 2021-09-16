package skeltalgui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import skeltal.Storage;
import skeltal.Ui;
import skeltal.task.Task;
import skeltal.task.TaskList;
import skeltal.task.expense.Expense;
import skeltal.task.expense.ExpenseList;

/**
 * Controller for skeltalgui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lady.png"));

    /**
     * Initialize a dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Introduce the bot.
     */
    public void introduction() {
        speak(Ui.introduction());
    }

    /**
     * Load tasks into the Skeltal system.
     */
    public void loadTasks() {
        Pair<ArrayList<Task>, String> tasksStringPair =
                Storage.loadFile(Storage.SKELTAL_PATH, Storage.wrappedStringToTask);
        Pair<ArrayList<Expense>, String> expenseStringPair =
                Storage.loadFile(Storage.EXPENSE_PATH, Storage.wrappedStringToExpense);
        TaskList.loadTaskList(tasksStringPair.getKey());
        ExpenseList.loadExpenseList(expenseStringPair.getKey());
        speak(tasksStringPair.getValue());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Ui.skeltalReply(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Displays the string as a DialogueBox.
     *
     * @param str the str
     */
    public void speak(String str) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(str, dukeImage)
        );
    }
}
