package duke.command;

import duke.*;
import duke.exception.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {

    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Pattern pattern = Pattern.compile("^delete (\\d+) *?$");
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            Task deleted = taskList.deleteFromList(Integer.parseInt(m.group(1)));
            return ui.getDeleteMessage(deleted, taskList);
        } else {
            throw new DukeException();
        }
    }
}