package duke.commands;
import java.util.Arrays;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.status.TypeTask;
import duke.exceptions.NoSuchCommandException;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongEventOrDeadlineFormatException;
import duke.exceptions.WrongTimeFormatException;

/**
 * Class that specifies the properties
 * of a task command.
 */
public class TaskCommand extends Command {

    /**
     * Calls parent class to initialise the
     * bye command with description "task",
     * either event, to do or deadline.
     * @param desc String description of
     * task.
     */
    public TaskCommand(String desc) {
        super(desc);
    }

    /**
     * Indicates whether the program should
     * stop running, and in this is case no.
     * @return boolean that this command
     * should exit the application or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Displays the task stored and entered by
     * the user.
     * @param taskList all the tasks stored.
     * @param ui Ui class to display messages to user.
     * @param storage Storage updates each time a command
     * make changes to the existing stored tasks.
     * @return String of the task description.
     * @throws WrongDateFormatException if date format
     * is not in dd/mm/yyyy format.
     * @throws WrongTimeFormatException if time format
     * is not in HH:MM format.
     * @throws WrongEventOrDeadlineFormatException if user
     * inputs an erroneous event or deadline format.
     * @throws NoSuchCommandException if the user inputs
     * gibberish.
     */
    @Override
    public String execute(
            TaskList taskList, Ui ui,
            Storage storage) throws WrongDateFormatException,
            WrongTimeFormatException,
            WrongEventOrDeadlineFormatException,
            NoSuchCommandException {
        String[] instructions = this.commandDescription.split(" ");
        assert (instructions.length >= 2) : "Task should have descriptions!";
        String importantInstructions = String.join(" ",
                Arrays.copyOfRange(instructions,
                        1, instructions.length));
        String identity = instructions[0];
        Task task = this.
                convertInstructionToTask(
                        identity,
                        importantInstructions);
        taskList.addNewTask(task);
        storage.updateStorageList(taskList.getTaskList());
        return ui.displayTaskInstructions(task.toString(),
                taskList.getTaskListLength());
    }

    private Task convertInstructionToTask(
            String taskIdentity,
            String taskBody) throws WrongDateFormatException,
            WrongTimeFormatException,
            WrongEventOrDeadlineFormatException,
            NoSuchCommandException {
        Task task;
        if (taskIdentity.equals(TypeTask.TODO.getTask())) {
            task = new ToDo(taskBody);
        } else if (taskIdentity.equals(TypeTask.DEADLINE.getTask())) {
            task = new Deadline(taskBody);
        } else if (taskIdentity.equals(TypeTask.EVENT.getTask())) {
            task = new Event(taskBody);
        } else {
            String errorCommand = "No such command!";
            throw new NoSuchCommandException(errorCommand);
        }
        return task;
    }
}
