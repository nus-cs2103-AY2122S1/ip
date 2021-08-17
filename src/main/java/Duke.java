import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void printTasks() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            System.out.println("Here are your tasks!");
            for (int i = 0; i<taskList.size(); i++) {
                String task = taskList.get(i).toString();
                System.out.printf("%d.%s%n", i + 1, task);
            }
        }
    }

    public static void addTodo(String task) {
        Todo todo = new Todo(task);
        taskList.add(todo);
        System.out.println("Okay! I've added this task:\n" + todo.toString());
        System.out.printf("You have %d task(s) in the list.\n", taskList.size());
    }

    public static void addDeadline(String task) {
        int split = task.indexOf("/by");
        String description = task.substring(0,split);
        String by = task.substring(split+4);
        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        System.out.println("Okay! I've added this task:\n" + deadline.toString());
        System.out.printf("You have %d task(s) in the list.\n", taskList.size());
    }

    public static void addEvent(String task) {
        int split = task.indexOf("/at");
        String description = task.substring(0,split);
        String at = task.substring(split+4);
        Event event = new Event(description, at);
        taskList.add(event);
        System.out.println("Okay! I've added this task:\n" + event.toString());
        System.out.printf("You have %d task(s) in the list.\n", taskList.size());
    }

    public static void markTask(int index) {
        Task task = taskList.get(index - 1);
        task.markDone();
        System.out.println("Okay! This task has been marked done:");
        System.out.println(task.toString() + "\n");
    }

    public static int handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("BYE")) {
                System.out.println("Bai bai!");
            } else if (input.equalsIgnoreCase("LIST")) {
                printTasks();
            } else if (input.toUpperCase().startsWith("DONE")) {
                int index = Integer.parseInt(input.substring(5));
                markTask(index);
            } else if (input.toUpperCase().startsWith("TODO")) {
                String task = input.substring(5);
                addTodo(task);
            } else if (input.toUpperCase().startsWith("DEADLINE")) {
                String task = input.substring(9);
                addDeadline(task);
            } else if (input.toUpperCase().startsWith("EVENT")) {
                String task = input.substring(6);
                addEvent(task);
            } else {
                System.out.println(input);
            }
        }
        while (!input.equalsIgnoreCase("BYE"));
        return 0;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = "Hello! I'm Duke.\n" + "What can I do for you? :)";
        String usage = "Usage:\n" +
                "list                                   - show current tasks\n" +
                "todo [Description]                     - add todo\n" +
                "deadline [Description] /by [Date/Time] - add deadline\n" +
                "event [Description] /at [Date/Time]    - add event\n" +
                "done [Task Number]                     - mark task as done\n" +
                "bye                                    - say goodbye";
        System.out.println(logo + greeting);
        System.out.println(usage);

        handleInput();
    }

}
