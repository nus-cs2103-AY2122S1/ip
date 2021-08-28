import java.util.*;

public class Duke {

    private String line = "__________________________________\n";

    private void greet() {
            System.out.println(line);
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);
            System.out.println("Hello! I'm Duke\n");
            System.out.println("What can I do for you?");
            System.out.println(line);
        }

    private void bye() {
        String line = "__________________________________";
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    private boolean echo() {
        Scanner user = new Scanner(System.in);
        String input = user.nextLine();
        if (input.equals("bye")) {
            bye();
            return false;
        } else {
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
            return true;
        }
    }


    public static void main(String[] args) {
            Duke bot = new Duke();
            bot.greet();
            boolean end = true;
            while (end) {
                end = bot.echo();
            }
        }
    }
