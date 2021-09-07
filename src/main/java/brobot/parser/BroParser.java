package brobot.parser;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import brobot.UI;
import brobot.exception.BroException;
import brobot.exception.InvalidCommandException;
import brobot.exception.InvalidCommandParameterException;
import brobot.exception.NoSuchTaskException;
import brobot.storage.DemoJFileChooser;
import brobot.storage.Storage;
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
     * @param storage The brobot.storage to handle.
     */
    public BroParser(TaskList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Takes in raw input of user, processes it and execute the  appropriate action.
     *
     * @param rawInput Raw user input string
     * @return Brobot's response to the input
     * @throws BroException An exception that represents an error in the Brobot programme
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
        case "storage":
            output = handleStorage(inputScanner);
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
     * @return List in string format
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for
     * the inputted command
     */
    public String handleList(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNext()) {
            throw new InvalidCommandParameterException();
        } else {
            return UI.getListText(list);
        }
    }

    /**
     * Action for done command.
     *
     * @param inputScanner Command Parameters.
     * @return Message for done command
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for
     * the inputted command
     * @throws NoSuchTaskException Exception that represents an invalid task number error
     */
    public String handleDone(Scanner inputScanner) throws InvalidCommandParameterException,
            NoSuchTaskException {
        if (inputScanner.hasNextInt()) {
            int taskPos = inputScanner.nextInt() - 1;
            assert(taskPos >= 0);
            list.markDone(taskPos);
            storage.writeList(list);
            return UI.getTaskDoneText(list.getTask(taskPos + 1));
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for delete command.
     *
     * @param inputScanner Command Parameters.
     * @return Message for delete command
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for
     * the inputted command
     * @throws NoSuchTaskException Exception that represents an invalid task number error
     */
    public String handleDelete(Scanner inputScanner) throws InvalidCommandParameterException,
            NoSuchTaskException {
        if (inputScanner.hasNextInt()) {
            int taskPos = inputScanner.nextInt() - 1;
            Task temp = list.deleteTask(taskPos);
            storage.writeList(list);
            return UI.getTaskDeletedText(temp, list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for todo command.
     *
     * @param inputScanner Command Parameters.
     * @return Message for todo command
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for
     * the inputted command
     */
    public String handleTodo(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String secondWord = inputScanner.nextLine();
            list.addTask(new Todo(secondWord));
            storage.writeList(list);
            return UI.getTaskAddedText(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for deadline command.
     *
     * @param inputScanner Command Parameters.
     * @return Message for deadline command
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for
     * the inputted command
     */
    public String handleDeadline(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/by ", 2);
            String content = contentAndDate[0];
            LocalDateTime date = LocalDateTime.parse(contentAndDate[1], formatter);
            list.addTask(new Deadline(content, date));
            storage.writeList(list);
            return UI.getTaskAddedText(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for Event command.
     *
     * @param inputScanner Command Parameters.
     * @return Message for event command
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for the
     * inputted command
     */
    public String handleEvent(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/at ", 2);
            String content = contentAndDate[0];
            LocalDateTime date = LocalDateTime.parse(contentAndDate[1], formatter);
            list.addTask(new Event(content, date));
            storage.writeList(list);
            return UI.getTaskAddedText(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Action for Find Command.
     *
     * @param inputScanner Command Parameters.
     * @return Message for find command
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for the
     * inputted command
     */
    public String handleFind(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String searchWord = inputScanner.nextLine();
            TaskList searchList = list.searchTask(searchWord);
            return UI.getSearchListText(searchList);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    /**
     * Handler for Storage Command.
     * Changes the brobot.storage file directory to the one selected.
     * @param inputScanner Command Parameters(if any).
     * @return Message for brobot.storage command
     * @throws InvalidCommandParameterException Exception that represents an invalid parameter error for the
     * inputted command
     */
    public String handleStorage(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNext()) {
            throw new InvalidCommandParameterException();
        } else {
            DemoJFileChooser fileChooser = new DemoJFileChooser(storage);
            File selectedFile = fileChooser.selectFile();
            storage.changeFilePath(selectedFile);
            return UI.getStorageChangeText(selectedFile.getAbsolutePath());
        }
    }

}
