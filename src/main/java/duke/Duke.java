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
     * Runs the chatbot. Begin to accept user input.
     */
    public void run() {
        ui.showLogo();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            try {
                Parser parser = new Parser();
                parser.parse(input, this);
                Commands command = parser.command;

                switch (command) {
                case LIST:
                    ui.print(tasks.toString());
                    break;

                case DONE:
                    if (parser.description == null) {
                        ui.showMarkedDoneMessage(tasks.markDone(parser.index));
                    } else {
                        ui.showMarkedDoneMessage(tasks.markDone(parser.description, parser.taskTypes));
                    }
                    break;

                case DELETE:
                    if (parser.description == null) {
                        ui.showDeletedMessage(tasks.remove(parser.index), tasks.size());
                    } else {
                        ui.showDeletedMessage(tasks.remove(parser.description, parser.taskTypes), tasks.size());
                    }
                    break;

                case FIND:
                    ui.print(tasks.find(parser.searchKey).toString().replace(
                            "Here are the tasks in your list, meow:",
                            "Here are the matching tasks found, meow:"));
                    break;

                case TODO:
                    // Extra Functionality: No duplicate tasks
                    if (tasks.getTaskIndex(parser.description, TaskTypes.TODO) != -1) {
                        throw new TaskExistsException(TaskTypes.TODO, parser.description);
                    }


                    Task todo = new Todo(parser.description);
                    tasks.add(todo);
                    ui.showAddedMessage(todo, tasks.size());
                    break;

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
                    ui.showAddedMessage(deadline, tasks.size());
                    break;

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
                    ui.showAddedMessage(event, tasks.size());
                    break;

                case SAVE:
                    storage.save(tasks);
                    ui.showSavedMessage();
                    break;

                default:
                    throw new IllegalCommandException(""); // should be unreachable by design
                }

            } catch (DukeException e) {
                ui.print(e.getMessage());
            } finally {
                input = sc.nextLine();
            }

        }

        ui.showExitMessage();

        sc.close();
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
