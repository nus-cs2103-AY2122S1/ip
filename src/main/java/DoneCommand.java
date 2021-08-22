public class DoneCommand extends Command {
    private final int id;
    DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.reply(String.format(
                "Nice! I've marked this task as done: \n" +
                "%s", taskManager.completeTask(id - 1).toString()
        ));
    }
}
