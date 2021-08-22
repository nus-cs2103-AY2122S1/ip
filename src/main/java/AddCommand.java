public class AddCommand extends Command {
    
    private Task task;
    
    public AddCommand(String command) throws DukeException {
        String[] holder = command.trim().split(" ");
        if (holder.length == 1) {
            throw new DukeException(" ☹ OOPS!!! The description of a task cannot be empty.");
        } else {
            if (holder[0].equals("todo")) {
                this.task = new Todo(command);
            } else {
                if (command.trim().split("/").length == 1) {
                    throw new DukeException(
                            " ☹ HEY DEAR! Please enter a date after / following the task description");
                }
                if (holder[0].equals("deadline")) {
                    this.task = new Deadline(command);
                } else {
                    this.task = new Event(command);
                }
            }
            
            
        }
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(task);
        ui.showAdd(task, taskList.size());
        storage.updateStorage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
