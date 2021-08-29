package duke;

public abstract class InputHandler {
    protected Ui ui;
    protected TaskList taskList;

    public InputHandler(Ui ui, TaskList taskList) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract String handle(String input) throws EmptyDescriptionException;

}
