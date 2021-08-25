package duke.commands;
import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.TaskList;
import duke.tasks.Todo;

public class AddCommand extends Command{
    private Task task;

    /**
     * Constructs a new add task command with the given task.
     * 
     * @param task the given task
     */
    public AddCommand(Task task) {
        this.task = task;
    }
    
    /**
     * Executes the add task command.
     * 
     * @param tasks given list of tasks
     * @param ui given ui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException{
        if (this.task instanceof Todo) {
            addTask(this.task, tasks);
            storage.saveTaskToFile(task.toString() + "\n");
        } else if (this.task instanceof Deadline) {
            Deadline deadline = (Deadline) this.task;
            addTask(deadline, tasks);
            storage.saveTaskToFile(task.toString() + "\n");
        } else {
            
            Event event = (Event) this.task;
            addTask(event, tasks);
            //System.out.println(event.timeFormatted);
            storage.saveTaskToFile(task.toString() + "\n");
        }
    };

    /**
     * Adds the task to the taskslist.
     * 
     * @param task the task to be added
     */
    private void addTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        task.showThisTask();
        tasks.getTaskList().add(task);
        
        String taskform;
        if (tasks.getTaskList().size() == 1) {
            taskform = " task";
        } else {
            taskform = " tasks";
        }
        System.out.println("Now you have " + (tasks.getTaskList().size()) + taskform + " in the list.");
    }

    public boolean isExit() {
        return false;
    }
}
