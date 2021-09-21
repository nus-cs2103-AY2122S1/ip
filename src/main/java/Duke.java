import duke.CommandHandler;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.UnknownInputException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

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

    //Solution below adapted from https://github.com/Wincenttjoi/CS2103T-Duke-chatbot
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
                    System.out.println(commandHandler.handleList());
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("done")) {
                    int indexOfTaskToBeMarkedDone = Parser.parseDoneTasks(userCommand);
                    System.out.println(commandHandler.handleDone(indexOfTaskToBeMarkedDone));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("delete")) {
                    int indexOfTaskToDelete = Parser.parseDeleteTasks(userCommand);
                    System.out.println(commandHandler.handleDelete(indexOfTaskToDelete));
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
                    String keyword = Parser.parseFindTasks(userCommand);
                    System.out.println(commandHandler.handleFind(keyword));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("tag")) {
                    int indexOfTaskToBeTagged = Parser.parseTagTasksForIndexOfTask(userCommand);
                    String tag = Parser.parseTagTasksForTag(userCommand);
                    System.out.println(commandHandler.handleTag(indexOfTaskToBeTagged, tag));
                } else if (userCommand.equals("help")) {
                    System.out.println(commandHandler.help());
                } else {
                    throw new UnknownInputException("error");
                }
            } catch (DateTimeParseException e) {
                System.out.println(ui.getDateTimeErrorMessage(e.getMessage()));
            } catch (DukeException e) {
                System.out.println(ui.getError(e.getMessage()));
            }
        }
    }

    //Solution below adapted from https://github.com/Wincenttjoi/CS2103T-Duke-chatbot
    public String getResponse(String input) {
        try {
            CommandHandler commandHandler = new CommandHandler(ui, taskList);
            if (input.equals("bye")) {
                storage.saveTasksToFile();
                return ui.exit();
            } else if (input.equals("list")) {
                storage.saveTasksToFile();
                return commandHandler.handleList();
            } else if (input.startsWith("done")) {
                storage.saveTasksToFile();
                int indexOfTaskToBeMarkedDone = Parser.parseDoneTasks(input);
                return commandHandler.handleDone(indexOfTaskToBeMarkedDone);
            } else if (input.startsWith("delete")) {
                storage.saveTasksToFile();
                int indexOfTaskToDelete = Parser.parseDeleteTasks(input);
                return commandHandler.handleDelete(indexOfTaskToDelete);
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
                String keyword = Parser.parseFindTasks(input);
                return commandHandler.handleFind(keyword);
            } else if (input.startsWith("tag")) {
                storage.saveTasksToFile();
                int indexOfTaskToBeTagged = Parser.parseTagTasksForIndexOfTask(input);
                String tag = Parser.parseTagTasksForTag(input);
                return commandHandler.handleTag(indexOfTaskToBeTagged, tag);
            } else if (input.equals("help")) {
                storage.saveTasksToFile();
                return commandHandler.help();
            } else {
                throw new UnknownInputException("error");
            }
        } catch (DateTimeParseException e) {
            return ui.getDateTimeErrorMessage(e.getMessage());
        } catch (DukeException e) {
            return ui.getError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
