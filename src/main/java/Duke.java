import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLines = "-----------------------------------------";
        System.out.println("Hello! I'm Naruto\nWhat can I do for you?\n" + horizontalLines);
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(horizontalLines);
                System.out.println("See ya! Hope to see you again!" + "\n" + horizontalLines);
                return;
            }
            System.out.println(horizontalLines);
            System.out.println(userInput + "\n" + horizontalLines);
        }
    }
}
