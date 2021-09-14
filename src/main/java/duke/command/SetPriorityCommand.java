package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Contains the objects and methods necessary for a Set Priority Command.
 *
 * @author Toh Wang Bin
 */
public class SetPriorityCommand implements Command {

    private static final int MIN_LENGTH_ARRAY = 3;
    private static final int ARRAY_INDEX_OF_TASK_INDEX = 1;
    private static final int ARRAY_INDEX_OF_PRIORITY = 2;

    private final TaskList taskList;
    private final String[] inputArray;
    private final Storage storage;

    /**
     * Constructor for a SetPriorityCommand instance.
     *
     * @param taskList The TaskList instance associated with the command.
     * @param inputArray The String[] containing the user inputs.
     * @param storage The Storage instance associated with the command.
     */
    public SetPriorityCommand(TaskList taskList, String[] inputArray, Storage storage) {
        this.taskList = taskList;
        this.inputArray = inputArray;
        this.storage = storage;
    }

    /**
     * Executes the command unique to this Command class.
     *
     * @return A string representing the response to running this command.
     */
    public String execute() {
        if (inputArray.length < MIN_LENGTH_ARRAY) {
            return Ui.getBadInputError();
        }
        StringBuilder str = new StringBuilder();
        int index;
        try {
            index = Integer.parseInt(inputArray[ARRAY_INDEX_OF_TASK_INDEX]);
        } catch (NumberFormatException exception) {
            //case if string entered was not a number
            return Ui.getNumberError();
        }
        int arrayIndex = index - 1;
        //case if entered index does not correspond to a task
        boolean isInvalidIndex = index > taskList.getTotalTasks() || index < 1;
        if (isInvalidIndex) {
            return Ui.getTaskError();
        }
        //retrieve the task
        Task currentTask = taskList.getTask(arrayIndex);
        //attempt to set the priority level
        try {
            currentTask.setPriorityLevel(inputArray[ARRAY_INDEX_OF_PRIORITY]);
        } catch (IllegalArgumentException e) {
            return Ui.getInvalidPriorityLevel();
        }
        str.append(Ui.getTaskPrioritised(currentTask));
        str.append(Ui.getTaskNumberReminder(taskList.getTotalTasks()));
        try {
            storage.saveData();
        } catch (IOException e) {
            return Ui.getFileError();
        }
        return str.toString();
    }

}
