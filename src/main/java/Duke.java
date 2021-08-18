import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Duke created by Tianyue\n" +
                "What can I do for you?");


        String text = scanner.nextLine();


        while(!text.isEmpty()) {
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(text);
            text = scanner.nextLine();
        }
    }
}
