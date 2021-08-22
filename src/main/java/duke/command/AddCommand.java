package duke.command;

public class AddCommand extends Command {
    
    private duke.task.Task task;
    
    public AddCommand(String command) throws duke.DukeException {
        String[] holder = command.trim().split(" ");
        if (holder.length == 1) {
            throw new duke.DukeException(" ☹ OOPS!!! The description of a duke.task cannot be empty.");
        } else {
            if (holder[0].equals("todo")) {
                task = new duke.task.Todo(command);
            } else {
                if (command.trim().split("/").length == 1) {
                    throw new duke.DukeException(
                            " ☹ HEY DEAR! Please enter a date after / following the duke.task description");
                }
                if (holder[0].equals("deadline")) {
                    task = new duke.task.Deadline(command);
                } else {
                    task = new duke.task.Event(command);
                }
            }
            
            
        }
    }
    
    @Override
    public void execute(duke.TaskList taskList, duke.Ui ui, duke.Storage storage) throws duke.DukeException {
        taskList.addTask(task);
        ui.showAdd(task, taskList.size());
        storage.updateStorage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
