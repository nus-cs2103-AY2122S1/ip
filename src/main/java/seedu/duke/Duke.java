package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.Parser;
import seedu.duke.commands.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.Task;
import seedu.duke.timetable.Timetable;

import java.util.ArrayList;

public class Duke {
    private final TaskList taskList;
    private final Timetable timetable;
    private final Storage storage;
    private String response;

    /**
     * Class Constructor.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.timetable = new Timetable();
    }

    public void loadDataFromStorage() {
        ArrayList<Task> savedTasks = this.storage.loadData();
        this.taskList.loadFromStorage(savedTasks);
        this.timetable.initialise(savedTasks);
    }

    public String getResponse(String userInput) {
        try {
            Parser parser = new Parser();

            Command command = parser.parseCommands(userInput);
            this.response = command.execute(taskList, timetable, storage);
            return this.response;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.printMessage(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
        }

    }
}
