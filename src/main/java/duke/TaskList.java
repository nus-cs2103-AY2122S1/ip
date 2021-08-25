package duke;

import java.util.*;

public class TaskList {
    List<Task> taskList;

    /**
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     *
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     *
     * @param input
     * @return
     */
    public Task addItem(String input) {
        Task t;

        if (input.contains("todo")) {
            String description = input.substring(input.indexOf(' ') + 1);
            t = new ToDo(description);
        } else {
            String description = input.substring(input.indexOf(' ') + 1, input.lastIndexOf('/') - 1);
            String time = input.substring(input.lastIndexOf("/") + 4);
            if (input.contains("deadline")) {
                t = new Deadline(description, time);
            } else {
                t = new Event(description, time);
            }
        }

        taskList.add(t);

        return t;
    }

    /**
     *
     * @param index
     * @return
     */
    public Task deleteItem(int index) {
        Task t = getTask(index);
        taskList.remove(index);

        return t;
    }

    /**
     *
     * @param index
     * @return
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
    
    public int getSize() { return taskList.size(); }

    /**
     *
     * @param keyword
     * @return
     */
    public List<Task> find (String keyword) {
        List<Task> results = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = getTask(i);
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }

        return results;
    }

    /**
     *
     * @param index
     */
    public void markAsDone(int index) {
        Task t = getTask(index);
        t.setDone();
    }
}
