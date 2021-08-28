package Du;

import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> list_of_tasks = new ArrayList<>();

    /**
     * public constructor for TaskList
     */
    public TaskList() {

    }

    /**
     * adds a Task to the TaskList
     * @param t Task added
     */
    public void addTask(Task t) {
        list_of_tasks.add(t);
    }


    /**
     * finds size of the TaskList
     * @return int
     */
    public int size() {
        return list_of_tasks.size();
    }

    /**
     * getter for list_of_task
     * @return ArrayList
     */
    public ArrayList<Task> getList_of_tasks() {
        return list_of_tasks;
    }

    /**
     * removes task from the list
     * @param order the number the task is labelled as
     */
    public void remove_task(int order) {
        System.out.println("____________________________________________________________\n"
                + "Okies, I have removed this task: \n"
                + list_of_tasks.get(order - 1) + "\n"
                + "Now you have " + (this.size() - 1) + " task(s) in the list.\n"
                + "____________________________________________________________");
        list_of_tasks.remove(order - 1);
    }


    /**
     * prints list of task
     */
    public void print_list_of_tasks() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:\n");
        if (this.size() == 0) {
            System.out.println(" ");
        } else {
            for (int i = 0; i < this.size(); i++) {
                System.out.println((i + 1) + "." + list_of_tasks.get(i).toString()
                );
            }
        }

        System.out.println("____________________________________________________________");
    }


    /**
     * finds the task in the list and mark it as done
     * @param order the number the task is labelled as
     */
    public void find_finished_task(int order) {
        Task complete_task = list_of_tasks.get(order - 1);
        complete_task.finish_task();
    }


    /**
     * searches for Tasks that contain the substring
     * @param substring the string that needs to be searched
     * @return an ArrayList of Tasks that contain the substring
     */
    public ArrayList<Task> search(String substring) {
        ArrayList<Task> have_substring = new ArrayList<>();
        for (Task t : this.getList_of_tasks()) {
            if (t.getName().contains(substring)) {
                have_substring.add(t);
            }
        }
        return have_substring;
    }

    /**
     * prints the Tasks that contain the substring from search function
     * @param arraylist prints the Tasks in the arraylist
     */
    public void print(ArrayList<Task> arraylist) {
        System.out.println("____________________________________________________________\n" +
                "Here are the matching tasks in your list:");
        for (Task t : arraylist) {
            System.out.println(t);
        }
        System.out.println("____________________________________________________________");
    }

}
