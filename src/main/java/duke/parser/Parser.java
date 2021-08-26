package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import duke.data.DukeException;
import duke.data.NoToDoDescriptionException;
import duke.data.UnknownCommandException;

import duke.storage.Storage;

import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Encapsulates the processing of user inputs to duke.
 *
 * @author Zhi Bin
 * @version Duke Level 8
 */
public class Parser {
    private final TaskList list;
    private final Storage storage;
    private final Ui ui;

    /**
     * The list of commands that the parser can handle.
     */
    private enum Command {
        EXIT, LIST, DONE, TODO, DEADLINE, EVENT, UNKNOWN, DELETE, FIND
    }

    /**
     * Creates a Parser that handles user input and turn it into respective
     * commands and execute the required functions that user demands.
     *
     * @param list    The TaskList handler that is handling the list of task.
     * @param storage The Storage handler that is in-charged of saving and loading files on local directory.
     * @param ui      The Ui handler that handles printing of output, if required.
     */
    public Parser(TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Processes the user inputs into commands that duke can understand.
     *
     * @param command The user inputs in the form of String.
     * @return Returns false if the command processed is to exit duke, else true.
     * @throws DukeException If some error occurred in the processing of user input,
     *                       like the user requires some function that duke does not support.
     */
    public boolean process(String command) throws DukeException {
        switch (stringToCommand(command)) {
        case EXIT:
            ui.farewellMessage();
            return false;

        case LIST:
            ui.listTasks(list.getList());
            break;

        case DONE:
            int taskNumber = Integer.parseInt(command.split(" ")[1]);
            ui.markDoneMessage(list.mark(taskNumber));
            storage.save(list.getList());
            break;

        case EVENT:
            String eventDescription = command.substring(command.indexOf(" ") + 1, command.indexOf("/at") - 1);
            try {
                LocalDateTime at = LocalDateTime.parse(command.substring(command.indexOf("at") + 3),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
                Event event = new Event(eventDescription, false, at);
                addTask(event);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter Date and Time in YYYY-MM-DD HH:MM.");
            }
            break;

        case DEADLINE:
            String deadlineDescription = command.substring(command.indexOf(" ") + 1, command.indexOf("/by") - 1);
            try {
                LocalDateTime by = LocalDateTime.parse(command.substring(command.indexOf("by") + 3),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
                Deadline deadline = new Deadline(deadlineDescription, false, by);
                addTask(deadline);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter Date and Time in YYYY-MM-DD HH:MM.");
            }
            break;

        case TODO:
            int spaceIndex = command.indexOf(" ");
            String toDoDescription = command.substring(spaceIndex + 1);
            if (toDoDescription.isBlank() || spaceIndex == -1) {
                throw new NoToDoDescriptionException();
            }
            ToDo toDo = new ToDo(toDoDescription, false);
            addTask(toDo);
            break;

        case DELETE:
            int taskNum = Integer.parseInt(command.substring(command.indexOf(" ") + 1));
            Task task = list.delete(taskNum);
            ui.deleteTaskMessage(list.size(), task);
            storage.save(list.getList());
            break;

        case FIND:
            String keyword = command.substring(command.indexOf(" ") + 1);
            ArrayList<Task> list = this.list.findRelatedTask(keyword);
            ui.printRelatedTasks(list);
            break;

        case UNKNOWN:
        default:
            throw new UnknownCommandException();
        }
        return true;
    }

    /**
     * Returns the respective Command that user want duke to do.
     * Anything not in the list of commands will be treated as UNKNOWN.
     *
     * @param str User's input on what they want duke to do.
     * @return The respective command to call the methods.
     */
    private Command stringToCommand(String str) {
        String cmdType = str.split(" ")[0];
        switch (cmdType) {
        case "list":
            return Command.LIST;

        case "bye":
            return Command.EXIT;

        case "done":
            return Command.DONE;

        case "todo":
            return Command.TODO;

        case "deadline":
            return Command.DEADLINE;

        case "event":
            return Command.EVENT;

        case "delete":
            return Command.DELETE;

        case "find":
            return Command.FIND;

        default:
            return Command.UNKNOWN;
        }
    }

    /**
     * Adds task to the task list and
     * prints a message after adding,
     * and update the local data file.
     *
     * @param task The task to be added.
     */
    private void addTask(Task task) {
        list.add(task);
        ui.addTaskMessage(list.size(), task);
        storage.save(list.getList());
    }
}
