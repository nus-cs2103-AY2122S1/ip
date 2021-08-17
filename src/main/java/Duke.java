import java.util.Scanner;

public class Duke {
    private static final String LINE = "-------------------------------------------------------";
    private static final String[] tasks = new String[100];
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
        if (msg.equals("list")) {
            StringBuilder listOfTasks = new StringBuilder();
            if (nextIndex == 0) {
                listOfTasks = new StringBuilder("There are no tasks currently.");
            } else {
                for (int i = 0; i < nextIndex; i++) {
                    String newTask = i + 1 + ". " + tasks[i] + "\n";
                    listOfTasks.append(newTask);
                }
            }
            printWithLines(listOfTasks.toString());
        } else {
            tasks[nextIndex] = msg;
            nextIndex++;
            printWithLines("added: " + msg);
        }
    }
}
