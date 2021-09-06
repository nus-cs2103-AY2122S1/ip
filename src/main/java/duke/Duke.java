package duke;

import duke.exception.DukeFileException;
import duke.task.TaskList;
import javafx.application.Application;

public class Duke {
    private static TaskList tList = new TaskList();

    public static TaskList getTList() {
        return tList;
    }

    public static void main(String[] args) {
        Application.launch(DukeUi.class, args);
        try {
            DukeStorage.saveTaskList(tList);
        } catch (DukeFileException e) {
            System.out.println(e.getMessage());
        }
    }
}
