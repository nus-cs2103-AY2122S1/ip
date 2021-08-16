package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

/**
 * Encapsulation of a task list in Duke, able to provide relevant responses on
 * its manipulation.
 */
public class DukeList {
    private final List<Task> list;

    public DukeList() {
        this.list = new ArrayList<>();
    }

    /**
     * Add a task to the list and return a response on the added task.
     * 
     * @param task to be added to the list
     * @return response to the addition
     */
    public Response addWithResponse(Task task) {
        this.list.add(task);
        return new Response("Got it. I've added this task:\n" + task + "\n" + currentSizeMessage());
    }

    /**
     * Return a response containing an iteration of all current tasks.
     * 
     * @return response to be printed
     */
    public Response currentListResponse() {
        String message = IntStream.range(0, this.list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i))
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
        this.testInRange(i);

        Task task = this.list.get(i - 1);
        if (task.isCompleted())
            return new Response("Bruh I've already marked it as completed!\n" + task);
        task.markCompleted();
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
        this.testInRange(i);

        Task task = this.list.remove(i - 1);
        return new Response("Noted. I've removed this task:\n" + task + "\n" + currentSizeMessage());
    }

    private String currentSizeMessage() {
        int size = this.list.size();
        String unit = size == 1 ? "task" : "tasks";
        return "You now have " + this.list.size() + " " + unit + " in the list!";
    }

    private void testInRange(int i) {
        if (i < 1 || i > this.list.size())
            throw new InvalidArgumentException("Invalid index!\n" + currentSizeMessage());
    }

}
