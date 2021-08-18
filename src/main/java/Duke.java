import java.util.Scanner;

public class Duke {

    static String introMsg = "Hello! I'm Biscuit.\n"
            + "What do you want me to do?\n";
    static String byeMsg = "Bye. Hope to see you again soon!";

    static void introduce() {
        System.out.println(introMsg);
    }

    static void reply() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(byeMsg);
                break;
            }

            System.out.println(userInput);
        }
    }

    static void run() {
        introduce();
        reply();
    }

    public static void main(String[] args) {
        run();
    }
}
