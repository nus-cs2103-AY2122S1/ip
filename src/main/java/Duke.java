import java.util.Scanner;

public class Duke {
    private static void echo(String line) {
        System.out.println(line);
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = sc.nextLine();
        while(!(input.equals("bye"))) {
            echo(input);
            input = sc.nextLine();
        }

        if(input.equals("bye")) {
            bye();
        }
    }
}
