import java.util.Scanner;

public class Duke {
    private  static Scanner sc;
    private static Task userTasks;
    private static String bye_input = "bye";
    private static String bye_output = "Bye. Hope to see you again soon!";
    private static String list_input = "list";
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet();
        userTasks = new Task();
        sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next_input = sc.nextLine();
            if (next_input.equals(bye_input)) {
                exit();
                break;
            } else if (next_input.equals(list_input)){
                userTasks.printAll();
            } else {
                userTasks.add(next_input);
                echo("added : " + next_input);
            }
        }
    }

    private static void exit() {
        echo(bye_output);
    }

    private static void echo(String next_input) {
        System.out.println(div_line + indent + next_input + "\n" + div_line);
    }

    private static void greet() {
        String line1 = "Hello! I'm Duke\n";
        String line2 = indent + "What can I do for you?";
        echo(line1 + line2);
    }
}
