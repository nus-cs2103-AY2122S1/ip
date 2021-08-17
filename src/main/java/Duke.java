import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // scan the line for the user's input
        while (true) {//create loop for the chat
            if (input.equals("bye") || input.equals("Bye") || input.equals("BYE")) {//input is bye
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else { //input is not bye so echo input
                System.out.println(input);
                input = sc.nextLine();
            }

        }

    }
}
