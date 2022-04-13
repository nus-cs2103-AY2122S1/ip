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
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        String description = getTaskDescription(this.taskString, this.taskType);
        Task task = null;
        try {
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
            case EVENT: {
                LocalDate time = getTaskTime(this.taskString);
                task = new Event(description, time);
                break;
            }
            default: {
                break;
            }
            }
            assert(task != null); //the task to be added is not null
            return taskList.addTask(task);
        } catch (InvalidFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Extracts the description of the task from a user input command to add task
     *
     * @param s the user input command
     * @param type the type of the task
     * @return the description of the task that is extracted from the user input command
     */
    public static String getTaskDescription(String s, Parser.CMDTYPE type) {
        try {
            if (s.trim().length() < type.toString().length() + 2) {
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
        } catch (TimeNotSpecifiedException | EmptyTaskDescriptionException e) {
            return e.getMessage();
        }

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
