package duke.data;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskStorageStub implements TaskStorage {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void saveTasks(List<Task> taskList) {
        tasks = taskList;
    }

    @Override
    public List<Task> loadTasks() {
        return tasks;
    }
}
