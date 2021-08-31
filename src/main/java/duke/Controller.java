package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;


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
            String listContent = "";
            for (int i = 0; i < tasks.size(); i++) {
                listContent += (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
            }
            listLabel.setText("Here are the tasks in your list:\n" + listContent);
        }
    }

    public void load() {
        String listContent = "";
        for (int i = 0; i < tasks.size(); i++) {
            listContent += (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        listLabel.setText("Here are the tasks in your list:\n" + listContent);
        addTaskError.setText("");
        deleteError.setText("");
        doneError.setText("");
    }

    public Task taskParse(String type, String description, String dateTime){
        if (type.equalsIgnoreCase("T")) {
            return new Todo(description);
        } else if (type.equalsIgnoreCase("D")) {
            return new Deadline(description,dateTime);
        } else if (type.equalsIgnoreCase("E")) {
            return new Event(description,dateTime);
        } else {
            return null;
        }
    }

    public void add(ActionEvent a) {
        if (taskType.getText().equalsIgnoreCase("T")) {
            tasks.addTask(taskParse(taskType.getText(), taskDescription.getText(), ""));
            this.load();
        } else if (taskType.getText().equalsIgnoreCase("D") || taskType.getText().equalsIgnoreCase( "E")) {
            tasks.addTask(taskParse(taskType.getText(), taskDescription.getText(),
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
        ArrayList<Task> result = new ArrayList<>();
        String listContent = "";

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).description.contains(findText.getText())) {
                result.add(tasks.getTask(i));
            }
        }
        if (result.size() == 0) {
            listLabel.setText("There are no matching task in your list!\nPress load to go back!");
        } else {
            for (int i = 0; i < result.size(); i++) {
                listContent += (i + 1) + ". " + result.get(i).toString() + "\n";
            }
            listLabel.setText("Here are the matching tasks in your list:\n" + listContent + "Press load to go back!");
        }
    }
}
