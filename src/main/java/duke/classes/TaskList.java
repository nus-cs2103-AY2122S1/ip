package duke.classes;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public int last() {
        return taskList.size() - 1;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void completeTask(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Filters taskList for tasks containing input filter in the description
     * @param filter String that contains the word(s) that the function filters by
     * @return Returns List of Tasks that contain tasks with the filter word(s) in their description
     */
    public List<Task> filter(String filter) {
        return taskList.stream().filter(task -> task.descContains(filter))
                                .collect(Collectors.toList());
    }
}
