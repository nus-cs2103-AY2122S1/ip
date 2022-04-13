package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class DoneCommand extends Command{
    private final String fullCommand;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        int doneId = Integer.parseInt(fullCommand.split(" ")[1]);
        return taskList.markAsDone(doneId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoneCommand that = (DoneCommand) o;

        return fullCommand != null ? fullCommand.equals(that.fullCommand) : that.fullCommand == null;
    }
}
