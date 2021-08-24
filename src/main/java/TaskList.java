import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
        System.out.println("     Got it. I have added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + size()
                + (size() == 1 ? " task" : " tasks") + " in the list.");
    }

    public int size() {
        return this.taskList.size();
    }

    public void markAsDone(int index) {
        Task taskToComplete = get(index - 1);
        if (taskToComplete.getIsDone()) {
            //if task is already completed
            System.out.println("     I have already marked this task as completed!");
        } else {
            taskToComplete.setIsDone(true);
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + taskToComplete.toString());
        }
    }

    public void delete(int index) {
        Task taskToDelete = this.taskList.remove(index - 1);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + taskToDelete.toString());
        System.out.println("     Now you have " + size()
                + (size() == 1 ? " task" : " tasks") + " in the list.");
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void showList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < size(); i++) {
            int currNum = i + 1;
            Task currTask = get(i);
            System.out.println("     " + currNum + ". " + currTask.toString());
        }
    }
}
