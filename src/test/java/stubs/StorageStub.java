package stubs;

import petal.components.Storage;
import petal.components.Ui;

public class StorageStub extends Storage {

    public StorageStub(TaskListStub taskListStub, Ui ui) {
        super(taskListStub, ui);
    }

    @Override
    public void saveTasks() {
    }
}
