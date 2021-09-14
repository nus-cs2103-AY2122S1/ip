package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.SetPriorityCommand;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskCreator;
import duke.task.Todos;
import javafx.application.Platform;

/**
 * A class containing methods that parse relevant data from strings.
 *
 * @author Toh Wang Bin
 */
public class Parser {

    /**
     * Parses a string into a LocalDate object.
     *
     * @param str The string to be parsed.
     * @return A LocalDate object created byb parsing the input string.
     * @throws IllegalArgumentException When an invalid string is parsed.
     */
    public static LocalDate parseDate(String str) throws IllegalArgumentException {
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Date could not be parsed!");
        }
        assert date != null : "Date should have been initialised and assigned.";
        return date;
    }

    /**
     * Parses a string into a Task object.
     *
     * @param str The string to be parsed.
     * @return A task object created by parsing the input string.
     */
    public static Task parseData(String str) {
        String[] splitArray = str.split("\\|");
        switch (splitArray[0]) {
        //case if task stored is a Event
        case "E":
            Task event = new Events(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) {
                event.setCompleted();
            }
            event.setPriorityLevel(splitArray[4]);
            return event;
        //case if task stored is a Deadline
        case "D":
            Task deadline = new Deadlines(splitArray[2], Parser.parseDate(splitArray[3]));
            if (splitArray[1].equals("1")) {
                deadline.setCompleted();
            }
            deadline.setPriorityLevel(splitArray[4]);
            return deadline;
        //last case will always be "T", or a Todo
        //no need for breaks in intermediate cases, as function has terminated at the return statements
        default:
            Task todo = new Todos(splitArray[2]);
            if (splitArray[1].equals("1")) {
                todo.setCompleted();
            }
            todo.setPriorityLevel(splitArray[3]);
            return todo;
        }
    }

    /**
     * Parses the given user input and returns a response.
     *
     * @param taskList The TaskList storing the Tasks.
     * @param storage The Storage instance containing the TaskList and files.
     * @param firstString The first String entered by the user
     * @param inputArray The array containing all strings input by the user.
     *
     * @return A string indicating the response to the user.
     */
    public static String parseInput(TaskList taskList, Storage storage, String firstString, String[] inputArray) {

        switch (firstString) {
        case "":
            //case if nothing is entered
            return new EmptyCommand().execute();

        case "bye":
            Platform.exit();
            return new ExitCommand().execute();

        case "help":
            return new HelpCommand().execute();

        case "list":
            return new ListCommand(taskList).execute();

        case "done":
            return new DoneCommand(taskList, inputArray, storage).execute();

        case "delete":
            return new DeleteCommand(taskList, inputArray, storage).execute();

        case "setpr":
            return new SetPriorityCommand(taskList, inputArray, storage).execute();

        case "find":
            return new FindCommand(inputArray, taskList).execute();

        //following cases are task creation
        case "todo":
            return TaskCreator.createTask(Task.Tasks.TODO, inputArray, storage, taskList);

        case "deadline":
            return TaskCreator.createTask(Task.Tasks.DEADLINE, inputArray, storage, taskList);

        case "event":
            return TaskCreator.createTask(Task.Tasks.EVENT, inputArray, storage, taskList);

        default:
            //case if first string input is not a keyword
            return Ui.getBadInputError();
        }
    }

}
