import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String output = scanner.nextLine();
            if (output.equals("bye")) {
                break;
            } else {
                System.out.println(output);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}