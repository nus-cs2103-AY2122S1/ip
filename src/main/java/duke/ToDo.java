package duke;

public class ToDo extends Task{
    public ToDo(String todo) {
        super(todo, "T");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getTaskSymbol(), super.toString());
    }
}
