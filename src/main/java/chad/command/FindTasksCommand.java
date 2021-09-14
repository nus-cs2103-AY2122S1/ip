package chad.command;

import java.util.List;
import java.util.Map;

import chad.task.Task;
import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents a "Find Tasks" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class FindTasksCommand extends Command {

    private static final CommandType COMMAND_TYPE = CommandType.FIND_TASKS;

    private String queryTaskDescription;

    /**
     * Creates a FindTasksCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public FindTasksCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException {
        assert queryTaskDescription != null : "Query cannot be null.";
        List<Map.Entry<Integer, Task>> queryResults = taskHandler.findTasksDescribedBy(queryTaskDescription);
        int n = queryResults.size();
        switch (n) {
        case 0:
            ui.startMessage()
                    .addLine("No matching tasks were found.")
                    .displayMessage();
            break;
        case 1:
            ui.startMessage()
                    .addLine("Here is the 1 matching task in your list:")
                    .addFindTasksResultsList(queryResults)
                    .displayMessage();
            break;
        default:
            ui.startMessage()
                    .addLine(String.format("Here are the %d matching tasks in your list:", n))
                    .addFindTasksResultsList(queryResults)
                    .displayMessage();
            break;
        }
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        String queryTaskDescription = parseQueryTaskDescription(tokens);
        checkQueryTaskDescriptionLength(queryTaskDescription);
        this.queryTaskDescription = queryTaskDescription;
    }

    private void checkQueryTaskDescriptionLength(String queryTaskDescription) throws ChadInvalidCommandException {
        if (queryTaskDescription.length() == 0) {
            throw new ChadInvalidCommandException(String.format("A query is required for \"%s\" commands.",
                    getCommandType().getCommandDescription()));
        }
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    private String parseQueryTaskDescription(String[] tokens) {
        return getTokenSequence(tokens, 1, tokens.length);
    }
}
