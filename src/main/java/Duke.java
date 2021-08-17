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
                printWithLines(listOfTasks.toString());
                break;
            case "done":
                int index = Integer.parseInt(commands[1]) - 1;
                tasks[index].markAsDone();
                printWithLines("Great job on completing this task!\n" + tasks[index].toString());
                break;
            default:
                tasks[nextIndex] = new Task(msg);
                nextIndex++;
                printWithLines("added: " + msg);
                break;
        }
    }
}
