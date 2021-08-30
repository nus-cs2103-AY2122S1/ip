package duke.command;

import duke.exceptions.UnclearInstructionException;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.TaskList;
import duke.task.Todo;
import duke.util.Ui;
import duke.util.Storage;
import java.util.ArrayList;


public class AddCommand extends Command {
    private final String fullCommand;
    private final String taskType;

    /**
     * Constructor method of AddCommand.
     *
     * @param fullCommand Full command given by the user.
     * @param taskType Task type indicated by "T", "D" or "E".                   
     */
    public AddCommand(String fullCommand, String taskType) {
        this.fullCommand = fullCommand;
        this.taskType = taskType;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    
    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task newTask = null;
            if (taskType.equals("todo")) {
                newTask = new Todo(fullCommand.substring(5).strip());
            } else if (taskType.equals("deadline")) {
                newTask = new Deadline(Deadline.extractTaskDescription(fullCommand), 
                        Deadline.extractTaskTime(fullCommand));
            } else if (taskType.equals("event"))  {
                newTask = new Event(Event.extractTaskDescription(fullCommand), 
                        Event.extractTaskTime(fullCommand));
            }
            return taskList.addTask(newTask);
        } catch (UnclearInstructionException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
}