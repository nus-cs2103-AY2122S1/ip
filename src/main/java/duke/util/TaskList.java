package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that stores and modifies Tasks of the software.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor of TaskList.
     * It instantiates the TaskList object with initial Tasks passed.
     * @param tasks Initial tasks to be stored in TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor of TaskList.
     * It instantiates an empty TaskList object.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Add task to the task list.
     * @param task The task to add.
     * @return String of messages to be printed by Ui object after adding.
     */
    public String addTask(Task task) {
        StringBuilder msg = new StringBuilder();
        this.tasks.add(task);
        msg.append("Got it. I've added this task:\n");
        msg.append("\t" + task + "\n");
        msg.append("Now you have " + tasks.size() + " tasks in the list.\n");

        return msg.toString();
    }

    /**
     * Delete task at the specified index.
     * @param idx The index of the task to be deleted.
     * @return String of messages to be printed by Ui object after deleting.
     * @throws DukeException When the index is invalid.
     */
    public String deleteTask(int idx) throws DukeException {
        try {
            StringBuilder msg = new StringBuilder();
            Task curr = this.tasks.remove(idx-1);
            msg.append("Noted. I've removed this task:\n");
            msg.append("\t" + curr + "\n");
            msg.append("Now you have " + tasks.size() + " tasks in the list.\n");

            return msg.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("The task index is invalid!");
        }
    }

    /**
     * Mark task at the specified index as done.
     * @param idx The index of the task to be marked.
     * @return String of messages to be printed by Ui object after marking.
     * @throws DukeException When the index is invalid.
     */
    public String markTaskDone(int idx) throws DukeException {
        try {
            StringBuilder msg = new StringBuilder();
            Task curr = this.tasks.get(idx - 1);
            curr.markDone();
            msg.append("Nice! I've marked this task as done:\n");
            msg.append("\t" + curr + "\n");

            return msg.toString();
        } catch(IndexOutOfBoundsException e) {
           throw new DukeException("The task index is invalid!");
        }
    }

    /**
     * String representation of a TaskList.
     * @return String representation of the TaskList.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= this.tasks.size(); i++) {
            str.append(String.format("%d.%s\n", i, tasks.get(i-1)));
        }
        return str.toString();
    }

    /**
     * Encodes and Saves current content of TaskList to
     * the file that associated with the Storage object.
     * @param storage The Storage object that handles saving.
     * @throws IOException When I/O system goes wrong.
     */
    public void saveToFile(Storage storage) throws IOException {
        String encoded = Parser.encode(this.tasks);
        storage.save(encoded);
    }

    /**
     * Loads and decodes content from the file associated
     * with the Storage object.
     * @param storage The Storage object that handles loading.
     * @throws FileNotFoundException When the file does not exist.
     */
    public void loadFromFile(Storage storage) throws FileNotFoundException {
        List<Task> decoded = Parser.decode(storage.load());
        this.tasks = decoded;
    }
}
