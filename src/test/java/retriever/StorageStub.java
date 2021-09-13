package retriever;

import java.util.ArrayList;

import retriever.task.Task;

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
