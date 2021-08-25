import java.util.*;

public class Duke {
    private static List<String> taskString = new ArrayList<>();
    private static List<Task> taskList = new ArrayList<>();
    private static int taskNumber = 1;
    private final static String LINE = "-----------------------------------------";
    private final static String DEFAULT_MESSAGE = LINE + "\nHello! I'm Duke \nWhat can I do for you?\n" + LINE;

    /**
     * Adds a task to the list and prints the addition.
     * @param task Task to be added
     */
    private static void addTask(Task task) {
        taskString.add(taskNumber + ". ");
        taskList.add(task);
        taskNumber++;
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Marks a task as completed and prints this change.
     * @param task Task to complete
     */
    private static void completeTask(String task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        String numString = task.replaceAll("[^0-9]", "");
        int num = Integer.parseInt(numString);
        for (int counter = 0; counter < taskString.size(); counter++) {
            if (counter == num - 1) {
                taskList.get(num - 1).markAsDone();
                System.out.println(taskString.get(counter) + taskList.get(counter).toString());
            }
        }
        System.out.println(LINE);
    }

    /**
     * Prints the current task list.
     */
    private static void printList() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        for (String str : taskString) {
            System.out.println(str + taskList.get(counter).toString());
            counter++;
        }
        System.out.println(LINE);
    }

    /**
     * Adds a specific task according to the correct class
     * @param task task to be added
     */
    private static void addSpecificTask(String task) throws DukeException {
        if (task.startsWith("deadline")) {
            addTask(new Deadline(task));
        } else if (task.startsWith("event") && task.contains("/at ")) {
            addTask(new Event(task));
        } else if (task.startsWith("todo")) {
            addTask(new Todo(task));
        }
    }

    /**
     * Deletes an item from the tasklist and taskstring.
     * @param i Number of task to be deleted
     */
    public static void deleteTask(int i) {
        Task deletedTask = taskList.get(i - 1);
        taskList.remove(i - 1);
        taskString.remove(i - 1);
        System.out.println("Noted. I've removed this task: \n" + deletedTask + "\nNow you have " + taskString.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(DEFAULT_MESSAGE);
        String task = sc.nextLine();

        while(!task.equals("bye")) {
            try {
                if (task.equals("list")) {
                    printList();
                } else if (task.startsWith("done")) {
                    completeTask(task);
                } else if (task.startsWith("deadline") || task.startsWith("event") || task.startsWith("todo")) {
                    switch (task) {
                        case "deadline":
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        case "event":
                            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                        case "todo":
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    addSpecificTask(task);
                } else if (task.startsWith("delete")) {
                    String numString = task.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(numString);
                    deleteTask(num);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            task = sc.nextLine();
        }
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        sc.close();
    }
}