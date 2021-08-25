package duke;

import java.io.IOException;

/**
 * This class parses and stores a command from the user.
 *
 * @author Chen Yanyu
 */

abstract class Command {
    private Action action;
    private String argument;

    public Command(Action action, String argument) {
        this.action = action;
        this.argument = argument;
    }

    /**
     * abstract method that executes the respective command.
     *
     * @param tasks the TaskList object that is used to interact with the tasks.
     * @param ui the ui object used to interact with the user
     * @param storage the storage object used to read/write to files.
     * @throws DukeException any exception thrown when interacting with Duke.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * getter for the Action.
     *
     * @return the String representing action
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * getter for the Argument.
     *
     * @return the String representing the argument
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * @return the true if the action is BYE;
     */
    public boolean isExit() {
        return action.equals(Action.BYE);
    }
}

class ExitCommand extends Command {
    public ExitCommand() {
        super(Action.BYE, "");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.save(storage);
            Ui.showMessage("Bye! Hope to see you again soon!");
        } catch (IOException e) {
            Ui.showError(e.getMessage());
            Ui.showError("error while saving the tasks!");
        }
    }
}

class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        super(Action.DELETE, "");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.remove(this.index - 1);
        } catch (NumberFormatException e) {
            throw new WrongFormatException("delete <index for the task>");
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexException();
        }
    }
}

class ListCommand extends Command {
    public ListCommand() {
        super(Action.LIST, "");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Ui.showMessage(tasks.toString());
    }
}

class DoneCommand extends Command {
    private final int index;
    public DoneCommand(int index) {
        super(Action.DONE, "");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.setDone(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexException();
        }
    }
}

class AddCommand extends Command {
    private final Task task;
    public AddCommand(Action action, Task task) {
        super(action, "");
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
    }
}

class FindCommand extends Command {
    public FindCommand(String searchString) {
        super(Action.FIND, searchString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.filter(super.getArgument());
    }
}
