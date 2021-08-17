import java.util.Scanner;

public class Duke {
    private static final String LINE = "-------------------------------------------------------";
    private static final Task[] tasks = new Task[100];
    private static int nextIndex = 0;

    public static void main(String[] args) {
        printWithLines("Hello! I'm 8-Bit!\nWhat can I do for you?");
        getUserInput();
    }

    private static void printWithLines(String msg) {
        System.out.println(LINE + "\n" + msg + "\n" + LINE);
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            processInput(command);
            command = sc.nextLine();
        }

        printWithLines("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void processInput(String msg) {
        String[] commands = msg.split(" ");

        switch (commands[0]) {
            case "list":
                StringBuilder listOfTasks = new StringBuilder();
                if (nextIndex == 0) {
                    listOfTasks = new StringBuilder("There are no tasks currently.");
                } else {
                    for (int i = 0; i < nextIndex; i++) {
                        String newTask = (i + 1) + ". " + tasks[i] + "\n";
                        listOfTasks.append(newTask);
                    }
                }
                printWithLines("Here are the tasks in your list:\n" + listOfTasks);
                break;
            case "done":
                int index = Integer.parseInt(commands[1]) - 1;
                tasks[index].markAsDone();
                printWithLines("Great job on completing this task!\n" + tasks[index].toString());
                break;
            case "todo":
                String todoDescription = msg.substring(5);
                ToDo todo = new ToDo(todoDescription);
                addTask(todo);
                break;
            case "deadline":
                String[] descriptionAndBy = msg.substring(9).split(" /by ");
                String deadlineDescription = descriptionAndBy[0];
                String deadlineBy = descriptionAndBy[1];
                Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
                addTask(deadline);
                break;
            case "event":
                String[] descriptionAndAt = msg.substring(6).split(" /at ");
                String eventDescription = descriptionAndAt[0];
                String eventAt = descriptionAndAt[1];
                Event event = new Event(eventDescription, eventAt);
                addTask(event);
                break;
            default:
                addTask(new Task(msg));
                break;
        }
    }

    private static void addTask(Task task) {
        tasks[nextIndex] = task;
        nextIndex++;

        printWithLines("Got it. I've added this task:\n  " + task
                + "\nNow you have " + nextIndex + " tasks in the list.");
    }
}
