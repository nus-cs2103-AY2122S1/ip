package duke.tasks;

import duke.main.StorageElement;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.taskIcon = "T";
    }

    public Todo(StorageElement storageElement) {
        super(storageElement);
    }

    public String toString() {
        return "[" + this.taskIcon + "]" + super.toString();
    }

    @Override
    public StorageElement getStorageElement() {
        return new StorageElement(this.taskIcon,
                this.isDone,
                this.description);
    }
}
