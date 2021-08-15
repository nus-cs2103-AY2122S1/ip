/**
 * TaskList class represents a list of tasks.
 *
 * @author Chng Zi Hao
 */
public class TaskList {
    private Task[] list;
    private int currentIndex;

    /**
     * Constructor for TaskList.
     */
    public TaskList(){
        this.list = new Task[100];
        this.currentIndex = 0;
    }

    /**
     * Adds task that user has given and formats it.
     *
     * @param description Task that user has entered.
     * @return Returns formatted string to be printed out to show task has been added or not.
     */
    public String addTask(String description) {
        if (currentIndex >= 2) {
            return "List is full.";
        }
        Task task = new Task(description);
        list[currentIndex] = task;
        currentIndex++;
        return "added: " + description;
    }

    /**
     * Prints out the task list and shows all the task user has added in order.
     */
    public void printTaskList() {
        System.out.println(Duke.DIVIDER);
        if (currentIndex == 0) {
            System.out.println("List is empty.");
            System.out.println(Duke.DIVIDER);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < currentIndex; i++) {
            String detail = String.format("%d.%s", i + 1, list[i]);
            System.out.println(detail);
        }
        System.out.println(Duke.DIVIDER);
    }

    /**
     * Marks task as done.
     *
     * @param index index of task to be mark as done.
     * @return a message of whether task has been successfully added or not
     */
    public String markTaskDone(int index) {
        if (index >= 0 && index < currentIndex) {
            if (list[index].getStatusIcon().equals(" ")) {
                list[index].markDone();
                return "Good job! I've marked this task as done:\n  " + list[index];
            } else {
                return "Task has already been completed!";
            }
        } else {
            return "No such task :< (Check the index input!!!)";
        }
    }
}
