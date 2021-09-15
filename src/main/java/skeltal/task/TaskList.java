package skeltal.task;

import skeltal.task.expense.Expense;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Expense> moneyList = new ArrayList<>();

    /**
     * A method to add a Task object into the list.
     * @param assignment A task object.
     */
    public static String addTask (Task assignment) {
        tasks.add(assignment);
        String reply = "Got it. I've added this task\n" +
                assignment + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.";
        return reply;
    }

    /**
     * A method to list the Task objects currently in the list.
     */
    public static String listReply() {
        String reply = "Here are the tasks in your list:\n";
        for (Task task : tasks) {
            reply += task + "\n";
        }
        return reply.trim();
    }

    /**
     * Returns the index position of the task in the list.
     * @param task The Task object.
     * @return The index position of the task in the list.
     */
    public static int getIndex(Task task) {
        return tasks.indexOf(task);
    }

    /**
     * Sets the Task at the index (index_1 - 1) in the TaskList to a "completed" state.
     * @param index_1 The index of the task in the list + 1.
     */
    public static String done(String index_1) {
        String reply = "";
        int i = Integer.parseInt(index_1) - 1;
        Task assignment = tasks.get(i);
        assignment.setComplete();
        reply += "Done! I've marked this task as done!\n";
        reply += assignment;
        return reply;
    }

    /**
     * Deletes the Task at the index index in the TaskList.
     * @param index The index of the task in the list.
     */
    public static String delete(String index) {
        String reply = "";
        int i = Integer.parseInt(index) - 1;
        reply += tasks.get(i) + "\n";
        tasks.remove(i);
        reply += "Now you have " + tasks.size() + " tasks in the list.";
        return reply;
    }

    /**
     * A method to load tasks into the task list from an ArrayList object.
     * @param arrayList An ArrayList object containing Task objects.
     */
    public static void loadTaskList(ArrayList<Task> arrayList) {
        tasks = arrayList;
    }

    public static String findMatchingTasks(String str) {
        String reply = "";
        reply += "Here are the matching tasks in your list.";
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(str)) {
                reply += task + "\n";
            }
        }
        return reply.trim();
    }

    /**
     * Returns a String that represents the loadable form of the TaskList.
     * @return A String representation of the TaskList
     */
    public static String storeTasks() {
        String toWrite = "";
        for (Task task : tasks) {
            toWrite += task.store() + "\n";
        }
        return toWrite;
    }

}
