import java.util.*;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Urgh I hate having to wake up. Why did you do that");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Duke.echo(input);
            input = sc.nextLine();
        }
        Duke.sayBye();
    }

    private static void sayBye() {
        System.out.println("Don't wake me up again");
    }

    private static void echo(String input) {
        System.out.println(input);
    }
}
