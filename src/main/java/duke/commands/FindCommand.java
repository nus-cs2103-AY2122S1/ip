package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates Find command's operation
 */
public class FindCommand extends Command {
    private String filter;

    /**
     * Constructor for FindCommand
     *
     * @param rest the user input after the command
     * @throws EmptyDescriptionException if input is empty
     */
    public FindCommand(String rest) throws EmptyDescriptionException {
        if (rest.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.filter = rest.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList filteredList = tasks.filter(task -> task.getDescription().toLowerCase().contains(filter));
        return ui.print(String.format(
                "Here are the matching tasks in your list:%s",
                filteredList.printList()));
    }
}
