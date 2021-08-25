package duke;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void completeTask(int index) {
        Task task = this.list.get(index - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + task);
    }

    public void addToList(Task task) {
        this.list.add(task);
    }

    public void printTasks() throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something.");
        } else {
            if (this.list.size() == 1) {
                System.out.println("Here is the sole task in your list:");
            } else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (this.list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something before " +
                    "deleting.");
        } else if (index <= this.list.size() && index >= 1) {
            Task toDelete = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + "  " + toDelete + "\n" + "Now you have " +
                    list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
        } else {
            throw new DukeException("Couldn't find that task in the list! Try again.");
        }
    }

    public int size() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void printFilteredTasks(String searchTerm) throws DukeException {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task task : list) {
            if (task.getTaskName().contains(searchTerm)) {
                filteredList.add(task);
            }
        }
        if (filteredList.size() < 1) {
            throw new DukeException("I couldn't find any tasks with that particular search term. Try again.");
        } else {
            if (filteredList.size() == 1) {
                System.out.println("Here is the sole matching task in your list:");
            } else {
                System.out.println("Here are the matching tasks in your list:");
            }
            for (int i = 1; i <= filteredList.size(); i++) {
                System.out.println(i + ". " + filteredList.get(i - 1));
            }
        }
    }

}
