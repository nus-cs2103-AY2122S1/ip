package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a List Command which includes information about
 * how it should be executed
 */

public class ListCommand extends Command {

    /**
     * Constructor of a List Command
     */

    public ListCommand() {
    }


    /**
     * Executes the List Command by printing list of tasks in tasklist
     *
     * @param tasklist Tasklist containing list of Tasks
     * @param ui Ui that handles what is shown to the user
     * @param storage Storage that handles writing and reading save file
     * @throws NoSuchTaskException If not all the tasks can be retrieved properly
     */

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.showListMessage();
        for (int i = 1; i <= tasklist.getTasklistSize(); i++){
            System.out.println(String.valueOf(i) + ". " + tasklist.getTask(i-1));
        }
    }
}