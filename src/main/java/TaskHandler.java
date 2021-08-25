import java.util.ArrayList;

public class TaskHandler {
    private ArrayList<Task> list;

    public TaskHandler(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskHandler() {
        this.list = new ArrayList<Task>();
    }

    public void printList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println("    " + (i + 1) + ". " + list.get(i).toString());
        }
    }

    public void printNoOfTasks() {
        System.out.printf("    Now you have %d tasks in the list.\n", list.size());
    }

    public void markTaskAsDone(int taskNo) {
        System.out.println("    Nice! I've marked this task as done: ");
        Task task = list.get(taskNo - 1);
        task.markAsDone();
        System.out.println("      " + task);
    }

    public void deleteTask(int taskNo) {
        System.out.println("    Noted. I've removed this task: ");
        Task task = list.get(taskNo - 1);
        list.remove(taskNo - 1);
        System.out.println("      " + task);
    }

    public void addToDo(ToDo todo) {
        list.add(todo);
        System.out.println("    Got it. I've added this task:\n      " + todo);
    }

    public void addDeadline(Deadline deadline) {
        list.add(deadline);
        System.out.println("    Got it. I've added this task:\n      " + deadline);
    }

    public void addEvent(Event event) {
        list.add(event);
        System.out.println("    Got it. I've added this task:\n      " + event);
    }

    public String formatTaskToSave() {
        String[] tasksToSave = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tasksToSave[i] = list.get(i).toString();
        }
        return String.join("\n", tasksToSave);
    }

}
