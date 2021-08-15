import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        boolean dukeOpen = true;
        String[] taskList = new String[100];
        int taskIndex = 0;

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        while (dukeOpen) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                dukeOpen = false;
                System.out.println("Bye. Hope to see you again soon!");

            } else if (userInput.equals("list")) {
                int i = 0;
                for (String task : taskList) {
                    if (task != null) {
                        System.out.println(++i + ". " + task);
                    } else {
                        break;
                    }
                }

            } else {
                taskList[taskIndex] = userInput;
                taskIndex++;
                System.out.println("added: " + userInput);
            }
        }
    }
}
