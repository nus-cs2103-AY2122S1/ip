import java.util.Scanner;

public class Duke {
    private  static Scanner sc;
    private static TaskList taskList;
    private static String bye_output = "Bye. Hope to see you again soon!";
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";

    private enum Command {
        BYE("bye"),
        LIST("list"),
        DONE("done"),
        DELETE("delete");

        private final String cmd;

        Command(String cmd) {
            this.cmd = cmd;
        }

        public String getCmd() {
            return cmd;
        }
    }

    private static void run() {
        greet();
        taskList = new TaskList(Storage.load());
        sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next_input = sc.nextLine();
            String[] arr = next_input.split(" ", 2);

            if (next_input.equals(Command.BYE.getCmd())) {
                exit();
                break;
            } else {
                try {
                    if (next_input.equals(Command.LIST.getCmd())) {
                        taskList.printAll();
                    } else if (arr[0].equals(Command.DONE.getCmd())) {
                        taskList.markDone(arr);
                    } else if (arr[0].equals(Command.DELETE.getCmd())) {
                        taskList.deleteTask(arr);
                    } else {
                        taskList.addTask(arr);
                    }
                } catch (DukeException e) {
                    echo(e.toString());
                }
            }
        }
    }

    private static void echo(String next_input) {
        System.out.println(div_line + "\n" + indent + next_input + "\n" + div_line);
    }

    private static void exit() {
        echo(bye_output);
    }

    private static void greet() {
        String line1 = "Hello! I'm Duke\n";
        String line2 = indent + "What can I do for you?";
        echo(line1 + line2);
    }

    public static void main(String[] args) {
        run();
    }
}
