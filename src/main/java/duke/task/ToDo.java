package duke.task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }
    
    private ToDo(ToDo oldTask) {
        super(oldTask);
    }
    
    public static ToDo createTask(String name, boolean isCompleted) {
        ToDo t = new ToDo(name);
        if (isCompleted) {
            return new ToDo(t);
        } else {
            return t;
        }
    } 

    @Override
    public ToDo markAsCompleted() {
        return new ToDo(this);
    }

    @Override
    public String toString() {
        return "T: " + super.toString();
    }
}
