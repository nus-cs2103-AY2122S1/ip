package Tasks;

import java.io.File;

public class Todo extends Task {
    public Todo(String taskDetails) {
        super(taskDetails);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String dataToString() {
        return String.format("T | %s", super.dataToString());
    }
}
