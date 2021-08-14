import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] items = new String[100];
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

            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println("====================================================\n" +
                        "Goodbai. Hope to see you again soon! （ ● ___ ●.）" +
                        "\n====================================================");
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null) {
                        System.out.printf("%d. %s\n", i + 1, items[i]);
                    }
                }
                System.out.println("\n----------------------------------------------------");
            } else {
                items[index] = userCommand;
                index++;

                System.out.println("----------------------------------------------------\n"
                        + "added: " + userCommand +
                        "\n----------------------------------------------------");
            }

        }



    }
}
