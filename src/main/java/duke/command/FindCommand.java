package duke.command;

import duke.*;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand extends Command {

    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Pattern pattern = Pattern.compile("^find (.+)$");
        Matcher m = pattern.matcher(input);

        if (m.find()) {
            ArrayList<Task> matchList = taskList.findMatches(m.group(1));
            return ui.getFindMessage(matchList);
        } else {
            throw new DukeException();
        }
    }
}
