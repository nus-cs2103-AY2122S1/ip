package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.commands.Parser;
import duke.commands.Ui;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;

import java.io.FileNotFoundException;
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
     * 
     * @throws FileNotFoundException when file is not found in the given location.
     */
    public void start() throws FileNotFoundException {
        String userInput = "";
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
                continue;
            }

        }

    }
}