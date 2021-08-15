import java.util.Scanner;

public class Duke {

    public void run() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");

        String input;
        Scanner sc = new Scanner(System.in);

        while(true) {
            input = sc.nextLine();
            System.out.println("__________________________________");
            if (input.equals("bye")) {
                sc.close();
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("__________________________________");
                break;
            }
            System.out.println(input);
            System.out.println("__________________________________");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
