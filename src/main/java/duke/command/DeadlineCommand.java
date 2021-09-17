package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The DeadlineCommand class encapsulates the execution of the deadline command from the user.
 */
public class DeadlineCommand extends Command {

    /** The deadline command description inputted by the user. */
    private String deadlineDescription;

    /**
     * Constructor to initialise a DeadlineCommand.
     *
     * @param deadlineDescription The deadline command description inputted by the user.
     */
    public DeadlineCommand(String deadlineDescription) {
        this.deadlineDescription = deadlineDescription;
    }

    /**
     * Executes the response to the deadline command from the user.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     * @return The String to be printed in the Duke GUI.
     * @throws IOException If there is an exception relating to the input and output.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {

        int pos = deadlineDescription.indexOf("/");
        String taskDescription = deadlineDescription.substring(0, pos - 1);
        String dueDate = deadlineDescription.substring(pos + 4);
        LocalDate byDate = LocalDate.parse(dueDate);

        Deadline deadline = new Deadline(taskDescription, byDate);
        String output = ui.showTaskAdded(deadline, taskList);
        taskList.storeTask(deadline);
        storage.saveFile(taskList.getAllTasks());
        return output;

    }
}
