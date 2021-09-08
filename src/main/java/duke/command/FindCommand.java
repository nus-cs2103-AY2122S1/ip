package duke.command;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    /**
     * Regex pattern for finding find commands.
     */
    private static final Pattern PATTERN_FIND = Pattern.compile("^find (.*)$");

    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) {

        // Check if user is attempting to find a task
        Matcher matcher = PATTERN_FIND.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Find tasks like this: find <search string>");
        }

        String prompt = matcher.group(1);

        List<TaskList.FindResult> results = tasks.find(prompt);

        return Ui.notifyFindResults(results);
    }
}
