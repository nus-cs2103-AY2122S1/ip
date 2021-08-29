package duke.data;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

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
