package dino.graphics;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class HelpWindow extends AnchorPane {


    @FXML
    private Label helpText;

    private String generalHelp = "Welcome to Dino! Here are all the commands available!";
    private String toDoHelp = "todo: creates a new todo task \n     "
            + "Format: todo <enter the task here!>";
    private String eventHelp = "event: creates a new event task \n      "
            + "Format: event <enter the event description here!> /at <enter the location here!>";
    private String deadlineHelp = "deadline: creates a new deadline task \n     "
            + "Format: deadline <enter the deadline description here!> /by <YYYY-MM-DD HH:MM>";

    private String doneHelp = "done: marks a task as completed \n      "
            + " Format: done <enter the task number here!>";
    private String deleteHelp = "delete: deletes a task \n      "
            + "Format: delete <enter the task number here!>";
    private String findHelp = "find: returns tasks matching the keyword \n      "
            + "Format: find <enter keyword here!>";
    private String byeHelp = "bye: exits the program \n     "
            + "Format: bye";
    private String listHelp = "list: view your saved tasks \n       "
            + "Format: list ";

    public HelpWindow() {
    }

    /**
     * Displays the help message in the new window
     */
    @FXML
    public void initialize() {
        helpText.setText(generalHelp + "\n\n" + toDoHelp + "\n\n" + eventHelp + "\n\n" + deadlineHelp + "\n\n"
                + doneHelp + deleteHelp + "\n\n" + findHelp + "\n\n" + listHelp + "\n\n" + byeHelp);
    }

}
