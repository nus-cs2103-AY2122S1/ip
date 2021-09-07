package duke;

public class ToDo extends Task {
    static final String DONE = "[T][X] ";
    static final String NOT_DONE = "[T][ ] ";
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String printTask() {
        String result = "";
        if (taskComplete) {
            result = DONE;
        } else {
            result = NOT_DONE;
        }
        return result + task;
    }
}
