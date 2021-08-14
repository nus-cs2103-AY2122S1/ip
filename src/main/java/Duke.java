import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> items = new ArrayList<>();

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

            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("====================================================\n" +
                        "Goodbai. Hope to see you again soon! （ ● ___ ●.）" +
                        "\n====================================================");
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                System.out.println("----------------------------------------------------");
                System.out.println("Here are the tasks in your list: ");

                for (int i = 0; i <  items.size(); i++) {
                    System.out.printf("%d. " + items.get(i) + "\n", i + 1);
                }

                System.out.println("----------------------------------------------------");
            } else if (userCommand.length() > 1) {
                String[] words = userCommand.split(" ");

                if (words[0].equalsIgnoreCase("done")) {
                    int idx = Integer.parseInt(words[1]) - 1;
                    items.get(idx).markAsDone();


                    System.out.println("----------------------------------------------------\n"
                            + "Nice! I've marked this task as done: ");

                    System.out.println(items.get(idx));
                    System.out.println("----------------------------------------------------");
                } else {
                    items.add(new Task(userCommand));

                    System.out.println("----------------------------------------------------\n"
                            + "added: " + userCommand +
                            "\n----------------------------------------------------");
                }
            }



        }



    }
}
