import duke.CommandHandler;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.UnknownInputException;

import java.io.IOException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * List of input commands the user can use.
     */
    public enum InputCommands {
        bye, list, done, delete, todo, deadline, event, find
    }

    /**
     * Constructor for Duke.
     * Creates a new storage file to store tasks inputted by the user.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = new TaskList();
            storage.loadTasksToUI();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Constructor for Duke.
     * Loads existing tasks to the file (if any).
     *
     * @param filePath filepath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.loadTasksToUI();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run () {
        System.out.println(ui.greet());
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = ui.echoCommand();
                CommandHandler commandHandler = new CommandHandler(ui, taskList);
                if (userCommand.equals("bye")) {
                    System.out.println(ui.exit());
                    isExit = true;
                } else if (userCommand.equals("list")) {
                    System.out.println(ui.retrieveList());
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("done")) {
                    System.out.println(commandHandler.handleDone(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("delete")) {
                    System.out.println(commandHandler.handleDelete(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("todo")) {
                    Task todoTask = Parser.parseTodoTasks(userCommand);
                    System.out.println(commandHandler.handleTodo(todoTask));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("deadline")) {
                    Task deadlineTask = Parser.parseDeadlineTasks(userCommand);
                    System.out.println(commandHandler.handleDeadline(deadlineTask));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("event")) {
                    Task eventTask = Parser.parseEventTasks(userCommand);
                    System.out.println(commandHandler.handleEvent(eventTask));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("find")) {
                    System.out.println(commandHandler.handleFind(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("tag")) {
                    System.out.println(commandHandler.handleTag(userCommand));
                } else if (userCommand.equals("listInputs")) {
                    for (InputCommands inputs : InputCommands.values()) {
                        System.out.println(inputs);
                    }
                } else {
                    throw new UnknownInputException("error");
                }
            } catch (DukeException e) {
                System.out.println(ui.getError(e.getMessage()));
            }
        }
    }

    public String getResponse(String input) {
        try {
            CommandHandler commandHandler = new CommandHandler(ui, taskList);
            if (input.equals("bye")) {
                storage.saveTasksToFile();
                return ui.exit();
            } else if (input.equals("list")) {
                storage.saveTasksToFile();
                return ui.retrieveList();
            } else if (input.startsWith("done")) {
                storage.saveTasksToFile();
                return commandHandler.handleDone(input);
            } else if (input.startsWith("delete")) {
                storage.saveTasksToFile();
                return commandHandler.handleDelete(input);
            } else if (input.startsWith("todo")) {
                storage.saveTasksToFile();
                Task todoTask = Parser.parseTodoTasks(input);
                return commandHandler.handleTodo(todoTask);
            } else if (input.startsWith("deadline")) {
                storage.saveTasksToFile();
                Task deadlineTask = Parser.parseDeadlineTasks(input);
                return commandHandler.handleDeadline(deadlineTask);
            } else if (input.startsWith("event")) {
                storage.saveTasksToFile();
                Task eventTask = Parser.parseEventTasks(input);
                return commandHandler.handleEvent(eventTask);
            } else if (input.startsWith("find")) {
                storage.saveTasksToFile();
                return commandHandler.handleFind(input);
            } else if (input.startsWith("tag")) {
                storage.saveTasksToFile();
                return commandHandler.handleTag(input);
            } else {
                throw new UnknownInputException("error");
            }
        } catch (DukeException e) {
            return ui.getError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
