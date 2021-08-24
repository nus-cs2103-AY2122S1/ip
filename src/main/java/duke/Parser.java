package duke;

import java.io.IOException;
/**
 * Deals with making sense of the user command.
 *
 * @author Timothy Wong Eu-Jin
 */
public class Parser {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Validates a to do command.
     *
     * @param req The full command
     * @return a ToDo object
     * @throws NoDescriptionException
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
     * @param req The full command
     * @return Deadline object
     * @throws NoDescriptionException
     * @throws MissingTimeCommandException
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
     * @param req The full command
     * @return Event object
     * @throws NoDescriptionException
     * @throws MissingTimeCommandException
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
     * Receives an index to specify a task to mark as done.
     * Converts string to integer.
     *
     * @param req The full command
     * @return Integer index of the task in the task list
     * @throws NoDescriptionException
     * @throws InvalidDescriptionException
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
     * Receives an index to specify a task to delete.
     * Converts string to integer.
     *
     * @param req The full command
     * @return Integer index of the task in the task list
     * @throws NoDescriptionException
     * @throws InvalidDescriptionException
     * @throws IOException
     */
    public Task validateDelete(String req) throws NoDescriptionException, InvalidDescriptionException,
            IOException{
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

        Task deletedTask = this.tasks.deleteTask(index);
        return deletedTask;
    }

    /**
     * Receives an invalid command and throws and exception.
     *
     * @param req The full command
     * @throws InvalidCommandException
     */
    public void invalidInput(String req) throws InvalidCommandException {
        throw new InvalidCommandException(
                "Sorry! I do not understand you? Try another command!");
    }

    /**
     * Passes the full command to various helper function to check validity.
     *
     * @param fullCommand
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
                storage.rewriteFile(tasks); //STORAGE!
                break;

            case "deadline":
                Deadline d = Parser.validateDeadline(fullCommand);
                tasks.add(d);
                ui.sendAddTask(d);
                storage.rewriteFile(tasks); //STORAGE!
                break;

            case "event":
                Event e = Parser.validateEvent(fullCommand);
                ui.sendAddTask(e);
                storage.rewriteFile(tasks); //STORAGE !
                break;

            case "done":
                int index = validateDone(fullCommand);
                Task completedTask = this.tasks.markAsDone(index);
                storage.rewriteFile(tasks);
                ui.sendDone(completedTask);
                break;

            case "delete":
                Task deletedTask = validateDelete(fullCommand);
                storage.rewriteFile(tasks);
                ui.sendDeleted(deletedTask);
                break;

            default:
                this.invalidInput(req);
        }
     }
}
