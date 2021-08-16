import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Empty TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList constructor with tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList add(String item) {
        String indent = "    ";
        Task newItem = new Task(item);
        ArrayList<Task> newList = new ArrayList<>(tasks);
        newList.add(newItem);
        System.out.println(String.format("%s added: %s", indent, newItem));

        return new TaskList(newList);
    }

    @Override
    public String toString() {
        String indent = "    ";
        String str = "";
        int i = 1;
        for (Task item : tasks) {
            str += (indent + i + ". " + item + '\n');
            i++;
        }
        return str;
    }
}
