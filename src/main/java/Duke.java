import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("-----------------------------------------");
        List<String> taskString = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        int taskNumber = 1;

        while(true) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.println("-----------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------------");
                sc.close();
                break;
            }
            if (task.equals("list")) {
                System.out.println("-----------------------------------------");
                System.out.println("Here are the tasks in your list:");
                int counter = 0;
                for (String str : taskString) {
                    System.out.println(str + taskList.get(counter).toString());
                    counter++;
                }
                System.out.println("-----------------------------------------");
            } else {
                if (task.contains("done")) {
                    System.out.println("-----------------------------------------");
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
                    System.out.println("-----------------------------------------");
                } else {
                    taskString.add(taskNumber + ". ");
                    taskList.add(new Task(task));
                    taskNumber++;
                    System.out.println("-----------------------------------------");
                    System.out.println("added: " + task);
                    System.out.println("-----------------------------------------");
                }
            }
        }
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