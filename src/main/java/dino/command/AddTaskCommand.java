package dino.command;

import dino.util.Storage;
import dino.exception.*;
import dino.task.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the command for which the user wants to add a task to the task list
 * The task can only be one of the task of type ToDo, Deadline, or Event
 */
public class AddTaskCommand extends Command {

    String taskString;
    CMDTYPE taskType;

    /**
     * Constructs a AskTaskCommand object
     *
     * @param taskString the user input command
     * @param taskType the type of the task, which can only be one of the type ToDo, Deadline, or Event
     */
    public AddTaskCommand(String taskString, CMDTYPE taskType) {
        this.taskString = taskString;
        this.taskType = taskType;
    }

    /**
     * Executes the command to add the specific type of task to the task list
     *
     * @param storage the local storage file
     * @param taskList the current task list to which the new task will be added
     * @throws TimeNotSpecifiedException if the task is of type Deadline or Event but the time is not specified
     * @throws EmptyTaskDescriptionException if the description of the task is empty
     * @throws InvalidFormatException if the task is of type Deadline or Event but the time is not entered in the
     * format of "yyyy-mm-dd"
     */
    @Override
    public void execute(Storage storage, TaskList taskList) throws TimeNotSpecifiedException, EmptyTaskDescriptionException, InvalidFormatException {
        String description = getTaskDescription(this.taskString, this.taskType);
        Task task;
        switch (this.taskType) {
            case TODO: {
                task = new ToDo(description);
                taskList.addTask(task);
                break;
            }
            case DEADLINE: {
                LocalDate time = getTaskTime(this.taskString);
                task = new Deadline(description, time);
                taskList.addTask(task);
                break;
            }
            case EVENT : {
                LocalDate time = getTaskTime(this.taskString);
                task = new Event(description, time);
                taskList.addTask(task);
                break;
            }
        }
    }

    /**
     * Extracts the description of the task from a user input command to add task
     *
     * @param s the user input command
     * @param type the type of the task
     * @return the description of the task that is extracted from the user input command
     * @throws EmptyTaskDescriptionException if the description of the task is empty
     * @throws TimeNotSpecifiedException if the task is of type Deadline or Event but the time is not specified
     */
    public static String getTaskDescription(String s, CMDTYPE type) throws EmptyTaskDescriptionException, TimeNotSpecifiedException {
        if (s.length() < type.toString().length() + 2) {
            throw new EmptyTaskDescriptionException();
        }
        switch (type) {
            case TODO: {
                return s.substring(5).trim();
            }
            case DEADLINE:
            case EVENT: {
                if (s.contains("/by ") || s.contains("/at ")) {
                    return s.substring(type.toString().length() + 1, s.indexOf("/")).trim();
                } else {
                    throw new TimeNotSpecifiedException(type.toString());
                }
            }
        }
        return s;
    }

    /**
     *
     * @param s the user input command
     * @return the time of the task as a LocalDate object
     * @throws InvalidFormatException if the task is of type Deadline or Event but the time is not entered in the
     *      * format of "yyyy-mm-dd"
     */
    public static LocalDate getTaskTime(String s) throws InvalidFormatException {
        String time = s.substring(s.indexOf("/") + 4);
        try {
            return LocalDate.parse(time);
        } catch (DateTimeParseException e){
            throw new InvalidFormatException("make sure the date", "yyyy-mm-dd");
        }
    }

}
