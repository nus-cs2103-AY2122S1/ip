package duke;

import java.io.IOException;

import javafx.application.Platform;


/**
 * Duke chat bot main class.
 */
public class Duke {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;
    /**
     * Creates a new duke object
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Provides response to user based on given input.
     * @param input Input that the user enters.
     * @return The output string from duke.
     */
    public String getResponse(String input) {
        return run(input);
    }

    private String run(String input) {
        int prevSize = taskList.getSize(); //for debug use, keeps track of the size of the tasklist before operation
        String[] commandAndParameter = Parser.inputParser(input);
        Command currentCommand = Parser.parseCommand(commandAndParameter[0]);
        String currentParameter = commandAndParameter[1];

        try {
            String output;
            StringBuilder sb = new StringBuilder();
            String[] descriptionAndTimeParts;
            switch (currentCommand) {
            case UNKNOWN:
                assert (taskList.getSize() == prevSize);
                throw new DukeException("Unknown input");
            case LIST:
                return taskList.listTasks();
            case TODO:
                if (currentParameter == "") {
                    throw new DukeException("TODO cannot have empty parameter.");
                }
                output = taskList.addTask(Command.TODO, currentParameter);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == 1);
                return output;
            case EVENT:
                if (currentParameter.equals("")) {
                    throw new DukeException("The description of a event cannot be empty.");
                } else if (!currentParameter.contains(" /at ")) {
                    throw new DukeException("Missing /at command");
                }
                descriptionAndTimeParts = Parser.dateParameterParser(Command.EVENT, currentParameter);
                output = taskList.addTask(Command.EVENT, descriptionAndTimeParts[0], descriptionAndTimeParts[1]);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == 1);
                return output;
            case DEADLINE:
                if (currentParameter.equals("")) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else if (!currentParameter.contains(" /by ")) {
                    throw new DukeException("Missing /by command");
                }
                descriptionAndTimeParts = Parser.dateParameterParser(Command.DEADLINE, currentParameter);
                output = taskList.addTask(Command.DEADLINE, descriptionAndTimeParts[0], descriptionAndTimeParts[1]);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == 1);
                return output;
            case DELETE:
                if (currentParameter.equals("")) {
                    throw new DukeException("Please indicate item to be deleted.");
                }
                int index = Integer.parseInt(currentParameter) - 1;
                if (index > taskList.getSize() - 1) {
                    throw new DukeException("Item does not exist.");
                }

                output = taskList.removeTask(index);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == -1);
                return output;
            case DONE:
                if (currentParameter.equals("")) {
                    throw new DukeException("Please indicate item to be completed.");
                }
                int number = Integer.parseInt(currentParameter) - 1;
                if (number > taskList.getSize() - 1 || number < 0) {
                    throw new DukeException("Invalid item does not exist");
                }
                output = taskList.markAsDone(number);
                Storage.updateLocalFile(taskList);
                return output;
            case FIND:
                if (currentParameter.equals("")) {
                    throw new DukeException("Please indicate word to be found.");
                }
                output = taskList.listMatchingTasks(currentParameter);
                return output;
            case BYE:
                Platform.exit();
                break;
            case HELP:
                String helpMessage =
                    "Welcome to Duke!\n"
                      + "Here are the available commands:\n"
                      + "help: Show the current help message.\n"
                      + "todo DESCRIPTION: Adds a new TODO task.\n"
                      + "event DESCRIPTION /at TIME: Adds a new EVENT task.\n"
                      + "deadline DESCRIPTION /at TIME: Adds a new DEADLINE task.\n"
                      + "find WORD: Find tasks with given word in the description.\n"
                      + "bye: Exits the program.\n"
                      + "list: Shows all tasks.\n"
                      + "delete INDEX: Removes the task at the INDEX number on the task list.\n"
                      + "done INDEX: Marks the task at the INDEX number on the task list as done.\n";
                return helpMessage;
            default:
                throw new DukeException("How did you get here?");
            }
        } catch (DukeException | IOException e) {
            return ("OOPS!!! " + e.getMessage());
        }
        return "How did you get here?";
    }
}
