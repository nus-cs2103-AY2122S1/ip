package retriever;

import retriever.task.Task;

import java.util.ArrayList;

public class StorageStub extends Storage {
    public StorageStub(String filePath) {
        super(filePath);
    }

    @Override
    public ArrayList<Task> readTasks() {
        return new ArrayList<Task>();
    }

    @Override
    public void writeTask(Task.TaskType task, String taskDescription, String taskDeadline) {
        // Assume we are writing a task to the file
    }

    @Override
    public void deleteTask(int taskNumber) {
        // Assume we are deleting task from file
    }

    @Override
    public void updateTaskStatusToDone(int taskNumber) {
        // Assume we are updating task status in file
    }

    @Override
    public void createNewFile() {
        // Assume we are creating a new file
    }
}
