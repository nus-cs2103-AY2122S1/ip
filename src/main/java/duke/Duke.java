package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Handles the logic of Duke e.g. adding, saving and deleting tasks.
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isOpen;

    /**
     * Initialises a new instance of Duke.
     */
    public Duke() {
        String filepath = "save.txt";
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.isOpen = false;
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException de) {
            this.ui.showError(de);
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the Duke bot.
     */
    public void run() {
        this.isOpen = true;

        while (this.isOpen) {
            try {
                String userInput = this.ui.readCommand();
                Parser.CommandType type = Parser.decipherInput(userInput);

                boolean isFunctionCommand = type == Parser.CommandType.EXIT
                        || type == Parser.CommandType.LIST
                        || type == Parser.CommandType.DONE
                        || type == Parser.CommandType.DELETE
                        || type == Parser.CommandType.FIND;

                boolean isTaskCommand = type == Parser.CommandType.TODO
                        || type == Parser.CommandType.DEADLINE
                        || type == Parser.CommandType.EVENT;

                if (isFunctionCommand) {
                    ui.printMessage(this.handleFunctionCommands(type, userInput));

                } else if (isTaskCommand) {
                    // Add a Task to the task list.
                    ui.printMessage(this.handleTaskCommand(type, userInput));

                } else {
                    throw new DukeException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de) {
                this.ui.showError(de);
            }
        }
    }

    private String handleFunctionCommands(Parser.CommandType type, String userInput) throws DukeException {
        if (type == Parser.CommandType.EXIT) {
            // Close the program.
            this.storage.save(this.taskList);
            this.isOpen = false;
            this.ui.exit();
            return Ui.CLOSING_MESSAGE;

        } else if (type == Parser.CommandType.LIST) {
            return this.taskList.listTasks();

        } else if (type == Parser.CommandType.DONE) {
            int taskNumber = Parser.parseDoneCommand(userInput);
            this.storage.save(this.taskList);
            return this.taskList.markTaskAsDone(taskNumber);

        } else if (type == Parser.CommandType.DELETE) {
            int taskNumber = Parser.parseDeleteCommand(userInput);
            this.storage.save(this.taskList);
            return this.taskList.deleteTask(taskNumber);

        } else if (type == Parser.CommandType.FIND) {
            String toSearch = Parser.parseFindCommand(userInput);
            return this.taskList.findTasksWithSubstring(toSearch);

        } else {
            throw new DukeException(
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String handleTaskCommand(Parser.CommandType type, String userInput) throws DukeException {
        String[] splitBySpace = userInput.split(" ", 2);
        boolean hasInputAfterSpace = splitBySpace.length > 1
                && splitBySpace[1].trim().length() > 0;

        if (hasInputAfterSpace) {
            String substring = splitBySpace[1].trim();
            Task newTask;
            if (type == Parser.CommandType.TODO) {
                newTask = Parser.parseTodoCommand(substring);

            } else if (type == Parser.CommandType.DEADLINE) {
                newTask = Parser.parseDeadlineCommand(substring);

            } else if (type == Parser.CommandType.EVENT) {
                newTask = Parser.parseEventCommand(substring);

            } else {
                throw new DukeException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            this.storage.save(this.taskList);
            return this.taskList.addTask(newTask);

        } else {
            throw new DukeException("☹ OOPS!!! The description of a " + splitBySpace[0]
                    + " cannot be empty.");
        }
    }

    private String runFromGui(String userInput) {
        try {
            Parser.CommandType type = Parser.decipherInput(userInput);

            boolean isFunctionCommand = type == Parser.CommandType.EXIT
                    || type == Parser.CommandType.LIST
                    || type == Parser.CommandType.DONE
                    || type == Parser.CommandType.DELETE
                    || type == Parser.CommandType.FIND;

            boolean isTaskCommand = type == Parser.CommandType.TODO
                    || type == Parser.CommandType.DEADLINE
                    || type == Parser.CommandType.EVENT;

            if (isFunctionCommand) {
                return this.handleFunctionCommands(type, userInput);

            } else if (isTaskCommand) {
                // Add a Task to the task list.
                return this.handleTaskCommand(type, userInput);

            } else {
                throw new DukeException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException de) {
            return de.getMessage();
        }
    }

    /**
     * Generates and returns response from Duke.
     *
     * @param input The input from the user.
     * @return Duke's response to the input.
     */
    public String getResponse(String input) {
        return runFromGui(input);
    }

    /**
     * The main method.
     * @param args Any CLI arguments. (Not used)
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
