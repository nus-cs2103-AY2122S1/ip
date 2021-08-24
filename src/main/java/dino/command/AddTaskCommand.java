package dino.command;

import dino.util.Storage;
import dino.exception.*;
import dino.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    String taskString;
    CMDTYPE taskType;

    public AddTaskCommand(String taskString, CMDTYPE taskType) {
        this.taskString = taskString;
        this.taskType = taskType;
    }

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

    public static LocalDate getTaskTime(String s) throws InvalidFormatException {
        String time = s.substring(s.indexOf("/") + 4);
        try {
            return LocalDate.parse(time);
        } catch (DateTimeParseException e){
            throw new InvalidFormatException("make sure the date", "yy-mm-dd");
        }
    }

}
