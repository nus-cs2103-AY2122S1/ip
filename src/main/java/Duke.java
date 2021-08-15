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

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        while (dukeOpen) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                dukeOpen = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(userInput);
            }
        }
    }
}
