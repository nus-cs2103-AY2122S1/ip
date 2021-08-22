package duke.storage;
import java.io.IOException;
import java.util.ArrayList;

import duke.commands.Ui;
import duke.tasks.Task;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void loadFromStorage(ArrayList<Task> savedTasks) {
        savedTasks.forEach(task -> {
            this.taskList.add(task);
        });
    }

    public void doneItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task curr = this.taskList.get(index);
        this.taskList.set(index, curr.markAsDone());
        curr = this.taskList.get(index);

        Ui.printMessage(Ui.DONE_MESSAGE + "\n" + Ui.INDENT + "  " + curr.toString());
    }

    public void addTask(Task task) throws IOException {
        this.taskList.add(task);
        Ui.printMessage("Got it. I've added this task:\n" + Ui.INDENT + "  " + task.toString() + "\n" + Ui.INDENT
                + "Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void deleteItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task task = this.taskList.get(index);
        this.taskList.remove(index);
        Ui.printMessage("Noted. I've removed this task:\n" + Ui.INDENT + "  " + task.toString() + "\n" + Ui.INDENT
                + "Now you have " + this.taskList.size() + " tasks in the list.");
    }
}
