import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private enum Commands {
        EXIT, ADD, LIST
    }

    private String HORIZONTAL_LINE = "____________________________________________________________";
    private String INDENTATION = "    ";
    private ArrayList<String> tasks;

    private Duke(){
        tasks = new ArrayList<>();
    }

    private void greet(){
        String msg = "Hello! I'm Duke\n";
        msg += INDENTATION + "What can I do for you?";
        printMessageWithFormat(msg);
    }

    private void printMessageWithFormat(String msg){
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + msg);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    private void exit(){
        String msg = "Bye. Hope to see you again soon!";
        printMessageWithFormat(msg);
    }

    private void echo(){;
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")){
            printMessageWithFormat(command);
            command = sc.nextLine();
        }
        exit();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.greet();
        duke.echo();
    }
}
