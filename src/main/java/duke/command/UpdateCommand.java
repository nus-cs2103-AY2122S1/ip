package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.DukeException;
import duke.exception.IncorrectIndexException;
import duke.exception.IncorrectUpdateParameterException;
import duke.task.Task;
import duke.task.TaskType;
import duke.tasklist.TaskList;

/**
 * A command that can update and replace a task at the i-th position.
 */
public class UpdateCommand extends Command {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "update";

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "Update a task.";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD
            + "/i id_of_the_task "
            + "/n new_name_of_task "
            + "/d new_date_of_task";

    /** Class level constant which represents the feedback string to be returned. */
    public static final String FEEDBACK_STRING = "I've updated this task in your list from\n";

    private final String commandStringParams;

    private int id;

    private String nameToUpdateTo;

    private String dateToUpdateTo;

    /**
     * Abstract constructor that will have to be implemented by all classes that extend Command.
     *
     * @param taskList that is passed by Duke.
     * @param commandStringParams the rest of the parameters input by User.
     */
    public UpdateCommand(TaskList taskList, String commandStringParams) {
        super(taskList);
        this.commandStringParams = commandStringParams;
    }

    /**
     * Returns the string representation of the command description and format.
     * @return String that represents the command description and format.
     */
    public static String formatAndDescription() {
        return COMMAND_WORD + ": " + DESCRIPTION + "\n" + FORMAT;
    }

    /**
     * Sets the name of the task to update to.
     * @param nameToUpdateTo the new name of the task.
     */
    public void setUpdatedName(String nameToUpdateTo) {
        this.nameToUpdateTo = nameToUpdateTo;
    }

    /**
     * Sets the date of the task to update to.
     * @param dateToUpdateTo the new date of the task.
     */
    public void setUpdatedDate(String dateToUpdateTo) {
        this.dateToUpdateTo = dateToUpdateTo;
    }

    /**
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     * @throws DukeException if any errors are found with the format of the update command.
     */
    @Override
    public CommandResult execute() throws DukeException {
        parseThroughCommandStringParams();
        Task task = this.getTaskList().get(this.id);
        if (!isATodoTask(task.getTaskType()) && checkIfNameAndDateToUpdateToAreNull()) {
            throw new IncorrectUpdateParameterException();
        }
        Task updatedTask = updateTask(task);
        Task oldTask = this.getTaskList().updateTask(updatedTask, this.id);
        String feedback = getFeedbackString(oldTask, updatedTask);
        return new CommandResult(feedback, false);
    }

    private boolean isATodoTask(TaskType taskType) {
        return taskType.equals(TaskType.T);
    }

    private boolean checkIfNameToUpdateToIsNull() {
        return this.nameToUpdateTo == null;
    }

    private boolean checkIfDateToUpdateToIsNull() {
        return this.dateToUpdateTo == null;
    }

    private boolean checkIfNameAndDateToUpdateToAreNull() {
        return checkIfNameToUpdateToIsNull() && checkIfDateToUpdateToIsNull();
    }

    private Task updateNameOfTask(Task task) throws DukeException {
        if (checkIfNameToUpdateToIsNull() && isATodoTask(task.getTaskType())) {
            throw new IncorrectUpdateParameterException();
        }
        if (checkIfNameToUpdateToIsNull()) {
            return task;
        }
        return task.updateName(this.nameToUpdateTo);
    }

    private Task updateDateOfTask(Task task) throws DukeException {
        if (checkIfDateToUpdateToIsNull()) {
            return task;
        }
        return task.updateDateTime(this.dateToUpdateTo);
    }

    private Task updateTask(Task task) throws DukeException {
        if (isATodoTask(task.getTaskType())) {
            return updateNameOfTask(task);
        }
        return updateDateOfTask(updateNameOfTask(task));
    }

    private void parseThroughCommandStringParams() throws IncorrectUpdateParameterException, IncorrectIndexException {
        String[] split = this.commandStringParams.split("(?=/)");
        lookForIdAndSetId(split);
        lookForNameAndSetName(split);
        lookForDateAndSetDateTime(split);
    }

    private void lookForIdAndSetId(String[] splitString) throws IncorrectUpdateParameterException,
            IncorrectIndexException {
        for (String s: splitString) {
            if (s.startsWith("/i")) {
                String res = s.substring(2);
                try {
                    this.id = Integer.parseInt(res.trim());
                } catch (NumberFormatException e) {
                    throw new IncorrectIndexException();
                }
                return;
            }
        }
        throw new IncorrectUpdateParameterException();
    }

    private void lookForNameAndSetName(String[] splitString) {
        for (String s: splitString) {
            if (s.startsWith("/n")) {
                setUpdatedName(s.substring(2).trim());
                return;
            }
        }
    }

    private void lookForDateAndSetDateTime(String[] splitString) {
        for (String s: splitString) {
            if (s.startsWith("/d")) {
                setUpdatedDate(s.substring(2).trim());
            }
        }
    }

    private String getFeedbackString(Task oldTask, Task updatedTask) {
        return FEEDBACK_STRING + "\n" + oldTask.toString() + "\n to" + "\n" + updatedTask.toString();
    }
}
