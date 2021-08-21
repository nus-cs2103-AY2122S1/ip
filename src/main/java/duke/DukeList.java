package duke;

import java.util.List;
import java.util.stream.IntStream;

import duke.database.Database;
import duke.database.SQLite;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

/**
 * Encapsulation of a task list in Duke, able to provide relevant responses on
 * its manipulation.
 */
public class DukeList {
    private final Database database;

    public DukeList() {
        this.database = new SQLite();
        this.database.load();
        this.database.initialize();
    }

    /**
     * Add a task to the list and return a response on the added task.
     * 
     * @param task to be added to the list
     * @return response to the addition
     */
    public Response addWithResponse(Task task) {
        this.database.addTask(task);
        return new Response("Got it. I've added this task:\n" + task + "\n" + currentSizeMessage());
    }

    /**
     * Return a response containing an iteration of all current tasks.
     * 
     * @return response to be printed
     */
    public Response currentListResponse() {
        List<Task> list = this.database.getTasksList();
        String message = IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i))
                .reduce((str1, str2) -> str1 + "\n" + str2).orElse("You have not added any tasks!");
        return new Response(message);
    }

    /**
     * Marks the given task (by index) as completed, and returns a response on
     * whether the operation is successful.
     * 
     * @param i index of task to be marked as completed
     * @return response on whether it is succcessful
     */
    public Response markCompleted(int i) {
        Task task = this.database.markCompleted(i);
        if (task == null)
            throw new InvalidArgumentException("Invalid index!\n" + currentSizeMessage());
        return new Response("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Removes the task in the given index from the list, and returns a response on
     * whether the operation is successful.
     * 
     * @param i index of task to be removed
     * @return response on whether it is succcessful
     */
    public Response deleteWithResponse(int i) {
        Task task = this.database.removeTask(i);
        if (task == null)
            throw new InvalidArgumentException("Invalid index!\n" + currentSizeMessage());
        return new Response("Noted. I've removed this task:\n" + task + "\n" + currentSizeMessage());
    }

    private String currentSizeMessage() {
        int size = this.database.getTasksList().size();
        String unit = size == 1 ? "task" : "tasks";
        return "You now have " + size + " " + unit + " in the list!";
    }

}
