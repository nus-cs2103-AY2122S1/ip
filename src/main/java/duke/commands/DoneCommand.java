package duke.commands;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Command that marks task.
 */
public class DoneCommand extends Command {
    private int doneIndex;

    /**
     * Constructor for DoneCommand
     *
     * @param desc
     * @param doneIndex
     */
    public DoneCommand(String desc, int doneIndex) {
        super(desc);
        this.doneIndex = doneIndex;
    }

    /**
     * Returns if the command is the exit command.
     *
     * @return false since this command is not the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks   the task list.
     * @param storage the storage of the programme.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.get(doneIndex - 1).markAsDone();
        System.out.println("-------------------------------------");
        System.out.println("Very well, Master Wayne. This task has been marked as per your request.");
        System.out.println((doneIndex) + ". " + tasks.get(doneIndex - 1)); //actual index is index - 1
        System.out.println("-------------------------------------");

        try {
            storage.markAsDone(doneIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
