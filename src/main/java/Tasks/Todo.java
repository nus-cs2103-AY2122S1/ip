package Tasks;

import Tasks.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        return "[T] " + super.printTask();
    }
}
