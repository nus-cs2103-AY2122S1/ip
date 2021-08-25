import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void markDone(int taskNumber) {
        try {
            Task completedTask = this.tasks.get(taskNumber);
            completedTask.markDone();
            Ui.printDoneMessage(completedTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! This is not a valid task number.");
        }
    }

    public void listTasks() {
        if (!this.tasks.isEmpty()) {
            Ui.printLine();
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
            }
            Ui.printLine();
        } else {
            System.out.println("\tYou don't have any tasks in your list!");
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        Ui.printAddedMessage(task, this.tasks.size());
    }

    public void deleteTask(int taskNumber) {
        try {
            Task removedTask = this.tasks.get(taskNumber);
            this.tasks.remove(taskNumber);
            Ui.printDeleteMessage(removedTask, this.tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! This is not a valid task number.");
        }
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public int size() {
        return this.tasks.size();
    }
}
