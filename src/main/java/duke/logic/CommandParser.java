package duke.logic;

import java.io.IOException;

import duke.DukeException;
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
    private static final String FULL_TASKLIST_MESSAGE = "Unable to add task. List is full. Consider deleting"
        + " some tasks";
    private static final String INVALID_NUMBER_MESSAGE = "Please input a valid task number after the command.";
    private final String output;
    private boolean willExit;

    /**
     * Creates a new command parser for the input.
     *
     * @param input    The string input from the user.
     * @param storage  The storage logic that allows the command parser to write the task list data to it.
     * @param taskList The list of tasks.
     */
    public CommandParser(String input, TaskList taskList, Storage storage, Ui ui) {
        if (input == null || input.equals("")) {
            throw new DukeException(EMPTY_INPUT_MESSAGE);
        }
        String[] inputArr = input.split(" ", 2);
        CommandsEnum commandEnum;
        try {
            commandEnum = CommandsEnum.valueOf(inputArr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException(INVALID_COMMAND);
        }
        this.willExit = false;
        switch (commandEnum) {
        case BYE:
            output = ui.goodByeMessage();
            willExit = true;
            return;
        case LIST:
            output = ui.allTasksMessage(taskList.getAllTasks(), taskList.size());
            return;
        case UPCOMING:
            output = ui.upcomingTasksMessage(taskList.getUpcomingTasks(), taskList.size());
            return;
        case HELP:
            try {
                output = ui.displayHelpMessage(
                    inputArr.length == 1 ? null : CommandsEnum.valueOf(inputArr[1].toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new DukeException(INVALID_COMMAND);
            }
            return;
        default:
            if (inputArr.length < 2) {
                throw new DukeException(TOO_LITTLE_ARGUMENTS_MESSAGE);
            }
            switch (commandEnum) {
            case FIND:
                output = ui.tasksContainingMessage(inputArr[1],
                    taskList.getTasksContaining(inputArr[1]), taskList.size());
                return;
            case TODO: // fallthrough intended // From here on, there will be updates to the storage.
            case EVENT: // fallthrough intended
            case DEADLINE:
                output = ui.addTaskMessage(addTask(commandEnum.name(), inputArr[1], taskList), taskList.size());
                break;
            default:
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(inputArr[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException(INVALID_NUMBER_MESSAGE);
                }
                switch (commandEnum) {
                case DONE:
                    if (!taskList.markAsDone(taskNumber)) { // task already marked as done
                        throw new DukeException("You have already marked this task (%s) as done",
                            taskList.getTask(taskNumber));
                    }
                    output = ui.markAsDoneMessage(taskList.getTask(taskNumber));
                    break;
                case DELETE:
                    output = ui.removeTaskMessage(taskList.removeTask(taskNumber), taskList.size());
                    break;
                default:
                    throw new DukeException(INVALID_COMMAND);
                }
            }
        }
        try {
            storage.updateDukeTextFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
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
        if (!taskList.addTask(result)) { // if adding task is unsuccessful
            throw new DukeException(FULL_TASKLIST_MESSAGE);
        }
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
        return willExit;
    }

}
