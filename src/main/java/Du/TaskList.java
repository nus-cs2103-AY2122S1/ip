package Du;

import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> list_of_tasks = new ArrayList<>();

    public TaskList() {

    }

    public void addTask(Task t) {
        list_of_tasks.add(t);
    }


    public int size() {
        return list_of_tasks.size();
    }

    public ArrayList<Task> getList_of_tasks() {
        return list_of_tasks;
    }

    /**
     * remove task from the list
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
     * print list of task
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
     * find the task in the list and mark it as done
     * @param order the number the task is labelled as
     */
    public void find_finished_task(int order) {
        Task complete_task = list_of_tasks.get(order - 1);
        complete_task.finish_task();
    }

}
