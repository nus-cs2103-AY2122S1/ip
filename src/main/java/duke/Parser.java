package duke;

import duke.task.TaskName;

/**
 *  A class that encapsulates the handling of the text input into Duke.
 */
public class Parser {
    /**
     * Handles all the text input to call the correct corresponding method.
     *
     * @param input The text input from the user to Duke
     * @return The corresponding String reply to the user's input
     * @throws DukeException Exceptions specific to Duke's input
     */
    public static String parseInput(TaskList list, String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String reply;
        String command = inputArray[0];

        switch (command) {
        case "list":
            reply = list.displayTask();
            break;

        case "done":
            // Fallthrough
        case "delete":
            // Fallthrough
        case "find":
            reply = parseOneInputCommand(list, inputArray);
            break;

        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            reply = parseTwoInputCommand(list, inputArray);
            break;

        case "bye":
            reply = "bye";
            break;

        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
        return reply;
    }

    private static String parseOneInputCommand(TaskList list, String[] inputArray) throws DukeException {
        String reply;
        String command = inputArray[0];

        if (inputArray.length < 2) {
            throw new DukeException("The second input is missing.");
        }

        String input = inputArray[1];

        switch (command) {
        case "done":
            reply = list.markTask(input);
            break;

        case "delete":
            reply = list.deleteTask(input);
            break;

        case "find":
            reply = list.findTask(inputArray[1]);
            break;

        default:
            throw new DukeException("Wrong command provided.");
        }
        return reply;
    }

    private static String parseTwoInputCommand(TaskList list, String[] inputArray) throws DukeException {
        if (inputArray.length < 2 || inputArray[1].isBlank()) {
            throw new DukeException("The description of " + inputArray[0] + " cannot be empty.");
        }

        TaskName taskType = TaskName.getTaskType(inputArray[0]);
        return list.addTask(taskType, inputArray[1]);
    }

    /**
     * Parse the string provided from the saved data text file and add the respective Task into TaskList provided.
     *
     * @param list The TaskList by which the Task is to be added to
     * @param dataLine The String corresponding to a single line of text in the saved data text file
     * @throws DukeException Exceptions specific to Duke's input
     */
    public static void parseSavedDataLine(TaskList list, String dataLine) throws DukeException {
        String[] inputArray = dataLine.split(" \\| ");

        TaskName type = TaskName.getTaskType(inputArray[0]);
        String secondInput = type != TaskName.TODO ? inputArray[3] : "";
        String description = inputArray[2] + type.getSplit() + secondInput;
        boolean isDone = inputArray[1].equals("1");

        list.addTask(type, description, isDone);
    }
}
