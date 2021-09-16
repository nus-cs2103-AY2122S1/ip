package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    String taskNumber;
    public DoneCommand(String command) {
        this.taskNumber = command;
    }

    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        try {
            Integer count = Integer.valueOf(this.taskNumber);
            if(count < 0 || count > tasks.getNumberOfTasks()) {
                return ui.showError("You have entered a index that does not correspond to any task.");
            }
            tasks.getTask(count - 1).markAsDone();
            storage.rewriteFile(tasks.getTasks(), notes.getNotes());
            return ui.respondToDone(tasks.getTasks(), count);
        } catch(NumberFormatException e) {
            return ui.showError("You have entered an invalid input that is not a number.");
        }
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
