package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Yiyang-bot's duke.TaskList";
    }

    /**
     * Returns all the tasks in this instance.
     * @return the task list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Displays all the tasks in the list.
     */
    public void displayList() {
        if (this.tasks.size() == 0) {
            System.out.println("\tYou don't have any task now.");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s",
                    i+1, this.tasks.get(i)));
        }
    }

    /**
     * Marks one task in the list as done.
     * @param id the id of the task to be marked as done.
     * @throws IndexOutOfBoundsException the task id > task list length.
     */
    public void markAsDone(int id) throws IndexOutOfBoundsException {
        if (id > this.tasks.size()) {
            throw new IndexOutOfBoundsException("Do not have such a task in the list.");
        }

        this.tasks.get(id-1).setDone(true);

        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t  " + this.tasks.get(id-1));
    }

    /**
     * Deletes one task in the list.
     * @param id the id of the task to be deleted.
     * @throws IndexOutOfBoundsException the task id > task list length.
     */
    public void deleteTask(int id) throws IndexOutOfBoundsException {
        if (id > this.tasks.size()) {
            throw new IndexOutOfBoundsException("Do not have such a task in the list.");
        }

        Task currTask = this.tasks.remove(id-1);

        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + currTask);
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.tasks.size()));
    }

    /**
     * Adds one task to the end of the list.
     * @param newItem the task to be added.
     */
    public void addTask(Task newItem) {
        this.tasks.add(newItem);

        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + newItem);
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.tasks.size()));
    }

    public void displayFind(String target) {
        System.out.println("\tHere are the matching tasks in your list:");

        int i = 1;
        for (Task task :  this.tasks) {
            if (task.isFound(target)) {
                System.out.println("\t" + i + "." + task);
                i++;
            }
        }
    }

}
