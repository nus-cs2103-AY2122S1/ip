public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String[] tasksText = tasks.getStringArr();
        int len = tasksText.length;
        for (int i = 0; i < len; i++) {
            tasksText[i] = (i + 1) + ". " + tasksText[i];
        }
        Ui.printFormattedReply(tasksText);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
