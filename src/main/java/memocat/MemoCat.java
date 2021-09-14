package memocat;

import memocat.task.Task;
import memocat.task.TaskList;

public class MemoCat {

    /**
     * storage instance to handle task list storage.
     */
    private final Storage storage;

    /**
     * task list instance to store tasks.
     */
    private final TaskList tasks;

    /**
     * Ui instance to handle memocat ui.
     */
    private final Ui ui;

    /**
     * Parser instance to handle parsing.
     */
    private final Parser parser;

    /**
     * Constructs a memocat instance to run memocat.
     *
     * @param filePath Path to the data save file directory
     * @param fileName File name of the data save file
     */
    public MemoCat(String filePath, String fileName) {
        this.storage = new Storage(filePath, fileName);
        this.tasks = this.storage.readData();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new MemoCat("./data/", "memocat.txt").run();
    }

    /**
     * Finds tasks from task list that contains a given pattern and print the tasks.
     *
     * @param command The command entered to the console.
     */
    private void find(String command) {
        String pattern = command.split("find ", 2)[1];
        String matches = tasks.getMatchedTasksString(pattern);
        ui.printMessage(matches);
    }

    private void run() {
        ui.greet();
        this.add();
        ui.bye();
    }

    /**
     * Adds and stores tasks entered and display them back with complete status when requested.
     */
    private void add() {
        String command;
        while (true) {
            command = ui.getNextLine();
            if (command.equals("bye")) {
                // end bot
                try {
                    // save all data
                    storage.writeTasksToData(tasks);
                } catch (MemoCatException e) {
                    ui.printMessage(e.getMessage());
                }
                break;
            } else {
                switch (parser.getCommandAction(command)) {
                case "list":
                    // view list
                    ui.printMessage(tasks.toString());
                    break;
                case "done":
                    done(command);
                    break;
                case "todo":
                    todo(command);
                    break;
                case "event":
                    event(command);
                    break;
                case "deadline":
                    deadline(command);
                    break;
                case "delete":
                    delete(command);
                    break;
                case "find":
                    find(command);
                    break;
                default:
                    // Message for unrecognised task type
                    ui.printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
            }
        }
    }

    /**
     * Responses for done command.
     *
     * @param command The command entered to console.
     */
    private void done(String command) {
        try {
            int doneIndex = parser.getCommandActionIndex(command);
            try {
                tasks.get(doneIndex - 1).markAsDone();
                storage.writeTasksToData(tasks);
                ui.printMessage("Nice! I've marked this task as done:\n\t" + tasks.get(doneIndex - 1));

            } catch (IndexOutOfBoundsException e) {
                // Task at doneIndex does not exist
                ui.printMessage("Task " + doneIndex + " does not exist. Please check your task list!");

            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            // command done is not followed by a number
            ui.printMessage("☹ OOPS!!! The index of a task done must be an integer.");
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Responses for todo command.
     *
     * @param command The command entered to console.
     */
    private void todo(String command) {
        Task todo;
        try {
            todo = parser.commandToTask(command);
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
            return;
        }
        tasks.add(todo);
        try {
            storage.writeTasksToData(tasks);
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
        }
        ui.printMessage("Got it. I've added this task:\n\t" + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Responses for event command.
     *
     * @param command The command entered to console.
     */
    private void event(String command) {
        Task event;
        try {
            event = parser.commandToTask(command);
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
            return;
        }
        tasks.add(event);
        try {
            storage.writeTasksToData(tasks);
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
        }
        ui.printMessage("Got it. I've added this task:\n\t" + event
                + "\nNow you have " + tasks.size() + " tasks in the list.");

    }

    /**
     * Responses for deadline command.
     *
     * @param command The command entered to console.
     */
    private void deadline(String command) {
        Task deadline;
        try {
            deadline = parser.commandToTask(command);
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
            return;
        }
        tasks.add(deadline);
        try {
            storage.writeTasksToData(tasks);
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
        }
        ui.printMessage("Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Responses for delete command.
     *
     * @param command The command entered to console.
     */
    private void delete(String command) {
        try {
            int deleteIndex = parser.getCommandActionIndex(command);
            try {
                Task removed = tasks.remove(deleteIndex - 1);
                storage.writeTasksToData(tasks);
                ui.printMessage("Noted. I've removed this task:\n\t" + removed);

            } catch (IndexOutOfBoundsException e) {
                // Task at deleteIndex does not exist
                ui.printMessage("Task " + deleteIndex + " does not exist. Please check your task list!");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            // command delete is not followed by a number
            ui.printMessage("☹ OOPS!!! The index of a task to be deleted must be an integer.");
        } catch (MemoCatException e) {
            ui.printMessage(e.getMessage());
        }
    }
}
