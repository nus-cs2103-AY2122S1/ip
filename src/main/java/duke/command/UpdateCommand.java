package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.UpdateException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateCommand extends Command {
    private final String input;

    public UpdateCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Pattern pattern = Pattern.compile("^update (\\d+) (.*?)$");
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            int index = Integer.parseInt(m.group(1));
            Task task = Task.createTaskFromInput(m.group(2));
            taskList.update(index - 1, task);
            return ui.getUpdateMessage(index, task);
        } else {
            throw new UpdateException();
        }
    }
}
