import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Task[] items = new Task[100];
        int index = 0;

        String logo = " ______      ____      __  \n"
                     + "|   _  \\    /    \\    |  | \n"
                     + "|  |_|  /  /  /\\  \\   |  | \n"
                     + "|  |_|  \\ /  ____  \\  |  |\n"
                     + "|______/ /__/    \\__\\ |__|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bai. \n" +
                            "What can I do for you?\n" +
                            "----------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userCommand = sc.nextLine();

            // Solution below adapted from https://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
            if (userCommand.replaceAll("\\s+","").equalsIgnoreCase("bye")) {
                System.out.println("====================================================\n" +
                        "Goodbai. Hope to see you again soon! （ ● ___ ●.）" +
                        "\n====================================================");
                break;
            } else if (userCommand.replaceAll("\\s+","").equalsIgnoreCase("list")) {
                System.out.println("----------------------------------------------------");
                System.out.println("Here are the tasks in your list: ");

                for (int i = 0; i <  index; i++) {
                    System.out.printf("%d. " + items[i] + "\n", i + 1);
                }

                System.out.println("----------------------------------------------------");
            } else {
                String[] words = userCommand.split(" ");

                try {
                    switch (words[0].toLowerCase()) {
                        case "done":
                            try {
                                int idx = Integer.parseInt(words[1]) - 1;
                                items[idx].markAsDone();

                                System.out.println("----------------------------------------------------\n"
                                        + "Nice! I've marked this task as done: ");
                                System.out.println(items[idx]);
                                System.out.println("----------------------------------------------------");

                            } catch (NullPointerException e) {
                                System.out.println("Uh oh! No such task exists.");
                                System.out.println("----------------------------------------------------");
                            }

                            break;
                        case "todo":
                            Todo newTodo = new Todo(userCommand);
                            items[index] = newTodo;
                            index++;

                            System.out.println("----------------------------------------------------\n"
                                    + "Got it. I've added this task: \n" + newTodo);
                            System.out.printf("\nNow you have %d tasks in the list", index);
                            System.out.println("\n----------------------------------------------------");
                            break;

                        case "deadline":
                            try {
                                int byIndex = userCommand.indexOf("/by");
                                String by = userCommand.substring(byIndex + 4);
                                Deadline newDeadline = new Deadline(userCommand.substring(0, byIndex - 1), by);
                                items[index] = newDeadline;
                                index++;

                                System.out.println("----------------------------------------------------\n"
                                        + "Got it. I've added this task: \n" + newDeadline);
                                System.out.printf("\nNow you have %d tasks in the list", index);
                                System.out.println("\n----------------------------------------------------");
                            } catch (StringIndexOutOfBoundsException e) {
                                System.out.println("----------------------------------------------------\n"
                                        + "Please specify the item name and/or deadline!" +
                                        "\n----------------------------------------------------");
                            }

                            break;

                        case "event":
                            try {
                                int atIndex = userCommand.indexOf("/at");
                                String at = userCommand.substring(atIndex + 4);
                                Event newEvent = new Event(userCommand.substring(0, atIndex - 1), at);
                                items[index] = newEvent;
                                index++;

                                System.out.println("----------------------------------------------------\n"
                                        + "Got it. I've added this task: \n" + newEvent);
                                System.out.printf("\nNow you have %d tasks in the list", index);
                                System.out.println("\n----------------------------------------------------");

                            } catch (StringIndexOutOfBoundsException e) {
                                System.out.println("----------------------------------------------------\n"
                                        + "Please specify the item name and/or event date!" +
                                        "\n----------------------------------------------------");
                            }

                            break;

                        default:
                            System.out.println("----------------------------------------------------\n"
                                    + "No such command! D:" +
                                    "\n----------------------------------------------------");
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("----------------------------------------------------\n"
                            + "Invalid input! D:" +
                            "\n----------------------------------------------------");
                }
            }
        }
    }
}
