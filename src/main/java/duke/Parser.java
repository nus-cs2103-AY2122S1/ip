package duke;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Deals with making sense of the user command.
 *
 * Calls ui methods to send acknowledgements to user.
 */
public class Parser {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Parser object.
     *
     * @param tasks TaskList
     * @param ui
     * @param storage
     */
    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Validates a to-do command.
     *
     * Static class method.
     *
     * @param req The full command
     * @return To Do object
     * @throws NoDescriptionException If no description is appended after command.
     * @throws IOException
     */
    public static ToDo validateToDo(String req) throws NoDescriptionException, IOException {
        if (req.equals("todo")) {
            throw new NoDescriptionException("The description of a todo cannot be empty.");
        }

        String[] splitReq = req.split(" ", 2);
        String body = splitReq[1];
        ToDo todo = new ToDo(body); //Could also throw and error in the constructor
        return todo;
    }

    /**
     * Validates a Deadline command.
     *
     * Static class method.
     *
     * @param req The full command
     * @return Deadline object
     * @throws NoDescriptionException If no description is appended after command.
     * @throws MissingTimeCommandException If "/by" is not in full command
     */
    public static Deadline validateDeadline(String req) throws NoDescriptionException, MissingTimeCommandException{
        if (req.equals("deadline")) {
            throw new NoDescriptionException("The description of a deadline cannot be empty.");
        }
        if (!req.contains("/by")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/by' in the command.");
        }
        String[] splitReq = req.split(" ", 2);
        String[] body = splitReq[1].split(" /by ", 2);
        String desc = body[0];
        String date = body[1];
        Deadline deadline = new Deadline(desc, date);
        return deadline;
    }

    /**
     * Validates a Event command.
     *
     * Static class method.
     *
     * @param req The full command
     * @return Event object
     * @throws NoDescriptionException If no description is appended after command.
     * @throws MissingTimeCommandException If "/at" is not in full command
     */
    public static Event validateEvent(String req) throws NoDescriptionException, MissingTimeCommandException {
        if (req.equals("event")) {
            throw new NoDescriptionException("The description of an event cannot be empty.");
        }
        if (!req.contains("/at")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/at' in the command.");
        }

        String[] splitReq = req.split(" ", 2);
        String[] body = splitReq[1].split(" /at ", 2);
        String desc = body[0];
        String date = body[1];
        Event event = new Event(desc, date);
        return event;
    }

    /**
     * Validates a done command.
     *
     * Static class method.
     *
     * Receives an index to specify a task in the TaskList.
     * Converts input string to integer.
     *
     * @param req The full command
     * @return Integer index of the task in the task list
     * @throws NoDescriptionException If no description is appended after command.
     * @throws InvalidDescriptionException If description after command is not a valid index.
     * @throws IOException
     */
    public static int validateDone(String req) throws NoDescriptionException, InvalidDescriptionException,
            IOException {
        if (req.equals("done")) {
            throw new NoDescriptionException("Please specify the task number.");
        }

        String[] splitReq = req.split(" ", 2);
        String desc = splitReq[1];

        int index;
        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'done'.");
        }

        return index;
    }

    /**
     * Validates a delete command.
     *
     * Receives an index to specify a task to delete.
     * Converts input string to integer.
     *
     * @param req The full command
     * @return Integer index of the task in the task list
     * @throws NoDescriptionException If no description is appended after command.
     * @throws InvalidDescriptionException If description after command is not a valid index.
     * @throws IOException
     */
    public static int validateDelete(String req) throws NoDescriptionException, InvalidDescriptionException {
        if (req.equals("delete")) {
            throw new NoDescriptionException("Please specify a task to delete.");
        }

        String[] splitReq = req.split(" ", 2);
        String desc = splitReq[1];

        int index;
        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'delete'.");
        }

        return index;
    }

    /**
     * Validates a find command.
     *
     * Receives a string to match.
     * @param req
     * @throws NoDescriptionException If no keyword is appended after command.
     */
    public void validateFind(String req) throws NoDescriptionException {
        if (req.equals("find")) {
            throw new NoDescriptionException("Please specify a keyword to search up.");
        }

        String[] splitReq = req.split(" ", 2);
        String keyword = splitReq[1];
        ArrayList<Task> matchedTasks = tasks.findTasks(keyword);
        ui.sendMatchedTasks(matchedTasks);
    }

    /**
     * Receives an invalid command and throws an exception.
     *
     * @param req The full command.
     * @throws InvalidCommandException Always thrown.
     */
    public static void invalidInput(String req) throws InvalidCommandException {
        throw new InvalidCommandException(
                "Sorry! I do not understand you? Try another command!");
    }

    /**
     * Passes the full command to various helper function to check validity.
     *
     * The only instance method in Parser class.
     *
     * @param fullCommand The full command.
     * @throws DukeException
     * @throws IOException
     */
    public void parse(String fullCommand) throws DukeException, IOException {
        String req = "";
        boolean end = false;
        //Get first command word
        String[] splitCmd = fullCommand.split(" ", 2);
        String cmd = splitCmd[0];

        //Switch statement based on initial command
        switch (cmd) {
            case "list":
                ui.enumTasks(this.tasks.getAll());
                break;

            case "bye":
                ui.sendGoodbye();
                end = true;
                break;

            case "todo":
                ToDo t = Parser.validateToDo(fullCommand);
                tasks.add(t);
                ui.sendAddTask(t);
                storage.rewriteFile(tasks);
                break;

            case "deadline":
                Deadline d = Parser.validateDeadline(fullCommand);
                tasks.add(d);
                ui.sendAddTask(d);
                storage.rewriteFile(tasks);
                break;

            case "event":
                Event e = Parser.validateEvent(fullCommand);
                ui.sendAddTask(e);
                storage.rewriteFile(tasks);
                break;

            case "done":
                int indexOfDoneTask = Parser.validateDone(fullCommand);
                Task completedTask = this.tasks.markAsDone(indexOfDoneTask);
                storage.rewriteFile(tasks);
                ui.sendDone(completedTask);
                break;

            case "delete":
                int indexOfDeletedTask = Parser.validateDelete(fullCommand);
                Task deletedTask = this.tasks.deleteTask(indexOfDeletedTask);
                storage.rewriteFile(tasks);
                ui.sendDeleted(deletedTask);
                break;

            case "find":
                validateFind(fullCommand);
                break;

            default:
                Parser.invalidInput(req);
        }
     }
}
