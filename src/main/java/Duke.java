import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // Greet the user
        Printer.prettyPrint("Welcome to\n" +
                Printer.logo +
                "\tI'm Desmond,\n" +
                "\thow may I serve you?\n");

        // Initialize string array to store the list
        Task[] tasks = new Task[100];
        int numOfTask = 0;

        // Initialize scanner to get user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Execute based on command (user input)
        // Exit when user commands "bye"
        while (!input.equals("bye")) {
            String[] command = input.split(" ", 2);
            switch (command[0]) {
                case "list":
                    Printer.prettyPrint("Here are the tasks in your list:\n" +
                            Printer.listTask(tasks));
                    break;
                case "done":
                    tasks[Integer.parseInt(command[1]) - 1].markAsDone();
                    break;
                case "todo":
                    String[] taskDetail = command[1].split(" /[\\w]{2} ");
                    tasks[numOfTask++] = new Todo(taskDetail[0]);
                    Printer.prettyPrint("Got it. I've added this task:\n\t " +
                            tasks[numOfTask - 1].toString() +
                            "\n\tNow you have " +
                            numOfTask +
                            " tasks in the list.");
                    break;
                case "event":
                    taskDetail = command[1].split(" /[\\w]{2} ");
                    tasks[numOfTask++] = new Event(taskDetail[0], taskDetail[1]);
                    Printer.prettyPrint("Got it. I've added this task:\n\t " +
                            tasks[numOfTask - 1].toString() +
                            "\n\tNow you have " +
                            numOfTask +
                            " tasks in the list.");
                    break;
                case "deadline":
                    taskDetail = command[1].split(" /[\\w]{2} ");
                    tasks[numOfTask++] = new Deadline(taskDetail[0], taskDetail[1]);
                    Printer.prettyPrint("Got it. I've added this task:\n\t " +
                            tasks[numOfTask - 1].toString() +
                            "\n\tNow you have " +
                            numOfTask +
                            " tasks in the list.");
                    break;
                default:
                    tasks[numOfTask++] = new Task(input);
                    Printer.prettyPrint("added: " +
                            input + "\n");
            }
            input = scanner.nextLine();
        }
        Printer.prettyPrint("Bye (*´▽｀)ノシ. Have a good day!\n");
    }
}

