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
import java.io.IOException;

public class Duke {
    private final TaskList taskList;
    private final Storage storage;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public void start() throws FileNotFoundException, IOException {
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

// action = actionDescription[0];
// switch (action) {
// case "bye":
// Ui.printBye();
// sc.close();
// return;

// case "list":
// Ui.printList(taskList.getTaskList());
// break;

// case "done":
// taskList.doneItem(userInput.split(" ")[1]);
// storage.updateDone(userInput.split(" ")[1]);
// break;

// case "todo":
// descriptions = actionDescription[1];
// ToDos todos = new ToDos(descriptions);
// command.execute(taskList, storage, todos);
// break;

// case "deadline":
// descriptions = actionDescription[1];
// onlyDescription = descriptions.split(" /")[0];
// dateTime = descriptions.split(" /by ")[1];
// dateTime = dateTimeFormatter(dateTime);
// Deadline deadline = new Deadline(onlyDescription, dateTime);
// command.execute(taskList, storage, deadline);
// break;

// case "event":
// descriptions = actionDescription[1];
// onlyDescription = descriptions.split(" /")[0];
// dateTime = descriptions.split(" /at ")[1];
// Events event = new Events(onlyDescription, dateTime);
// command.execute(taskList, storage, event);
// break;

// case "delete":
// Command deleteCommand = new DeleteCommand(userInput.split(" ")[1]);
// deleteCommand.execute(taskList, storage);
// break;

// case "":
// break;

// default:
// Ui.printMessage(Ui.ERROR_MSG_UNKOWN_MSG);
// break;
// }