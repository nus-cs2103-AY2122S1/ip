package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    String taskNumber;
    public DeleteCommand(String command) {
        this.taskNumber = command;
    }

    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        Integer number = Integer.valueOf(this.taskNumber);
        ArrayList<Task> originalTaskList = tasks.getTasks();
        Task task = tasks.getTask(number - 1);
        tasks.getTasks().remove(number - 1);
        storage.rewriteFile(tasks.getTasks(), notes.getNotes());
        return ui.respondToDelete(tasks.getTasks(), task);
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
