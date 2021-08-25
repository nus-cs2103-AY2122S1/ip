public class DisplayTasksCommand implements Command {

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        if (taskList.numberOfTasks() > 0) {
            System.out.println("Here are your tasks!");
            for (int i = 0; i < taskList.numberOfTasks(); i++) {
                System.out.println((i + 1) + ". " + taskList.getTask(i));
            }
        } else {
            System.out.println("You have no tasks! Nice.");
        }
    }

    public boolean isQuit() { return false; }
}
