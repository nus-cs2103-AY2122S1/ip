package duke;

import java.io.IOException;

/**
 * Todo class is a task. The input must be in such a format
 * "todo <todo name>".
 */
public class ToDo extends Task implements GeneralCommand {
    private final String TODO = "[T]";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a todo.
     *
     * @param command Parsed command.
     * @param storage Storage to be updated.
     * @param tasks TaskList of current tasks.
     * @param ui Ui to return String.
     * @throws DukeException If todo command is empty.
     */
    public ToDo(String command, Storage storage, TaskList tasks, Ui ui) throws DukeException {
        super(command.substring(4));
        String description = command.substring(4);
        if (description.isEmpty()) {
            throw new DukeException("todo", "'todo borrow book'");
        }
        this.description = command.substring(5);
        assert description.substring(1).length() > 0 : "Description should be present";
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Constructs a todo.
     *
     * @param description Parsed description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string of the todo.
     *
     * @return String of event.
     */
    @Override
    public String toString() {
        return TODO + this.getStatusIcon() + " " + this.getDescription();
    }

    @Override
    public Task getToggledDone() {
        ToDo toggledTodo = new ToDo(description);
        if (!this.getDone()) {
            toggledTodo.setDone();
        }
        return toggledTodo;
    }

    /**
     * Checks to see if two todos are equal in description and status.
     * Returns false if object is not equal to this todo.
     *
     * @param object Object compared to.
     * @return Boolean true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof ToDo) {
            ToDo toDo = (ToDo) object;
            return toDo.getDone() == this.getDone() && toDo.description.equals(this.description);
        }
        return false;
    }

    /**
     * Executes ToDo and returns a String to be printed.
     *
     * @return String to be printed on Gui.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    @Override
    public String execute() throws IOException {
        tasks.add(this);
        storage.save(tasks);
        return ui.taskMessageToString(this, tasks);
    }
}
