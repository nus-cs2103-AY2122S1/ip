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

    /**
     * Class Constructor.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    /**
     * Starts the application, allows the application to start taking in inputs from
     * the users.
     */
    public void start() {
        String userInput;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> savedTasks = storage.loadData();

        taskList.loadFromStorage(savedTasks);

        Ui.printIntro();
        while (true) {
            userInput = sc.nextLine();

            try {
                Parser parser = new Parser();

                Command command = parser.parseCommands(userInput);
                command.execute(taskList, storage);
                if (command.getIsExit()) {
                    sc.close();
                    return;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printMessage(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
            }

        }

    }
}