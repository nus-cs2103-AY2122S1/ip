package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;

public class FindCommand implements Command {
    private String word;

    /**
     * Initialises the FindCommand.
     *
     * @param description the user input
     * @throws DukeException if user passes in more than one keyword
     */
    public FindCommand(String description) throws DukeException {
        String[] wordArray = description.split(" ");
        if (wordArray.length > 1) {
            throw new DukeException("With my magic, I can only find using ONE keyword :(");
        }
        this.word = wordArray[0];
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.findTasks(word);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
