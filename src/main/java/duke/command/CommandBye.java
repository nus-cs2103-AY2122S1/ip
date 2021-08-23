package duke.command;

import duke.exception.DukeFileException;
import duke.DukeStorage;
import duke.DukeUi;
import duke.task.TaskList;

public class CommandBye extends DukeCommand {
    boolean isEnd = true;

    @Override
    public void execute(TaskList tl) {
        try {
            DukeStorage.saveTaskList(tl);
            DukeUi.sayBye();
        } catch (DukeFileException e) {
            DukeUi.printLine(e.getMessage());
            this.isEnd = false;
        }
    }

    @Override
    public boolean isExit() {
        return isEnd;
    }
}
