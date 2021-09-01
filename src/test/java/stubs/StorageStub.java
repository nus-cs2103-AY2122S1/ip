package stubs;

import petal.components.Storage;

public class StorageStub extends Storage {

    public StorageStub(TaskListStub taskListStub) {
        super(taskListStub);
    }

    @Override
    public void saveTasks() {
    }
}
