package duke;

public abstract class Command {
    private String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList t, Storage s);
}
