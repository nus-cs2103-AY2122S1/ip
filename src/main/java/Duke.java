import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.greet();
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            duke.echo(command);
            command = sc.nextLine();
        }
        sc.close();
        duke.bye();
    }

    /**
     * Greet the user.
     */
    public void greet() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    /**
     * Echo the command entered.
     *
     * @param command a command said by the user
     */
    public void echo(String command) {
        System.out.println(command);
    }

    /**
     * exit behavior.
     */
    public void bye() {
        String byeCommand = "Bye. Hope to see you again soon!";
        System.out.println(byeCommand);
    }
}
