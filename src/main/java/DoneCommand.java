public class DoneCommand extends Command {

    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task t = taskList.getTasks().get(taskIndex - 1);
            if (t.getIsDone()) {
                ui.displayText(space + "You have already done this task!");
            } else {
                t.markAsDone();
                ui.displayText(space + "Nice! I've marked this task as done: \n"
                        + space + "  " + t.getDescriptionWithStatus());
                // dataHandler.storeTaskList(taskList);
            }
        } catch (IndexOutOfBoundsException ex) {
            ui.displayText(space + "Oops, the task doesn't seem to exist.");
        }
    }

}
