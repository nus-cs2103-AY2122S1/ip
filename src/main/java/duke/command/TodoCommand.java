package duke.command;

import duke.task.Todo;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class TodoCommand extends Command {
    protected Todo todo;
    public static final String INSTRUCTION_TODO = "todo";

    /**
     * Class constructor for TodoCommand Class specifying parameter_1
     */
    public TodoCommand(String parameter_1) throws DukeException {
        if (parameter_1.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        todo = new Todo(parameter_1);
    }

    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(this.todo);
        tasks.add(this.todo);
        ui.formatPrint("Got it. I've added this task:", "  " + this.todo.toString(), tasks.toString());
    }

    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_TODO + "] - " + todo.toString();
    }
}
