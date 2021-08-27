package skeltal;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * A method to add a Task object into the list.
     * @param assignment A task object.
     */
    public static void addReply(Task assignment) {
        tasks.add(assignment);
        System.out.println("Got it. I've added this task");
        System.out.println(assignment);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * A method to list the Task objects currently in the list.
     */
    public static void listReply() {
        for (Task task : tasks) {
            System.out.println(task);
        }
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
    public static void done(String index_1) {
        int i = Integer.parseInt(index_1) - 1;
        Task assignment = tasks.get(i);
        assignment.setComplete();
        System.out.println("Done! I've marked this task as done!");
        System.out.println(assignment);

    }

    /**
     * Deletes the Task at the index index in the TaskList.
     * @param index The index of the task in the list.
     */
    public static void delete(String index) {
        int i = Integer.parseInt(index) - 1;
        System.out.println(tasks.get(i));
        tasks.remove(i);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * A method to load tasks into the task list from an ArrayList object.
     * @param arrayList An ArrayList object containing Task objects.
     */
    public static void loadTaskList(ArrayList<Task> arrayList) {
        tasks = arrayList;
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

