package duke;

/**
 * Duke is a chatbot that responds to user input.
 *
 * @author Gabriel Goh
 */
public class Duke {

    private boolean hasQuit;
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public enum Commands {
        LIST,
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        SAVE,
        BYE,
        FIND,
        LOAD
    }

    public enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructor to create Duke instance.
     *
     * @param filePath Path to storage
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        if (storage.hasSave()) {
            tasks = storage.load();
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the size of the task list.
     *
     * @return size of task list.
     */
    public int taskSize() {
        return tasks.size();
    }

    /**
     * Returns if duke has stopped running.
     *
     * @return true if stopped
     */
    public boolean isQuit() {
        return hasQuit;
    }

    /**
     * Gets response to user input.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            ParsedInput parsedInput = parser.parse(input, this);
            Commands command = parsedInput.command;

            switch (command) {
            case BYE:
                hasQuit = true;
                return ui.showExitMessage();

            case LIST:
                return ui.print(tasks.toString());

            case DONE:
                if (parsedInput.description == null) {
                    return ui.showMarkedDoneMessage(tasks.markDone(parsedInput.index));
                } else {
                    return ui.showMarkedDoneMessage(tasks.markDone(parsedInput.description, parsedInput.taskType));
                }
            case DELETE:
                if (parsedInput.description == null) {
                    return ui.showDeletedMessage(
                            tasks.remove(parsedInput.index),
                            tasks.size());
                } else {
                    return ui.showDeletedMessage(
                            tasks.remove(parsedInput.description, parsedInput.taskType),
                            tasks.size());
                }

            case FIND:
                return ui.print(tasks.find(parsedInput.searchKey).toString().replace(
                        "Here are the tasks in your list, meow:",
                        "Here are the matching tasks found, meow:"));

            case TODO:
                // Extra Functionality: No duplicate tasks
                if (tasks.getTaskIndex(parsedInput.description, TaskTypes.TODO) != -1) {
                    throw new TaskExistsException(TaskTypes.TODO, parsedInput.description);
                }

                Task todo = new Todo(parsedInput.description);
                tasks.add(todo);
                return ui.showAddedMessage(todo, tasks.size());

            case DEADLINE:
                // Extra Functionality: No duplicate tasks
                if (tasks.getTaskIndex(parsedInput.description, TaskTypes.DEADLINE) != -1) {
                    throw new TaskExistsException(TaskTypes.DEADLINE, parsedInput.description);
                }

                Task deadline = new Deadline(parsedInput.description, parsedInput.dateTime);
                tasks.add(deadline);
                return ui.showAddedMessage(deadline, tasks.size());

            case EVENT:
                // Extra Functionality: No duplicate tasks
                if (tasks.getTaskIndex(parsedInput.description, TaskTypes.EVENT) != -1) {
                    throw new TaskExistsException(TaskTypes.EVENT, parsedInput.description);
                }

                Task event = new Event(parsedInput.description, parsedInput.dateTime);
                tasks.add(event);
                return ui.showAddedMessage(event, tasks.size());

            case SAVE:
                storage.save(tasks);
                return ui.showSavedMessage();

            case LOAD:
                tasks = storage.load();
                return ui.showLoadedMessage() + " " + getResponse("LIST");

            default:
                throw new IllegalCommandException(""); // should be unreachable by design
            }

        } catch (DukeException e) {
            return ui.print(e.getMessage());
        }

    }
}
