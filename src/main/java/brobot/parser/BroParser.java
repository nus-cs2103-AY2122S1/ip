package brobot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import brobot.Storage;
import brobot.UI;
import brobot.exception.BroException;
import brobot.exception.InvalidCommandException;
import brobot.exception.InvalidCommandParameterException;
import brobot.exception.NoSuchTaskException;
import brobot.task.Deadline;
import brobot.task.Event;
import brobot.task.Task;
import brobot.task.TaskList;
import brobot.task.Todo;


/**
 * Represents the input parser of the Duke Program.
 */
public class BroParser {
    private final TaskList list;
    private final Storage storage;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm",
            Locale.ENGLISH);

    /**
     * Constructor.
     *
     * @param list    The list to handle.
     * @param storage The storage to handle.
     */
    public BroParser(TaskList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Takes in raw input of user, processes it and execute the  appropriate action.
     *
     * @param rawInput Raw user input string
     * @throws BroException
     */
    public String parse(String rawInput) throws BroException {
        Scanner inputScanner = new Scanner(rawInput);
        String checkForKeyword = inputScanner.next();
        String output;
        switch (checkForKeyword) {
        case "list":
            output = handleList(inputScanner);
            break;
        case "done":
            output = handleDone(inputScanner);
            break;
        case "delete":
            output = handleDelete(inputScanner);
            break;
        case "todo":
            output = handleTodo(inputScanner);
            break;
        case "deadline":
            output = handleDeadline(inputScanner);
            break;
        case "event":
            output = handleEvent(inputScanner);
            break;
        case "find":
            output = handleFind(inputScanner);
            break;
        default:
            throw new InvalidCommandException();
        }
        return output;
    }

    /**
     * Action for list command.
     *
     * @param inputScanner Command Parameters.
     * @return List
     * @throws InvalidCommandParameterException
     */
    public String handleList(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNext()) {
            throw new InvalidCommandParameterException();
        } else {
            return UI.printList(list);
        }
    }

    /**
     * Action for done command.
     *
     * @param inputScanner Command Parameters.
     * @throws InvalidCommandParameterException
     * @throws NoSuchTaskException
     */
    public String handleDone(Scanner inputScanner) throws InvalidCommandParameterException,
            NoSuchTaskException {
        if (inputScanner.hasNextInt()) {
            int taskPos = inputScanner.nextInt() - 1;
            list.markDone(taskPos);
            storage.writeList(list);
            return UI.printTaskDone(list.getTask(taskPos + 1));
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for delete command.
     *
     * @param inputScanner Command Parameters.
     * @throws InvalidCommandParameterException
     * @throws NoSuchTaskException
     */
    public String handleDelete(Scanner inputScanner) throws InvalidCommandParameterException,
            NoSuchTaskException {
        if (inputScanner.hasNextInt()) {
            int taskPos = inputScanner.nextInt() - 1;
            Task temp = list.deleteTask(taskPos);
            storage.writeList(list);
            return UI.printTaskDeleted(temp, list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for todo command.
     *
     * @param inputScanner Command Parameters.
     * @throws InvalidCommandParameterException
     */
    public String handleTodo(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String secondWord = inputScanner.nextLine();
            list.addTask(new Todo(secondWord));
            storage.writeList(list);
            return UI.printTaskAdded(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for deadline command.
     *
     * @param inputScanner Command Parameters.
     * @throws InvalidCommandParameterException
     */
    public String handleDeadline(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/by ", 2);
            String content = contentAndDate[0];
            LocalDateTime date = LocalDateTime.parse(contentAndDate[1], formatter);
            list.addTask(new Deadline(content, date));
            storage.writeList(list);
            return UI.printTaskAdded(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for Event command.
     *
     * @param inputScanner Command Parameters.
     * @throws InvalidCommandParameterException
     */
    public String handleEvent(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/at ", 2);
            String content = contentAndDate[0];
            LocalDateTime date = LocalDateTime.parse(contentAndDate[1], formatter);
            list.addTask(new Event(content, date));
            storage.writeList(list);
            return UI.printTaskAdded(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for Find Command.
     *
     * @param inputScanner Command Parameters.
     * @throws InvalidCommandParameterException
     */
    public String handleFind(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String searchWord = inputScanner.nextLine();
            TaskList searchList = list.searchTask(searchWord);
            return UI.printSearchList(searchList);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

}
