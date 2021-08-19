import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String response = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("_______________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("_______________________________________________");

        while (scanner.hasNext()) {
            response = scanner.nextLine();
            if (response.equals("bye")) {
                System.out.println("_______________________________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("_______________________________________________");
                System.exit(0);
            }
            System.out.println("_______________________________________________");
            System.out.println(response);
            System.out.println("_______________________________________________");
        }
    }
}
