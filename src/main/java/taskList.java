import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.lst = loadedTasks;
    }


    public String addToList(Task t) {
        this.lst.add(t);
        return String.format("Got it. I've added thsi task:\n    %s\n" +
                "You now have %d tasks in the list.", t, this.lst.size());
    }

    public String deleteTask(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            Task removed = this.lst.remove(n - 1);
            return String.format("Noted. I've removed this task:\n    %s\n" +
                    "Now you have %d tasks in the list.", removed.toString(), this.lst.size());
        }
    }

    public String[] getListContent() {
        if (this.lst.isEmpty()) {
            throw new DukeException("The list is empty");
        }

        String[] temp = new String[this.lst.size() + 1];
        temp[0] = "Here are the tasks in your list:";

        for (int i = 1; i < this.lst.size() + 1; ++i) {
            temp[i] = i + "." + this.lst.get(i - 1).toString();
        }

        return temp;
    }

    public String getSaveData() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.lst) {
            output.append(task.getSaveData()).append("~");
        }
        return output.toString();
    }

    public String markAsDone(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            return "Nice! I've marked this task as done:\n" +
                    this.lst.get(n - 1).completeTask();
        }
    }
}
