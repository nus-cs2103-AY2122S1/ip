import java.util.Scanner;

public class UI {
    private final Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String formatMessage(String s) {
        return "    ____________________________________________________________\n     " +
                s + "\n" +
                "    ____________________________________________________________";
    }

    public String formatMessage(String[] messages) {
        StringBuilder res = new StringBuilder("    ____________________________________________________________" );
        for(String s : messages) {
            res.append("\n    ").append(s);
        }
        res.append("\n    ____________________________________________________________");
        return res.toString();
    }

    public void welcomeMessage() {
        print("Hello! I'm Duke\n" + "     What can I do for you?");
    }

    public void goodByeMessage() {
        print("Bye. Hope to see you again soon!");
    }

    public void unrecognisedCommand() {
        print("That is not a recognised command");
    }

    public void print(String s) {
        System.out.println(formatMessage(s));
    }
    public void print(String[] s) {
        System.out.println(formatMessage(s));
    }
}
