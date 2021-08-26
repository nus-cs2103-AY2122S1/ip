package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command{

    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

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
