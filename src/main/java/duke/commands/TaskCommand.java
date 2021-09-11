package duke.commands;
import java.util.Arrays;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.status.typeTask;
import duke.exceptions.NoSuchCommandException;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongEventOrDeadlineFormatException;
import duke.exceptions.WrongTimeFormatException;

public class TaskCommand extends Command {

    public TaskCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(
            TaskList taskList, Ui ui,
            Storage storage) throws WrongDateFormatException,
            WrongTimeFormatException,
            WrongEventOrDeadlineFormatException,
            NoSuchCommandException {
        String[] instructions = this.commandDescription.split(" ");
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
        return ui.displayTaskInstructions(task.toString(), taskList.getTaskListLength());
    }

    private Task convertInstructionToTask(
            String taskIdentity,
            String taskBody) throws WrongDateFormatException,
            WrongTimeFormatException,
            WrongEventOrDeadlineFormatException,
            NoSuchCommandException {
        Task task;
        if (taskIdentity.equals(typeTask.TODO.getTask())) {
            task = new ToDo(taskBody);
        } else if (taskIdentity.equals(typeTask.DEADLINE.getTask())) {
            task = new Deadline(taskBody);
        } else if (taskIdentity.equals(typeTask.EVENT.getTask())) {
            task = new Event(taskBody); 
        } else {
            String errorCommand = "No such command!";
            throw new NoSuchCommandException(errorCommand);
        }
        return task;
    }
    
}
