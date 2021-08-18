import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String intro = "Hello! I'm Biscuit.\n"
                + "What do you want me to do?\n";
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(bye);
                break;
            }

            System.out.println(userInput);
        }
    }
}
