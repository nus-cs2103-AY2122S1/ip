package stubs;

import petal.components.Storage;
import petal.components.TaskList;

public class StorageStub extends Storage {

    public StorageStub(TaskList taskList) {
        super(taskList);
    }

    @Override
    public void saveTasks() {
        System.out.println("You're leaving :( I hope you return soon!");
    }
}
