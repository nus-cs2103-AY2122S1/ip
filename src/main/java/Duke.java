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
        System.out.println("Duke is gone. Hello, this is Duchess.\nHow can I help you?");
        while (true) {
            String user_input = sc.nextLine();
            if (user_input.equals("bye")) {
                System.out.println("It has been a pleasure, goodbye!");
                sc.close();
                break;
            }
            System.out.println(user_input);
        }
    }
}
