public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.taskList.size(); i++) {
            Task t = tasks.taskList.get(i);
            System.out.print((i+1) + ".");
            t.showThisTask();
        }
    };

    public boolean isExit() {
        return false;
    }
}
