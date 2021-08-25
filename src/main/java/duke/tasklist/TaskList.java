package duke.tasklist;

import duke.task.LocalDateTimeOrString;
import duke.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String listTasks() {
        if (taskList.isEmpty()) {
            return getTaskCountDesc();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(String.format("\n%d.%s", i + 1, taskList.get(i).toString()));
        }
        return sb.toString();
    }

    public String getTaskCountDesc() {
        return taskList.isEmpty() ? "There are no tasks."
                : String.format("Now you have %d task%s in the list.", taskList.size(),
                (taskList.size() == 1 ? "" : "s"));
    }

    public void add(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    public Task remove(int indexToDelete) {
        return taskList.remove(indexToDelete);
    }

    public Task markAsDone(int indexToMarkDone) {
        Task task = taskList.get(indexToMarkDone);
        task.markAsDone();
        return task;
    }

    public TaskList filterByDateTimeOrString(LocalDateTimeOrString dateTimeOrString) {
        List<Task> filtered = taskList.stream().filter(x -> x.isAtTime(dateTimeOrString)).collect(Collectors.toList());
        return new TaskList(filtered);
    }
}
