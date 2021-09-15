package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * when the user requests for help.
 */
public class HelpCommand extends Command {

    /**
     * Gives a list of possible commands that user can type.
     * @param taskList TaskList object that is being used in the app
     * @param ui the Ui object that is being used in the app
     * @param storage the Storage object that is being used in the app
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printResponse("Here are the different commands in Duke");
        ui.printResponse("Type 'bye' to exit.");
        ui.printResponse("Type 'list' to view all tasks in your list.");
        ui.printResponse("Type 'delete x' to delete task number x. Eg. 'delete 5'");
        ui.printResponse("Type 'done x' to mark task number x as done. Eg. 'done 5'");
        ui.printResponse("Type 'find wordtofind' to search for tasks that contain the word");
        ui.printResponse("Eg. 'find apple' ");

        ui.printResponse("There are 3 types of tasks: Todo, Event, and Deadline");
        ui.printResponse("To add a Todo task, type 'todo' followed by your task description");
        ui.printResponse("Eg. 'todo read book' ");
        ui.printResponse("To add a Event task, type 'event' followed by your task description, "
                +
                "followed by '/at' followed by your event date");
        ui.printResponse("Eg. 'event finance paper /at 2/12/2019 1800' ");
        ui.printResponse("To add a Deadline task, type 'event' followed by your task description, "
                +
                "followed by '/by' followed by your deadline ");
        ui.printResponse("Eg. 'deadline return book /by 2/12/2019 1800' ");

        ui.printResponse("Type 'help' to see the list of commands available");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
