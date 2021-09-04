package Du;

import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> list_of_tasks = new ArrayList<>();

    /**
     * Public constructor for TaskList
     */
    public TaskList() {

    }

    /**
     * Adds a Task to the TaskList
     * @param t Task added
     */
    public void addTask(Task t) {
        list_of_tasks.add(t);
    }


    /**
     * Finds size of the TaskList
     * @return int
     */
    public int size() {
        return list_of_tasks.size();
    }

    /**
     * Getter for list_of_task
     * @return ArrayList
     */
    public ArrayList<Task> getList_of_tasks() {
        return list_of_tasks;
    }

    /**
     * Removes task from the list
     * @param order the number the task is labelled as
     */
    public String remove_task(int order) {
        String print = "Okies, I have removed this task: \n"
                + list_of_tasks.get(order - 1) + "\n"
                + "Now you have " + (this.size() - 1) + " task(s) in the list.\n";
        list_of_tasks.remove(order - 1);
        return print;
    }


    /**
     * Prints list of task
     */
    public String print_list_of_tasks() {
        if (this.size() == 0) {
            return "Here are the tasks in your list:\n ";
        } else {
            String print = "Here are the tasks in your list:\n";
            for (int i = 0; i < this.size(); i++) {
                print += (i + 1) + "." + list_of_tasks.get(i).toString() + "\n";

            }
            return print;
        }
    }


    /**
     * Finds the task in the list and mark it as done
     * @param order the number the task is labelled as
     */
    public String find_finished_task(int order) {
        Task complete_task = list_of_tasks.get(order - 1);
        return complete_task.finish_task();
    }


    /**
     * Searches for Tasks that contain the substring
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
     * Prints the Tasks that contain the substring from search function
     * @param arraylist prints the Tasks in the arraylist
     */
    public String print(ArrayList<Task> arraylist) {
        String print = "Here are the matching tasks in your list:";
        for (Task t : arraylist) {
            print += t;
        }
        return print;
    }

}
