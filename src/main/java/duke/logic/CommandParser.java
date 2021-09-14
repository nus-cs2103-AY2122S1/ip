package duke.logic;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TasksEnum;
import duke.ui.Ui;

/**
 * The logic for parsing commands typed by the user.
 */
public class CommandParser {
    private static final String INVALID_COMMAND = "Invalid input. Type \"help\" for more information.";
    private static final String EMPTY_INPUT_MESSAGE = "Input is empty. Type \"help\" for more information.";
    private static final String TOO_LITTLE_ARGUMENTS_MESSAGE = "Too little arguments. Type \"help\" "
        + "followed by the command for more information.";
    private static final String INVALID_NUMBER_MESSAGE = "Please input a valid task number after the command.";
    private final String output;
    private boolean isExiting;

    /**
     * Creates a new command parser for the input.
     *
     * @param input    The string input from the user.
     * @param storage  The storage logic that allows the command parser to write the task list data to it.
     * @param taskList The list of tasks.
     * @param ui       The user interface generating output messages.
     */
    public CommandParser(String input, TaskList taskList, Storage storage, Ui ui) {
        if (input == null || input.equals("")) {
            throw new InvalidCommandException(EMPTY_INPUT_MESSAGE);
        }
        String[] inputArr = input.split(" ", 2);
        CommandsEnum commandEnum;
        try {
            commandEnum = CommandsEnum.valueOf(inputArr[0].toUpperCase());
            this.isExiting = false;
            switch (commandEnum) {
            // Single word commands
            case BYE:
                output = ui.getGoodByeMessage();
                isExiting = true;
                return;
            case LIST:
                output = ui.getAllTasksMessage(taskList.getAllTasks(), taskList.size());
                return;
            case UPCOMING:
                output = ui.getUpcomingTasksMessage(taskList.getUpcomingTasks(), taskList.size());
                return;
            case HELP:
                output = ui.displayHelpMessage(
                    inputArr.length == 1 ? null : CommandsEnum.valueOf(inputArr[1].toUpperCase()));
                return;
            default:
                if (inputArr.length < 2) {
                    throw new InvalidCommandException(TOO_LITTLE_ARGUMENTS_MESSAGE);
                }
                output = parseMultiWord(inputArr[1], commandEnum, taskList, ui);
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(INVALID_COMMAND);
        }
        try {
            storage.updateDukeTextFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Parses the input if it contains more than 1 word.
     *
     * @param inputWords   The input string without the command.
     * @param commandsEnum The enum representing the command.
     * @param taskList     The list of tasks to be interacted with.
     * @param ui           The user interface generating the output messages.
     * @return The output string from ui.
     */
    private static String parseMultiWord(String inputWords, CommandsEnum commandsEnum, TaskList taskList, Ui ui) {
        switch (commandsEnum) {
        case FIND:
            return ui.getTasksWithPatternMessage(inputWords,
                taskList.getTasksContaining(inputWords), taskList.size());
        case TODO: // fallthrough intended // From here on, there will be updates to the storage.
        case EVENT: // fallthrough intended
        case DEADLINE:
            return ui.addTaskMessage(addTask(commandsEnum.name(), inputWords, taskList), taskList.size());
        default:
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(inputWords);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_NUMBER_MESSAGE);
            }
            switch (commandsEnum) {
            case DONE:
                if (!taskList.markAsDone(taskNumber)) { // task already marked as done
                    throw new DukeException("You have already marked this task (%s) as done",
                        taskList.getTask(taskNumber));
                }
                return ui.markAsDoneMessage(taskList.getTask(taskNumber));
            case DELETE:
                return ui.removeTaskMessage(taskList.removeTask(taskNumber), taskList.size());
            default:
                throw new InvalidCommandException(INVALID_COMMAND);
            }
        }
    }

    /**
     * Attempts to add the task to the tasklist based on the user command.
     *
     * @param action     the action of either todo, event or deadline
     * @param otherInput the rest of the string without the action
     * @param taskList   the list of task to be added to
     * @return the task that is added.
     */
    private static Task addTask(String action, String otherInput, TaskList taskList) {
        assert !action.isBlank() : "action cannot be blank";
        assert !otherInput.isBlank() : "other input cannot be blank";
        assert taskList != null : "task list cannot be null";
        TasksEnum tasksEnum = TasksEnum.valueOf(action);
        Task result = tasksEnum.getTask(otherInput);
        taskList.addTask(result);
        return result;
    }

    /**
     * Gets the output after parsing the command.
     *
     * @return the output string
     */
    public String getOutput() {
        return output;
    }

    /**
     * Checks if the command given by the user is to exit.
     *
     * @return true if and only if the command is to exit
     */
    public boolean willExit() {
        return isExiting;
    }

}
