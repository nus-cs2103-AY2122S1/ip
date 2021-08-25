package duke.task;

public class Todo extends Task {

    public Todo(String name, boolean isCompleted) {
        super(name, TaskType.T, isCompleted);
    }

    @Override
    public Task updateName(String input) {
        return new Todo(input, this.getCompleted());
    }

    @Override
    public Task complete() {
        return new Todo(this.getName(), true);
    }

    @Override
    public String getDetails() {
        String checkbox = "[" + ( getCompleted() ? "X" : " ") + "]";
        return getTaskTypeString() + checkbox + " " + this.getName();
    }

    @Override
    public String getLabel() {
        return this.getName() + "|";
    }
}
