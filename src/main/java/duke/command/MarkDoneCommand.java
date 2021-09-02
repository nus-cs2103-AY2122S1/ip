package duke.command;

import duke.*;
import duke.exception.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkDoneCommand extends Command {

    private final String input;

    public MarkDoneCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Pattern pattern = Pattern.compile("^done (\\d+) *?$");
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            Task toMark = taskList.markAsDone(Integer.parseInt(m.group(1)));
            return ui.getDoneMessage(toMark);
        } else {
            throw new DukeException();
        }
    }
}