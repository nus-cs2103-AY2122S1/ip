public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task t = taskList.getTasks().get(taskIndex - 1);
            String description = t.getDescriptionWithStatus();
            if (taskList.deleteTask(taskIndex - 1)) {
                ui.displayText(space + "Noted. I've removed this task: \n"
                        + space + "  " + description + "\n"
                        + space + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.");
                // dataHandler.storeTaskList(taskList);
            } else {
                System.exit(1);
            }
        } catch (IndexOutOfBoundsException ex) {
            ui.displayText(space + "Oops, the task doesn't seem to exist.");
        }
    }

}
