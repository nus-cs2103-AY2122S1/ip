import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        while(!finished) {
            String userResponse = sc.nextLine();

            switch (userResponse) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    finished = true;
                    break;
                default:
                    System.out.println(userResponse);
            }

        }
    }
}
