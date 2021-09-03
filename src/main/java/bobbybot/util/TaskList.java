package bobbybot.util;

import bobbybot.tasks.Task;


import java.util.List;

/**
 * Represents a task list
 */
public class TaskList {
    private final List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for task
     * @param i task number to get, index starts from 0
     * @return task chosen
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to task list
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Getter for List of tasks
     * @return List of task
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Mark a task as done
     * @param taskNo bobbybot Task Number (starting from index 1)
     */
    public void markAsDone(int taskNo) {
        Task taskCompleted = getTask(taskNo - 1);
        taskCompleted.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskCompleted);
    }

    /**
     * Delete a task
     * @param taskNo bobbybot.tasks.Task Number (starting from index 1)
     */
    public void deleteTask(int taskNo) {
        if (taskNo > tasks.size() || taskNo < 1) {
            System.out.println("Cannot find task! Use list command to see available tasks");
            return;
        }
        Task taskToDelete = tasks.get(taskNo - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToDelete);
        tasks.remove(taskToDelete);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Finds all tasks with a keyword
     * @param keyword keyword to search for
     */
    public void findMatchingTasks(String keyword) {
        int i = 1;
        System.out.println("Here are the tasks you're looking for sir!");
        for (Task task : tasks) {
            String sentence = task.toString();
            if (sentence.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(i + "." + sentence);
                i++;
            }
        }
    }
    /**
     * Prints all task in TaskList
     */
    public void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.print(i + ". ");
            System.out.println(task);
            i++;
        }
    }
}
