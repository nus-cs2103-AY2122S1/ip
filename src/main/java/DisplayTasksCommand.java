public class DisplayTasksCommand implements Command {

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        System.out.println("Here are your tasks!");
        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
    }

    public boolean isQuit() { return false; }
}
