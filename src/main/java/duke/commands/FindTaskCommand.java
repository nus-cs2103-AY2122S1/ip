package duke.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import duke.Duke;
import duke.task.Task;
import duke.ui.UserInput;

class FindTaskCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("find", "search", "/"));
    private static final String HELP_MESSAGE = "Here are the matching tasks in your list:\n%s";
    private static final String NO_RESULTS_MESSAGE = "No tasks found matching \"%s\"!";

    @Override
    public void run(Duke duke, UserInput input) {
        assert input != null;
        String keyword = input.getArgs();
        List<Task> results = duke.getTaskList().findByKeyword(keyword);

        if (results.size() == 0) {
            duke.say(String.format(NO_RESULTS_MESSAGE, keyword));
            return;
        }

        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            resultString.append(String.format("%d. %s\n", i + 1, results.get(i).toString()));
        }
        duke.say(String.format(HELP_MESSAGE, resultString));
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
