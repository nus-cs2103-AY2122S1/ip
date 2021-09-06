package duke;

import duke.exception.DukeFileException;
import duke.task.TaskList;
import javafx.application.Application;

public class Duke {
    private static TaskList taskList = new TaskList();

    public static TaskList getTaskList() {
        return taskList;
    }

    public static void main(String[] args) {
        Application.launch(DukeUi.class, args);
        try {
            DukeStorage.saveTaskList(taskList);
        } catch (DukeFileException e) {
            System.out.println(e.getMessage());
        }
    }
}
