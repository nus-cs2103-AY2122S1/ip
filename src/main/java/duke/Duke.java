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

    private void taskUnknown() throws DukeException {
        throw new DukeException("Unknown input");
    }

    private String listTask() {
        return taskList.listTasks();
    }

    private String todoTask(String currentParameter) throws IOException {
        if (currentParameter == "") {
            throw new DukeException("TODO cannot have empty parameter.");
        }
        String output = taskList.addTask(Command.TODO, currentParameter);
        Storage.updateLocalFile(taskList);
        return output;
    }

    private String eventTask(String currentParameter) throws IOException {
        if (currentParameter.equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (!currentParameter.contains(" /at ")) {
            throw new DukeException("Missing /at command");
        }
        System.out.println(currentParameter);
        String[] descriptionAndTimeParts = Parser.dateParameterParser(Command.EVENT, currentParameter);
        System.out.println(descriptionAndTimeParts);
        String output = taskList.addTask(Command.EVENT, descriptionAndTimeParts[0], descriptionAndTimeParts[1]);
        Storage.updateLocalFile(taskList);
        return output;
    }

    private String deadlineTask(String currentParameter) throws IOException {
        if (currentParameter.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!currentParameter.contains(" /by ")) {
            throw new DukeException("Missing /by command");
        }
        String[] descriptionAndTimeParts = Parser.dateParameterParser(Command.DEADLINE, currentParameter);
        String output = taskList.addTask(Command.DEADLINE, descriptionAndTimeParts[0], descriptionAndTimeParts[1]);
        Storage.updateLocalFile(taskList);
        return output;
    }

    private String deleteTask(String currentParameter) throws IOException {
        if (currentParameter.equals("")) {
            throw new DukeException("Please indicate item to be deleted.");
        }
        int index = Integer.parseInt(currentParameter) - 1;
        if (index > taskList.getSize() - 1) {
            throw new DukeException("Item does not exist.");
        }

        String output = taskList.removeTask(index);
        Storage.updateLocalFile(taskList);
        return output;
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
                taskUnknown();
                break;
            case LIST:
                return listTask();
            case TODO:
                return todoTask(currentParameter);
            case EVENT:
                return eventTask(currentParameter);
            case DEADLINE:
                return deadlineTask(currentParameter);
            case DELETE:
                return deleteTask(currentParameter);
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
