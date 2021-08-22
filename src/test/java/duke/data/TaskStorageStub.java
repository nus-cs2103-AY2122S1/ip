package duke.data;

import duke.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskStorageStub implements TaskStorage {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void saveTasks(List<Task> taskList) throws IOException {
        tasks = taskList;
    }

    @Override
    public List<Task> loadTasks() throws IOException {
        return tasks;
    }
}
