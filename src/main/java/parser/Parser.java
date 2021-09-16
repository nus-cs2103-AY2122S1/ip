package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import alice.exceptions.AliceException;
import command.Command;
import command.exceptions.InvalidTimeFormatException;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.TaskList;
import model.vocab.Vocab;

/**
 * Class for parsing different data type to other type or different format.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class Parser {
    /**
     * Static method for converting a string of command to Command Type.
     *
     * @param s the string.
     * @return CommandType of the command.
     * @throws AliceException invalid command is parsed into the method.
     */
    public static Command.CommandType stringToCommand(String s) throws AliceException {
        switch (s) {
        case "list":
            return Command.CommandType.LIST;
        case "date":
            return Command.CommandType.DATE;
        case "find":
            return Command.CommandType.FIND;
        case "todo":
            return Command.CommandType.TODO;
        case "deadline":
            return Command.CommandType.DEADLINE;
        case "event":
            return Command.CommandType.EVENT;
        case "done":
            return Command.CommandType.DONE;
        case "delete":
            return Command.CommandType.DELETE;
        case "learn":
            return Command.CommandType.LEARN;
        case "unlearn":
            return Command.CommandType.UNLEARN;
        case "commands":
        case "?":
        case "help":
            return Command.CommandType.COMMANDS;
        case "bye":
            return Command.CommandType.BYE;
        default:
            throw new AliceException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Static method for converting a string to different TaskType.
     *
     * @param s the string.
     * @return TaskType of the tasks.
     * @throws AliceException invalid models.task string representation is parsed into the method.
     */
    public static TaskList.TaskType stringToTaskType(String s) throws AliceException {
        switch (s) {
        case "TD":
            return TaskList.TaskType.TODO;
        case "DL":
            return TaskList.TaskType.DEADLINE;
        case "EV":
            return TaskList.TaskType.EVENT;
        default:
            throw new AliceException("Invalid String detected: " + s);
        }
    }

    /**
     * Static method for converting a className of a task to the TaskType enum.
     *
     * @param cn task subclass class name to be converted to TaskType enum.
     * @return taskType of the classname passed.
     * @throws AliceException invalid classname is parsed into the method.
     */
    public static TaskList.TaskType classNameToTaskType(String cn) throws AliceException {
        switch (cn) {
        case "model.task.Todo":
        case "Todo":
            return TaskList.TaskType.TODO;
        case "model.task.Deadline":
        case "Deadline":
            return TaskList.TaskType.DEADLINE;
        case "model.task.Event":
        case "Event":
            return TaskList.TaskType.EVENT;
        default:
            throw new AliceException("Invalid ClassName detected: " + cn);
        }
    }

    /**
     * Static method for converting the taskType into its string representation.
     *
     * @param t the taskType to be converted to string representation.
     * @return the String representation fo that taskType.
     * @throws AliceException invalid TaskType has been parsed into the method.
     */
    public static String taskTypeToString(TaskList.TaskType t) throws AliceException {
        switch (t) {
        case TODO:
            return "TD";
        case DEADLINE:
            return "DL";
        case EVENT:
            return "EV";
        default:
            throw new AliceException("Invalid TaskType detected: " + t);
        }
    }

    /**
     * Static method for converting the task to a save format string representation.
     *
     * @param task the task to be converted to String save format.
     * @return string representation to be seen in the save file of the task.
     */
    public static String taskToSaveFormat(Task task) {
        TaskList.TaskType type = classNameToTaskType(task.getClass().getName());
        String s = taskTypeToString(type) + " | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription();
        switch (type) {
        case EVENT:
            Event ev = (Event) task;
            s += " | " + ev.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            break;
        case DEADLINE:
            Deadline dl = (Deadline) task;
            s += " | " + dl.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            break;
        default:
            break;
        }
        return s;
    }

    /**
     * Static method for converting a vocab object to its String save format.
     *
     * @param vocab the vocab to be converted to String save format.
     * @return string representation of that vocab.
     */
    public static String vocabToSaveFormat(Vocab vocab) {
        return vocab.getPhrase() + " | " + vocab.getFeedback();
    }

    /**
     * Static method for converting a string of yes or others to boolean.
     *
     * @param yesNo string of yes or no.
     * @return true for string resembling yes, false otherwise.
     */
    public static boolean yesNoToBoolean(String yesNo) {
        switch (yesNo) {
        case "Yes":
        case "yes":
        case "y":
        case "Y":
            return true;
        case "No":
        case "no":
        case "n":
        case "N":
            return false;
        default:
            throw new AliceException("Invalid format of yes or no detected: " + yesNo);
        }
    }

    /**
     * Static method for parsing string representation to LocalDate object.
     *
     * @param s the string representation of time.
     * @return LocalDate object if is is of format yyyy-MM-dd.
     */
    public static LocalDate parseTimeString(String s) {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("the inputted time format is invalid, please enter as yyyy-MM-dd");
        }
    }

    /**
     * For parsing a string of full command to a Command object.
     *
     * @param fullCommand command in string format.
     * @return Command object of the string.
     */
    public static Command parse(String fullCommand) {
        return new Command(fullCommand);
    }
}
