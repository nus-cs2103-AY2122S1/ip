package duke;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) throws FileNotFoundException, IOException, DukeException {
        this.storage = storage;
        this.tasks = this.storage.getTasksFromFile();
    }

    /**
     * @param task
     * @throws DukeException
     */
    public void add(Task task) throws DukeException {
        this.tasks.add(task);
        this.storage.saveToFile(tasks);
    }

    /**
     * @param idx
     * @return Task
     */
    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * @return int
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * @return boolean
     */
    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     *
     * @param idx
     */
    public void setDone(int idx) throws DukeException {
        tasks.get(idx).setDone(true);
        storage.saveToFile(tasks);
    }

    /**
     * @param idx
     * @throws DukeException
     */
    public void remove(int idx) throws DukeException {
        this.tasks.remove(idx);
        this.storage.saveToFile(tasks);
    }

    /**
     * Returns String of filtered tasks that match the input query.
     * @param input Query to be searched.
     * @return String result of filtered tasks to be printed.
     * @throws DukeException If the input string is empty.
     */
    public String findTasks(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("HOW AM I SUPPOSED TO FIND AN EMPTY STRING YOU FOOL.");
        }
        List<Task> filteredTasks = this.tasks.stream().filter(task -> task.toString().contains(input.trim()))
                .collect(Collectors.toList());
        if (filteredTasks.isEmpty()) {
            throw new DukeException("THERE ARE NO MATCHES");
        }

        StringBuilder output = new StringBuilder("THESE ARE THE TASKS THAT MATCH YOUR PLEA:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            output.append(String.format(" %s. %s\n", i + 1, filteredTasks.get(i)));
        }
        return output.toString();
    }
}