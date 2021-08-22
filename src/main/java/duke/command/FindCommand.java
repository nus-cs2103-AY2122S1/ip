package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Commands;
import duke.task.Task;

public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    private void printTaskMatchingSearch(TaskList tasks, Ui ui) throws DukeException {
        // Preliminary check for validity of user input.
        Parser.checkInputValidity(this.userInput, Commands.FIND.getCommand(), Ui.exceptionMissingSearchInput());

        // Initalize counter to track number of matching tasks.
        int counter = 0;

        // Extract search keyword from 1 space after "find" command in user input.
        String keyword = this.userInput.substring(Commands.FIND.getLength() + 1);

        // Print standard response for search begin to user.
        ui.showFindBegin();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            // contains() performs case-sensitive search for keyword in task description.
            if (task.getDescription().contains(keyword)) {
                // Add 1 as display index is 1-based while TaskList index is 0-based.
                int idx = i + 1;
                System.out.println(idx + "." + task);
                counter++;
            }
        }

        // Print standard response for search success to user.
        ui.showFindSuccess(counter, keyword);

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            printTaskMatchingSearch(tasks, ui);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }


}
