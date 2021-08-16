import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dash = "__________________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(dash);
        System.out.println("Howdy! I'm Duke" + '\n'+ "How may I assist you?");
        System.out.println(dash);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.matches("bye")) {
                System.out.println(dash);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(dash);
                break;
            } else {
                System.out.println(dash);
                System.out.println(userInput);
                System.out.println(dash);
            }
        }
    }
}
