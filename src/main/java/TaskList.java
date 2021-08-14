/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * The TaskList Class is a representation of a list of tasks that Duke is keeping track of.
 * Tasks can be added into the list via Duke and the list can be displayed as well.
 *
 * @author Benedict Chua
 */
public class TaskList {
    private static final String INDENTATION = "     ";
    private String[] taskList;
    private int taskCounter;

    /**
     * Constructor for a TaskList.
     *
     * @param listCapacity The max number of tasks the task list can store.
     */
    public TaskList(int listCapacity) {
        taskList = new String[listCapacity];
        taskCounter = 0;
    }

    /**
     * Adds a given task to the tasks list.
     *
     * @param task the task to add to the list.
     */
    public void addToList(String task) {
        taskList[taskCounter] = task;
        taskCounter++;
    }

    /**
     * Prints the tasks in the list with indexing starting from 1.
     */
    public void printList() {
        for (int i = 0; i < taskCounter; i++) {
            System.out.println(INDENTATION + String.format("%d: %s", i + 1, taskList[i]));
        }
    }
}