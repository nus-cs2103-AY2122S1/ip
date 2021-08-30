package duke;

import java.util.Scanner;

/**
 * Duke is a chatbot that responds to user input.
 *
 * @author Gabriel Goh
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    boolean hasQuit;


    public enum Commands {
        LIST,
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        SAVE,
        BYE,
        FIND
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
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        // future: Load
        /*
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        */
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
     * Gets response to user input.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            parser.parse(input, this);
            Commands command = parser.command;

            switch (command) {
            case BYE:
                hasQuit = true;
                return ui.showExitMessage();

            case LIST:
                return ui.print(tasks.toString());

            case DONE:
                if (parser.description == null) {
                    return ui.showMarkedDoneMessage(tasks.markDone(parser.index));
                } else {
                    return ui.showMarkedDoneMessage(tasks.markDone(parser.description, parser.taskTypes));
                }
            case DELETE:
                if (parser.description == null) {
                    return ui.showDeletedMessage(tasks.remove(parser.index), tasks.size());
                } else {
                    return ui.showDeletedMessage(tasks.remove(parser.description, parser.taskTypes), tasks.size());
                }

            case FIND:
                return ui.print(tasks.find(parser.searchKey).toString().replace(
                        "Here are the tasks in your list, meow:",
                        "Here are the matching tasks found, meow:"));

            case TODO:
                // Extra Functionality: No duplicate tasks
                if (tasks.getTaskIndex(parser.description, TaskTypes.TODO) != -1) {
                    throw new TaskExistsException(TaskTypes.TODO, parser.description);
                }

                Task todo = new Todo(parser.description);
                tasks.add(todo);
                return ui.showAddedMessage(todo, tasks.size());

            case DEADLINE:
                // Extra Functionality: No duplicate tasks
                if (tasks.getTaskIndex(parser.description, TaskTypes.DEADLINE) != -1) {
                    throw new TaskExistsException(TaskTypes.DEADLINE, parser.description);
                }

                Task deadline;
                if (parser.by.trim().split("\\s+").length < 2) { // no time given
                    deadline = new Deadline(parser.description, Parser.parseDate(parser.by));
                } else {
                    deadline = new Deadline(parser.description, Parser.parseDateTime(parser.by));
                }

                tasks.add(deadline);
                return ui.showAddedMessage(deadline, tasks.size());

            case EVENT:
                // Extra Functionality: No duplicate tasks
                if (tasks.getTaskIndex(parser.description, TaskTypes.EVENT) != -1) {
                    throw new TaskExistsException(TaskTypes.EVENT, parser.description);
                }

                Task event;
                if (parser.at.trim().split("\\s+").length < 2) { // no time given
                    event = new Event(parser.description, Parser.parseDate(parser.at));
                } else {
                    event = new Event(parser.description, Parser.parseDateTime(parser.at));
                }

                tasks.add(event);
                return ui.showAddedMessage(event, tasks.size());

            case SAVE:
                storage.save(tasks);
                return ui.showSavedMessage();

            default:
                throw new IllegalCommandException(""); // should be unreachable by design
            }

        } catch (DukeException e) {
            return ui.print(e.getMessage());
        }

    }
}
