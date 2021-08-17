import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String start = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Hello! My name is LHWBot!\n"
                + "What can I do for you today?\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
        System.out.println(start);
        Scanner reader = new Scanner(System.in);
        while(true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                String bye = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + "Bye... Hope to see you again soon!\n"
                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(bye);
                break;
            } else {
                String cmd = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + "Command Received: "
                        + input
                        + "\n"
                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(cmd);
            }
        }
    }
}
