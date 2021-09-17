package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * finding tasks with keywords
 */
public class FindCommand extends Command {

    private String commandString;

    public FindCommand(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandArr = commandString.split(" ");
        boolean anyMatchFound = false;
        int numberOfMatches = 0;

        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            boolean currentTaskHasBeenPrinted = false;

            // loop again for each keyword in input
            for (int word = 1; word < commandArr.length; word++) {
                boolean matches = taskList.getTask(i).getName().toLowerCase().contains(commandArr[word].toLowerCase());
                if (matches) {
                    if (!anyMatchFound) {
                        ui.printResponse("Here are the matching tasks:");
                    }
                    anyMatchFound = true;
                    numberOfMatches++;
                    if (!currentTaskHasBeenPrinted) {
                        ui.printResponse((numberOfMatches) + "." + taskList.getTask(i).toString());
                        currentTaskHasBeenPrinted = true;
                    }

                }
            }
        }
        if (!anyMatchFound) {
            ui.printResponse("Sorry, we are unable to find any matching tasks");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
