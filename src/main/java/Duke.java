import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("bye"))  {
            System.out.println(byeString());
        } else {
            interpretInput(input);
            run();
        }
    }

    public void interpretInput(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String hello = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(hello);
        duke.run();
    }
}
