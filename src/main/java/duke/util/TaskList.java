package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import javax.print.attribute.HashPrintServiceAttributeSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * The class that stores and modifies Tasks of the software.
 */
public class TaskList {
    private TreeSet<Task> tasks;

    /**
     * Constructor of TaskList.
     * It instantiates the TaskList object with initial Tasks passed.
     *
     * @param taskList Initial tasks to be stored in TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.tasks = new TreeSet<>(taskList);
    }


    /**
     * Constructor of TaskList.
     * It instantiates an empty TaskList object.
     */
    public TaskList() {
        tasks = new TreeSet<>();
    }


    /**
     * Add task to the task list.
     *
     * @param task The task to add.
     * @return String of messages to be printed by Ui object after adding.
     */
    public String addTask(Task task) {
        assert task != null;

        tasks.add(task);

        StringBuilder msg = new StringBuilder();
        msg.append("Got it. I've added this task:\n");
        msg.append("\t" + task + "\n");
        msg.append("Now you have " + tasks.size() + " tasks in the list.\n");

        return msg.toString();
    }


    /**
     * Delete task at the specified index.
     *
     * @param idx The index of the task to be deleted.
     * @return String of messages to be printed by Ui object after deleting.
     * @throws DukeException When the index is invalid.
     */
    public String deleteTask(int idx) throws DukeException {
        try {
            Task[] taskArr = tasks.toArray(new Task[0]);
            Task curr = taskArr[idx - 1];
            tasks.remove(curr);

            StringBuilder msg = new StringBuilder();
            msg.append("Noted. I've removed this task:\n");
            msg.append("\t" + curr + "\n");
            msg.append("Now you have " + tasks.size() + " tasks in the list.\n");

            return msg.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index is invalid!");
        }
    }


    /**
     * Mark the task at the specified index as done.
     *
     * @param idx The index of the task to be marked.
     * @return String of messages to be printed by Ui object after marking.
     * @throws DukeException When the index is invalid.
     */
    public String markTaskDone(int idx) throws DukeException {
        try {
            Task[] taskArr = tasks.toArray(new Task[0]);
            Task curr = taskArr[idx - 1];
            curr.markDone();

            StringBuilder msg = new StringBuilder();
            msg.append("Nice! I've marked this task as done:\n");
            msg.append("\t" + curr + "\n");

            return msg.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index is invalid!");
        }
    }


    /**
     * Search and return a TaskList containing all tasks with keyword.
     *
     * @param keyword The keyword used to match tasks.
     * @return A TaskList containing all tasks with keyword.
     */
    public TaskList find(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                matchedTasks.add(task);
            }
        }

        return new TaskList(matchedTasks);
    }


    public boolean isEmpty() {
        return tasks.isEmpty();
    }


    /**
     * String representation of a TaskList.
     *
     * @return String representation of the TaskList.
     */
    public String toString() {
        Task[] taskArr = tasks.toArray(new Task[0]);

        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= tasks.size() - 1; i++) {
            str.append(String.format("%d.%s\n", i, taskArr[i - 1]));
        }
        str.append(String.format("%d.%s", tasks.size(), taskArr[taskArr.length - 1]));
        return str.toString();
    }


    /**
     * Encodes and Saves current content of TaskList to
     * the file that associated with the Storage object.
     *
     * @param storage The Storage object that handles saving.
     * @throws IOException When I/O system goes wrong.
     */
    public void saveToFile(Storage storage) throws IOException {
        assert storage != null;

        String encoded = Parser.encode(tasks);
        storage.save(encoded);
    }


    /**
     * Loads and decodes content from the file associated
     * with the Storage object.
     *
     * @param storage The Storage object that handles loading.
     * @throws FileNotFoundException When the file does not exist.
     */
    public void loadFromFile(Storage storage) throws FileNotFoundException, DukeException {
        assert storage != null;

        TreeSet<Task> decodedTasks = Parser.decode(storage.load());
        tasks = decodedTasks;
    }
}
