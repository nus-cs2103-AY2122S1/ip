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
import duke.exceptions.WrongDateFormatException;
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
    public void execute(
            TaskList taskList, Ui ui,
            Storage storage) throws WrongDateFormatException,
            WrongTimeFormatException {
        String[] instructions = this.commandDescription.split(" ");
        String importantInstructions = String.join(" ",
                Arrays.copyOfRange(instructions,
                        1, instructions.length));
        String identity = instructions[0];
        Task task;
        if (identity.equals(typeTask.TODO.getTask())) {
            task = new ToDo(importantInstructions);
        } else if (identity.equals(typeTask.DEADLINE.getTask())) {
            task = new Deadline(importantInstructions);
        } else {
            task = new Event(importantInstructions); 
        }
        taskList.addNewTask(task);
        ui.displayTaskInstructions(task.toString(), taskList.getTaskListLength());
        storage.updateStorageList(taskList.getTaskList());
    }
    
}
