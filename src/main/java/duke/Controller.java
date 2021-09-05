package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class Controller {

    private static Storage storage = new Storage("C:\\Users\\65906\\IdeaProjects\\ip\\duke.txt");
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

    public void load() {
        listLabel.setText(tasks.toString());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addTaskError.setText("");
        assert addTaskError.getText().equals("");
        deleteError.setText("");
        assert deleteError.getText().equals("");
        doneError.setText("");
        assert doneError.getText().equals("");
    }



    public void add(ActionEvent a) {
        if (taskType.getText().equalsIgnoreCase("T")) {
            tasks.addTask(Parser.taskParse(taskType.getText(), taskDescription.getText(), ""));
            this.load();
        } else if (taskType.getText().equalsIgnoreCase("D") || taskType.getText().equalsIgnoreCase( "E")) {
            tasks.addTask(Parser.taskParse(taskType.getText(), taskDescription.getText(),
                    taskDate.getValue().toString()));
            this.load();
        } else {
            addTaskError.setText("Wrong Format");
        }

    }

    public void done(ActionEvent a) {
        try {
            int doneIndex = Integer.parseInt(doneText.getText()) - 1;
            if(doneIndex > tasks.size() - 1 || doneIndex < 0) {
                doneError.setText("Please enter a number in the list");
            } else {
                tasks.markAsDone(doneIndex);
                this.load();
            }
        } catch (NumberFormatException e) {
            doneError.setText("Please enter integer");
        }
    }

    public void delete(ActionEvent a){
        try {
            int deleteIndex = Integer.parseInt(deleteText.getText()) - 1;
            if(deleteIndex > tasks.size() - 1 || deleteIndex < 0) {
                deleteError.setText("Please enter a number in the list");
            } else {
                tasks.removeTask(deleteIndex);
                this.load();
            }
        } catch (NumberFormatException e) {
            deleteError.setText("Please enter integer");
        }
    }

    public void find(ActionEvent a) {
        listLabel.setText(tasks.findTask(findText.getText()) + "Press load to go back!");
    }
}
