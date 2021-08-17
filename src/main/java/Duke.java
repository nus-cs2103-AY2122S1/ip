import java.io.OptionalDataException;
import java.util.Scanner;

public class Duke {

    private static final Task[] taskList = new Task[100];
    private static int tasks = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today? ");

        String userInput;

        mainLoop:
        while (true) {
            userInput = scanner.next();
            switch (userInput) {
                case "list":
                    for (int i = 0; i < tasks; i++) {
                        System.out.printf("%d. %s%n", i + 1, taskList[i]);
                    }
                    break;
                case "bye":
                    System.out.println("Good bye.");
                    break mainLoop;
                case "done":
                    int done = scanner.nextInt() - 1;

                    if (done >= tasks || done < 0) {
                        System.out.println("Task does not exist!");
                        continue;
                    }

                    Task doneTask = taskList[done];
                    doneTask.markAsDone();

                    System.out.printf("I've marked this task as done: \n" +
                            "%s\n", doneTask.toString());

                    break;
                default:
                    taskList[tasks] = new Task(userInput);
                    tasks += 1;
                    System.out.printf("added: %s\n", userInput);
                    break;
            }
            scanner.nextLine();
        }
    }
}
