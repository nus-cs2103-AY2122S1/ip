import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo
                = " _____       _                    _        \n"
                + "|  ___|  __  _  ___   _,____     | |       \n"
                + "| |_  |/  _|| |/ _ \\ |  __  |____| |       \n"
                + "| __| | /   | |  __/ | / \\  |  __  |       \n"
                + "|_|   |_|   |_|\\____ |_|  |_|______|       \n";

        System.out.println("Hi there! Start chatting with your new \n" + logo);
        System.out.println("What's up today?");

        echo();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String message = sc.nextLine();
        String friendGreeting = "(*^_^*) your virtual friend says: \n";

        if (message.equals("bye")) {
            System.out.println(friendGreeting + "See you again my friend!");
        }
        else {
            System.out.println(friendGreeting + message);
            echo();
        }
    }
}
