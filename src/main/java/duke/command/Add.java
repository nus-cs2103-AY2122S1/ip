package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.EmptyDescriptionException;
import duke.dukeexception.IllegalCommandException;
import duke.dukeexception.NoListException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a type of Command that adds a Task to a tasklist.
 */
public class Add extends Command {

    private Task task;

    /**
     * Constructs the Command Add.
     *
     * @param input The user's input to be converted into a Task.
     * @throws IllegalCommandException If a command given by the user is invalid.
     * @throws EmptyDescriptionException If a todo command is not followed by a description.
     */
    public Add(String input) throws IllegalCommandException, EmptyDescriptionException {
        if (input.startsWith("todo")) {
            String temp = input.substring(4);
            if (temp.trim().length() == 0) {
                String message = "OOPS!!! The description of a todo cannot be empty.";
                throw new EmptyDescriptionException(message);
            }
            String[] arr = temp.split("p=");
            this.task = new Todo(arr[0].trim(), arr[1].trim());
        } else if (input.startsWith("deadline")) {
            String[] arr = input.split(" /by ");
            String[] temp = arr[1].split("p=");
            this.task = new Deadline(arr[0].substring(9), temp[1].trim(), temp[0]);
        } else if (input.startsWith("event")) {
            String[] arr = input.split(" /at ");
            String[] temp = arr[1].split("p=");
            this.task = new Event(arr[0].substring(6), temp[1].trim(), temp[0]);
        } else {
            throw new IllegalCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Performs the addition of the given task to the tasklist.
     *
     * @param tasks The tasklist.
     * @param ui The ui.
     * @param storage The storage.
     * @throws NoListException If there is no list to be loaded.
     */
    public String exec(TaskList tasks, Ui ui, Storage storage) throws NoListException {
        tasks.add(this.task);
        System.out.println("hello");
        try {
            storage.save(tasks);
            return "Got it. I've added this task:\n"
                    + this.task.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        } catch (NoListException e) {
            throw e;
        }
    }
}
