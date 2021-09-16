package duke.command;

import java.util.ArrayList;

import duke.exception.NoActionException;
import duke.task.Task;
import duke.util.Reply;
import duke.util.Storage;
import duke.util.TaskList;

public class FindCommand extends Command {
    private final String searchTerms;

    /**
     * Constructor for the find command.
     *
     * @param searchTerms Word to search for.
     */
    public FindCommand(String searchTerms) {
        super(false);
        this.searchTerms = searchTerms;
    }

    /**
     * Finds the tasks based on the search terms provided by user
     *
     * @param tasks List of existing tasks
     * @param storage Storage class handling the persistence of the tasks
     * @return CommandResult of the encapsulating the effects of the command after it completes
     * @throws NoActionException Thrown if no action is provided
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws NoActionException {
        assert tasks != null;
        assert storage != null;
        if (this.searchTerms.length() == 0) {
            throw new NoActionException("Command 'find' requires search terms to be provided.");
        }
        ArrayList<Task> matchList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.contains(searchTerms)) {
                matchList.add(currTask);
                continue;
            }
            if (currTask.getTag().contains(searchTerms)) {
                matchList.add(currTask);
            }
        }
        return new CommandResult(Reply.showMatchingTasks(new TaskList(matchList)),
                true, super.isExit());
    }

}
