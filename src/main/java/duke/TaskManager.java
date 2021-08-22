package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskManager {
    private List<Task> taskList;
    private final Storage storage = new Storage();

    TaskManager() throws DateTimeException {
        load();
    }

    public void addTask(Task task) {
        taskList.add(task);
        save();
    }

    public Task deleteTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            Task ret = taskList.remove(taskId);
            save();
            return ret;
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    public int taskCount() {
        return taskList.size();
    }

    public Task completeTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            taskList.set(taskId, taskList.get(taskId).done());
            save();
            return taskList.get(taskId);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    public String listTasks() {
        return Stream.iterate(0, x -> x < taskList.size(), x -> x+1)
                .map(x -> String.format("%d. %s", x+1, taskList.get(x).toString()))
                .collect(Collectors.joining("\n"));
    }

    private void load() throws DateTimeException {
        try {
            this.taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
