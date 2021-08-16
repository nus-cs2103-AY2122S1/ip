import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(logo);

        Scanner sc = new Scanner(System.in);
        String command = "";
        String[] list = new String[100];
        int currentIndex = 0;

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                String exitText = "____________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________";
                System.out.println(exitText);
                sc.close();
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < currentIndex; i++) {
                    System.out.println(String.format("%d. %s", i + 1, list[i]));
                }
                System.out.println("____________________________________________________________");
            } else {
                list[currentIndex] = command;
                currentIndex++;
                String echoText = "____________________________________________________________\n"
                        + "added: " + command + '\n'
                        + "____________________________________________________________";
                System.out.println(echoText);
            }
        }
    }
}
