package seedu.duke;

import java.util.Scanner;

import seedu.duke.commands.Command;
import seedu.duke.commands.Parser;
import seedu.duke.commands.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.Task;

import java.util.ArrayList;

public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private String response;

    /**
     * Class Constructor.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    public void loadDataFromStorage() {
        ArrayList<Task> savedTasks = storage.loadData();
        taskList.loadFromStorage(savedTasks);
    }

    public String getResponse(String userInput) {

        // Ui.printIntro();

        try {
            Parser parser = new Parser();

            Command command = parser.parseCommands(userInput);
            this.response = command.execute(taskList, storage);
            return this.response;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMessage(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            return "error";
        }

    }
}
