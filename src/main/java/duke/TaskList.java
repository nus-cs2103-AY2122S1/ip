package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public TaskList(List<Task> tasks) {
        this.addAll(tasks);
    }

    public void addTask(Task task) {
        this.add(task);
        String addMessage = "Got it. I've added this task:\n  " + task
                + "\nNow you have " + this.size() + " tasks in the list.";
        Ui.printReply(addMessage);
    }

    public void deleteTask(int counter) throws DukeException {
        if (counter <= 0 || counter > this.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task taskToRemove = this.remove(counter - 1);
        Ui.printReply("Noted. I've removed this task:\n  " + taskToRemove
                + "\nNow you have " + this.size() + " tasks in the list.");
    }
}
