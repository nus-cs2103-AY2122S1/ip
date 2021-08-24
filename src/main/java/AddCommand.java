import java.io.IOException;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException{
        if (this.task instanceof Todo) {
            addTask(this.task, tasks);
            storage.saveTaskToFile("[T] " + this.task.showStatus() + this.task.name + "\n");
        } else if (this.task instanceof Deadline) {
            Deadline deadline = (Deadline) this.task;
            addTask(deadline, tasks);
            storage.saveTaskToFile("[D] " + deadline.showStatus() + deadline.name + ":" + deadline.dateFormatted + "\n");
        } else {
            
            Event event = (Event) this.task;
            addTask(event, tasks);
            storage.saveTaskToFile("[E] " + event.showStatus() + event.name + ":" + event.timeFormatted + "\n");
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
        tasks.taskList.add(task);
        
        String taskform;
        if (tasks.taskList.size() == 1) {
            taskform = " task";
        } else {
            taskform = " tasks";
        }
        System.out.println("Now you have " + (tasks.taskList.size()) + taskform + " in the list.");
    }

    public boolean isExit() {
        return false;
    }
}
