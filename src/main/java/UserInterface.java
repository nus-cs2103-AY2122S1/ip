import java.util.Scanner;

public class UserInterface {
    Scanner stdin;
    public UserInterface() {
        stdin = new Scanner(System.in);
    }

    public void displayGreeting() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("----------------------");
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------");
        System.out.println("What can I do for you?");
    }

    public void displayFarewell() {
        System.out.println("----------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------");
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.err.println(error);
    }

    public String getResponse() {
        String response = "";
        while (response.equals("")) {
            response = stdin.nextLine();
        }
        return response;
    }
}
