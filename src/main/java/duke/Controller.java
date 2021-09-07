package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for the JavaFX application
 * Controls the input given by user
 */
public class Controller {

    private static final Storage storage = new Storage("C:\\Users\\65906\\IdeaProjects\\ip\\duke.txt");
    private static TaskList tasks;

    @FXML
    private Label listLabel;

    @FXML
    private Label deleteError;

    @FXML
    private TextField deleteText;

    @FXML
    private TextField taskType;

    @FXML
    private TextField taskDescription;

    @FXML
    private DatePicker taskDate;

    @FXML
    private Label addTaskError;

    @FXML
    private TextField doneText;

    @FXML
    private Label doneError;

    @FXML
    private TextField findText;

    /**
     * Loads the tasklist when the load button is pressed
     *
     * @param a action of pressing load button
     */
    public void load(ActionEvent a) {
        {
            try {
                tasks = new TaskList(storage.load());
            } catch (IOException | DukeException e) {
                tasks = new TaskList();
            }
            listLabel.setText(tasks.toString());
        }
    }

    /**
     * Loads updated tasklist when other buttons are pressed
     */
    public void load() {
        listLabel.setText(tasks.toString());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addTaskError.setText("");
        deleteError.setText("");
        doneError.setText("");
    }


    /**
     * Adds a task to the list when add button is pressed
     *
     * @param a action of pressing add button
     */
    public void add(ActionEvent a) {
        if (taskType.getText().equalsIgnoreCase("T")) {
            tasks.addTask(Parser.taskParse(taskType.getText(), taskDescription.getText(), ""));
            this.load();
        } else if (taskType.getText().equalsIgnoreCase("D") || taskType.getText().equalsIgnoreCase("E")) {
            tasks.addTask(Parser.taskParse(taskType.getText(), taskDescription.getText(),
                    taskDate.getValue().toString()));
            this.load();
            assert addTaskError.getText().equals("");
        } else {
            addTaskError.setText("Wrong Format");
        }

    }

    /**
     * Mark a task as done on the list when done button is pressed
     *
     * @param a action of press add button
     */
    public void done(ActionEvent a) {
        try {
            int doneIndex = Integer.parseInt(doneText.getText()) - 1;
            if (doneIndex > tasks.size() - 1 || doneIndex < 0) {
                doneError.setText("Please enter a number in the list");
            } else {
                tasks.markAsDone(doneIndex);
                this.load();
                assert doneError.getText().equals("");
            }
        } catch (NumberFormatException e) {
            doneError.setText("Please enter integer");
        }
    }

    /**
     * Deletes a task to the list when delete button is pressed
     *
     * @param a action of press delete button
     */
    public void delete(ActionEvent a) {
        try {
            int deleteIndex = Integer.parseInt(deleteText.getText()) - 1;
            if (deleteIndex > tasks.size() - 1 || deleteIndex < 0) {
                deleteError.setText("Please enter a number in the list");
            } else {
                tasks.removeTask(deleteIndex);
                this.load();
                assert deleteError.getText().equals("");
            }
        } catch (NumberFormatException e) {
            deleteError.setText("Please enter integer");
        }
    }

    /**
     * Displays a filtered tasklist given a string to find
     *
     * @param a action of press find button
     */
    public void find(ActionEvent a) {
        listLabel.setText(tasks.findTask(findText.getText()) + "Press load to go back!");
    }
}
