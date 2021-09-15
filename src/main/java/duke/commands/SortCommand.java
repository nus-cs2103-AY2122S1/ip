package duke.commands;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidActionException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * The {@code SortCommand} class extends from {@code Command}, which
 * sorts the {@code Task}s in the {@code TaskList}.
 */
public class SortCommand extends Command {

    private static final int OPTION_LIMIT = 6;

    private String arg = "0";

    /**
     * Options for sorting.
     */
    public enum SortOptions {
        LEXICOGRAPHIC_ASCENDING("Lexicographic (ascending)"),
        LEXICOGRAPHIC_DESCENDING("Lexicographic (descending)"),
        TASK_TYPE("Task type"),
        TASK_STATUS("Task status (Not done first)"),
        TASK_STATUS_DESCENDING("Task status (Done first)"),
        TASK_TIME("Task time (ascending)"),
        TASK_TIME_DESCENDING("Task time (descending)");

        private String name;

        SortOptions(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private SortOptions sortType = SortOptions.LEXICOGRAPHIC_ASCENDING;

    /**
     * {@code String[] args} will either be {@code sort} (no sort option specified) or {@code sort <int>}.
     */
    public SortCommand(String... args) {
        if (args[0].length() > 5) {
            this.arg = args[0].split(" ")[1];
        }
    }

    /**
     * Sorts the {@code TaskList}.
     *
     * @return {@code String} of sorted {@code TaskList}.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AuguryException {

        try {
            setSortTypeAccordingToArg(Integer.parseInt(this.arg));
        } catch (NumberFormatException | InvalidActionException e) {
            throw new InvalidActionException("Invalid argument! "
                    + "Please enter an option from 0-" + OPTION_LIMIT
                    + " for the sort command.");
        }

        tasks.sort(sortType);
        storage.saveTaskListToStorage(tasks);

        String res = "I've sorted your list with method <" + sortType.toString() + ">\n\n\t";
        return res + tasks.toString();
    }

    private void setSortTypeAccordingToArg(int argument) throws InvalidActionException {
        switch(argument) {
        case 0:
            this.sortType = SortOptions.LEXICOGRAPHIC_ASCENDING;
            break;
        case 1:
            this.sortType = SortOptions.LEXICOGRAPHIC_DESCENDING;
            break;
        case 2:
            this.sortType = SortOptions.TASK_TYPE;
            break;
        case 3:
            this.sortType = SortOptions.TASK_STATUS;
            break;
        case 4:
            this.sortType = SortOptions.TASK_STATUS_DESCENDING;
            break;
        case 5:
            this.sortType = SortOptions.TASK_TIME;
            break;
        case 6:
            this.sortType = SortOptions.TASK_TIME_DESCENDING;
            break;
        default:
            throw new InvalidActionException("Must be 0 - " + OPTION_LIMIT);
        }
    }
}
