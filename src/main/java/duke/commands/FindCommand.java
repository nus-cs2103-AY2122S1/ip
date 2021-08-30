package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;


public class FindCommand extends Command {

    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Finds all tasks from the tasklist containing the keyword and reflects the result via the Ui.
     * @throws InvalidInputException if the task does not exist in the list.
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        if (keyWord.equals("") || keyWord.equals(" ")) {
            throw new InvalidInputException("   Keyword cannot be a space or be empty");
        } else {
            ArrayList<Task> matched = task.findMatching(keyWord);
            ui.printFoundTasks(matched, keyWord);
        }
    }

    public boolean isExit() {
        return false;
    }
}
