package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;

/**
 * A class that represents a command that will update tasks.
 */
public class UpdateTaskCommand extends Command {
    private int taskNum;
    private String newDescription;
    private LocalDate newTime;

    /**
     * Constructs a {@code UpdateTaskCommand} object.
     *
     * @param commandBody The command body. It should be in the format:
     *                    <ul>
     *                        <li>{@code {item number} {new Description} /time {new time}}; or</li>
     *                        <li>{@code {item number} {new Description}}</li>.
     *                    </ul>
     *                    If the description or the time remain the same, you can replace it by {@code *}.
     */
    public UpdateTaskCommand(String commandBody) {
        super(commandBody.trim());
        decodeCommandBody();
    }

    /**
     * Decodes the command body and set up the new task description/time accordingly.
     *
     * @throws DukeException If anything goes wrong.
     */
    private void decodeCommandBody() throws DukeException {
        assert this.commandBody != null;

        String[] data = this.commandBody.split("/time ", 2);
        String[] itemNumAndDescription = data[0].split(" ", 2);

        if (itemNumAndDescription.length < 2) {
            throw new InvalidCommandException();
        }

        String taskNum = itemNumAndDescription[0].trim();
        String newDescription = itemNumAndDescription[1].trim();

        if (!taskNum.matches("\\d+")) {
            throw new InvalidCommandException();
        }
        this.taskNum = Integer.parseInt(taskNum);

        if (!newDescription.equals("*")) {
            this.newDescription = newDescription;
        }

        if (!this.commandBody.contains("/time")) {
            return;
        }

        String newTime = data[1].trim();

        if (!newTime.equals("*")) {
            try {
                this.newTime = LocalDate.parse(newTime);
            } catch (DateTimeException e) {
                throw new InvalidCommandException();
            }
        }
    }

    /**
     * Executes the command and return the reply.
     *
     * @param taskList The task list that may be modified or referenced by the command.
     * @param storage  The storage that may be modified of referenced by the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Task task = taskList.getTask(this.taskNum);

        if (this.newDescription != null) {
            task.setDescription(this.newDescription);
        }
        if (this.newTime != null) {
            task.setDate(this.newTime);
        }

        storage.refreshTask(taskList);

        return Ui.updateTaskMessage(taskNum, task);
    }
}
