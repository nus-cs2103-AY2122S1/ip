package duke.command;

import duke.exceptions.UnclearInstructionException;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.Parser;

import java.util.ArrayList;

/**
 * AddCommand class which handles the logic of adding tasks.
 */
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
        assert fullCommand != null : "Full command should not be null";
        assert taskType != null : "Task type should not be null";
        
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
                newTask = new Deadline(Parser.extractTaskDescription(fullCommand), 
                        Parser.extractTaskTime(fullCommand));
            } else if (taskType.equals("event"))  {
                newTask = new Event(Parser.extractTaskDescription(fullCommand), 
                        Parser.extractTaskTime(fullCommand));
            } else if (taskType.equals("t")) {
                newTask = new Todo(fullCommand.substring(2).strip());
            } else if (taskType.equals("d")) {
                newTask = new Deadline(Parser.extractTaskDescription(fullCommand),
                        Parser.extractTaskTime(fullCommand));
            } else if (taskType.equals("e")) {
                newTask = new Event(Parser.extractTaskDescription(fullCommand),
                        Parser.extractTaskTime(fullCommand));
            }
            return taskList.addTask(newTask);
        } catch (UnclearInstructionException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
}