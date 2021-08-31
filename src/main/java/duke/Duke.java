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
                    this.handleFunctionCommands(type, userInput);

                } else if (isTaskCommand) {
                    // Adds a Task to the task list.
                    this.handleTaskCommand(type, userInput);

                } else {
                    throw new DukeException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de) {
                this.ui.showError(de);
            }
        }
    }

    private void handleFunctionCommands(Parser.CommandType type, String userInput) throws DukeException {
        if (type == Parser.CommandType.EXIT) {
            //Closes the program.
            this.storage.save(this.taskList);
            this.isOpen = false;
            this.ui.exit();

        } else if (type == Parser.CommandType.LIST) {
            // List all tasks in the task list.
            this.ui.printMessage(this.taskList.listTasks());

        } else if (type == Parser.CommandType.DONE) {
            // Mark a certain task as done.
            int taskNumber = Parser.parseDoneCommand(userInput);
            this.ui.printMessage(this.taskList.markTaskAsDone(taskNumber));
            this.storage.save(this.taskList);

        } else if (type == Parser.CommandType.DELETE) {
            // Deletes a task from the task list.
            int taskNumber = Parser.parseDeleteCommand(userInput);
            this.ui.printMessage(this.taskList.deleteTask(taskNumber));
            this.storage.save(this.taskList);

        } else if (type == Parser.CommandType.FIND) {
            // Finds the tasks in the task list that contain the String.
            String toSearch = Parser.parseFindCommand(userInput);
            this.ui.printMessage(this.taskList.findTasksWithSubstring(toSearch));

        } else {
            throw new DukeException(
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    private void handleTaskCommand(Parser.CommandType type, String userInput) throws DukeException {
        String[] splitBySpace = userInput.split(" ", 2);

        if (splitBySpace.length > 1 && splitBySpace[1].trim().length() > 0) {
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
            this.ui.printMessage(this.taskList.addTask(newTask));
            this.storage.save(this.taskList);

        } else {
            throw new DukeException("☹ OOPS!!! The description of a " + splitBySpace[0]
                    + " cannot be empty.");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
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
