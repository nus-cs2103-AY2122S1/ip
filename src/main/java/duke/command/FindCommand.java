package duke.command;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The FindCommand class encapsulates information
 * and methods pertaining to "find" command in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class FindCommand extends Command {
    private String pattern;

    /**
     * Creates and initalizes a new FindCommand with the given pattern.
     *
     * @param pattern The pattern used to find matching tasks.
     * @return A new MarkDoneCommand object.
     */
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Executes this Command and prints appropriate responses.
     * <p>
     * The FindCommand searching for tasks with the specified pattern as a substring.
     *
     * @param taskList the TaskList object of Duke.
     * @param ui the UI handler of Duke.
     * @param storage the Storage handler of Duke.
     * @throws IOException on failed loading of Storage files.
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        List<Task> matches = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            String description = task.getDescription();
            int n = description.length();
            int m = pattern.length();
            if (isSubSequence(pattern, description, m, n)) {
                matches.add(task);
            }
        }
        if (matches.isEmpty()) {
            ui.add("Sorry, there are no matches for tasks with \"" + pattern + "\".");
        } else {
            ui.add("Here are the matching tasks in your list:");
            int counter = 1;
            for (Task task : matches) {
                ui.add("" + counter + "." + task);
                counter++;
            }
        }
        this.setOutput(ui.getOutput());
        storage.saveTasks(taskList.getTasks());
    }

    /**
     * A recurssive method to check if a pattern is a sub-sequence of a description string.
     * 
     * @param pattern The pattern string to be checked.
     * @param description The descriptoin string to be check against.
     * @param m The length of the pattern string.
     * @param n The length of the description string.
     * @return Returns true if pattern is a subsequence of description.
     */
    private boolean isSubSequence(String pattern, String description, int m, int n) {
        if (m == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        if (pattern.charAt(m - 1) == description.charAt(n - 1)) {
            return isSubSequence(pattern, description, m - 1, n - 1);
        } else {
            return isSubSequence(pattern, description, m, n - 1);
        }
    }
}
