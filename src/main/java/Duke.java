import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Amped");
        System.out.println("What can I do for you?");
        do {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Good Bye. Have a nice day!");
            }
            else {
                System.out.println(input);
            }
        } while (!input.equals("bye"));
    }
}
