package duke;

import java.io.IOException;

/**
 * This class parses and stores a command from the user.
 *
 * @author Chen Yanyu
 */

abstract class Command {
    private final Action action;
    private final String argument;

    public Command(Action action, String argument) {
        this.action = action;
        this.argument = argument;
    }

    /**
     * The abstract method that executes the respective command.
     *
     * @param tasks   the TaskList object that is used to interact with the tasks.
     * @param storage the storage object used to read/write to files.
     * @throws DukeException any exception thrown when interacting with Duke.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Getter for the Action.
     *
     * @return the String representing action
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * Getter for the Argument.
     *
     * @return the String representing the argument
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * This is unused at the moment.
     *
     * @return True if the action is BYE;
     */
    public boolean isExit() {
        return action.equals(Action.SAVE);
    }
}

class SaveCommand extends Command {
    public SaveCommand() {
        super(Action.SAVE, "");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            tasks.save(storage);
            return "Tasks saved successfully!";
        } catch (IOException e) {
            return e.getMessage() + "\nerror while saving the tasks!";
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
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String response = tasks.remove(this.index - 1);
            new SaveCommand().execute(tasks, storage);
            return response;
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
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.toString();
    }
}

class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super(Action.DONE, "");
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String response = tasks.setDone(index - 1);
            new SaveCommand().execute(tasks, storage);
            return response;
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
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String response = tasks.add(task);
        new SaveCommand().execute(tasks, storage);
        return response;
    }
}

class FindCommand extends Command {
    public FindCommand(String searchString) {
        super(Action.FIND, searchString);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.filter(super.getArgument());
    }
}

class HelpCommand extends Command {
    public HelpCommand() {
        super(Action.HELP, "");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "list - list all current tasks.\n\n"
                + "todo <description> - add a todo task.\n\n"
                + "deadline <description> /by <yyyy-mm-dd> - add a deadline task.\n\n"
                + "event <description> /at <yyyy-mm-dd> - add a event task.\n\n"
                + "delete <index> - delete the task at the index.\n\n"
                + "done <index> - mark the task at the index as done.\n\n"
                + "find <search string> - search the task description that matches the string.\n\n"
                + "save - save the task to /data/duke.txt\n\n"
                + "Press UP/DOWN to navigate your input history.";
    }
}

class ClearCommand extends Command {
    public ClearCommand() {
        super(Action.CLEAR, "");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String response = tasks.clear();
        new SaveCommand().execute(tasks, storage);
        return response;
    }
}
