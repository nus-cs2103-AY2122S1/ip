package dino.command;

import java.time.format.DateTimeParseException;

import dino.util.Parser;
import dino.util.Storage;
import dino.exception.*;
import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;
import dino.task.TaskList;
import dino.task.ToDo;
import java.time.LocalDate;


/**
 * Represents the command for which the user wants to add a task to the task list
 * The task can only be one of the task of type ToDo, Deadline, or Event
 */
public class AddTaskCommand extends Command {

    private final String taskString;
    private final Parser.CMDTYPE taskType;

    /**
     * Constructs a AskTaskCommand object
     *
     * @param taskString the user input command
     * @param taskType the type of the task, which can only be one of the type ToDo, Deadline, or Event
     */
    public AddTaskCommand(String taskString, Parser.CMDTYPE taskType) {
        this.taskString = taskString;
        this.taskType = taskType;
    }

    /**
     * Executes the command to add the specific type of task to the task list
     *
     * @param storage the local storage file
     * @param taskList the current task list to which the new task will be added
     * @return the output message after execution
     * @throws TimeNotSpecifiedException if the task is of type Deadline or Event
     * but the time is not specified
     * @throws EmptyTaskDescriptionException if the description of the task is empty
     * @throws InvalidFormatException if the task is of type Deadline or Event
     * but the time is not entered in the format of "yyyy-mm-dd"
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws
            TimeNotSpecifiedException, EmptyTaskDescriptionException, InvalidFormatException {
        String description = getTaskDescription(this.taskString, this.taskType);
        Task task = null;
        switch (taskType) {
        case TODO: {
            task = new ToDo(description);
            break;
        }
        case DEADLINE: {
            LocalDate time = getTaskTime(this.taskString);
            task = new Deadline(description, time);
            break;
        }
        case EVENT : {
            LocalDate time = getTaskTime(this.taskString);
            task = new Event(description, time);
            break;
        }
        default: {
            break;
        }
        }
        return taskList.addTask(task);
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
    public static String getTaskDescription(String s, Parser.CMDTYPE type) throws EmptyTaskDescriptionException, TimeNotSpecifiedException {
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
        default: {
            break;
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
