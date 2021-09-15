package duke;

public abstract class InputHandler {
    protected Ui ui;
    protected TaskList taskList;

    public InputHandler(Ui ui, TaskList taskList) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Abstract method to handle various user inputs.
     *
     * @param input
     * @return
     * @throws EmptyDescriptionException
     */
    public abstract String handle(String input) throws EmptyDescriptionException;

}
