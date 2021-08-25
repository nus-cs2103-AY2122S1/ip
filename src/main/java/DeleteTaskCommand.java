import java.io.IOException;

public class DeleteTaskCommand implements Command {

    int index;

    DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Task t = taskList.getTask(this.index);
            taskList.deleteTask(this.index);
            ui.printDeleteTask(t, this.index);
            storage.writeTasksToFile(taskList, storage.getTaskFile());
        } catch (IndexOutOfBoundsException e) {
            if (taskList.numberOfTasks() > 0) {
                System.out.println("Invalid index given, enter a number from 1 to " + taskList.numberOfTasks());
            } else if (taskList.numberOfTasks() == 0) {
                System.out.println("You cannot delete any task because you have no tasks!");
            }
        } catch (IOException e) {
            ui.printFileWriteFail(storage.getTaskFile());
        }
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
