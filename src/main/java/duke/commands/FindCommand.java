package duke.commands;

import duke.exception.DukeException.EmptyKeywordException;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents the class that executes the command "Find". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class FindCommand extends Command {
    protected static final String USAGE = "find <keyword>\n\n";
    private String message;

    /**
     * Constructs the instance of a FindCommand.
     *
     * @param description description of a FindCommand as inputted by the user
     */
    public FindCommand(String description) throws EmptyKeywordException {
        if (description.length() == 0) {
            throw new EmptyKeywordException();
        }
        ArrayList<TaskList> taskListArrayList = TaskList.findMatching(description);
        int index = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (TaskList t : taskListArrayList) {
            stringBuilder.append(index).append(". ").append(t).append("\n");
            index++;
        }
        message = "\nHere are the tasks matching \""
                + description
                + "\" in your list:\n--------------------\n"
                + stringBuilder;
    }

    /**
     * Retrieves the message stored after execution.
     *
     * @return message stored after execution
     */
    @Override
    public String getMessage() {
        return message;
    }
}
