import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String logo2 = " ______      ____      __  \n"
                     + "|   _  \\    /    \\    |  | \n"
                     + "|  |_|  /  /  /\\  \\   |  | \n"
                     + "|  |_|  \\ /  ____  \\  |  |\n"
                     + "|______/ /__/    \\__\\ |__|\n";
        System.out.println(logo2);
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
            } else {
                System.out.println("----------------------------------------------------\n"
                        + userCommand +
                        "\n----------------------------------------------------");
            }

        }



    }
}
