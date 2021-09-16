package duke.parser;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EditCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TasksOnCommand;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.IncorrectFormatException;
import duke.exceptions.InvalidKeywordException;
import duke.exceptions.InvalidNumberInputException;
import duke.exceptions.NonExistentTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.utils.Constants;

import java.time.LocalDate;


/**
 * Encapsulates the parsing, interpretation and validation of the user's input commands
 */
public class Parser {

    /**
     * Interprets the keyword of the user's input and accordingly calls the other task-specific
     * parse functions
     *
     * @param input user's string input
     * @param taskList the list of tasks to be operated upon
     * @return a command returned by the respective task-specific parse function
     */
    public static Command parse(String input, TaskList taskList) {
        String[] parsedInput = input.trim().split("\\s", 2);
        String command = parsedInput[0];
        if (!command.isEmpty()) {
            switch (command.toUpperCase()) {
            case "BYE":
                return parseBye();
            case "LIST":
                return parseList();
            case "DONE":
                return parseDone(input, taskList);
            case "DELETE":
                return parseDelete(input, taskList);
            case "TASKS_ON":
                return parseTasksOn(input);
            case "FIND":
                return parseFind(input);
            case "EDIT":
                return parseEdit(input, taskList);
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                return parseTask(input);
            default:
                throw new InvalidKeywordException();
            }
        }
        throw new DukeException("Missing input!!!");
    }

    private static EditCommand parseEdit(String input, TaskList taskList) {
        try {
            String[] parsedString = input.split("\\s", 2);
            String body = parsedString[1].trim();
            int taskNo = Integer.parseInt(body.split("\\s", 2)[0]) - 1;
            String fieldAndContent = body.split("\\s", 2)[1];
            String content = fieldAndContent.split("\\s", 2)[1];
            String field = fieldAndContent.split("\\s", 2)[0];
            if (taskNo > taskList.size()) {
                throw new NonExistentTaskException();
            }
            Task taskToBeEdited = taskList.get(taskNo);
            if (taskToBeEdited instanceof ToDo || taskToBeEdited instanceof Deadline || taskToBeEdited instanceof Event) {
                return new EditCommand(taskToBeEdited, field, content, taskNo);
            } else {
                throw new DukeException("This task cannot be edited");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex){
            throw new EmptyDescriptionException("Please enter a valid task number and field in the given format:\n "
                    + Constants.EDIT_FORMAT);
        }
    }



    /**
     * Returns a FindCommand when the user uses the 'find' keyword
     *
     * @param input user's string input
     * @return a FindCommand which will execute the task corresponding to the 'find' keyword
     */
    private static FindCommand parseFind(String input) {
        try {
            String[] parsedString = input.split("\\s", 2);
            String word = parsedString[1].trim();
            return new FindCommand(word);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new EmptyDescriptionException("Please enter a word to search the list");
        }
    }

    /**
     * Returns a ByeCommand when the user uses the 'bye' keyword
     *
     * @return a ByeCommand which will execute the task corresponding to the 'bye' keyword
     */
    private static ByeCommand parseBye() {
        return new ByeCommand();
    }

    /**
     * Returns a ListCommand when the user uses the 'list' keyword
     *
     * @return a ListCommand which will execute the task corresponding to the 'list' keyword
     */
    private static ListCommand parseList() {
        return new ListCommand();
    }

    /**
     * Returns an AddCommand when the user uses the 'todo', 'deadline' or 'event' keyword.
     * Throws an exception if the tasks are formatted incorrectly
     *
     * @param input the user's task input
     * @return an AddCommand which will execute the task corresponding to the 'todo', 'deadline' or 'event' keyword
     */
    private static AddCommand parseTask(String input) {
        String[] splitTasks = input.split("\\s", 2);
        String taskType = splitTasks[0].toLowerCase();
        try {
            switch (taskType) {
            case "todo":
                return parseTodo(splitTasks);
            case "deadline":
                return parseDeadline(splitTasks);
            case "event":
                return parseEvent(splitTasks);
            default:
                throw new InvalidKeywordException();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            switch (taskType) {
            case "deadline": throw new IncorrectFormatException(
                    "Please specify a description and date for your deadline!" + Constants.DEADLINE_FORMAT);
            case "event": throw new IncorrectFormatException(
                    "Please specify a description, date and time for your event!" + Constants.EVENT_FORMAT);
            default: throw new IncorrectFormatException(
                    "Please specify a description and date/time for your task!");
            }
        }
    }

    private static AddCommand parseTodo(String[] splitTasks) {
        Task task;
        if (hasEmptyDesc(splitTasks)) {
            throw new EmptyDescriptionException(
                    "Sorry, the description of a todo cannot be empty" + Constants.TODO_FORMAT
            );
        } else {
            String desc = splitTasks[1].trim();
            task = new ToDo(desc);
        }
        return new AddCommand(task);
    }

    private static AddCommand parseDeadline(String[] splitTasks) {
        Task task;
        if (hasEmptyDesc(splitTasks)) {
            throw new EmptyDescriptionException(
                    "Sorry, the description of a deadline cannot be empty" + Constants.DEADLINE_FORMAT
            );
        } else {
            String[] parsedDeadline = splitTasks[1].split("/by");
            if (hasDateButEmptyDesc(parsedDeadline)) {
                throw new EmptyDescriptionException(
                        "Sorry, the description of a deadline cannot be empty" + Constants.DEADLINE_FORMAT
                );
            } else if (hasEmptyDesc(parsedDeadline)) {
                throw new IncorrectFormatException(
                        "Please add a date for your deadline!" + Constants.DEADLINE_FORMAT);
            } else {
                String desc = parsedDeadline[0].trim();
                String date = parsedDeadline[1].trim();
                task = new Deadline(desc, DateTimeParser.deadlineDateParse(date));
            }
        }
        return new AddCommand(task);
    }

    private static AddCommand parseEvent(String[] splitTasks) {
        Task task;
        if (hasEmptyDesc(splitTasks)) {
            throw new EmptyDescriptionException(
                    "Sorry the description of an event cannot be empty" + Constants.EVENT_FORMAT
            );
        } else {
            String[] parsedEvent = splitTasks[1].split("/at");
            if (hasDateButEmptyDesc(parsedEvent)) {
                throw new EmptyDescriptionException(
                        "Sorry the description of an event cannot be empty" + Constants.EVENT_FORMAT
                );
            } else if (hasEmptyDesc(parsedEvent)) {
                throw new IncorrectFormatException(
                        "Please add a date and time for your event!" + Constants.EVENT_FORMAT);
            } else {
                String details = parsedEvent[0].trim();
                String at = parsedEvent[1].trim();
                task = new Event(details, DateTimeParser.eventDateTimeParse(at));
            }
        }
        return new AddCommand(task);
    }

    /**
     * Returns a DoneCommand when the user uses the 'done' keyword
     *
     * @param input the user's string input
     * @param taskList the list to be operated on
     * @return a DoneCommand to execute the done operation
     */
    private static DoneCommand parseDone(String input, TaskList taskList) {
        try {
            String[] parsedTask = input.split("\\s", 2);
            String indexOfTask = parsedTask[1].trim();
            int index = Integer.parseInt(indexOfTask);
            if (index > 0 && index > taskList.size()) {
                throw new NonExistentTaskException();
            }
            return new DoneCommand(index);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }

    /**
     * Returns a DeleteCommand when the user uses the 'delete' keyword
     *
     * @param input the user's string input
     * @param taskList the list to be operated on
     * @return a DeleteCommand to execute the delete operation
     */
    private static DeleteCommand parseDelete(String input, TaskList taskList) {
        try {
            String[] parsedTask = input.split("\\s", 2);
            String indexOfTask = parsedTask[1].trim();
            int index = Integer.parseInt(indexOfTask);
            if (index > 0 && index > taskList.size()) {
                throw new NonExistentTaskException();
            } else {
                return new DeleteCommand(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }

    /**
     * Returns a TasksOnCommand when the user uses the 'tasks_on' keyword
     *
     * @param input the user's string input
     * @return a TasksOnCommand to execute the tasks_on operation
     */
    private static TasksOnCommand parseTasksOn(String input) {
        try {
            String[] parsedString = input.split("\\s", 2);
            LocalDate date = DateTimeParser.deadlineDateParse(parsedString[1].trim());
            return new TasksOnCommand(date);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new EmptyDescriptionException("Please enter a date to view all the tasks due");
        }
    }

    /**
     * Returns a boolean value which checks if a task has an empty description
     *
     * @param taskArray parsed string array
     * @return boolean value of whether a task has an empty description
     */
    private static boolean hasEmptyDesc(String[] taskArray) {
        return taskArray.length == 1 || taskArray[1].isBlank() || taskArray[1].isEmpty();
    }

    /**
     * Returns a boolean value which checks if a task has a date but an empty description
     *
     * @param taskArray parsed string array
     * @return boolean value of whether a task has a date but an empty description
     */
    private static boolean hasDateButEmptyDesc(String[] taskArray) {
        return (taskArray[0].isBlank() || taskArray[0].isEmpty()) && (!taskArray[1].isBlank());
    }

}
