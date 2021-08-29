package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Commands;
import duke.task.Task;

/**
 * Represents a command that can be executed to print tasks with descriptions that match a search keyword.
 */
public class FindCommand extends Command {
    private final String userInput;

    /**
     * Constructor for FindCommand.
     * Creates a FindCommand containing user input.
     *
     * @param userInput User's input into Duke chatbot.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Extracts out a search keyword from user input, then finds tasks and
     * returns String describing found tasks that contain the search keyword.
     * Search keyword is case-sensitive.
     *
     * @param tasks TaskList to perform search on.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @return String describing found tasks that contain the search keyword.
     * @throws DukeException If user input has missing spaces.
     * @throws DukeException If user input has no search keyword.
     */
    private String getTaskMatchingSearch(TaskList tasks, Ui ui) throws DukeException {
        // Preliminary check for validity of user input.
        Parser.checkInputValidity(this.userInput, Commands.FIND.getCommand(), Ui.exceptionMissingSearchInput());

        // Initalize counter to track number of matching tasks.
        int counter = 0;

        // Extract search keyword from 1 space after "find" command in user input.
        String keyword = this.userInput.substring(Commands.FIND.getLength() + 1);

        // String standard response for search begin.
        String begin = ui.getFindBegin();

        StringBuilder foundTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            // contains() performs case-sensitive search for keyword in task description.
            if (task.getDescription().contains(keyword)) {
                // Add 1 as display index is 1-based while TaskList index is 0-based.
                int idx = i + 1;
                String foundTask = idx + "." + task + "\n";
                foundTasks.append(foundTask);
                counter++;
            }
        }

        // String standard response for search success.
        String success = ui.getFindSuccess(counter, keyword);

        return begin + "\n" + foundTasks + success;
    }

    /**
     * Finds tasks and returns String describing found tasks with descriptions that matches search keyword.
     * Accepts user inputs of the form "find keyword" where keyword can be any search keyword.
     * Search keyword can also be just spaces or consisting of multiple words.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     * @return String describing found tasks with descriptions that matches search keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            return getTaskMatchingSearch(tasks, ui);
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Indicates whether another object is equals to this FindCommand.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this FindCommand.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            FindCommand other = (FindCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }


}
