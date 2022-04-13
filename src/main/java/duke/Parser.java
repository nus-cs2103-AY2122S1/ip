package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ScheduleCommand;
import duke.exception.DukeException;


/**
 * A class to process and make sense of the command
 * inputted by the user.
 */

public class Parser {

    private Ui ui;
    private TaskList taskList;

    /**
     * A public constructor for Parser to initialize the
     * Ui and TaskList to the given ones.
     *
     * @param ui       The Ui to deal with interactions of the user.
     * @param taskList the list storing all the tasks.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Returns a Command representing the command
     * inputted by the user.
     *
     * @param command The user command.
     * @return A Command representing the user command.
     * @throws DukeException If the command is inputted wrongly.
     */
    public Command parse(String command) throws DukeException {
        String trimmedCommand = command.trim();
        String[] splitInput = trimmedCommand.split(" +", 2);
        switch (splitInput[0].trim()) {

        //fall through
        case "bye":
            return new ExitCommand(trimmedCommand);
        case "list":
            if (splitInput.length > 1) {
                throw new DukeException(ui.commandError());
            }
            return new ListCommand(trimmedCommand);
        case "find":
            if (splitInput.length == 1) {
                throw new DukeException(ui.commandError());
            }
            return new FindCommand(trimmedCommand);
        case "schedule":
            return checkScheduleCommand(trimmedCommand);
        case "done":
        case "delete":
            return checkDeleteAndDone(trimmedCommand);
        case "todo":
        case "event":
        case "deadline":
            return checkInputTask(trimmedCommand, splitInput[0].trim());

        default:
            throw new DukeException(ui.commandError());

        }
    }

    /**
     * Checks if the input contains description and date.
     * Returns the boolean value containing the result
     * of the check.
     *
     * @param descriptionAndDate The input description.
     * @param taskType The type of the task.
     * @return The boolean value of the result of the check.
     *
     */
    public boolean hasDescription(String descriptionAndDate, String taskType) {
        boolean isMissingDescription;

        switch (taskType) {
        case "deadline":
            isMissingDescription = descriptionAndDate.startsWith("/by");
            break;
        case "event":
            isMissingDescription = descriptionAndDate.startsWith("/at");
            break;
        default:
            isMissingDescription = false;
            assert false;
            break;
        }
        return !isMissingDescription;
    }

    /**
     * Checks if the input contains the correct date
     * command, either /at or /by.Returns the boolean
     * value containing the result of the check.
     *
     * @param descriptionAndDate The input description.
     * @param taskType The type of the task.
     * @return The boolean value of the result of the check.
     *
     */
    public boolean hasDateCommand(String descriptionAndDate,
            String taskType) {

        boolean hasDateCommand;

        switch (taskType) {
        case "deadline":
            hasDateCommand = descriptionAndDate.contains("/by");
            break;
        case "event":
            hasDateCommand = descriptionAndDate.contains("/at");
            break;
        default:
            hasDateCommand = false;
            assert false : "invalid task command";
            break;
        }
        return hasDateCommand;

    }

    /**
     * Checks if the input of the user for commands indicating
     * the addition of tasks is inputted correctly. Returns the
     * AddCommand if input is correct.
     *
     * @param input    The user command.
     * @param taskType The type of the task.
     * @return A command representing the user command.
     * @throws DukeException If the input is in incorrect format.
     */
    public Command checkInputTask(String input, String taskType)
            throws DukeException {
        String[] splitInputWords = input.split(" +", 2);

        if (splitInputWords.length != 2) {
            throw new DukeException(ui.noDescription(taskType));
        }

        String descriptionAndDate = splitInputWords[1].trim();
        if (taskType.equals("deadline") || taskType.equals("event")) {
            boolean hasDescription = hasDescription(descriptionAndDate,
                    taskType);
            boolean hasDateCommand = hasDateCommand(descriptionAndDate,
                    taskType);
            if (!hasDateCommand) {
                throw new DukeException(ui.incorrectAtOrBy(taskType));
            }
            if (!hasDescription) {
                throw new DukeException(ui.noDescription(taskType));
            }
        }
        return new AddCommand(input, taskType);
    }


    /**
     * Checks if the command for delete and done is inputted correctly.
     * The task number must be indicated and valid.
     * Returns the respective command if it is in correct form.
     *
     * @param input The user command.
     * @return The command representing user command.
     * @throws DukeException If the command is in incorrect form.
     */
    public Command checkDeleteAndDone(String input) throws DukeException {
        String[] splitInput = input.trim().split(" +");
        boolean isDelete = splitInput[0].equals("delete");
        String errorCommand = isDelete ? " deleted" : " marked done";

        if (splitInput.length != 2) {
            throw new DukeException("OOPS!!! The task to be"
                    + errorCommand + " is not indicated properly!!");
        }

        int taskNum = Integer.valueOf(splitInput[1]);
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new DukeException("OOPS!!! There is no corresponding task to be "
                    + errorCommand);
        } else {
            return isDelete ? new DeleteCommand(input, taskNum)
                            : new DoneCommand(input, taskNum);
        }
    }

    /**
     * Checks if the input schedule command is in correct
     * format and returns the respective command if correct.
     * Otherwise, an exception is thrown.
     *
     * @param input The user input.
     * @return The schedule command representing the input.
     * @throws DukeException if the format of the command is incorrect.
     */
    public Command checkScheduleCommand(String input) throws DukeException {
        String[] inputs = input.split(" +", 2);
        if (inputs.length != 2) {
            throw new DukeException("OOPS!! Please indicate the date of the "
                    + "schedule you want to view :) Use the format "
                            + "yyyy-mm-dd!!");
        }
        try {
            LocalDate scheduleDate = LocalDate.parse(inputs[1].trim());
            return new ScheduleCommand(input, scheduleDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("I can only understand the date in yyyy-mm-dd"
                    + "format :)");
        }
    }

}
