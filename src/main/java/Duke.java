import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        initialiseBot();

        boolean status = true;
        List<Task> tasks = new ArrayList<>();
        int numberOfText = 0;

        Scanner sc = new Scanner(System.in);

        while (status) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                status = false;
                closeBot();
            } else if (message.equals("list")) {
                printStoredText(tasks);
            } else if (message.contains("done")) {
                changeStatus(message, tasks);
            } else {
//                echo(message);
                numberOfText++;
                add(message, numberOfText, tasks);
            }
        }

        sc.close();

    }

    private static int getTaskNumber(String message) {
        String numberString = "";
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (!numberString.isEmpty() && Character.isWhitespace(currentChar)) {
                break; //task number string complete
            } else if (Character.isDigit(currentChar)) {
                numberString += message.charAt(i);
            } else {}
        }

        int number;
        if (numberString.isEmpty()) {
            number = -1;
        } else {
            number = Integer.parseInt(numberString);
        }
        return number;
    }

    private static void changeStatus(String message, List<Task> tasks) {
        int taskPosition = getTaskNumber(message) - 1;
        if (taskPosition < 0) {
            System.out.println("error");
        } else {
            Task taskMarked = tasks.get(taskPosition);
            taskMarked.markAsDone();
            String displayedMessage = "Nice! I've marked this task as done: \n"
                    + "  [X] "
                    + taskMarked.getTaskName();
            System.out.println(displayedMessage);
        }
    }

    private static void printStoredText(List<Task> tasks) {
        String message = "Here are the tasks in your list:";
        System.out.println(message);
        tasks.forEach(System.out::println);
    }

    private static void add(String message, int numberOfText, List<Task> tasks) {
        String displayedMessage = "added: " + message;
        Task storedTask = new Task(numberOfText, message);
        tasks.add(storedTask);
        System.out.println(displayedMessage);
    }

    private static void closeBot() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    private static void echo(String message) {
        System.out.println(message);
    }

    private static void initialiseBot() {
        String message = "Hello! I'm Duke \n"
                + "What can I do for you?";
        System.out.println(message);
    }


}
