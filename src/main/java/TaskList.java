/**
 * TaskList class represents a list of tasks.
 *
 * @author Chng Zi Hao
 */
public class TaskList {
    private String[] list;
    private int currentIndex;

    /**
     * Constructor for TaskList.
     */
    public TaskList(){
        this.list = new String[100];
        this.currentIndex = 0;
    }

    /**
     * Adds task that user has given and formats it.
     *
     * @param task Task that user has entered.
     * @return Returns formatted string to be printed out to show task has been added or not.
     */
    public String addTask(String task) {
        if (currentIndex >= 100) {
            return "List is full.";
        }
        list[currentIndex] = task;
        currentIndex++;
        return "added: " + task;
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
        for (int i = 0; i < currentIndex; i++) {
            String detail = String.format("%d. %s", i + 1, list[i]);
            System.out.println(detail);
        }
        System.out.println(Duke.DIVIDER);
    }
}
