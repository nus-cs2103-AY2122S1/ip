public class ExitCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.reply("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
