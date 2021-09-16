package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            if(tasks.getTask(i).getDescription().contains(command)) {
                foundTasks.add(tasks.getTask(i));
            }
        }
        return ui.respondToFind(foundTasks);
    }

    /**@Override
    public String execute(NotesList notes, Ui ui, Storage storage) {
        return "";
    };*/

    /**@Override
    public Boolean isTaskRelatedCommand() {
        return true;
    };*/

    @Override
    public Boolean isExit() {
        return false;
    }
}
