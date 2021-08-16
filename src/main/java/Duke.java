import java.util.Scanner;


public class Duke {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "\n____________________________________________________________");
                break;
            }
            System.out.println("____________________________________________________________\n" +
                    userInput
                    +"\n____________________________________________________________");

        }
    }
}
