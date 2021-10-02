package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static duke.Parser.taskParse;


public class Controller implements Initializable {

    private static Storage storage;
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

    @FXML
    private TextField updateIndex;

    @FXML
    private TextField updateDescription;

    @FXML
    private Label updateError;

    @FXML
    private DatePicker updateDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        storage = new Storage("duke.txt");
        this.load();
    }

    /**
     * Loads the tasklist when the load button is pressed
     *
     * @param a action of pressing load button
     */
    public void load(ActionEvent a) {
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            tasks = new TaskList();
        }
        listLabel.setText(tasks.toString());
    }

    /**
     * Loads updated tasklist when other buttons are pressed
     */
    public void load() {
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            tasks = new TaskList();
        }
        listLabel.setText(tasks.toString());
        addTaskError.setText("");
        deleteError.setText("");
        doneError.setText("");
        updateError.setText("");
    }

    public void save() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listLabel.setText(tasks.toString());
        addTaskError.setText("");
        deleteError.setText("");
        doneError.setText("");
        updateError.setText("");
    }

    /**
     * Adds a task to the list when add button is pressed
     *
     * @param a action of pressing add button
     */
    public void add(ActionEvent a) {
        if (taskType.getText().equalsIgnoreCase("T")) {
            tasks.addTask(taskParse(taskType.getText(), taskDescription.getText(), ""));
            this.save();
            assert addTaskError.getText().equals("");
        } else if (taskType.getText().equalsIgnoreCase("D") || taskType.getText().equalsIgnoreCase("E")) {
            if (taskDate.getValue() == null) {
                addTaskError.setText("Add a Date!");
            } else {
                tasks.addTask(taskParse(taskType.getText(),
                        taskDescription.getText(),
                        taskDate.getValue().toString()));
                this.save();
                assert addTaskError.getText().equals("");
            }
        } else {
            addTaskError.setText("Task type to be T, D or E");
        }

    }

    /**
     * Marks a task as done on the list when the done button is pressed
     *
     * @param a action of press done button
     */
    public void done(ActionEvent a) {
        try {
            int doneIndex = Integer.parseInt(doneText.getText()) - 1;
            if (doneIndex > tasks.size() - 1 || doneIndex < 0) {
                doneError.setText("Please enter a number in the list");
            } else {
                tasks.markAsDone(doneIndex);
                this.save();
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
                this.save();
                assert deleteError.getText().equals("");
            }
        } catch (NumberFormatException e) {
            deleteError.setText("Please enter integer");
        }
    }


    /**
     * Updates task in tasklist given user input
     *
     * @param a action of press update button
     */
    public void update(ActionEvent a) {
        try {
            int index = Integer.parseInt(updateIndex.getText()) - 1;
            if (index > tasks.size() - 1 || index < 0) {
                updateError.setText("Please enter a number in the list");
            } else {
                tasks.updateTask(index, updateDescription.getText(), updateDate.getValue());
                this.save();
                assert updateError.getText().equals("");
            }
        } catch (NumberFormatException e) {
            updateError.setText("Please enter integer");
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
