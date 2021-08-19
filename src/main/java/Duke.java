//code is in 1 file for now as i have some issues with intellij and creating new class files

import java.util.*;

public class Duke {
    private static List<String> taskString = new ArrayList<>();
    private static List<Task> taskList = new ArrayList<>();
    private static int taskNumber = 1;
    private final static String line = "-----------------------------------------";
    private final static String defaultMsg = line + "\nHello! I'm Duke \nWhat can I do for you?\n" + line;

    /**
     * Adds a task to the list and prints the addition.
     * @param task Task to be added
     */
    private static void addTask(Task task) {
        taskString.add(taskNumber + ". ");
        taskList.add(task);
        taskNumber++;
        System.out.println(line);
        System.out.println("added: " + task);
        System.out.println(line);
    }

    /**
     * Marks a task as completed and prints this change.
     * @param task Task to complete
     */
    private static void completeTask(String task) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        String numString = task.replaceAll("[^0-9]", "");
        int num = Integer.parseInt(numString);
        for (int counter = 0; counter < taskString.size(); counter++) {
            if (counter == num - 1) {
                System.out.println("should print once");
                taskList.get(num - 1).markAsDone();
                System.out.println(taskString.get(counter) + taskList.get(counter).toString());
            }
        }
        System.out.println(line);
    }

    /**
     * Prints the current task list.
     */
    private static void printList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        for (String str : taskString) {
            System.out.println(str + taskList.get(counter).toString());
            counter++;
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(defaultMsg);
        String task = sc.nextLine();

        while(!task.equals("bye")) {
            if (task.equals("list")) {
                printList();
            } else if (task.contains("done")) {
                completeTask(task);
            } else {
                addTask(new Task(task));
            }
            task = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        sc.close();
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task (X if done, blank if not done).
     * @return the status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}