package duke.data;

import duke.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    List<Task> taskData = null;

    public TaskList() {
        this.taskData = new ArrayList<>();
    }

    public TaskList(List<Task> taskData) {
        this.taskData = taskData;
    }

    public void add(Task task) {
        this.taskData.add(task);
    }

    public Task remove(int index) {
        return this.taskData.remove(index);
    }

    public Task get(int index) {
        return this.taskData.get(index);
    }

    public boolean isEmpty() {
        return this.taskData.isEmpty();
    }

    public int size() {
        return this.taskData.size();
    }

    public TaskList filterDate(LocalDate queryDate) {
        return new TaskList(this.taskData.stream().filter(x -> x.hasDueDate(queryDate))
                .collect(Collectors.toList()));
    }

}
